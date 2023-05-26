<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert show><h3>글작성</h3></b-alert>
        </b-col>
      </b-row>
      <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form>
      
  
          <b-form-group
            id="subject-group"
            label="제목:"
            label-for="title"
            description="제목을 입력하세요."
            
          >
            <b-form-input
              id="subject"
              v-model="article.title"
              type="text"
              required
              placeholder="제목 입력..." 
            ></b-form-input>
          </b-form-group>
  
          <b-form-group id="content-group" label="내용:" label-for="content">
            <b-form-textarea
              id="content"
              v-model="article.content"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>
  
          <b-button variant="primary" class="m-1" @click="registArticle">글 작성</b-button>
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
        article: {
          title: "",
          content: "",  
        },
    }
  },
   computed:{
        tripTeamId(){
            return this.$store.getters.getTripTeamId;
        }
   },   
    methods:{
      registArticle() {
    
        api.post(`/enjoytrip/TripTeam/${this.tripTeamId}/boards/write`, this.article)
          .then(() => {
            alert('게시글을 작성하였습니다.');
            this.$router.push("teamBoard");
          })
          .catch(error => {
            alert(error);
          });
      },
      authenticate() {
        const accessToken = localStorage.getItem('access_token');
        if (!accessToken) {
          console.log('토큰이 없다.');
        }
      }
    }
  };
  </script>
  
  
  
  
  <style></style>
  