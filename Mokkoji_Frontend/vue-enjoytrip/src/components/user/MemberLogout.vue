<template>
  <b-container class="bv-example-row mt-3">
   
    <b-row>
      <b-col><b-alert show variant="danger">로그아웃 중...</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
// import api from '@/util/http-common.js'
import { mapActions } from 'vuex'

export default {
  name: "MemberLogout",
  methods: {
    ...mapActions(["logout"])
  },
  created() {
    console.log("created")
    console.log("logout")
        sessionStorage.removeItem('access-token');
        sessionStorage.removeItem('refresh-token');
        sessionStorage.removeItem('isLoggedIn');
        sessionStorage.removeItem('userId');
        this.logout();
        alert("로그아웃되었습니다.");
        this.$router.push('/');
    // api.post("/enjoytrip/user/logout")
    //   .then(() => {
        
      // })
      // .catch((error)=>{ console.log(error); })
  },
};
</script>

<style></style>

