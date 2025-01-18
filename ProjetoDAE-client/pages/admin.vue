<template>
  <div class="bg-gray-100 min-h-screen p-8">
    <h1 class="text-3xl font-bold text-blue-600 mb-6">Admin</h1>
    <div class="bg-white shadow-lg rounded-lg p-6 mb-8">
      <h2 class="text-3xl font-bold mb-6">Encomendas</h2>
      <Table
          :headers="['ID', 'Cliente', 'Status', 'Ações']"
          :rows="encomendas.map(encomenda => [encomenda.id,encomenda.cliente,encomenda.status,generateActionButton(encomenda)])"
      >
        <!-- Link Customizado na Primeira Coluna -->
        <template #col-0="{ value }">
          <nuxt-link
              :to="`/encomenda/admin${value}`"
              class="text-blue-600 hover:underline"
          >
            {{ value }}
          </nuxt-link>
        </template>

        <template #col-3="{ value }">
          <button
              @click="value.onClick"
              :disabled="value.disabled"
              class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-700"
          >
            {{ value.label }}
          </button>
        </template>

      </Table>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useFetch } from '#app'
import Table from "~/components/Table.vue"

definePageMeta({
  layout: 'default' // Nome do layout (sem a extensão .vue)
})

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

// Função para gerar o botão "Cancelar"
function generateActionButton(encomenda) {
  return {
    type: 'button',
    label: 'Cancelar',
    disabled: encomenda.status === 'Cancelada',
    onClick: () => cancelarEncomenda(encomenda.id)
  }
}
</script>