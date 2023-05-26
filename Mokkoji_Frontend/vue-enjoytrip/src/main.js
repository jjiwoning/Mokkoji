import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "axios";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import memberStore from "./store/modules/memberStore";
import attractionStore from "./store/modules/attractionStore";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

Vue.config.productionTip = false;

axios.interceptors.response.use(function (response) {
  // 특정 헤더 값을 확인
  const headerValue = response.headers['access-token'];

  // 헤더 값이 있는 경우 세션 스토리지에 저장
  if (headerValue) {
    sessionStorage.setItem('access-token', headerValue);
  }

  return response;
}, function (error) {
  return Promise.reject(error);
});

Vue.prototype.$http = axios;

new Vue({
  router,
  store,
  modules: {
    memberStore,
    attractionStore,
  },
  render: (h) => h(App),
}).$mount("#app");