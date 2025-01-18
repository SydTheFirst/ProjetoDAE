<template>
  <div class="bg-gray-100 min-h-screen p-8">
    <div class="bg-white shadow-lg rounded-lg p-6 mb-8">
      <h1 class="text-3xl font-bold mb-6">Embalagem {{ embalagem.id }}</h1>
    <Table
        :headers="['Encomenda', 'Produto', 'Quantidade']"
        :rows="[[embalagem.idEncomenda, produtoNomes[embalagem.idProduto] || embalagem.idProduto, embalagem.quantidade]]"
    >
      <template #col-1="{ value }">
        <nuxt-link :to="`/produto/${embalagem.idProduto}`" class="text-blue-600 hover:underline">
          {{ value }}
        </nuxt-link>
      </template>
    </Table>
      <br>
      <h2 class="text-3xl font-bold mb-6">Sensores</h2>
    <Table
        :headers="['ID', 'Tipo', 'Valor']"
        :rows="sensores.map(sensor => [sensor.id, sensor.tipoSensor, registosRecentes[sensor.id]])"
    >
      <template #col-0="{ value }">
        <nuxt-link :to="`/sensor/${value}`" class="text-blue-600 hover:underline">
          {{ value }}
        </nuxt-link>
      </template>
    </Table>
      <br>
      <h2 class="text-3xl font-bold mb-6">Criar Registo</h2>
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
  </div>>
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

async function fetchProduto(idProduto) {
  if (!produtoNomes[idProduto]) {
    const { data: produto } = await useFetch(`${api}/produtos/${idProduto}`)
    if (produto.value) {
      produtoNomes[idProduto] = produto.value.nome // Armazena o nome do produto
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


