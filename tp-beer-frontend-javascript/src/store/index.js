import {createStore} from 'vuex'
import createPersistedState from "vuex-persistedstate"

export default createStore({
    state: {
        beer: {}
    },
    getters: {
        doneBeer: state => state.beer
    },
    mutations: {
        SET_BEER(state, beer) {
            state.beer = beer;
        },
        RESET_BEER(state) {
            state.beer = {};
        }
    },
    actions: {},
    modules: {},
    plugins : [createPersistedState({
        storage : window.sessionStorage,
        paths: ['beer']
    })]
})
