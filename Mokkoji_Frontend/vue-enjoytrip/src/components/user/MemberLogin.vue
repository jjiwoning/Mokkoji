<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="loginId">
              <b-form-input
                id="loginId"
                v-model="user.loginId"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="password">
              <b-form-input
                type="password"
                id="password"
                v-model="user.password"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="confirm"
              >로그인</b-button
            >
            <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="movePage"
              >회원가입</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import RestApi from '@/util/http-common.js'

export default {
  name: "MemberLogin",
  data() {
    return {
      isLoginError: false,
      user: {
        loginId: "",
        password: "",
      },
    };
  },
  methods: {
    confirm() {
      RestApi.post("/enjoytrip/user/login",{
        loginId:this.user.loginId,
        password:this.user.password
      }).then((response)=>{
        //서버로부터 토큰 값을 받아온다. 
        
        const { accessToken, refreshToken} = response.data;
        sessionStorage.setItem('access-token',accessToken);
        sessionStorage.setItem('refresh-token',refreshToken);
        alert('로그인 되었습니다.')
       
      this.$store.dispatch('login', {
        loginId: this.user.loginId
      })
        .then(() => {
          
        })
        .catch(error => {
          console.error(error)
        });
    
        // RestApi.defaults.headers.common['Authorization']=`Bearer ${accessToken}`;
        this.$router.push('/').catch(()=>{});
      
        // console.log(localStorage.getItem('access_token'))
        //console.log(sessionStorage.getItem('refresh-token'))
      })
      .catch(error =>{
        console.error(error)
      });

    },
    //Bearer 스킴: Http Header에 토큰을 포함하여 인증을 수행하는 방식
    authentificate(){ //인증 필요 시 헤더에 accessToken값을 전달 
      const accessToken=localStorage.getItem('access_token');
      
      RestApi.get("",{ 
        headers:{
          Authorization: `Bearer ${accessToken}`
        }
      })
      .then(response=>{
        console.log(response.data)
      })
      .catch(error=>{
        console.log(error);
      })
    },

    movePage() {
      this.$router.push({ name: "SignUp" });
    },
  },
};
</script>

<style></style>
