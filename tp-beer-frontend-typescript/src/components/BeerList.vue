<template>
  <div class="container margeBottom">
    <div class="margeBottom">
      <v-row justify="center" no-gutters>
        <v-table class="tableBeer">
          <thead>
          <th class="text-center">Beer Id</th>
          <th class="text-center">Name</th>
          <th class="text-center">Price</th>
          <th class="text-center">Remark</th>
          <th class="text-center">Alcohol Degrees</th>
          <th class="text-center">Kind Of Beer</th>
          <th class="text-center">Owner</th>
          <th class="text-center">Update</th>
          <th class="text-center">Delete</th>
          </thead>
          <tbody>
          <tr v-for="beer in beerList" :key="beer.id">
            <td>{{ beer.id }}</td>
            <td>{{ beer.name }}</td>
            <td> {{utilsFunction.priceExcludingTaxes(beer.priceExcludingTaxCents) }}€</td>
            <td>{{ beer.remark }}</td>
            <td>{{ beer.alcoholDegrees }}</td>
            <td>{{ beer.kindOfBeer }}</td>
            <td>{{ beer.owner }}</td>
            <td>
              <v-btn @click="$emit('@updateBeer', beer)" type="submit">Update</v-btn>
            </td>
            <td>
              <v-btn @click="$emit('@deleteBeer', beer.id)" type="submit">Delete</v-btn>
            </td>
          </tr>
          </tbody>
        </v-table>
      </v-row>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {Beer} from "@/interfaces/Beer";
import {defineEmits, defineProps, ref} from "vue";
import utils from "@/utils/utils";

const utilsFunction = ref(utils); // function in template

defineProps({
  beerList: Array,
  priceExcludingTaxes: Number
})

defineEmits<{
  (e: "@updateBeer", beer: Beer): void;
  (e: "@deleteBeer", beer: Beer): void;
}>();


</script>