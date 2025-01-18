<template>
    <h1>Login Form</h1>
    <div>Username: <input v-model="loginFormData.username"></div>
    <div>Password: <input v-model="loginFormData.password"></div>
    <button @click="login">LOGIN</button>
    <button @click="reset">RESET</button>

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
                        console.log(authStore.user.tipouser)
                    
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