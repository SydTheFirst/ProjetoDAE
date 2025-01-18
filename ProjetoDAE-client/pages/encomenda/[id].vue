<template>
  <div class="bg-gray-100 min-h-screen p-8">
    <div class="bg-white shadow-lg rounded-lg p-6 mb-8">
      <h1 class="text-3xl font-bold text-blue-600 mb-6">
        Encomenda {{ encomenda.id }}
      </h1>
      <Table
          :headers="['Data de Partida', 'Data de Chegada', 'Método de Pagamento', 'Status']"
          :rows="[[formatDate(encomenda.dataPartida), formatDate(encomenda.dataChegada), encomenda.metodoPagamento, encomenda.status]]"
      >
        <template #col-3="{ value }">
          <span :class="getStatusClasses(value)">{{ value }}</span>
        </template>
      </Table>
    </div>

    <div class="bg-white shadow-lg rounded-lg p-6">
      <h2 class="text-2xl font-semibold text-blue-600 mb-4">Embalagens</h2>
      <Table
          :headers="['ID', 'Produto', 'Quantidade']"
          :rows="embalagens.map((embalagem) => [
          embalagem.id,
          produtoNomes[embalagem.idProduto] || 'Carregando...',
          embalagem.quantidade,
        ])"
      >
        <!-- Link Customizado na Primeira Coluna -->
        <template #col-0="{ value }">
          <nuxt-link
              :to="`/embalagem/${value}`"
              class="text-blue-600 hover:underline"
          >
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

import { useRuntimeConfig } from "nuxt/app";
import { useFetch } from "#app";
import {reactive} from "vue";

const route = useRoute();
const id = route.params.id;

const config = useRuntimeConfig();
const api = config.public.API_URL;

const { data: encomenda } = await useFetch(`${api}/encomendas/${id}`);
const { data: embalagens } = await useFetch(
    `${api}/embalagens/encomenda/${id}`
);

const produtoNomes = reactive({})

async function fetchProduto(idProduto) {
  if (!produtoNomes[idProduto]) {
    const { data: produto } = await useFetch(`${api}/produtos/${idProduto}`)
    if (produto.value) {
      produtoNomes[idProduto] = produto.value.nome // Armazena o nome do produto
    }
  }
  return produtoNomes[idProduto]
}

await Promise.all(
    embalagens.value.map((embalagem) => fetchProduto(embalagem.idProduto))
);

</script>


