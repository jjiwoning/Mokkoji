<template>
  <div>
    <div></div>
   <h3 class="underline-pink">
    <i class="bi bi-card-checklist"></i> 여행계획 목록
    </h3>
    <div></div>
     <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveAddTripPlan"
          >계획 추가하기</b-button>
     </b-col>

    <b-card-group deck>
      <div v-for="tripPlan in tripPlans" :key="tripPlan.tripPlanId">
          <b-card @click="moveToPlanDetail(tripPlan.tripPlanId)"
            :title="tripPlan.planName"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top
          >
            <b-card-text>{{ tripPlan.planContent }}</b-card-text>
            <b-card-text>시작일: {{ tripPlan.startDate }}</b-card-text>
            <b-card-text>종료일: {{ tripPlan.endDate }}</b-card-text>
          </b-card>
      </div>
    </b-card-group>
  </div>
</template>


<script>
// import tripTeamapi from '@/util/http-common.js'
import api from '@/util/http-common.js'
export default {
  name: "TripTeamList",
  data(){
      return{
          tripPlans: [],

      }
  },
    created() {

    api.get(`/enjoytrip/TripTeam/${this.tripTeamId}/plans`)
    .then(({data})=>{
            this.tripPlans = data;
         });
  },

  computed:{
    tripTeamId(){
      return this.$store.getters.getTripTeamId;
    }
   },
  methods:{
    // moveToDetail(tripPlanId){
    //   this.$emit('moveToDetail', { tripPlanId: tripPlanId, tripTeamId: this.tripTeamId });
    //   },
    moveToPlanDetail(tripPlanId) { 
      this.$store.dispatch('updateTripPlanId', tripPlanId);
      this.$router.push( 'tripPlanDetail')
          
          
  },
  moveAddTripPlan(){
    this.$router.push( 'addPlan')
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

 .underline-pink {
    display: inline-block;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, pink 30%);
  }
</style>