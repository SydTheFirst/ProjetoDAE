<template>
  <div>
    <h1>Encomenda {{ encomenda.id }}</h1>
    <table>
      <thead>
        <tr>
          <th>Data de Partida</th>
          <th>Data de Chegada</th>
          <th>Método de Pagamento</th>
          <th>Status</th>
        </tr>
      </thead>

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
import { useRuntimeConfig } from 'nuxt/app'
import { useFetch } from '#app'

// Acessando o ID da rota dinâmica
const route = useRoute()
const id = route.params.id

// Configurando a URL da API e buscando os dados da encomenda
const config = useRuntimeConfig()
const api = config.public.API_URL

const { data: encomenda } = await useFetch(`${api}/encomendas/${id}`);
const { data: embalagens } = await useFetch(`${api}/embalagens/encomenda/${id}`);

const produtoNomes = reactive({});

// Função para buscar detalhes do produto
async function fetchProduto(idProduto) {
  if (!produtoNomes[idProduto]) {
    const { data: produto } = await useFetch(`${api}/produtos/${idProduto}`);
    if (produto.value) {
      produtoNomes[idProduto] = produto.value.nome; // Armazena o nome do produto
    }
  }
  return produtoNomes[idProduto];
}

// Busque os nomes dos produtos para todas as embalagens
await Promise.all(
    embalagens.value.map(embalagem => fetchProduto(embalagem.idProduto))
);
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
</style>
