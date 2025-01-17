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
            {{ embalagem.idProduto}}
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
        <td><nuxt-link :to="`/sensor/${sensor.id}`">
          {{ sensor.id }}
        </nuxt-link></td>
        <td>{{ sensor.tipoSensor }}</td>
        <td>{{ registosRecentes[sensor.id] }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { useRuntimeConfig } from 'nuxt/app'
import { useFetch } from '#app'
import { reactive, onMounted } from 'vue'

const route = useRoute()
const id = route.params.id

const config = useRuntimeConfig()
const api = config.public.API_URL

// Dados reativos
const { data: embalagem } = await useFetch(`${api}/embalagens/${id}`)
const { data: sensores } = await useFetch(`${api}/sensors/embalagem/${id}`)

const produtoNomes = reactive({})
const registosRecentes = reactive({}) // Armazena os registos mais recentes para cada sensor

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

async function fetchRegistoMaisRecente(idSensor) {
  if (!registosRecentes[idSensor]) {
    const { data: registo } = await useFetch(`${api}/registos/sensor/${idSensor}/mostRecent`)
    if (registo.value) {
      registosRecentes[idSensor] = registo.value.valor // Armazena o valor do registo
    }
  }
  return registosRecentes[idSensor]
}

onMounted(async () => {
  if (embalagem.value) {
    await fetchProduto(embalagem.value.idProduto)
  }

  if (sensores.value) {
    await Promise.all(
        sensores.value.map(sensor => fetchRegistoMaisRecente(sensor.id))
    )
  }
})
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
</style>
