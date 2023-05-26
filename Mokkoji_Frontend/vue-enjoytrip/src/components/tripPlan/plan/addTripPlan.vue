<template>
    <b-container class="bv-example-row mt-3 text-center">
          <h3>여행 계획 추가 페이지</h3>
          <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form>
    

        <b-form-group
          id="subject-group"
          label="여행 계획 이름:"
          label-for="planName"
          description="여행 계획 이름을 입력하세요."
          
        >
          <b-form-input
            id="subject"
            v-model="planName"
            type="text"
            required
            placeholder="여행 계획 이름" 
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="여행 계획 내용:"
          label-for="planContent"
          description="여행 계획 내용을 입력하세요."
          
        >
          <b-form-input
            id="subject"
            v-model="planContent"
            type="text"
            required
            placeholder="여행 계획 내용" 
          ></b-form-input>
        </b-form-group>

        <label for="startDate">출발 날짜:</label>
      <b-form-datepicker v-model="startDate" id="startDate" placeholder="시작일" required></b-form-datepicker>

      <label for="endDate">종료 날짜:</label>
      <b-form-datepicker v-model="endDate" id="endDate" placeholder="종료일" required></b-form-datepicker>

        <b-button variant="primary" class="m-1" @click="registArticle">계획 생성</b-button>
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
    </b-container>
  </template>

  <script>
import api from '@/util/http-common.js';

  export default {
    name: "addTripPlanVue",
    components: {
  },

  data() {
    return {
      planName: "",
      planContent: "",
      startDate: null,
      endDate: null,
    };
  },
  computed:{
    tripTeamId(){
      return this.$store.getters.getTripTeamId;
    },
     tripPlanId(){
      return this.$store.getters.getTripPlanId;
    }
  },
    methods: {

    registArticle() {
      if (this.startDate && this.endDate) {
        const startDateTime = this.startDate;
        const endDateTime = this.endDate;

        api
          .post(`/enjoytrip/TripTeam/${this.tripTeamId}/addTripPlan`, {
            planName: this.planName,
            planContent: this.planContent,
            startDate: startDateTime,
            endDate: endDateTime,
          })
          .then(() => {
            alert("계획 생성이 완료되었습니다.");
            this.$router.push("TripTeamdetail");
          })
          .catch((error) => {
            alert(error);
          });
      } else {
        alert("출발 날짜와 종료 날짜를 선택해주세요.");
      }
  },
    authentificate(){
      const accessToken=localStorage.getItem('access_token')
      if(!accessToken){
          console.log('토큰이 없다. ')
      }
    }
  }
  };
  </script>
  <style scoped>
  .underline-green {
    display: inline-block;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(27, 231, 75, 0.3) 30%);
  }
  </style>