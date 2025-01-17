<template>
  <div>
    <!-- Cabeçalho de Navegação -->
    <Nav />

    <!-- Conteúdo Principal -->
    <main>
      <slot />
    </main>
  </div>
</template>

<script setup>
import Nav from "@/components/Nav.vue"; // Importando o componente Nav
</script>

<style>
/* Estilos adicionais do layout */
</style>
