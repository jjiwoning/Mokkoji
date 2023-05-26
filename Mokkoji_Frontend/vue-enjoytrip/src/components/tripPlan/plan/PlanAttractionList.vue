<template>
  <b-row>
    <h3>
      <i class="bi bi-info-circle"></i> 세부 계획 정보
    </h3>
    <b-col v-if="planAttraction">
      <b-table-simple hover responsive>
        <b-thead head-variant="light">
          <b-tr>
             <b-th>번호</b-th>
            <b-th>이름</b-th>
            <b-th>주소</b-th>
            <b-th>전화번호</b-th>
            <b-th v-if="isEditMode"></b-th>
          </b-tr>
        </b-thead>
        <b-tbody>
          <b-tr v-for="(attraction,index) in planAttraction" :key=index>
            <b-td>{{ index+1 }}</b-td>
            <b-td>{{ attraction.title }}</b-td>
            <b-td>{{ attraction.addr1 }}</b-td>
            <b-td>{{ attraction.tel }}</b-td>
            <b-td v-if="isEditMode">
              <b-button @click="deletePlanDetail(attraction.planAttractionId)">삭제</b-button>
            </b-td>
          </b-tr>
        </b-tbody>
      </b-table-simple>
    </b-col>
    <b-col v-else>
      <button @click="moveToAddPlanAttraction">세부 여행 계획 추가하기</button>
    </b-col>
  </b-row>
</template>

<script>
 import api from '@/util/http-common.js'
  export default {
    
    name: "PlanAttractionList",
    data(){
        return {
          planAttractions:[],
          tripPlan:[],
        }    
    },
    created(){
       this.$store.dispatch('updateIsEditMode', false);
    },
    methods:{
     
      loadPlanAttraction(){
        console.log("load")
          api.get(`/enjoytrip/TripTeam/${this.tripTeamId}/${this.tripPlanId}`)
          .then(({data})=>{
          this.tripPlan=data
          this.planAttractions=this.tripPlan.planAttractions
          this.$store.dispatch('updatePlanAttraction', this.planAttractions);
          console.log(this.planAttractions)
  
      });
        },
        deletePlanDetail(planAttractionId){
          console.log(planAttractionId)
          api.delete(`/enjoytrip/TripTeam/${this.tripTeamId}/${this.tripPlanId}/${planAttractionId}`)
          .then(()=>{
            this.loadPlanAttraction();
          })
      },
    },

    
   computed:{
    planAttraction(){
      return this.$store.getters.getPlanAttraction;
    },
    isEditMode(){
       return this.$store.getters.getIsEditMode;
    },
    tripTeamId(){
        return this.$store.getters.getTripTeamId;
    },
    tripPlanId(){
      return this.$store.getters.getTripPlanId;
    }
    
   },


}
</script>

<style scoped>
 .underline-pink {
    display: inline-block;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, pink 30%);
  }
</style>
