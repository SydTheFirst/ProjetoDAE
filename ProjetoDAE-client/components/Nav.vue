<template>
  <nav class="bg-white shadow-lg p-4 flex items-center justify-between">
    <!-- Logo/Ícone -->
    <div class="flex items-center space-x-2">
      <img src="/logo.png" alt="Logo" class="h-8 w-8" />
      <span class="text-xl font-bold text-gray-800">Minha App</span>
    </div>

    <!-- Botão de Logout -->
    <button
        @click="logout"
        class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
    >
      Logout
    </button>
  </nav>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

function logout() {
  console.log("Usuário deslogado");
  router.push("/"); // Redireciona para a página inicial
}
</script>

<style scoped>
/* Estilização adicional, se necessário */
</style>
