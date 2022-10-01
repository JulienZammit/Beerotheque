<template>
  <h1 class="text-center art-shadow margeBottom">Beer List</h1>
  <div class="list">
    <BeerSort @@SortBeer="handleSortBeer"/>
    <BeerList :beerList="beerList" @@updateBeer="handleUpdateEntry" @@deleteBeer="handleDeleteEntry"/>
    <PaginationList :numberPage="numberPage" @@leftPage="handleLeftPage" @@rightPage="handlerightPage"/>
  </div>
</template>

<script lang="ts" setup>
import BeerList from '@/components/BeerList.vue';
import PaginationList from '@/components/PaginationList.vue';
import BeerSort from '@/components/SortList.vue';
import {Beer} from "@/interfaces/Beer";
import {createApp, ref} from 'vue'
import BeerService from "@/services/BeerService";
import router, {BeerListMetaName} from "@/router";
import {store} from "@/store";

const NAME_SORT = ref<string | undefined>(undefined);
const TYPE_SORT = ref<string | undefined>(undefined);
const numberPage = ref<number>(0);
const beerList = ref<Beer[] | Beer>(router.currentRoute.value.meta[BeerListMetaName] as Beer[]);
const errors = ref<string[]>([]);
const app = createApp({})

function getAllBeers(nameSort?: string, typeSort?: string): void {
  NAME_SORT.value = nameSort;
  TYPE_SORT.value = typeSort;
  BeerService.getAllBeer(numberPage.value, nameSort, typeSort).then(response => {
    beerList.value = response;
  });
}

const handleSortBeer = (nameSort: string, typeSort: string) => {
  getAllBeers(nameSort, typeSort);
};

const handleUpdateEntry = (beer: Beer) => {
  store.commit('SET_BEER', beer);
  router.push('/formBeer');
};

const handleDeleteEntry = (id: string) => {
  BeerService.deleteBeer(id).then(() => {
    getAllBeers();
  }).catch(() => {
    errors.value.push("Error deleting beer : invalid request");
  });
};

const handleLeftPage = () => {
  if (numberPage.value > 0) {
    BeerService.getAllBeer(--numberPage.value, NAME_SORT.value, TYPE_SORT.value).then(response => {
      beerList.value = response;
    }).catch(() => {
      numberPage.value++;
      errors.value.push("Error getting next list of beer : invalid request");
    });
  }
};

const handlerightPage = () => {
  let numberFlag = numberPage.value;
  BeerService.getAllBeer(++numberFlag, NAME_SORT.value, TYPE_SORT.value).then(response => {
    if (response.length > 0) {
      beerList.value = response;
      numberPage.value++;
    }
  }).catch(() => {
    errors.value.push("Error getting next list of beer : invalid request");
  });
};

app.component(
    'HomeView',
    {
      BeerList, PaginationList, BeerSort
    }
)
</script>
