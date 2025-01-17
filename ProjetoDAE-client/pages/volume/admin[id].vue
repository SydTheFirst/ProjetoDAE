<template>
  <div>
    <h1>Volume {{ id }}</h1>

    <h2>Embalagens</h2>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Produto</th>
        <th>Quantidade</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="embalagem in embalagens" :key="embalagem.id">
        <td>
          <nuxt-link :to="`/embalagem/admin${embalagem.id}`">
            {{ embalagem.id }}
          </nuxt-link>
        </td>
        <td>
          <nuxt-link :to="`/produto/${embalagem.idProduto}`">
            {{ produtoNomes[embalagem.idProduto] || 'Carregando...' }}
          </nuxt-link>
        </td>
        <td>{{ embalagem.quantidade }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import { useFetch, useRuntimeConfig } from '#app'
import { reactive } from 'vue'

const route = useRoute()
const id = route.params.id

const config = useRuntimeConfig()
const api = config.public.API_URL

const { data: embalagens } = await useFetch(`${api}/embalagens/volume/${id}`)

const produtoNomes = reactive({})

async function fetchProduto(idProduto) {
  if (!produtoNomes[idProduto]) {
    const { data: produto } = await useFetch(`${api}/produtos/${idProduto}`)
    if (produto.value) {
      produtoNomes[idProduto] = produto.value.nome // Armazena o nome do produto
    }
  }
  return produtoNomes[idProduto]
}

// Buscando os nomes dos produtos para todas as embalagens
await Promise.all(
    embalagens.value.map(embalagem => fetchProduto(embalagem.idProduto))
)
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
