<template>
  <div class="container">
    <h1 class="text-center art-shadow">Form Beer</h1>
    <v-row class="margeBottom" justify="center" no-gutters>
      <form class="box" @submit.prevent="saveBeer" method="Post">
        <div v-if="errors.length">
          <v-alert border="top" color="red lighten-2" dark>Nombre d'erreur : {{ errors.length }}<br>
            <p v-for="error in errors" v-bind:key="error">{{ error }}</p>
          </v-alert>
        </div>

        <div class="large">
          <label>Beer Name : </label>
          <v-text-field v-model="beer.name" :rules="rules" counter="25" hint="Enter Beer's Name"
                        required minlength="2" maxlength="45"></v-text-field>
        </div>
        <div class="large">
          <label>Kind of beer : </label>
          <v-text-field v-model="beer.kindOfBeer" :rules="rules" counter="25"
                        hint="Enter Kind of Beer (DARK, BLONDE, IPA, BROWN)"
                        required minlength="2" maxlength="10"></v-text-field>
        </div>
        <div class="large">
          <label>Alcohol degrees : </label>
          <v-text-field v-model="beer.alcoholDegrees" hide-details single-line type="number" required minlength="1"
                        min="0"/>
        </div>
        <br>
        <div class="large">
          <label>Owner Name : </label>
          <v-text-field v-model="beer.owner" :rules="rules" counter="25" hint="Enter Owner's name"
                        required minlength="1" maxlength="45"></v-text-field>
        </div>
        <div class="large">
          <label>Remark : </label>
          <v-text-field v-model="beer.remark" :rules="rules" counter="25"
                        hint="Enter a remark in relation with the beer"
                        required minlength="1" maxlength="100"></v-text-field>
        </div>
        <div class="large">
          <label>Prix (en centime) :</label>
          <v-text-field v-model="beer.priceExcludingTaxCents" hide-details single-line type="number" required
                        minlength="1" min="0"/>
          <br>
          Prix TTC = {{ utilsFunction.priceExcludingTaxes(beer.priceExcludingTaxCents) }}€
          <br><br>
          <v-btn class="margeBottom large" type="submit" block>Save</v-btn>
        </div>
      </form>
    </v-row>
  </div>
</template>

<script lang="ts" setup>

import {useStore} from 'vuex';
import {onUnmounted, ref} from "vue";
import BeerService from "@/services/BeerService";
import {Beer} from "@/interfaces/Beer";
import router from "@/router";
import {KIND_OF_BEER} from "@/enums/enumBeers";
import utils from "@/utils/utils";

const utilsFunction = ref(utils); // function in template
const store = useStore();
const errors = ref([] as string[]);
const flag = ref(false);
const beer = ref(store.getters.doneBeer ? store.getters.doneBeer : {} as Beer);

function saveBeer(): void {
  errors.value = [];
  for (const property in KIND_OF_BEER) {
    if (beer.value.kindOfBeer === property.valueOf()) flag.value = true;
  }
  if (flag.value) {
    BeerService.saveBeer(beer.value).then(() => {
      router.push('/');
    }).catch(() => {
      errors.value.push("Error saving beer : invalid request");
    });
  } else {
    errors.value.push("Invalid kind of beer (DARK, BLONDE, IPA, BROWN)");
  }
}

onUnmounted(() => {
  store.commit('RESET_BEER');
});
</script>


