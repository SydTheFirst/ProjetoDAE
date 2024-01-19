<template>
  <div>
    <NuxtWelcome />
  </div>
</template>
<script setup>
const config = useRuntimeConfig();
const api = config.public.API_URL;
const { data: students, error, refresh } = await useFetch(`${api}/students`)
</script>