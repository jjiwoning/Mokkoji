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
            label="팀 이름:"
            label-for="teamName"
            description="팀 명을 입력하세요."
            
          >
            <b-form-input
              id="teamName"
              v-model="article.teamName"
              type="text"
              required
            ></b-form-input>
          </b-form-group>

          <b-button variant="primary" class="m-1" @click="modifyTeam">팀 정보 수정</b-button
          >
          <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-col>
    </b-row>
    </b-container>
    
  </template>
  
  <script>
  import api from '@/util/http-common.js'
  
  export default {
    name: "TripTeamModify",
    data(){
      return {
        article: { 
      },
    }
  }, 
  created() {
    
        api.get(`/enjoytrip/TripTeam/${this.tripTeamId}`).then(({ data }) => {
          this.article = data;
        });
    },
 
    methods: {
      modifyTeam() {
          api.patch(`/enjoytrip/TripTeam/${this.tripTeamId}`, { teamName: this.article.teamName })
        .then(() => {
            alert('정보 수정이 완료되었습니다.')
          });

      },
    },
    computed:{
      tripTeamId(){
        return this.$store.getters.getTripTeamId;
      }
    },
  
  };
  </script>
  
  <style></style>
  