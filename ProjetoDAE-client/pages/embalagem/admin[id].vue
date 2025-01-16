<template>
  <div>
    <h1>Detalhes da Embalagem</h1>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Encomenda</th>
        <th>Produto</th>
        <th>Quantidade</th>
      </tr>
      </thead>

      <tbody>
      <tr>
        <td>{{ embalagem.id }}</td>
        <td>{{ embalagem.idEncomenda }}</td>
        <td>{{ produtoNomes[embalagem.idProduto] || 'Carregando...' }}</td>
        <td>{{ embalagem.quantidade }}</td>
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

// Buscando os detalhes da embalagem
const { data: embalagem } = await useFetch(`${api}/embalagens/${id}`)

const produtoNomes = reactive({})

// Função para buscar o nome do produto baseado no ID
async function fetchProdutoNome(idProduto) {
  if (!produtoNomes[idProduto]) {
    const { data: produto } = await useFetch(`${api}/produtos/${idProduto}`)
    if (produto.value) {
      produtoNomes[idProduto] = produto.value.nome
    }
  }
  return produtoNomes[idProduto]
}

// Ao montar o componente, buscar o nome do produto correspondente
onMounted(async () => {
  if (embalagem.value) {
    await fetchProdutoNome(embalagem.value.idProduto)
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
