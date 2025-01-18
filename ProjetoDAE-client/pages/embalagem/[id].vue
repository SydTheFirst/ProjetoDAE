<template>
  <div class="bg-gray-100 min-h-screen p-8">
    <div class="bg-white shadow-lg rounded-lg p-6 mb-8">
    <h1 class="text-3xl font-bold mb-6">Embalagem {{ embalagem.id }}</h1>

    <Table
        :headers="['Encomenda', 'Produto', 'Quantidade']"
        :rows="[[embalagem.idEncomenda, produtoNomes[embalagem.idProduto] || embalagem.idProduto, embalagem.quantidade]]"
    />

    <h2 class="text-2xl font-bold mt-8 mb-4">Sensores</h2>
    <Table
        :headers="['ID', 'Tipo', 'Valor']"
        :rows="sensores.map(sensor => [sensor.id, sensor.tipoSensor, registosRecentes[sensor.id]])"
    >
      <template #col-0="{ value }">
        <nuxt-link :to="`/sensor/${value}`" class="text-blue-600 hover:underline">
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
import { reactive, onMounted } from 'vue';

const route = useRoute();
const id = route.params.id;

const config = useRuntimeConfig();
const api = config.public.API_URL;

const { data: embalagem } = await useFetch(`${api}/embalagens/${id}`);
const { data: sensores } = await useFetch(`${api}/sensors/embalagem/${id}`);

const produtoNomes = reactive({});
const registosRecentes = reactive({});

async function fetchProduto(idProduto) {
  if (!produtoNomes[idProduto]) {
    const { data: produto } = await useFetch(`${api}/produtos/${idProduto}`)
    if (produto.value) {
      produtoNomes[idProduto] = produto.value.nome // Armazena o nome do produto
    }
  }
  return produtoNomes[idProduto]
}

async function fetchRegistoMaisRecente(idSensor) {
  if (!registosRecentes[idSensor]) {
    const { data: registo } = await useFetch(`${api}/registos/sensor/${idSensor}/mostRecent`);
    if (registo.value) registosRecentes[idSensor] = registo.value.valor;
  }
}

onMounted(async () => {
  if (embalagem.value) {
    await fetchProduto(embalagem.value.idProduto);
  }
  if (sensores.value) {
    await Promise.all(sensores.value.map(sensor => fetchRegistoMaisRecente(sensor.id)));
  }
});
</script>
