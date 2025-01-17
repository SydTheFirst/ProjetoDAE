<template>
  <div>
    <h1>{{ produto.nome }}</h1>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Preço</th>
        <th>Categoria</th>
      </tr>
      </thead>

      <tbody>
      <tr>
        <td>{{ produto.id }}</td>
        <td>{{ produto.preco }}€</td>
        <td>{{ produto.categoria }}</td>
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

const { data: produto } = await useFetch(`${api}/produtos/${id}`);
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
