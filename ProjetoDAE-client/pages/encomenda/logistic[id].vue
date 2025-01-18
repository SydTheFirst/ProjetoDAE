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
          <nuxt-link :to="`/volume/logistic${volume.id}`">
            {{ volume.id }}
          </nuxt-link>
        </td>
        <td>
          <button @click="eliminarVolume(volume.id)" class="eliminar-button">Eliminar</button>
        </td>
      </tr>
    </table>

    <h3>Criar Novo Volume</h3>
    <label for="novoVolumeId">ID do Novo Volume V{{ encomenda.id }}-</label>
    <input
        type="text"
        id="novoVolumeId"
        v-model="novoVolumeId"
    />
    <p>
      <button @click="criarVolume" :disabled="isCreatingVolume">Criar Volume</button>
    </p>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRuntimeConfig } from 'nuxt/app'
import { useFetch } from '#app'

const route = useRoute()
const id = route.params.id

const config = useRuntimeConfig()
const api = config.public.API_URL

const { data: encomenda } = await useFetch(`${api}/encomendas/${id}`)
const { data: volumes, refresh } = await useFetch(`${api}/volumes/encomenda/${id}`)

const novoVolumeId = ref('')
const isCreatingVolume = ref(false)

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
      await refresh()
    } catch (error) {
      console.error(error)
      alert('Erro ao tentar eliminar o volume.')
    }
  }
}

// Função para criar um novo volume
async function criarVolume() {
  if (!novoVolumeId.value) {
    alert('Por favor, insira um ID para o novo volume.')
    return
  }

  const novoVolumeIdCompleto = `V${encomenda.value.id}-${novoVolumeId.value}`

  // Verificar se o ID já existe
  const volumeExiste = volumes.value.some(volume => volume.id === novoVolumeIdCompleto)

  if (volumeExiste) {
    alert(`O volume com o ID ${novoVolumeIdCompleto} já existe!`)
    return
  }

  isCreatingVolume.value = true

  try {
    const response = await fetch(`${api}/volumes`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ id: novoVolumeIdCompleto, idEncomenda: encomenda.value.id }),
    })

    if (!response.ok) {
      throw new Error('Erro ao criar o volume.')
    }

    alert('Volume criado com sucesso!')
    novoVolumeId.value = ''  // Limpar o campo de ID do novo volume
    await refresh()  // Atualizar a lista de volumes
  } catch (error) {
    console.error(error)
    alert('Erro ao tentar criar o volume.')
  } finally {
    isCreatingVolume.value = false
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
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button:hover {
  opacity: 0.8;
}
input {
  margin-top: 5px;
  padding: 8px;
  width: 100%;
  max-width: 250px;
}
button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.eliminar-button {
  background-color: #f44336; /* Cor vermelha para os botões de eliminar */
  color: white;
}

.eliminar-button:hover {
  background-color: #e53935; /* Cor vermelha mais escura quando hover */
}

button:not(.eliminar-button) {
  background-color: #007BFF; /* Cor azul para os outros botões */
  color: white;
}

button:not(.eliminar-button):hover {
  background-color: #0056b3; /* Cor azul mais escura quando hover */
}
</style>
