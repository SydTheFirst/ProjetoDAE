<template>
  <div>
    <h1>Admin</h1>
  </div>

  <h2>Encomendas</h2>
  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Cliente</th>
      <th>Status</th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="encomenda in encomendas" :key="encomenda.id">
      <td>
        <nuxt-link :to="`encomenda/admin${encomenda.id}`">
          {{ encomenda.id }}
        </nuxt-link>
      </td>
      <td>
          {{ encomenda.cliente }}
      </td>
      <td>
        {{ encomenda.status }}
      </td>
      <td>
        CANCELAR
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
const { data: encomendas } = await useFetch(`${api}/encomendas`)

</script>