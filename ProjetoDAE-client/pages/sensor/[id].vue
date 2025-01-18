<template>
  <div class="bg-gray-100 min-h-screen p-8">
    <div class="bg-white shadow-lg rounded-lg p-6 mb-8">
    <h1 class="text-3xl font-bold mb-6">Sensor {{ id }}</h1>

    <h2 class="text-2xl font-bold mt-8 mb-4">Histórico</h2>
    <Table
        :headers="['Data', 'Valor']"
        :rows="registos.map(registo => [registo.timeStamp, registo.valor])"
    />
  </div>
  </div>
</template>

<script setup>
import Table from "@/components/Table.vue";
import { useRoute } from 'vue-router';
import { useFetch, useRuntimeConfig } from '#app';

const route = useRoute();
const id = route.params.id;

const config = useRuntimeConfig();
const api = config.public.API_URL;

const { data: registos } = await useFetch(`${api}/registos/sensor/${id}`);
</script>

