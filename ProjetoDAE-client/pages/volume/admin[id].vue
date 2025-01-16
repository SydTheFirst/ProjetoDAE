<template>
  <div>
    <h1>Volume {{ idVolume }}</h1>
  </div>
</template>


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
  <td><nuxt-link :to="`/embalagem/${embalagem.id}`">
    {{ embalagem.id }}
  </nuxt-link></td>
  <td>{{ embalagem.quantidade }}</td>
</tr>
</tbody>
</table>

<script setup>
import { useRoute } from 'vue-router'
import {useFetch, useRuntimeConfig} from "#app";

// Obtendo o parâmetro "id" da rota
const route = useRoute()
const idVolume = route.params.id

// Configurando a URL da API e buscando os dados da encomenda
const config = useRuntimeConfig()
const api = config.public.API_URL

const { data: encomenda } = await useFetch(`${api}/embalagens/volume/${id}`);
</script>

<style scoped>
h1 {
  color: #333;
}
</style>
