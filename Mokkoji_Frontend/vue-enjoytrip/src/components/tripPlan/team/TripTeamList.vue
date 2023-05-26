
<template>
    <div>
     <h2> <i class="bi bi-people-fill"></i>나의 여행 팀 보기<i class="bi bi-people-fill"></i></h2>
        <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveToAddTripTeam()"
          >팀 생성하기</b-button
        >
      </b-col>
  <b-card-group deck>
  <div v-for="tripTeam in tripTeams" :key="tripTeam.tripTeamId">
    <b-card :title="tripTeam.teamName " img-src="" img-alt="Image" img-top @click="moveToDetail(tripTeam.tripTeamId)">  
           <img v-if="tripTeam.teamRole === 'LEADER'" src="@/assets/img/icons8-왕관-48.png" alt="리더 아이콘">
            <img v-else src="@/assets/img/icons8-그룹.png" alt="멤버 아이콘">
      <b-card-text>
      </b-card-text>
    </b-card>
    </div>
  </b-card-group>
  <div class="button-container">
    
    <b-button variant="primary" @click="moveToInvitedPage">나에게 온 초대 목록 </b-button>
  </div>
</div>


</template>


<script>
// import tripTeamapi from '@/util/http-common.js'
import api from '@/util/http-common.js'
export default {
  name: "TripTeamList",
  data(){
      return{
        tripTeams:[],
        leaderIconUrl: 'https://img.icons8.com/material-rounded/24/000000/leader.png'
      }
  },
  
  created(){
    api.get("/enjoytrip/TripTeam")
    .then(({data})=>{
            this.tripTeams = data;
            console.log(this.tripTeams);
         });

      api.g
  },
  methods:{
    addPlan(){
      this.$router.push("addPlan")
    },
    moveToDetail(tripTeamId){
      console.log("check"+tripTeamId)
      this.$store.dispatch('updateTripTeamId', tripTeamId);
      this.$router.push({name: "TripTeamdetail"});
      
    },
   moveToInvitedPage(){
    this.$router.push({name:"userInvite"})
    },
   moveToAddTripTeam() { 
    this.$router.push({name:"addTripTeam"})
   }
}
}
</script>

<style scoped>
.button-container {
  margin-top: 20px;
  display: flex;
  align-items: center;
}

.button-gap {
  width: 10px;
}
</style>