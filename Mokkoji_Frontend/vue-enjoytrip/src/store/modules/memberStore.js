import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const memberStore= new Vuex.Store({
    state:{
        isLoggedIn:false,
        userId:null,
    }, 
    mutations:{
        login(state,{loginId}){
            state.isLoggedIn=true;
            state.userId={loginId};
        },
        logout(state){
            state.isLoggedIn=false;
            state.user=null;
        },
    },
    actions:{
        login({commit}, {loginId}){
            return new Promise((resolve) => {
                // Perform asynchronous login operation here
                // For example, make an API request
                // Upon successful login, resolve the promise
                // Upon login failure, reject the promise with an error message
        
                // Simulating a successful login for demonstration
                setTimeout(() => {
                  commit('login', { loginId });
                  resolve();
                }, 1000);
              });
        },
        logout({commit}){
            commit('logout');
        },
    },
});

export default memberStore;

