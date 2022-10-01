import {createRouter, createWebHistory} from 'vue-router'
import Beer from "@/components/Beer";
import BeerForm from "@/components/BeerForm";
import store from '../store';

function test() {
    console.log('store', store.state.beer);
}

test();
const routes = [
    {
        path: '/',
        name: 'home',
        component: Beer
    },
    {
        path: '/formBeer',
        name: 'BeerForm',
        component: BeerForm
    }
]
const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
