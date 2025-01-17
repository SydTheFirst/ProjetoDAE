<template>
  <div>
    <h1>Sensor {{ id }}</h1>

    <h2>Historico</h2>
    <table>
      <thead>
      <tr>
        <th>Data</th>
        <th>Valor</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="registo in registos" :key="registo.id">
        <td>{{ registo.timeStamp }}</td>
        <td>{{ registo.valor }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import {useFetch, useRuntimeConfig} from "#app";

// Obtendo o parâmetro "id" da rota
const route = useRoute()
const id = route.params.id

// Configurando a URL da API e buscando os dados da encomenda
const config = useRuntimeConfig()
const api = config.public.API_URL

const { data: registos } = await useFetch(`${api}/registos/sensor/${id}`);
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
