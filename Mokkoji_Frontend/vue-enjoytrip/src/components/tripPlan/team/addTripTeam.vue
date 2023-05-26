<template>
    <b-container class="bv-example-row mt-3 text-center">
        <b-row>
      <b-col>
        <b-alert show><h3>여행 팀 추가</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form>
    

        <b-form-group
          id="subject-group"
          label="팀 이름:"
          label-for="teamName"
          description="팀 이름을 입력하세요."
          
        >
          <b-form-input
            id="subject"
            v-model="teamName"
            type="text"
            required
            placeholder="팀 이름" 
          ></b-form-input>
        </b-form-group>

        <b-button variant="primary" class="m-1" @click="registArticle">팀 생성</b-button>
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
    </b-container>
  </template>

  <script>
 import api from '@/util/http-common.js'
export default {
  
  name: "BoardWrite",
  data(){
    return {
    teamName: "", 
  }
},
 
  methods:{
    registArticle() {
      api
        .post(`/enjoytrip/TripTeam/add`, {
          teamName:this.teamName
        })
        .then(() => {
            alert('팀 생성이 완료되었습니다.')
            this.$router.push("list");
        })
        .catch(error=>{
          alert(error)
        });
    // this.moveList();
  },
    authentificate(){
      const accessToken=localStorage.getItem('access_token')
      if(!accessToken){
          console.log('토큰이 없다. ')
      }
    }
  }
};
  </script>
  <style scoped>
  .underline-green {
    display: inline-block;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(27, 231, 75, 0.3) 30%);
  }
  </style>