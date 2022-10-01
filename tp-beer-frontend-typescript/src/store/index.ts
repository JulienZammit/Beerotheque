import {createStore} from 'vuex'
import createPersistedState from "vuex-persistedstate"
import {Beer} from "@/interfaces/Beer";

export const store = createStore({
    state: {
        beer: {} as Beer
    },
    getters: {
        doneBeer: state => state.beer
    },
    mutations: {
        SET_BEER(state, beer: Beer): void {
            state.beer = beer;
        },
        RESET_BEER(state): void {
            state.beer = {} as Beer;
        }
    },
    plugins: [createPersistedState({
        storage: window.sessionStorage,
        paths: ['beer']
    })]
})
