<template>
  <div class="bg-gray-100 min-h-screen p-8">
    <div class="bg-white shadow-lg rounded-lg p-6 mb-8">
      <h1 class="text-3xl font-bold text-blue-600 mb-6">
        Encomenda {{ encomenda.id }}
      </h1>
      <Table
          :headers="['Data de Partida', 'Data de Chegada', 'Método de Pagamento', 'Status']"
          :rows="[[encomenda.dataPartida, encomenda.dataChegada, encomenda.metodoPagamento, encomenda.status]]"
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
      <tbody>
        <tr>
          <td>{{ encomenda.dataPartida }}</td>
          <td>{{ encomenda.dataChegada }}</td>
          <td>{{ encomenda.metodoPagamento }}</td>
          <td>{{ encomenda.status }}</td>
        </tr>
      </tbody>
    </table>

    <h2>Embalagens</h2>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Produto</th>
          <th>Quantidade</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="embalagem in embalagens" :key="embalagem.id">
          <td><nuxt-link :to="`/embalagem/${embalagem.id}`">
            {{ embalagem.id }}
          </nuxt-link></td>
          <td>
            <nuxt-link :to="`/produto/${embalagem.idProduto}`">
              {{ produtoNomes[embalagem.idProduto] || 'Carregando...' }}
            </nuxt-link>
          </td>
          <td>{{ embalagem.quantidade }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import Table from "@/components/Table.vue";

import { useRuntimeConfig } from "nuxt/app";
import { useFetch } from "#app";

const route = useRoute();
const id = route.params.id;

const config = useRuntimeConfig();
const api = config.public.API_URL;

const { data: encomenda } = await useFetch(`${api}/encomendas/${id}`);
const { data: embalagens } = await useFetch(
    `${api}/embalagens/encomenda/${id}`
);

const produtoNomes = reactive({});

async function fetchProduto(idProduto) {
  if (!produtoNomes[idProduto]) {
    const { data: produto } = await useFetch(`${api}/produtos/${idProduto}`);
    if (produto.value) {
      produtoNomes[idProduto] = produto.value.nome;
    }
  }
  return produtoNomes[idProduto];
}

await Promise.all(
    embalagens.value.map((embalagem) => fetchProduto(embalagem.idProduto))
);

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


