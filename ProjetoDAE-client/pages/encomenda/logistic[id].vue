<template>
  <div>
    <h1>Encomenda {{ encomenda.id }}</h1>
    <table>
      <thead>
      <tr>
        <th>Cliente</th>
        <th>Data de Partida</th>
        <th>Data de Chegada</th>
        <th>Método de Pagamento</th>
        <th>Status</th>
      </tr>
      </thead>

      <tbody>
      <tr>
        <td>{{ encomenda.cliente }}</td>
        <td>{{ encomenda.dataPartida }}</td>
        <td>{{ encomenda.dataChegada }}</td>
        <td>{{ encomenda.metodoPagamento }}</td>
        <td>{{ encomenda.status }}</td>
      </tr>
      </tbody>
    </table>

    <h2>Volumes</h2>

    <table>
      <tr v-for="volume in volumes" :key="volume.id">
        <td>
          <nuxt-link :to="`/volume/admin${volume.id}`">
            {{ volume.id }}
          </nuxt-link>
        </td>
        <td>
          <button @click="eliminarVolume(volume.id)">Eliminar</button>
        </td>
      </tr>
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
const { data: volumes, refresh } = await useFetch(`${api}/volumes/encomenda/${id}`);

// Função para eliminar volume
async function eliminarVolume(volumeId) {
  if (confirm(`Tem certeza de que deseja eliminar o volume ${volumeId}?`)) {
    try {
      const response = await fetch(`${api}/volumes/${volumeId}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
      })

      if (!response.ok) {
        throw new Error('Erro ao eliminar volume.')
      }

      alert('Volume eliminado com sucesso!')
      // Atualizar a lista de volumes após a exclusão
      await refresh()
    } catch (error) {
      console.error(error)
      alert('Erro ao tentar eliminar o volume.')
    }
  }
}
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
button {
  padding: 6px 12px;
  background-color: #f44336;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button:hover {
  background-color: #e53935;
}
</style>
