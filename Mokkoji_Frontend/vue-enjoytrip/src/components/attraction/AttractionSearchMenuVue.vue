<template>
    <!-- ======= Services Section ======= -->
    <main class="container mt-0">
		<h3 class="underline-hotpink"><b-icon icon="camera"></b-icon> 여행지 검색 </h3>
		<section class="container d-flex flex-column gap-3">
			<form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search"
				method="get" v-on:submit="search">
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
				
				<section id="services" class="services">
					<div class="container" style="max-width: 300;">
						<div class="container d-flex justify-content-center">
							<label for="filter1">
							<div class="icon-btn icon-box iconbox-orange" >
								<div class="icon" :class="{ 'icon-selected': contentTypeId === '12' }">
									<i class="bx bx-flag"></i>
								</div>
								<div>관광지</div>
							<input id="filter1" class="filter-input" style="display:none" type="radio" v-model="contentTypeId" value="12"/> 
							</div>
							</label>	
							<label for="filter2">
							<div class="icon-btn icon-box iconbox-pink">
								<div class="icon" :class="{ 'icon-selected': contentTypeId === '14' }">
									<i class="bx bx-mask"></i>
								</div>
								<div>문화시설</div>
								<input id="filter2" style="display:none" class="filter-input"  type="radio"  v-model="contentTypeId" value="14"/>
							</div>
							</label>
							<label for="filter3">
							<div class="icon-btn icon-box iconbox-teal">
								<div class="icon" :class="{ 'icon-selected': contentTypeId === '15' }">
									<i class="bx bx-party"></i>
								</div>
								<div>축제공연행사</div>
								<input id="filter3" class="filter-input" style="display:none" type="radio"  v-model="contentTypeId" value="15"/>
							</div>
							</label>
							<label for="filter4">
								<div class="icon-btn icon-box iconbox-yellow">
									<div class="icon" :class="{ 'icon-selected': contentTypeId === '25' }">
										<i class="bx bx-paper-plane"></i>
									</div>
									<div>여행코스</div>
									<input id="filter4" class="filter-input" style="display:none" type="radio" v-model="contentTypeId" value="25"/>
								</div>
							</label>
							<label for="filter5">
							<div class="icon-btn icon-box iconbox-orange">
								<div class="icon" :class="{ 'icon-selected': contentTypeId === '28' }">
									<i class="bx bx-football"></i>
								</div>
								<div>레포츠</div>
								<input id="filter5" class="filter-input"  style="display:none" type="radio" v-model="contentTypeId" value="28"/>
							</div>
							</label>
							<label for="filter6">
							<div class="icon-btn icon-box iconbox-red">
								<div class="icon" :class="{ 'icon-selected': contentTypeId === '32' }">
									<i class="bx bx-bed"></i>
								</div>
								<div>숙박</div>
								<input id="filter6" class="filter-input" style="display:none" type="radio" v-model="contentTypeId" value="32"/>
							</div>
							</label>
							<label for="filter7">
							<div class="icon-btn icon-box iconbox-blue">
								<div class="icon" :class="{ 'icon-selected': contentTypeId === '38' }">
									<i class="bx bx-shopping-bag"></i>
								</div>
								<div>쇼핑</div>
								<input id="filter7" class="filter-input" style="display:none" type="radio" v-model="contentTypeId" value="38" />
							</div>
							</label>
							<label for="filter8">
							<div class="icon-btn icon-box iconbox-orange">
								<div class="icon" :class="{ 'icon-selected': contentTypeId === '39' }">
									<i class="bx bx-fork"></i>
								</div>
								<div>음식점</div>
								<input id="filter8" class="filter-input" style="display:none"  type="radio" v-model="contentTypeId" value="39"/> 
							</div>
							</label>
						</div>
					</div>
				</section>
			</form>
    </section>
    </main>
      <!-- End Services Section -->
</template>

<script>
import api  from '@/util/http-common.js'

export default {
  name: "AttractionSearchMenuVue",
//   props:['search_keyword'],

  data(){
	return{
		title:'',
		sidoCode:0,
		selectedSido:'',
		gugunCode:'',
		contentTypeId:'',
		gugunList:[],

	}
	
  },
  watch: {
	sidoCode(){
		console.log("change")
		api.get("/enjoytrip/attraction/gugun-list", {params:{sidoCode: this.sidoCode}})
		.then(({data})=>{
		this.gugunList=data
		console.log(this.gugunList)
	})
	}
  },

  methods:{
	getSidoName(sidoCode){
		if (sidoCode==1)
			return "서울"
	},
	search(){
		
		if(this.title==''){
			alert("검색 조건을 입력해주세요");
		}
		else{
			
			api.get("/enjoytrip/attraction/search", {params:{
				title: this.title, contentTypeId:this.contentTypeId, 
				sidoCode: this.sidoCode, gugunCode:this.gugunCode.gugunCode,page:this.page, size:this.size
				}})
            .then(({data})=>{
				this.attractions=data
				if(this.attractions.length>0)
					this.$emit('search', this.attractions)
				else alert('검색 결과가 없습니다')
			})
            .catch(()=>console.log('관광지 목록 조회에 실패하였습니다.'))
			
		}
	},
  },
 
 
};


</script>

<style scoped>
.icon-selected {
  border: 3px solid orange;
}

main.container {
margin-top: 0;
    padding-top: 0;
  }

</style>
