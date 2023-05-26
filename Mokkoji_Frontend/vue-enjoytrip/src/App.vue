<template>
  <div id="app">
    <navi-bar />
    <router-view />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
  },
};
</script>

<style>
a:hover {
  text-decoration: none;
  font-weight: bold;
}

a.router-link-exact-active {
  color: #3aa4ca;
}


@import "@/assets/vendor/bootstrap/css/bootstrap.min.css";
@import "@/assets/vendor/bootstrap-icons/bootstrap-icons.css";
@import "@/assets/vendor/boxicons/css/boxicons.min.css";
@import "@/assets/vendor/glightbox/css/glightbox.min.css";
@import "@/assets/vendor/swiper/swiper-bundle.min.css";
@import "@/assets/css/style.css";


</style>