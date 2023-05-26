<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert show><h3>계획 정보 수정</h3></b-alert>
        </b-col>
      </b-row>
      <b-row class="mb-1">
      <b-col style="text-align: left">
      
  
          <b-form-group
            id="subject-group"
            label="planName:"
            label-for="planName"
            description="계획 명을 입력하세요."
            
          >
            <b-form-input
              id="title"
              v-model="plan.planName"
              type="text"
              required
              placeholder="계획 입력..." 
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="subject-group"
            label="planContent:"
            label-for="planContent"
            description="계획 명을 입력하세요."
            
          >
            <b-form-input
              id="planContent"
              v-model="plan.planContent"
              type="text"
              required
              placeholder="계획 내용 입력..." 
            ></b-form-input>
          </b-form-group>
  
          <label for="startDate">출발 날짜:</label>
        <b-form-datepicker v-model="plan.startDate" id="startDate" placeholder="시작일" required></b-form-datepicker>

         <label for="endDate">종료 날짜:</label>
      <b-form-datepicker v-model="plan.endDate" id="endDate" placeholder="종료일" required></b-form-datepicker>

      <b-button variant="primary" class="m-1 mb-2" @click="modifyPlan">계획 정보수정</b-button
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
       plan:[],
    }
  }, 
  created() {
          api.get(`/enjoytrip/TripTeam/${this.tripTeamId}/${this.tripPlanId}`,{
               

          }).then(({ data }) => {
            this.plan = data;
          });
      },
    computed:{
      tripTeamId(){
        return this.$store.getters.getTripTeamId;
      },

      tripPlanId(){
            return this.$store.getters.getTripPlanId;
        },
    },
    methods: {
        modifyPlan() {
        api
          .patch(`/enjoytrip/TripTeam/${this.tripTeamId}/${this.tripPlanId}`,{
             planName:this.plan.planName,
                planContent:this.plan.planContent,
                startDate:this.plan.startDate,
                endDate:this.plan.endDate,

          })
          .then(() => {
             alert("수정이 완료되었습니다.");
            // 현재 route를 /list로 변경.
            this.$router.push("tripPlanDetail");
          });
      },
  
    },
    
  };
  </script>
  
  <style></style>
  