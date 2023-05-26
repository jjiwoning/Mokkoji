<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert show><h3>팀 정보 수정</h3></b-alert>
        </b-col>
      </b-row>
      <b-row class="mb-1">
      <b-col style="text-align: left">
      
  
          <b-form-group
            id="subject-group"
            label="제목:"
            label-for="title"
            description="제목을 입력하세요."
            
          >
            <b-form-input
              id="title"
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

  
          <b-button variant="primary" class="m-1" @click="modifyArticle">글 수정</b-button
          >
          <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-col>
    </b-row>
    </b-container>
    
  </template>
  
  <script>
  import api from '@/util/http-common.js'
  
  export default {
    name: "TeamBoardModify",
    data(){
      return {
        article: { 
        },
    }
  }, 
  created() {
          api.get(`enjoytrip/TripTeam/${this.tripTeamId}/boards/${this.$route.params.boardId}`).then(({ data }) => {
            this.article = data;
          });
      },
    computed:{
      tripTeamId(){
        return this.$store.getters.getTripTeamId;
      },
    },
    methods: {
      modifyArticle() {
        api
          .patch(`/enjoytrip/TripTeam/${this.tripTeamId}/boards/${this.$route.params.boardId}`, this.article)
          .then(() => {
             alert("수정이 완료되었습니다.");
            // 현재 route를 /list로 변경.
            this.$router.push("teamBoard");
          });
      },
  
    }
  
  };
  </script>
  
  <style></style>
  