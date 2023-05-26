import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const attractionStore=new Vuex.Store({
  state: {
    planAttraction: null
  },
  mutations: {
    setPlanAttraction(state, planAttraction) {
      state.planAttraction = planAttraction;
    }
  },
  actions: {
    updatePlanAttraction({ commit }, planAttraction) {
      commit('setPlanAttraction', planAttraction);
    }
  },
  getters: {
    getPlanAttraction(state) {
      console.log("getters")
      return state.planAttraction;
    }
  }
});

export default attractionStore;