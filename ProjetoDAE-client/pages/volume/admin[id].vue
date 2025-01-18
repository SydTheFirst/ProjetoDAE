<template>
  <div class="bg-gray-100 min-h-screen p-8">
    <div class="bg-white shadow-lg rounded-lg p-6 mb-8">
      <h1 class="text-3xl font-bold text-blue-600 mb-6">Volume {{ id }}</h1>

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
import { useRoute } from 'vue-router'
import { useFetch, useRuntimeConfig } from '#app'
import { reactive } from 'vue'

const route = useRoute()
const id = route.params.id

const config = useRuntimeConfig()
const api = config.public.API_URL

const { data: embalagens } = await useFetch(`${api}/embalagens/volume/${id}`)

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

// Buscando os nomes dos produtos para todas as embalagens
await Promise.all(
    embalagens.value.map(embalagem => fetchProduto(embalagem.idProduto))
)
</script>

