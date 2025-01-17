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

    <h2>Criar Registo</h2>
    <form @submit.prevent="criarRegisto">
      <label for="sensor">Sensor:</label>
      <select v-model="novoRegisto.idSensor" id="sensor" required>
        <option v-for="sensor in sensores" :value="sensor.id" :key="sensor.id">
          {{ sensor.id }}
        </option>
      </select>

      <label for="valor">Valor:</label>
      <input
          type="text"
          id="valor"
          v-model="novoRegisto.valor"
          placeholder="Insira o valor"
          required
      />

      <p>
        <button type="submit">OK</button>
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
const novoRegisto = reactive({
  idSensor: '',
  valor: '',
})

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
async function criarRegisto() {
  if (!novoRegisto.idSensor || !novoRegisto.valor) {
    alert('Por favor, preencha todos os campos antes de enviar.');
    return;
  }

  const timeStamp = new Date().toISOString();

  try {
    const response = await fetch(`${api}/registos`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        timeStamp,
        valor: novoRegisto.valor,
        idSensor: novoRegisto.idSensor,
      }),
    });

    if (!response.ok) {
      throw new Error('Erro ao criar o registro.');
    }

    alert('Registro criado com sucesso!');

    registosRecentes[novoRegisto.idSensor] = novoRegisto.valor;

    // Resetar os campos do formulário
    novoRegisto.idSensor = '';
    novoRegisto.valor = '';

    // Atualizar a lista de registros recentes
    await fetchRegistoMaisRecente(novoRegisto.idSensor);
  } catch (error) {
    console.error(error);
    alert('Houve um erro ao tentar criar o registro.');
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


