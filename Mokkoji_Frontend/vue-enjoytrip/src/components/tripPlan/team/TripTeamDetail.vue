<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-green">
     <i class="bi bi-people-fill"></i> 나의 팀 보기
    </h3>
    <b-row class="mb-3">
     <b-col class="text-right">
        <b-button variant="outline-danger" @click="deleteTeam" class="mr-2"
          >팀 해체하기</b-button
        >
         <b-button variant="outline-primary" @click="modifyTeam" 
          >팀 정보 수정하기</b-button>
     </b-col>
     </b-row>
     
    <b-row class="mb-3">
      <b-col>
        <b-card
          :header-html="`<h3>${tripTeam.tripTeamId}.
          ${tripTeam.teamName}</h3><div><h6></div><div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div class="d-flex flex-wrap">
              <div v-for="member in members" :key="member.id" v-bind="member" class="mr-3 mb-3">
                <div>{{ member.nickname }} : {{ member.teamRole }}</div>
              </div>
            </div>
          </b-card-body>
        </b-card>

         <b-row class="mb-2 mt-3">
          <b-col></b-col>
        </b-row>
      </b-col>
      </b-row>

      <b-row class="mb-2">
      <b-col >
        <b-button variant="outline-danger" class="rounded-pill w-50" @click="goToTeamBoard">
           <i class="bi bi-pencil-square"></i> 팀 게시판
        </b-button>
      </b-col>
    </b-row>
      <invite-modal :tripTeamId="this.tripTeam.tripTeamId"></invite-modal>
      <div style="margin: 30px;"></div>
    <trip-plan-list></trip-plan-list>

  </b-container>
</template>


<script>
 import api from '@/util/http-common.js'
import TripPlanList from '../plan/TripPlanList.vue';
import InviteModal from '../../InviteModal.vue';
  export default {
  components: { TripPlanList, InviteModal,   },
    
    name: "TripTeamDetail",
    data(){
        return {
            isModal:false,
            tripTeam: {},
            members:[],
            // tripTeamId:{}
        };
    },
    created(){
      
         api.get(`/enjoytrip/TripTeam/${this.tripTeamId}`)
          .then(({data})=>{
            this.tripTeam = data;
            this.members=this.tripTeam.userTripTeams
            console.log(this.tripTeam)
         });
    },
    computed:{
    tripTeamId(){
      return this.$store.getters.getTripTeamId;
    }
   },
    methods: {
      deleteTeam() {
      api.delete(`/enjoytrip/TripTeam/${this.tripTeamId}`).then(() => {
        alert('팀이 해체되었습니다.')
        this.$router.push({ name: "tripTeamList" });
      });
    },
    modifyTeam(){
      this.$router.push({ name: "modifyTripTeam" });
    },
    goToTeamBoard(){
      this.$router.push({ path: "teamBoard"});
    }

    },
    
  
  }
  </script>


  <style scoped>
  .underline-green {
    display: inline-block;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(27, 231, 75, 0.3) 30%);
  }
  </style>