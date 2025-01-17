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
    <tr v-for="encomenda in encomendas" :key="encomenda.id">
      <td>
        <nuxt-link :to="`encomenda/${encomenda.id}`">
          {{ encomenda.id }}
        </nuxt-link>
      </td>
      <td>
        {{ encomenda.status }}
      </td>
    </tr>
    </tbody>
  </table>
</template>

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


<script setup>
const config = useRuntimeConfig()
const api = config.public.API_URL
const username = 'joaosilva'; // Isto vai mudar conforme a sessão aberta
const { data: encomendas } = await useFetch(`${api}/encomendas/cliente/${username}`)

</script>