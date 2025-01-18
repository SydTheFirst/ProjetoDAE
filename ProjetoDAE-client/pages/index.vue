<template>
  <div class="dashboard">
    <h1>Dashboard</h1>
    <button @click="goToLogin">Entrar</button>
  </div>
</template>

<script setup>
import { navigateTo } from '#app';

function goToLogin() {
  navigateTo('/login');
}
</script>

<style scoped>
.dashboard {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #f5f5f5;
  text-align: center;
}

h1 {
  font-size: 3rem;
  color: #333;
  margin-bottom: 2rem;
}

button {
  padding: 1rem 2rem;
  font-size: 1.5rem;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 0.5rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #0056b3;
}
</style>
