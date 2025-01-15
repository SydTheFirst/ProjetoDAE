<template>

<div>

<h2>Registos</h2>
 <table>
    <thead>
        <tr>
            <th>ID Sensor</th>
            <th>Data</th>
            <th>Valor</th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="registo in registos">
            <td>{{ registo.idSensor }}</td>
            <td>{{ registo.timeStamp }}</td>
            <td>{{ registo.valor }}</td>
        </tr>
    </tbody>
 </table>
</div>

</template>


<script setup>
 const config = useRuntimeConfig()
 const api = config.public.API_URL
 const { data: registos } = await useFetch(`${api}/registos`)
</script>