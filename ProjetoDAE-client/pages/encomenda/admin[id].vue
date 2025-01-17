<template>
  <div class="bg-gray-100 min-h-screen p-8">
    <div class="bg-white shadow-lg rounded-lg p-6 mb-8">
    <h1 class="text-3xl font-bold mb-6">Encomenda {{ encomenda.id }}</h1>

    <Table
        :headers="['Cliente', 'Data de Partida', 'Data de Chegada', 'Método de Pagamento', 'Status']"
        :rows="[[encomenda.cliente, encomenda.dataPartida, encomenda.dataChegada, encomenda.metodoPagamento, encomenda.status]]"
    >
    </Table>

    <h2 class="text-2xl font-bold mt-8 mb-4">Volumes</h2>
    <Table
        :headers="['ID']"
        :rows="volumes.map(volume => [volume.id])"
    >
      <template #col-0="{ value }">
        <nuxt-link :to="`/volume/admin${value}`" class="text-blue-600 hover:underline">
          {{ value }}
        </nuxt-link>
      </template>
    </Table>
  </div>
  </div>
</template>

<script setup>
import Table from "@/components/Table.vue";
import { useRuntimeConfig } from 'nuxt/app';
import { useFetch } from '#app';

const route = useRoute();
const id = route.params.id;

const config = useRuntimeConfig();
const api = config.public.API_URL;

const { data: encomenda } = await useFetch(`${api}/encomendas/${id}`);
const { data: volumes } = await useFetch(`${api}/volumes/encomenda/${id}`);

function getStatusClasses(status) {
  switch (status) {
    case "Pendente":
      return "text-yellow-600 bg-yellow-100 px-2 py-1 rounded";
    case "Concluído":
      return "text-green-600 bg-green-100 px-2 py-1 rounded";
    case "Cancelado":
      return "text-red-600 bg-red-100 px-2 py-1 rounded";
    default:
      return "text-blue-600 bg-blue-100 px-2 py-1 rounded";
  }
}
</script>
