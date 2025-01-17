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
        <td><nuxt-link :to="`/embalagem/admin${embalagem.id}`">
          {{ embalagem.id }}
        </nuxt-link></td>
        <td>{{ embalagem.idProduto }}</td>
        <td>{{ embalagem.quantidade }}</td>
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

const { data: embalagens } = await useFetch(`${api}/embalagens/volume/${id}`);
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
