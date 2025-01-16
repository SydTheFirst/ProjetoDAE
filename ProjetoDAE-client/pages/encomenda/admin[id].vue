<template>
  <div>
    <h1>Encomenda {{ encomenda.id }}</h1>
    <table>
      <thead>
      <tr>
        <th>Cliente</th>
        <th>Data de Partida</th>
        <th>Data de Chegada</th>
        <th>Método de Pagamento</th>
        <th>Status</th>
      </tr>
      </thead>

      <tbody>
      <tr>
        <td>{{ encomenda.cliente }}</td>
        <td>{{ encomenda.dataPartida }}</td>
        <td>{{ encomenda.dataChegada }}</td>
        <td>{{ encomenda.metodoPagamento }}</td>
        <td>{{ encomenda.status }}</td>
      </tr>
      </tbody>
    </table>

    <h2>Volumes</h2>

    <table>
    <tr v-for="volume in volumes" :key="volume.id">
      <td><nuxt-link :to="`/volume/admin${volume.id}`">
        {{ volume.id }}
      </nuxt-link></td>
    </tr>
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

const { data: encomenda } = await useFetch(`${api}/encomendas/${id}`);
const { data: volumes } = await useFetch(`${api}/volumes/encomenda/${id}`);

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
