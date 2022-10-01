import {createRouter, createWebHistory, NavigationGuardNext, RouteLocationNormalized, RouteRecordRaw} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import FormView from "@/views/FormView.vue";
import BeerService from "@/services/BeerService";

export const BeerListMetaName = 'beersList';

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'BeerList',
        component: HomeView,
        beforeEnter: beersResolver
    },
    {
        path: '/formBeer',
        name: 'BeerForm',
        component: FormView
    },
    {
        path: '/404',
        name: 'PageNotFound',
        component: () => import('@/views/PageNotFound.vue'),
    },
    {
        path: "/:catchAll(.*)", // Unrecognized path automatically matches 404
        redirect: '/404',
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

function beersResolver(to: RouteLocationNormalized, from: RouteLocationNormalized, next: NavigationGuardNext): void {
    BeerService.getAllBeer(0).then(response => {
        to.meta[BeerListMetaName] = response;
        next();
    });
}

export default router
