import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import hotel from './modules/hotel'
import hotelManager from './modules/hotelManager'
import frontDesk from "./modules/frontDesk";
import admin from './modules/admin'
import getters from './getters'
Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    hotel,
    user,
    hotelManager,
    admin,
    frontDesk,
  },
  state: {
  },
  mutations: {
  },
  actions: {
  },
  getters
})
