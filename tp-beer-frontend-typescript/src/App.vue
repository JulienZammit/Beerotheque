<template>
  <v-app>
    <v-main>
      <HeaderApp></HeaderApp>
      <router-view/>
      <FooterApp></FooterApp>
    </v-main>
  </v-app>
</template>

<script lang="ts">
import {defineComponent} from 'vue'
import FooterApp from "@/components/FooterApp.vue";
import HeaderApp from "@/components/HeaderApp.vue";

require('./assets/style/BeerStyle.css');

export default defineComponent({
  name: 'App',
  components: {HeaderApp, FooterApp}
})
</script>
