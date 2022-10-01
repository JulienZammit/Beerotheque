<template>
  <v-row justify="center" no-gutters>
    <nav class="margeBottom" aria-label="navigation">
      Order by :
      <div class="inputSort">
        <v-btn @click="$emit('@SortBeer', nameSort.ALPHA, typeSort.ASCENDING)" type="button">Ascending Name</v-btn>
      </div>
      <div class="inputSort">
        <v-btn @click="$emit('@SortBeer', nameSort.ALPHA, typeSort.DESCENDING)" type="button">Descending Name</v-btn>
      </div>
      <div class="inputSort">
        <v-btn @click="$emit('@SortBeer', nameSort.PRICE, typeSort.ASCENDING)" type="button">Ascending Price</v-btn>
      </div>
      <div class="inputSort">
        <v-btn @click="$emit('@SortBeer', nameSort.PRICE, typeSort.DESCENDING)" type="button">Descending Price</v-btn>
      </div>
    </nav>
  </v-row>
</template>

<script lang="ts" setup>
import {nameSort, typeSort} from "@/enums/enumBeers"; // Ne pas supprimer (utiliser dans template)
import {defineEmits} from "vue";

defineEmits<{
  (e: "@SortBeer", nameSort: string, typeSort: string): void;
}>();

</script>

<style scoped>

</style>