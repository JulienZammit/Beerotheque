<template>
  <div class="container2">
    <h1 class="text-center">Form Beer</h1>
    <div v-if="errors.length">
      Nombre d'erreur : {{ errors.length }}<br>
      <b>Please correct the following error(s):</b>
      <p v-for="error in errors" v-bind:key="error" v-bind:error="error">{{ error }}</p>
    </div>
    <form class="box" @submit.prevent="saveBeer" method="Post">
      <div>
        <label>Beer Name : </label>
        <input type="text" v-model="beer.name" required minlength="2" maxlength="45"/>
      </div>
      <div>
        <label>Kind of beer : </label>
        <input type="text" v-model="beer.kindOfBeer" required minlength="2" maxlength="10"/>
      </div>
      <div>
        <label>Alcohol degrees : </label>
        <input type="number" v-model="beer.alcoholDegrees" required minlength="1" min="0"/>
      </div>
      <div>
        <label>Owner Name : </label>
        <input type="text" v-model="beer.owner" required minlength="1" maxlength="45"/>
      </div>
      <div>
        <label>Remark : </label>
        <input type="text" v-model="beer.remark" required minlength="1" maxlength="100"/>
      </div>
      <div>
        <label>Prix (en centime) :</label>
        <input type="number" v-model="beer.priceExcludingTaxCents" required minlength="1" min="0"/><br>
        Prix TTC = {{ priceExcludingTaxes(beer.priceExcludingTaxCents) }}€
        <br><br>
        <button style="width: 300px" type="submit">Save</button>
      </div>
    </form>
  </div>
</template>

<script>
import {onUnmounted} from "vue";
import {useStore} from 'vuex';
import BeerService from "@/services/BeerService";
import {KIND_OF_BEER} from "@/utils/constants";


export default {
  setup() {
    const store = useStore();
    onUnmounted(() => {
      store.commit('RESET_BEER');
    })
  },
  name: "BeerForm",
  data() {
    return {
      beers: [],
      errors: [],
      flag: false
    }
  },
  computed: {
    beer: {
      get() {
        return this.$store.getters.doneBeer ? this.$store.getters.doneBeer : {};
      }
    }
  },
  methods: {
    priceExcludingTaxes(beerPrice) {
      return beerPrice ? Math.round(beerPrice + (beerPrice * 20 / 100)) / 100 : 0;
    },
    saveBeer() {
      this.errors = [];
      for (const property in KIND_OF_BEER) {
        if(this.beer.kindOfBeer === KIND_OF_BEER[property]) this.flag = true;
      }
      if (this.flag === true) {
        BeerService.saveBeer(this.beer).then((response) => {
          this.beers = response.data.content;
          this.$router.push('/');
        });
      } else {
        this.errors.push("Invalid kind of beer (DARK, BLONDE, IPA, BROWN)");
      }
    }
  }
}
</script>

<style scoped>
.box {
  justify-content: center;
  display: flex;
  flex-direction: column;
}

label {
  display: inline-block; /* La balise devient de type block. */
  width: 150px;
}
</style>