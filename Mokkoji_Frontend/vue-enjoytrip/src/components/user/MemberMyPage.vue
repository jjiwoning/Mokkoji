<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>마이페이지</h3></b-alert>
      </b-col>
    </b-row>
    <b-form-group
      label="아이디:"
      label-for="loginId"
      label-cols-sm="3"
      label-align-sm="right"
    >
      <b-form-input id="loginId" v-model="article.loginId"></b-form-input>
    </b-form-group>      
    <b-form-group
      label="비밀번호:"
      label-for="password"
      label-cols-sm="3"
      label-align-sm="right"
    >
      <b-form-input id="password" type="password" v-model="article.password"></b-form-input>
    </b-form-group>
      
    <b-form-group
      label="닉네임:"
      label-for="nickname"
      label-cols-sm="3"
      label-align-sm="right"
    >
      <b-form-input id="nickname" v-model="article.nickname"></b-form-input>
    </b-form-group>

    <b-form-group
      label="이름 :"
      label-for="name"
      label-cols-sm="3"
      label-align-sm="right"
    >
      <b-form-input id="name" v-model="article.name"></b-form-input>
    </b-form-group>

    <b-form-group
      label="이메일 :"
      label-for="mail"
      label-cols-sm="3"
      label-align-sm="right"
    >
      <b-form-input id="mail" v-model="article.mail"></b-form-input>
    </b-form-group>

    <b-form-group
      label="전화번호 :"
      label-for="phoneNumber"
      label-cols-sm="3"
      label-align-sm="right"
    >
      <b-form-input id="phoneNumber" v-model="article.phoneNumber"></b-form-input>
    </b-form-group>
    
  </b-container>
</template>

<script>
 import api from '@/util/http-common.js'
export default {
  name: "MemberMypage",
  data() {
    return {
      article: {},
    };
  },
  created(){
    api.get(`/enjoytrip/user/getUserId`)
    .then(response => {
        const userId = response.data.userId;
        console.log(userId);
        api.get(`/enjoytrip/user/${userId}`)
          .then(response => {
            this.article = response.data;
            console.log(response.data);
          })
          .catch(error => {
            console.error(error);
          });
      })
      .catch(error => {
        console.error(error);
      });
  },
};
</script>

<style></style>
