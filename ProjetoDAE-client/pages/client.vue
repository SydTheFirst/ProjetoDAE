<template>
  <div class="bg-gray-100 min-h-screen p-8">
    <h1 class="text-3xl font-bold text-blue-600 mb-6">Cliente</h1>
    <div class="bg-white shadow-lg rounded-lg p-6 mb-8">
      <h2 class="text-2xl font-semibold text-blue-600 mb-4">Encomendas</h2>
      <Table
          :headers="['ID', 'Status',]"
          :rows="encomendas.map(encomenda => [encomenda.id,encomenda.status,])"
      >
        <!-- Link Customizado na Primeira Coluna -->
        <template #col-0="{ value }">
          <nuxt-link
              :to="`/encomenda/${value}`"
              class="text-blue-600 hover:underline"
          >
            {{ value }}
          </nuxt-link>
        </template>
        <template #col-1="{ value }">
          <span :class="getStatusClasses(value)">{{ value }}</span>
        </template>

      </Table>
    </div>
  </div>
</template>

<script setup>
import Table from "~/components/Table.vue";
import { getStatusClasses } from "@/utils/funcionsUtils.js";

const config = useRuntimeConfig()
const api = config.public.API_URL
const username = 'joaosilva'; // Isto vai mudar conforme a sessão aberta
const { data: encomendas } = await useFetch(`${api}/encomendas/cliente/${username}`)

definePageMeta({
  layout: 'default' // Nome do layout (sem a extensão .vue)
});


</script>