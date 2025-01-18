<template>
  <div>
    <h1>Logistic</h1>
  </div>

  <h2>Encomendas</h2>
  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Cliente</th>
      <th>Status</th>
      <th>Ação</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="encomenda in encomendas" :key="encomenda.id">
      <td>
        <nuxt-link :to="`encomenda/logistic${encomenda.id}`">
          {{ encomenda.id }}
        </nuxt-link>
      </td>
      <td>
        {{ encomenda.cliente }}
      </td>
      <td>
        <select
            v-model="encomenda.status"
            :disabled="encomenda.status === 'Cancelada'"
            :class="{ cancelada: encomenda.status === 'Cancelada' }"
        >
          <option
              v-for="status in statusOptions"
              :value="status"
              :key="status"
          >
            {{ status }}
          </option>
          <option
              v-if="encomenda.status === 'Cancelada'"
              value="Cancelada"
          >
            Cancelada
          </option>
        </select>
      </td>
      <td>
        <button
            v-if="encomenda.status !== 'Cancelada'"
            @click="atualizarEncomenda(encomenda.id)"
            :disabled="encomenda.status === originalStatus[encomenda.id]"
        >
          Atualizar
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

// Opções de status sem incluir "Cancelada"
const statusOptions = ["Pendente", "Enviada", "Entregue"]

const { data: encomendas, refresh } = await useFetch(`${api}/encomendas`)

// Armazena o status original para comparação
const originalStatus = ref(
    encomendas.value.reduce((acc, encomenda) => {
      acc[encomenda.id] = encomenda.status
      return acc
    }, {})
)

async function atualizarEncomenda(id) {
  try {
    const encomenda = encomendas.value.find(e => e.id === id)
    if (!encomenda) {
      alert("Encomenda não encontrada!")
      return
    }

    const payload = { ...encomenda, dataChegada: new Date() }

    console.log(payload + " " + encomenda.status)

    switch (encomenda.status) {
      case "Enviada":
        // Atualiza a data de envio no formato 2025-01-16T16:34:29.623408Z[UTC]
        payload.dataPartida = new Date().toISOString()
          console.log(payload.dataPartida)
        break
      case "Entregue":
        payload.dataChegada = new Date().toISOString()
        console.log(payload.dataChegada)
        break
    }

    const response = await fetch(`${api}/encomendas/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    })

    if (!response.ok) {
      throw new Error("Falha ao atualizar a encomenda")
    }

    // Atualizar o status original após a alteração bem-sucedida
    originalStatus.value[encomenda.id] = encomenda.status
    alert("Encomenda atualizada com sucesso!")
    await refresh()
  } catch (error) {
    console.error(error)
    alert("Erro ao tentar atualizar a encomenda")
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
  background-color: #007BFF;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
select {
  padding: 6px;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: #fff;
}
select.cancelada {
  color: red;
  font-weight: bold;
}
</style>
