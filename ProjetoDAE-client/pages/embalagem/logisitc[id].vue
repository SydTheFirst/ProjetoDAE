<template>
  <div>
    <h1>Embalagem {{ embalagem.id }}</h1>
    <table>
      <thead>
      <tr>
        <th>Encomenda</th>
        <th>Produto</th>
        <th>Quantidade</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>{{ embalagem.idEncomenda }}</td>
        <td>
          <nuxt-link :to="`/produto/${embalagem.idProduto}`">
            {{ embalagem.idProduto }}
          </nuxt-link>
        </td>
        <td>{{ embalagem.quantidade }}</td>
      </tr>
      </tbody>
    </table>

    <h2>Sensores</h2>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Tipo</th>
        <th>Valor</th>
        <th>Ações</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="sensor in sensores" :key="sensor.id">
        <td>
          <nuxt-link :to="`/sensor/${sensor.id}`">
            {{ sensor.id }}
          </nuxt-link>
        </td>
        <td>{{ sensor.tipoSensor }}</td>
        <td>{{ registosRecentes[sensor.id] || "---" }}</td>
        <td>
          <button @click="desassociarSensor(sensor.id)" class="eliminar-button">Desassociar</button>
        </td>
      </tr>
      </tbody>
    </table>

    <h2>Associar Sensor</h2>
    <form @submit.prevent="associarSensor">
      <label for="sensor">Sensor:</label>
      <select v-model="novoSensorId" id="sensor" required @focus="fetchSensoresNaoAtivos">
        <option v-for="sensor in sensoresNaoAtivos" :value="sensor.id" :key="sensor.id">
          {{ sensor.id }} - {{ sensor.tipoSensor }}
        </option>
      </select>

      <p>
        <button type="submit">Associar</button>
      </p>
    </form>
  </div>
</template>

<script setup>
import { useRuntimeConfig } from 'nuxt/app'
import { useFetch } from '#app'
import { reactive, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const id = route.params.id

const config = useRuntimeConfig()
const api = config.public.API_URL

// Dados reativos
const { data: embalagem } = await useFetch(`${api}/embalagens/${id}`)
const { data: sensores } = await useFetch(`${api}/sensors/embalagem/${id}`)

const produtoNomes = reactive({})
const registosRecentes = reactive({}) // Armazena os registos mais recentes para cada sensor
const novoSensorId = ref('') // ID do sensor selecionado para associação

const sensoresNaoAtivos = ref([]) // Sensores não ativos

// Buscar sensores não ativos apenas quando o dropdown é ativado
async function fetchSensoresNaoAtivos() {
  try {
    const { data } = await useFetch(`${api}/sensors/nonactive`)
    if (data.value) {
      sensoresNaoAtivos.value = data.value
      console.log('Sensores não ativos:', sensoresNaoAtivos.value)
    }
  } catch (error) {
    console.error('Erro ao buscar sensores não ativos:', error)
  }
}

// Função para buscar detalhes do produto
async function fetchProduto(idProduto) {
  if (!produtoNomes[idProduto]) {
    const { data: produto } = await useFetch(`${api}/produtos/${idProduto}`)
    if (produto.value) {
      produtoNomes[idProduto] = produto.value.nome
    }
  }
  return produtoNomes[idProduto]
}

// Função para buscar o registro mais recente de um sensor
async function fetchRegistoMaisRecente(idSensor) {
  if (!registosRecentes[idSensor]) {
    const { data: registo } = await useFetch(`${api}/registos/sensor/${idSensor}/mostRecent`)
    if (registo.value) {
      registosRecentes[idSensor] = registo.value.valor
    }
  }
  return registosRecentes[idSensor]
}

// Função para associar sensor à embalagem
async function associarSensor() {
  if (!novoSensorId.value) {
    alert('Por favor, selecione um sensor para associar.')
    return
  }

  try {
    // Buscar detalhes do sensor
    const { data: sensorAtual } = await useFetch(`${api}/sensors/${novoSensorId.value}`)
    if (!sensorAtual.value) {
      throw new Error('Sensor não encontrado.')
    }

    // Atualizar sensor com o ID da embalagem e definir como ativo
    const response = await fetch(`${api}/sensors/${novoSensorId.value}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        ...sensorAtual.value,
        idEmbalagem: embalagem.value.id,
        ativo: true,
      }),
    })

    if (!response.ok) {
      throw new Error('Erro ao associar sensor.')
    }

    alert('Sensor associado com sucesso!')

    // Atualizar listas de sensores
    await fetchSensoresNaoAtivos()
    const { data: sensoresAtualizados } = await useFetch(`${api}/sensors/embalagem/${id}`)
    sensores.value = sensoresAtualizados.value || []
  } catch (error) {
    console.error(error)
    alert('Houve um erro ao tentar associar o sensor.')
  }
}

// Função para desassociar sensor da embalagem
async function desassociarSensor(sensorId) {
  try {
    // Buscar detalhes do sensor
    const { data: sensorAtual } = await useFetch(`${api}/sensors/${sensorId}`)
    if (!sensorAtual.value) {
      throw new Error('Sensor não encontrado.')
    }

    // Atualizar sensor para remover a embalagem e definir como não ativo
    const response = await fetch(`${api}/sensors/${sensorId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        ...sensorAtual.value,
        idEmbalagem: 0,
        ativo: false,
      }),
    })

    if (!response.ok) {
      throw new Error('Erro ao desassociar sensor.')
    }

    alert('Sensor desassociado com sucesso!')

    // Atualizar listas de sensores
    await fetchSensoresNaoAtivos()
    const { data: sensoresAtualizados } = await useFetch(`${api}/sensors/embalagem/${id}`)
    sensores.value = sensoresAtualizados.value || []
  } catch (error) {
    console.error(error)
    alert('Houve um erro ao tentar desassociar o sensor.')
  }
}

// Buscar registros recentes ao montar o componente
onMounted(async () => {
  if (embalagem.value) {
    await fetchProduto(embalagem.value.idProduto);
  }

  if (sensores.value) {
    await Promise.all(sensores.value.map(sensor => fetchRegistoMaisRecente(sensor.id)));
  }
});
</script>
<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  text-align: left;
  padding: 8px;
  border-bottom: 1px solid #ddd;
}
th {
  background-color: #f4f4f4;
}
form {
  margin-top: 20px;
}
label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}
select, button {
  margin-bottom: 15px;
  padding: 10px;
  width: 100%;
  max-width: 400px;
}
button {
  background-color: #007BFF;
  color: white;
  border: none;
  cursor: pointer;
}
button:hover {
  background-color: #0056b3;
}

/* Botão Desassociar */
button.eliminar-button {
  padding: 6px 12px;
  background-color: #f44336;
  color: white;
  width: 100px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button.eliminar-button:hover {
  background-color: #e53935;
}

button.eliminar-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>
