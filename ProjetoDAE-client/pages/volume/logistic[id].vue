<template>
  <div>
    <h1>Volume {{ id }} </h1>

    <h2>Embalagens</h2>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Produto</th>
        <th>Quantidade</th>
        <th>Ações</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="embalagem in embalagens" :key="embalagem.id">
        <td>
          <nuxt-link :to="`/embalagem/logisitc${embalagem.id}`">
            {{ embalagem.id }}
          </nuxt-link>
        </td>
        <td>
          <nuxt-link :to="`/produto/${embalagem.idProduto}`">
            {{ embalagem.idProduto }}
          </nuxt-link>
        </td>
        <td>{{ embalagem.quantidade }}</td>
        <td>
          <button @click="eliminarEmbalagem(embalagem.id)" class="eliminar-button">Eliminar</button>
        </td>
      </tr>
      </tbody>
    </table>

    <h2>Adicionar Nova Embalagem</h2>
    <form @submit.prevent="adicionarEmbalagem">
      <div>
        <label for="id">ID:</label>
        <input v-model="novaEmbalagem.id" type="number" />
        <span v-if="novaEmbalagem.id <= 0" style="color: red;">ID deve ser maior que 0!</span>
        <span v-if="!idDisponivel && novaEmbalagem.id > 0" style="color: red;">ID já está em uso!</span>
      </div>
      <div>
        <label for="idProduto">ID Produto:</label>
        <input v-model="novaEmbalagem.idProduto" type="number" required />
        <span v-if="produtoInexistente" style="color: red;">Produto não encontrado!</span>
      </div>
      <div>
        <label for="quantidade">Quantidade:</label>
        <input v-model="novaEmbalagem.quantidade" type="number" required />
        <span v-if="novaEmbalagem.quantidade <= 0" style="color: red;">Quantidade deve ser maior que 0!</span>
      </div>
      <div>
        <button type="submit" :disabled="!idDisponivel || produtoInexistente || novaEmbalagem.quantidade <= 0 || novaEmbalagem.id <= 0">Adicionar Embalagem</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import { useFetch, useRuntimeConfig } from '#app'
import { reactive, ref, watch, onMounted } from 'vue'

const route = useRoute()
const id = route.params.id

const config = useRuntimeConfig()
const api = config.public.API_URL

const { data: embalagens, refresh } = await useFetch(`${api}/embalagens/volume/${id}`)
const { data: volume } = await useFetch(`${api}/volumes/${id}`)

// Dados do formulário para nova embalagem
const novaEmbalagem = reactive({
  id: '', // Será inicializado como sugestão, mas editável
  idVolume: volume.value.id,
  idEncomenda: volume.value.idEncomenda,
  idProduto: '',
  quantidade: '',
})

// Variáveis de estado
const idDisponivel = ref(true)
const produtoInexistente = ref(false)

// Função para buscar o último ID e sugerir o próximo
async function carregarUltimoId() {
  try {
    const response = await fetch(`${api}/embalagens/lastId`, {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
      },
    })

    if (!response.ok) {
      throw new Error('Erro ao buscar último ID.')
    }

    const lastId = await response.json()
    novaEmbalagem.id = lastId + 1
  } catch (error) {
    console.error(error)
    alert('Erro ao carregar o último ID da embalagem.')
  }
}

// Função para verificar se o ID da embalagem já existe
async function verificarIdDisponivel(id) {
  if (!id || id <= 0) {
    idDisponivel.value = true
    return
  }

  const { data } = await useFetch(`${api}/embalagens/${id}`)
  idDisponivel.value = !data.value
}

// Função para verificar se o produto existe
async function verificarProdutoExistente(idProduto) {
  const response = await fetch(`${api}/produtos/${idProduto}`, {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
    },
  })

  if (response.ok) {
    produtoInexistente.value = false
    return true
  } else {
    produtoInexistente.value = true
    return false
  }
}

// Watchers
watch(() => novaEmbalagem.id, async (novoId) => {
  if (novoId && novoId > 0) {
    await verificarIdDisponivel(novoId)
  }
})

watch(() => novaEmbalagem.idProduto, async (novoIdProduto) => {
  if (novoIdProduto) {
    await verificarProdutoExistente(novoIdProduto)
  }
})

// Função para adicionar nova embalagem
async function adicionarEmbalagem() {
  const produtoExistente = await verificarProdutoExistente(novaEmbalagem.idProduto)

  if (!produtoExistente) {
    alert('Produto com o ID informado não encontrado. Por favor, insira um ID válido.')
    return
  }

  if (!idDisponivel.value) {
    alert('ID não disponível. Por favor, escolha outro ID.')
    return
  }

  try {
    const response = await fetch(`${api}/embalagens`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(novaEmbalagem),
    })

    if (!response.ok) {
      throw new Error('Erro ao adicionar embalagem.')
    }

    alert('Embalagem adicionada com sucesso!')
    await refresh()

    // Atualizar ID automaticamente após adicionar
    await carregarUltimoId()

    // Limpar os campos, exceto o ID sugerido
    novaEmbalagem.idProduto = ''
    novaEmbalagem.quantidade = ''
  } catch (error) {
    console.error(error)
    alert('Erro ao tentar adicionar a embalagem.')
  }
}

async function eliminarEmbalagem(embalagemId) {
  if (confirm(`Tem certeza de que deseja eliminar a embalagem ${embalagemId}?`)) {
    try {
      const response = await fetch(`${api}/embalagens/${embalagemId}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
      })

      if (!response.ok) {
        throw new Error('Erro ao eliminar embalagem.')
      }

      alert('Embalagem eliminada com sucesso!')
      await refresh() // Atualiza a lista de embalagens
    } catch (error) {
      console.error(error)
      alert('Erro ao tentar eliminar a embalagem.')
    }
  }
}


// Carregar o último ID na montagem do componente
onMounted(async () => {
  await carregarUltimoId()
})
</script>

<style scoped>
/* Estilos permanecem inalterados */
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

span {
  color: red;
  padding-left: 5px;
}

button.eliminar-button {
  background-color: #f44336;
  color: white;
}

button.eliminar-button:hover {
  background-color: #e53935;
}

button:not(.eliminar-button) {
  background-color: #007BFF;
  color: white;
}

button:not(.eliminar-button):hover {
  background-color: #0056b3;
}

form {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  max-width: 1000px;
}
form div {
  margin-bottom: 10px;
}
form label {
  margin-bottom: 5px;
  font-weight: bold;
}
form input {
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ccc;
}
form button {
  background-color: #007BFF;
  color: white;
  padding: 10px 15px;
  cursor: pointer;
}
form button:hover {
  background-color: #0056b3;
}
</style>
