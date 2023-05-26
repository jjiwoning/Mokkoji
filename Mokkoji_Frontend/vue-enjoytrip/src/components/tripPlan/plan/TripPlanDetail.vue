<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-pink">
      <i class="bi bi-info-circle"></i> 계획 정보
    </h3>
    <b-col class="text-right">
        <b-button variant="outline-danger" @click="deletePlan" class="mr-2"
          >계획 삭제하기</b-button
        >
        <b-button variant="outline-primary" @click="moveToUpdate"
          >계획 수정하기</b-button
        >
     </b-col>
    <b-row class="mb-3">
      <b-col style="text-align: left"></b-col>
    </b-row>
    <b-row>
      <b-table-simple hover responsive>
        <tbody>
          <b-tr>
            <b-th>여행 이름</b-th>
            <b-td>{{ tripPlan.planName }}</b-td>
          </b-tr>
          <b-tr>
            <b-th>여행 내용</b-th>
            <b-td>{{ tripPlan.planContent }}</b-td>
          </b-tr>
          <b-tr>
            <b-th>시작일</b-th>
            <b-td>{{ tripPlan.startDate }}</b-td>
          </b-tr>
          <b-tr>
            <b-th>종료일</b-th>
            <b-td>{{ tripPlan.endDate }}</b-td>
          </b-tr>
        </tbody>
      </b-table-simple>                       
    </b-row>
   <b-col class="text-right">
        <b-button variant="outline-primary mb-3" @click="moveToAddPlanAttraction"
          >세부 계획 수정하기</b-button
        >
     </b-col>
    <plan-attraction-list></plan-attraction-list>

    
  </b-container>
</template>


<script>
import api from '@/util/http-common.js'
import PlanAttractionList from './PlanAttractionList.vue'

export default {
  components: { PlanAttractionList, },
  name: "BoardList",

  data() {
    return {
      tripPlan:{},
      planAttractions:{}
      
    };
  },
  created() {
    
    api.get(`/enjoytrip/TripTeam/${this.tripTeamId}/${this.tripPlanId}`)
      .then(({data})=>{
          this.tripPlan=data
          this.planAttractions=this.tripPlan.planAttractions
          this.$store.dispatch('updatePlanAttraction', this.planAttractions);
      });
  },
  computed:{
    tripTeamId(){
      return this.$store.getters.getTripTeamId;
    },
    tripPlanId(){
      return this.$store.getters.getTripPlanId;
    },
    planAttraction(){
      return this.$store.getters.getPlanAttraction;
    }
   },

methods: {
    deletePlan() {
      api.delete(`/enjoytrip/TripTeam/${this.tripTeamId}/${this.tripPlanId}`).then(() => {
        alert('계획이 삭제되었습니다.')
        this.$router.push({ name: "TripTeamdetail", params: {tripTeamId: this.tripTeamId} },
        );
      });
    },
    moveToUpdate(){
      this.$router.push(
    '/tripPlan/tripPlanModify');
    },
     
  moveToAddPlanAttraction(){
    this.$store.dispatch('updatePlanAttraction', this.planAttraction);
    this.$router.push(
    '/tripPlan/addPlanAttraction');
    },
  },
}
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}

 .underline-pink {
    display: inline-block;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, pink 30%);
  }
</style>
