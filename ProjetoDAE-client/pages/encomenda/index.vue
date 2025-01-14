<template>

  <div>

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
  </div>

</template>


<script setup>
const config = useRuntimeConfig()
const api = config.public.API_URL
const { data: encomendas } = await useFetch(`${api}/encomendas`)
</script>