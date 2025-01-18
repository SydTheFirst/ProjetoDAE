<template>
  <div class="login-container">
    <h1>Login</h1>
    <div class="form-group">
      <label for="username">Username:</label>
      <input id="username" v-model="loginFormData.username" type="text" placeholder="Digite seu username" />
    </div>
    <div class="form-group">
      <label for="password">Password:</label>
      <input id="password" v-model="loginFormData.password" type="password" placeholder="Digite sua senha" />
    </div>
    <button @click="login">LOGIN</button>
    <button @click="reset" class="reset-btn">RESET TOKEN</button>
  </div>
</template>

<script setup>

import { useAuthStore } from '~/store/authStore'
import { navigateTo } from '#app'

const authStore = useAuthStore()
const {token, user} = storeToRefs(authStore)


const config = useRuntimeConfig()
const api = config.public.API_URL

const loginFormData = reactive({
    username: "",
    password: ""
})

const messages = ref([])

function reset() {
    token.value = null
    messages.value = []
}

async function login() {
  reset()
    try {
        await $fetch(`${api}/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json'
            },
            body: loginFormData,
            onResponse({ request, response, options }) {
                messages.value.push({
                    method: options.method,
                    request: request,
                    status: response.status,
                    statusText: response.statusText,
                    payload: response._data
                })
                if (response.status == 200) {
                    token.value = response._data
                    getUserInfo()    
                    //console.log(authStore.user.tipouser)
                    
                        if(authStore.user.tipouser == "Admin"){
                            navigateTo('/admin');
                        }
                        else if (authStore.user.tipouser == "Cliente"){
                            navigateTo("/client");
                        }
                        else if (authStore.user.tipouser == "Logistica"){
                            navigateTo("/logistic");
                        }
                        else{
                            alert("Tipo de user nao reconhecido")
                        }
                }
            }
        })
    } catch (e) {
        console.error('login request failed: ', e)
    }
}
async function getUserInfo() {
    try {
        await $fetch(`${api}/auth/user`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
                Authorization: `Bearer ${token.value}`
            },
            onResponse({ request, response, options }) {
                messages.value.push({
                    method: options.method,
                    request: request,
                    status: response.status,
                    statusText: response.statusText,
                    payload: response._data
                })
                if (response.status == 200) {
                    user.value = response._data
                }
            }
        })
    } catch (e) {
        console.error('user info request failed: ', e)
    }
}
</script>

<style scoped>
.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #f0f0f0;
}

h1 {
  font-size: 2rem;
  margin-bottom: 1.5rem;
}

.form-group {
  margin-bottom: 1rem;
  width: 100%;
  max-width: 300px;
  text-align: left;
}

label {
  display: block;
  margin-bottom: 0.5rem;
}

input {
  width: 100%;
  padding: 0.5rem;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 0.5rem;
}

button {
  padding: 0.75rem 1.5rem;
  margin: 0.5rem;
  font-size: 1rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  opacity: 0.9;
}

.reset-btn {
  background-color: #6c757d;
  color: #fff;
}

button:not(.reset-btn) {
  background-color: #007bff;
  color: #fff;
}
</style>