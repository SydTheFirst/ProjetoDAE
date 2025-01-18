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
      </tr>
      </tbody>
    </table>

    <h2>Ativar Sensor</h2>
    <form @submit.prevent="ativarSensor">
      <label for="sensor">Selecionar Sensor:</label>
      <select v-model="novoRegisto.idSensor" id="sensor" required>
        <option v-for="sensor in sensoresNaoAtivos" :value="sensor.id" :key="sensor.id">
          {{ sensor.id }} - {{ sensor.tipoSensor }}
        </option>
      </select>

      <p>
        <button type="submit">Ativar Sensor</button>
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
const { data: sensoresNaoAtivos } = await useFetch(`${api}/sensors/nonactive`)

const produtoNomes = reactive({})
const registosRecentes = reactive({}) // Armazena os registos mais recentes para cada sensor
const novoRegisto = reactive({
  idSensor: '',
})
const sensores = ref([])

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

// Função para ativar o sensor e associá-lo à embalagem
async function ativarSensor() {
  const sensorId = novoRegisto.idSensor

  try {
    const response = await fetch(`${api}/sensors/ativar`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        idSensor: sensorId,
        idEmbalagem: id,
      }),
    })

    if (!response.ok) {
      throw new Error('Erro ao ativar o sensor.')
    }

    alert('Sensor ativado com sucesso!');

    // Atualizar a lista de sensores (agora com o sensor ativado)
    await buscarSensores()

    // Limpar campos do formulário
    novoRegisto.idSensor = ''
  } catch (error) {
    console.error(error)
    alert('Houve um erro ao tentar ativar o sensor.')
  }
}

// Função para buscar e atualizar a lista de sensores ativos
async function buscarSensores() {
  const { data: sensoresAtivos } = await useFetch(`${api}/sensors/embalagem/${id}`)
  sensores.value = sensoresAtivos.value
}

// Buscar registros recentes e sensores ativos ao montar o componente
onMounted(async () => {
  if (embalagem.value) {
    await fetchProduto(embalagem.value.idProduto);
  }

  // Buscar sensores ativos para a embalagem
  await buscarSensores()
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
input, select, button {
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
</style>
