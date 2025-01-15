<template>
  <div>
    <h1>Detalhes da Encomenda</h1>
    <table>
      <tr>
        <th>ID:</th>
        <td>{{ encomenda.id }}</td>
      </tr>
      <tr>
        <th>Usuário:</th>
        <td>{{ encomenda.cliente }}</td>
      </tr>
      <tr>
        <th>Data de Criação:</th>
        <td>{{ encomenda.dataPartida }}</td>
      </tr>
      <tr>
        <th>Data de Conclusão:</th>
        <td>{{ encomenda.dataChegada }}</td>
      </tr>
      <tr>
        <th>Método de Pagamento:</th>
        <td>{{ encomenda.metodoPagamento }}</td>
      </tr>
      <tr>
        <th>Status:</th>
        <td>{{ encomenda.status }}</td>
      </tr>
    </table>

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
        <tr v-for="volume in encomenda.volumes" :key="volume.id">
          <td>{{ embalagem.id }}</td>
          <td>{{ embalagem.idProduto }}</td>
          <td>{{ embalagem.quantidade }}</td>
        </tr>
      </tbody>
    </table>
  </div>

</template>

<script setup>
import { useRuntimeConfig } from 'nuxt/app'
import { useFetch } from '#app'

// Acessando o ID da rota dinâmica
const route = useRoute()
const id = route.params.id

// Configurando a URL da API e buscando os dados da encomenda
const config = useRuntimeConfig()
const api = config.public.API_URL

const { data: encomenda } = await useFetch(`${api}/encomendas/${id}`)
const { data: embalagem } = await useFetch(`${api}/embalagens/encomenda/${id}`)


// Função para formatar timestamps (opcional)
function formatTimestamp(timestamp) {
  const date = new Date(timestamp)
  return date.toLocaleString('pt-PT', { dateStyle: 'short', timeStyle: 'short' })
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
