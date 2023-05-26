<template>
    <b-container class="bv-example-row mt-3 text-center">
      <router-view></router-view>
    </b-container>
  </template>
  <script>
  export default {
    name: "TripPlanView",
  };
  </script>
  <style scoped>
  .underline-green {
    display: inline-block;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(27, 231, 75, 0.3) 30%);
  }
  </style>
  