<template>
  <div>
    <h1>Admin</h1>
  </div>

  <p>
    <nuxt-link to="/registo">Criar registo</nuxt-link>
  </p>

  <h2>Encomendas</h2>
  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Cliente</th>
      <th>Status</th>
      <th>Ações</th>
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
        <button @click="cancelarEncomenda(encomenda.id)" :disabled="encomenda.status === 'Cancelada'">
          Cancelar
        </button>
      </td>
    </tr>
    </tbody>
  </table>
</template>

<script setup>
import { ref } from 'vue'
import { useFetch } from '#app'

const config = useRuntimeConfig()
const api = config.public.API_URL

const { data: encomendas, refresh } = await useFetch(`${api}/encomendas`)

async function cancelarEncomenda(id) {
  try {
    const encomenda = encomendas.value.find(e => e.id === id)
    if (!encomenda) {
      alert("Encomenda não encontrada!")
      return
    }

    const payload = {
      ...encomenda,
      status: "Cancelada"
    }

    const response = await fetch(`${api}/encomendas/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    })

    if (!response.ok) {
      throw new Error("Falha ao cancelar a encomenda")
    }

    await refresh()
  } catch (error) {
    console.error(error)
    alert("Erro ao tentar cancelar a encomenda")
  }
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
button {
  padding: 6px 12px;
  background-color: #f44336;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>
