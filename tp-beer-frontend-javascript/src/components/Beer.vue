<template>
  <div class="container">
    <h1 class="text-center">Beer List</h1>
    <nav aria-label="navigation">
      Order by :
      <div class="inputSort"><input @click="getAllBeers('alpha', 'ascending')" type="button" value="Ascending Name">
      </div>
      <div class="inputSort"><input @click="getAllBeers('alpha', 'descending')" type="button" value="Descending Name">
      </div>
      <div class="inputSort"><input @click="getAllBeers('priceTTC', 'ascending')" type="button" value="Ascending Price">
      </div>
      <div class="inputSort"><input @click="getAllBeers('priceTTC', 'descending')" type="button"
                                    value="Descending Price"></div>
    </nav>

    <table class="tableBeer">
      <thead>
        <th>Beer Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Remark</th>
        <th>Alcohol Degrees</th>
        <th>Kind Of Beer</th>
        <th>Owner</th>
        <th>Update</th>
        <th>Delete</th>
      </thead>
      <tbody>
        <tr v-for="beer in beers" :key="beer.id" v-bind:beer="beer">
          <td>{{ beer.id }}</td>
          <td>{{ beer.name }}</td>
          <td>{{ priceExcludingTaxes(beer.priceExcludingTaxCents) }} €</td>
          <td>{{ beer.remark }}</td>
          <td>{{ beer.alcoholDegrees }}</td>
          <td>{{ beer.kindOfBeer }}</td>
          <td>{{ beer.owner }}</td>
          <td>
            <button @click="updateBeer(beer)" type="submit">Update</button>
          </td>
          <td>
            <form @submit.prevent="deleteBeer(beer.id)">
              <button type="submit">Delete</button>
            </form>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <nav aria-label="pagination">
    <input v-if="numberPageIsNotNull" v-on:click="changePageLeft()" type="button" value="«">&nbsp;
    <input @click="changePageRight()" type="button" value="»">
  </nav>
</template>

<script>
import BeerService from "@/services/BeerService";
export default {
  name: 'MyBeer',
  data() {
    return {
      beers: [],
      nameSort: null,
      typeSort: null,
      numberPage: 0
    }
  },
  computed: {
    numberPageIsNotNull() {
      return this.numberPage > 0;
    }
  },
  methods: {
    priceExcludingTaxes(beerPrice){
      return Math.round(beerPrice + (beerPrice * 20 / 100)) / 100;
    },
    getAllBeers(nameSort, typeSort) {
      BeerService.getAllBeers(this.numberPage, nameSort, typeSort).then((response) => {
        this.beers = response.data.content;
        this.typeSort = typeSort;
        this.nameSort = nameSort;
      });
    },
    deleteBeer(id) {
      BeerService.deleteBeer(id).then((response) => {
        this.beers = response.data.content;
        this.getAllBeers();
      });
    },
    updateBeer(beer) {
      this.$store.commit('SET_BEER', beer);
      this.$router.push('/formBeer');
    },
    changePageLeft() {
      if (this.numberPage > 0) {
        let number = this.numberPage;
        BeerService.getAllBeers(--number, this.nameSort, this.typeSort).then((response) => {
          this.beers = response.data.content;
          this.numberPage = this.numberPage - 1;
        }).catch((error) => {console.error(error);});
      }
    },
    changePageRight() {
      BeerService.getAllBeers(this.numberPage + 1, this.nameSort, this.typeSort).then((response) => {
        if (response.data.content.length > 0) {
          this.beers = response.data.content;
          this.numberPage = this.numberPage + 1;
        }
      }).catch((error) => {console.error(error);});
    }
  },
  created() {
    this.getAllBeers();
  }
}
</script>

<style scoped>
.tableBeer {
  display: inline-block;
  border: 1px #2c3e50 solid;
  margin: auto 0;
}

.inputSort {
  display: inline-block;
  margin-left: 10px;
}
</style>