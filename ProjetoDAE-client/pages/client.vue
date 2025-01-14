<template>
  <div>
    <h1>Client</h1>
  </div>


  <h2>Encomendas</h2>
  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="encomenda in encomendas">
      <td>{{ encomenda.id }}</td>
      <td>{{ encomenda.status }}</td>
    </tr>
    </tbody>
  </table>
</template>

<style scoped>

</style>


<script setup>
const config = useRuntimeConfig()
const api = config.public.API_URL
const { data: encomendas } = await useFetch(`${api}/encomendas`)
</script>