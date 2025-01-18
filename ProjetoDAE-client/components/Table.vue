<template>
  <div class="overflow-x-auto bg-white shadow-md rounded-lg">
    <table class="w-full text-sm text-gray-800 bg-blue-50 border-collapse">
      <!-- Cabeçalho -->
      <thead class="bg-blue-200 text-gray-800">
      <tr>
        <th
            v-for="(header, index) in headers"
            :key="index"
            class="px-4 py-3 text-left font-semibold border-b"
        >
          {{ header }}
        </th>
      </tr>
      </thead>

      <!-- Corpo -->
      <tbody>
      <tr
          v-for="(row, rowIndex) in rows"
          :key="rowIndex"
          class="hover:bg-blue-100"
      >
        <td
            v-for="(value, colIndex) in row"
            :key="colIndex"
            class="px-4 py-3 border-b"
        >
          <slot :name="'col-' + colIndex" :value="value">
            {{ value }}
          </slot>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
defineProps({
  headers: {
    type: Array,
    required: true,
  },
  rows: {
    type: Array,
    required: true,
  },
});
</script>

<style scoped>
table {
  border-spacing: 0;
}
th:first-child,
td:first-child {
  border-left: none;
}
th:last-child,
td:last-child {
  border-right: none;
}
</style>
