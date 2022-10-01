<template>
  <div class="form">
    <BeerForm/>
  </div>
</template>

<script lang="ts" setup>
import {createApp} from 'vue';
import BeerForm from '@/components/BeerForm.vue';

const app = createApp({})

app.component(
    'FormView',
    {
      BeerForm
    }
)
</script>