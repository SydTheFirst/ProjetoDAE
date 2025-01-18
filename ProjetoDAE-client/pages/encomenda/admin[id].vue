<template>
  <div class="bg-gray-100 min-h-screen p-8">
    <div class="bg-white shadow-lg rounded-lg p-6 mb-8">
    <h1 class="text-3xl font-bold mb-6">Encomenda {{ encomenda.id }}</h1>

    <Table
        :headers="['Cliente', 'Data de Partida', 'Data de Chegada', 'Método de Pagamento', 'Status']"
        :rows="[[encomenda.cliente, formatDate(encomenda.dataPartida), formatDate(encomenda.dataChegada), encomenda.metodoPagamento, encomenda.status]]">

      <template #col-4="{ value }">
        <span :class="getStatusClasses(value)">{{ value }}</span>
      </template>

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
import { formatDate } from "@/utils/funcionsUtils.js";
import { getStatusClasses } from "@/utils/funcionsUtils.js";
import { useRuntimeConfig } from 'nuxt/app';
import { useFetch } from '#app';

const route = useRoute();
const id = route.params.id;

const config = useRuntimeConfig();
const api = config.public.API_URL;

const { data: encomenda } = await useFetch(`${api}/encomendas/${id}`);
const { data: volumes } = await useFetch(`${api}/volumes/encomenda/${id}`);

</script>
