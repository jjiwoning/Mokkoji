<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert show><h3>글목록</h3></b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
      </b-row>
    </b-container>
  </template>
  
  <script>
  import api from '@/util/http-common.js'
  
  export default {
    name: "BoardDelete",
    created() {
      
      api.delete(`/enjoytrip/TripTeam/${this.tripTeamId}/boards/${this.$route.params.teamBoardId}`)
      .then(() => { 
        alert("삭제 처리가 완료되었습니다.")
        this.$router.push({ path: "teamBoard" });
        })
        .catch(()=>{
          console.log(sessionStorage.getItem('access-token'))
        })
        
        // 현재 route를 /list로 변경.
      
    },
    computed:{
        tripTeamId(){
            return this.$store.getters.getTripTeamId;
        },
        boardId(){
          return this.$store.getters.getBoardId;
        },
       
    }
  };
  </script>
  
  <style></style>
  