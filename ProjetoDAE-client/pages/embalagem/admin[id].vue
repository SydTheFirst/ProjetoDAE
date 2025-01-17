<template>
  <div>
    <h1>Embalagem {{ embalagem?.id || 'Carregando...' }}</h1>
    <table>
      <thead>
      <tr>
        <th>Encomenda</th>
        <th>Produto</th>
        <th>Quantidade</th>
      </tr>
      </thead>
      <tbody>
      <tr v-if="embalagem">
        <td>{{ embalagem.idEncomenda }}</td>
        <td>
          <nuxt-link :to="`/produto/${embalagem.idProduto}`">
            {{ produtoNomes[embalagem.idProduto] || 'Carregando...' }}
          </nuxt-link>
        </td>
        <td>{{ embalagem.quantidade }}</td>
      </tr>
      </tbody>
    </table>

    <p>
      <nuxt-link to="/registo">Criar registo</nuxt-link>
    </p>

    <h2>Sensores</h2>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Tipo</th>
        <th>Valor</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="sensor in sensores" :key="sensor.id">
        <td><nuxt-link :to="`/sensor/${sensor.id}`">{{ sensor.id }}</nuxt-link></td>
        <td>{{ sensor.tipoSensor }}</td>
        <td>{{ registosRecentes[sensor.id] || 'Carregando...' }}</td>
      </tr>
      </tbody>
    </table>

    <!-- Seção para criação de registo -->
    <h2>Criar Registo</h2>
    <form @submit.prevent="criarRegisto">
      <div>
        <label for="sensor">Sensor</label>
        <select v-model="selectedSensor" id="sensor" required>
          <option value="" disabled selected>Selecione um sensor</option>
          <option v-for="sensor in sensores" :key="sensor.id" :value="sensor.id">
            {{ sensor.id }}
          </option>
        </select>
      </div>
      <div>
        <label for="valor">Valor</label>
        <input v-model="valor" type="text" id="valor" required />
      </div>
      <button type="submit">OK</button>
    </form>
  </div>
</template>

<script setup>
import { useRuntimeConfig } from 'nuxt/app'
import { useFetch } from '#app'
import { reactive, ref, onMounted } from 'vue'

const route = useRoute()
const id = route.params.id

const config = useRuntimeConfig()
const api = config.public.API_URL

// Dados reativos
const { data: embalagem } = await useFetch(`${api}/embalagens/${id}`)
const { data: sensores } = await useFetch(`${api}/sensors/embalagem/${id}`)

const produtoNomes = reactive({})
const registosRecentes = reactive({}) // Armazena os registos mais recentes para cada sensor

// Variáveis para o formulário
const selectedSensor = ref("")
const valor = ref("")

// Função para buscar detalhes do produto
async function fetchProduto(idProduto) {
  if (!produtoNomes[idProduto]) {
    const { data: produto } = await useFetch(`${api}/produtos/${idProduto}`)
    if (produto.value) {
      produtoNomes[idProduto] = produto.value.nome
    }
  }
  return produtoNomes[idProduto]
}

async function fetchRegistoMaisRecente(idSensor) {
  if (!registosRecentes[idSensor]) {
    const { data: registo } = await useFetch(`${api}/registos/sensor/${idSensor}/mostRecent`)
    if (registo.value) {
      registosRecentes[idSensor] = registo.value.valor // Armazena o valor do registo
    }
  }
  return registosRecentes[idSensor]
}

onMounted(async () => {
  if (embalagem.value) {
    await fetchProduto(embalagem.value.idProduto)
  }

  if (sensores.value) {
    await Promise.all(
        sensores.value.map(sensor => fetchRegistoMaisRecente(sensor.id))
    )
  }
})

// Função para criar o registo
async function criarRegisto() {
  if (!selectedSensor.value || !valor.value) {
    alert('Por favor, preencha todos os campos.')
    return
  }

  const timeStamp = new Date().toISOString() // Obtém o timestamp atual no formato ISO
  const registoData = {
    timeStamp,
    valor: valor.value,
    idSensor: selectedSensor.value
  }

  // Enviar dados para a API
  try {
    const res = await fetch(`http://localhost:8080/academics/api/registos`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(registoData)
    })

    if (res.ok) {
      alert('Registo criado com sucesso!')
      // Limpar o formulário após o envio
      selectedSensor.value = ""
      valor.value = ""
    } else {
      const error = await res.json()
      alert(`Erro ao criar o registo: ${error.message || 'Erro desconhecido.'}`)
    }
  } catch (error) {
    alert('Erro ao conectar com o servidor. Verifique sua conexão e tente novamente.')
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

form {
  margin-top: 20px;
}

form div {
  margin-bottom: 10px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input, select {
  width: 100%;
  padding: 8px;
  font-size: 14px;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}
</style>
