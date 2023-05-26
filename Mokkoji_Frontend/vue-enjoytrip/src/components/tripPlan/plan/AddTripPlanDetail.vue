<template>
    <!-- ======= Services Section ======= -->
    <main class="container">
		<section class="container d-flex flex-column gap-3">
				<div class="input-group flex-nowrap">
					<select id="search-area" name="sidoCode" v-model="sidoCode" class="form-select me-2">
						<option value="0" disabled>시/도</option>
						<option value="1">서울</option>
						<option value="2">인천</option>
						<option value="3">대전</option>
						<option value="4">대구</option>
						<option value="5">광주</option>
						<option value="6">부산</option>
						<option value="7">울산</option>
						<option value="8">세종특별자치시</option>
						<option value="31">경기도</option>
						<option value="32">강원도</option>
						<option value="33">충청북도</option>
						<option value="34">충청남도</option>
						<option value="35">경상북도</option>
						<option value="36">경상남도</option>
						<option value="37">전라북도</option>
						<option value="38">전라남도</option>
						<option value="39">제주도</option>
					</select>
					<select id="search-region" name="gugunCode" v-model="gugunCode" class="form-select me-2">
						<option disabled value="">구/군</option>
						<option v-for="gugun in gugunList" :value="gugun" :key="gugun">{{ gugun.gugunName }}</option>
					</select>
					
					<input id="search-keyword" name="title" v-model="title" type="search"
						class="form-control" placeholder="검색어" aria-label="Search" value="서울" />
					<input id="btn-search" @click="search" class="input-group-text"
						value="🔍︎" >
				</div>
	

<template class="component">
  <div class="container" style="display: flex;">
    <div class="search-box" style="flex: 1;">
      <!-- search-box 내용 -->
      <div class="search-results">
        <div class="result-item" v-for="attraction in attractions" :key="attraction.contentId" v-bind="attraction">
          <div class="title">
            {{ attraction.firstImg }} {{ attraction.title }}
          </div>
          <div class="button">
            <b-button @click="addTempoPlanAttraction(attraction)">추가</b-button>
          </div>
        </div>
      </div>
    </div>
    <!--카카오 지도-->
    <div class="kakao-map" style="flex: 1;">
      <kakao-map-2></kakao-map-2>
    </div>

    <!--임시 계획리스트-->
    <div  class="search-results" style="flex: 1; display: flex; flex-direction: column;">
  <div v-if="tempoPlanAttraction.length > 0" class="result-items">
    <div class="result-item" v-for="(attraction, index) in tempoPlanAttraction" :key="attraction.contentId" v-bind="attraction">
      <div class="title">
        {{ attraction.title }}
      </div>
      <div class="button">
        <b-button @click="deleteTempoPlanAttraction(index)">삭제</b-button>
      </div>

    </div>
  </div>
  <div class="buttons">
    <b-button @click="savePlan">확인</b-button>
  </div>
</div>

</div>
</template>
<!--수정,삭제 모드-->
 <b-button @click="edit()">{{ isEditMode ? '수정 완료' : '수정하기' }}</b-button>
<!--최종적인 여행 계획 리스트-->
  <PlanAttractionList></PlanAttractionList>
	</section>
    </main>
    
</template>

<script>

//import { mapGetters } from "vuex"
import api  from '@/util/http-common.js'
import PlanAttractionList from '@/components/tripPlan/plan/PlanAttractionList.vue'
import KakaoMap2 from '@/components/attraction/kakaoMap2.vue';
export default {
    name: "addTripPlanDetail",
    data() {
        return {
            title: "",
            sidoCode: 0,
            selectedSido: "",
            gugunCode: "",
            contentTypeId: "",
            gugunList: [],
            tripPlan: [],
            attractions: [],
            tempoPlanAttraction:[],
            planAttractions:[],
            isEditMode:false,
        };
    },
  // computed:{
  //     ...mapGetters(["tripTeamId","tripPlanId"])
  // },
  computed:{
    tripTeamId(){
       return this.$store.getters.getTripTeamId;
    },
     tripPlanId(){
      return this.$store.getters.getTripPlanId;
    },
  },

    watch: {
        sidoCode() {
            api.get("/enjoytrip/attraction/gugun-list", { params: { sidoCode: this.sidoCode } })
                .then(({ data }) => {
                this.gugunList = data;
                console.log(this.gugunList);
            });
        }
    },
    methods: {
        search() {
            if (this.title == "") {
                alert("검색 조건을 입력해주세요");
            }
            else {
                api.get("/enjoytrip/attraction/search", { params: {
                        title: this.title,
                        contentTypeId: this.contentTypeId,
                        sidoCode: this.sidoCode,
                        gugunCode: this.gugunCode.gugunCode,
                        page: this.page,
                        size: this.size
                    } })
                    .then(({ data }) => {
                    this.attractions = data;
                    if (this.attractions.length > 0)
                        this.$emit("search", this.attractions);
                    else
                        alert("검색 결과가 없습니다");
                })
                    .catch(() => console.log("관광지 목록 조회에 실패하였습니다."));
            }
        },
         edit(){
          if(this.isEditMode)
            this.$store.dispatch('updateIsEditMode',false);
           else
           this.$store.dispatch('updateIsEditMode',true);
      },
      
        addTempoPlanAttraction(attraction){
        
          // api.post(`/enjoytrip/TripTeam/${this.tripTeamId}/${this.tripPlanId}/addAttraction`,{
          //   attractionInfo:attraction
          // })
          // .then(()=>{
          //   alert('추가가 완료되었습니다.')
          //   })
            

        // 추가되지 않은 관광지인 경우 여행 계획 목록에 추가
           this.tempoPlanAttraction.push(attraction)

        },
        deleteTempoPlanAttraction(index){
          this.tempoPlanAttraction.splice(index,1)
        },
        savePlan(){
          console.log(this.tempoPlanAttraction)
          // const attractionInfo = this.planAttractions.map(attraction => {
          //   return {
          //       //  addr1:attraction.addr1,
          //       //  addr2:attraction.addr2,
          //        contentId:attraction.contentId,
          //       //  contentTypeId: attraction.contentTypeId,
          //       //  firstImage: attraction.firstImage,
          //       //  firstImage2:attraction.firstImage2,
          //       //  gugunName:attraction.gugunName,
          //       //  latitude:attraction.latitude,
          //       //  longitude: attraction.longitude,
          //       //  sidoName:attraction.sidoName,
          //       //  title:attraction.title,
          //       //  zipcode:attraction.zipcode,


          //    };
        

            api.post(`/enjoytrip/TripTeam/${this.tripTeamId}/${this.tripPlanId}/addAttraction`,
                        this.tempoPlanAttraction
            )
                  .then(()=>{
                    this.loadPlanAttraction()
                    alert('추가가 완료되었습니다.')
                   })
        },
        loadPlanAttraction(){
        console.log("load")
          api.get(`/enjoytrip/TripTeam/${this.tripTeamId}/${this.tripPlanId}`)
          .then(({data})=>{
          this.tripPlan=data
          this.planAttractions=this.tripPlan.planAttractions
          this.$store.dispatch('updatePlanAttraction', this.planAttractions);
      });
        },
    },
    components: { PlanAttractionList, KakaoMap2 },

};


</script>

<style scoped>
.icon-selected {
  border: 3px solid orange;
}
</style>



<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  /* 템플릿의 가로 크기 조정 */
}
.search-box {
  width: 400px;
  height: 500px;
  border: 1px solid #ccc;
  overflow-y: auto;
}

.component2 {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%
}

.search-results {
  padding: 10px;
}

.result-item {
  margin-bottom: 10px;
  border: 1px solid #ccc;
  padding: 5px;
}

.title{
  display: inline-flex;
}
.button{
  display: inline-flex;
  margin-left: 10px;
}

main.container {
margin-top: 0;
    padding-top: 0;
  }

</style>