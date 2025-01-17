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
        <td>{{ produtoNomes[embalagem.idProduto] || 'Carregando...' }}</td>
        <td>{{ embalagem.quantidade }}</td>
      </tr>
      </tbody>
    </table>

    <!--for Sensores-->
    <h2>Sensores</h2>



  </div>
</template>

<script setup>
import { useRuntimeConfig } from 'nuxt/app'
import { useFetch } from '#app'

// Obter o ID da embalagem pela rota dinâmica
const route = useRoute()
const id = route.params.id

// Configurar a URL da API e buscar os detalhes
const config = useRuntimeConfig()
const api = config.public.API_URL

// Dados reativos
const { data: embalagem } = await useFetch(`${api}/embalagens/${id}`)
const produtoNomes = reactive({})

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

// Buscar o nome do produto da embalagem
if (embalagem.value) {
  await fetchProduto(embalagem.value.idProduto)
}
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
