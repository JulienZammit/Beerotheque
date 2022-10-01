<template>
  <div class="margeBottom">
    <v-row justify="center" no-gutters>
      <nav aria-label="pagination">
        <template v-if="numberPage>0">
          <v-btn @click="$emit('@leftPage')" type="button">«</v-btn>
          |
        </template>
        <v-btn @click="$emit('@rightPage')" type="button">»</v-btn>
      </nav>
    </v-row>
  </div>
</template>

<script setup lang="ts">

import {defineEmits, defineProps} from "vue";

defineProps({
  numberPage: Number
})

defineEmits<{
  (e: "@leftPage"): void;
  (e: "@rightPage"): void;
}>();

</script>

<style scoped>

</style>