<template>
  <b-container class="bv-example-row mt-3 text-center">
   
    <div class="container">
      <div class="component1">
        <AttractionVue @search="handleSearch"></AttractionVue>
      </div>
      <div class="component2">
        <div class="sub-container">
          <kakaoMap :attractions="pagedAttractions"  class="map-component" />
          <div class="component-gap"></div>
          <AttractionList :attractions="pagedAttractions" :size="size" class="list-component" />
        </div>
      </div>
    </div>
    <b-pagination
        v-model="currentPage"
        :total-rows="totalAttractions"
        :per-page="size"
        @change="changePage"
        class="mt-3"
    ></b-pagination>
  </b-container>
</template>

<script>
import AttractionVue from '@/components/attraction/AttractionSearchMenuVue.vue';
import AttractionList from '@/components/attraction/AttractionListVue.vue';
import kakaoMap from '@/components/attraction/kakaoMap.vue';

export default {
  components: { AttractionVue, kakaoMap, AttractionList },
  name: "AttractionView",
  data() {
    return {
      attractions: [],
      currentPage: 1,
      size: 4,
    };
  },

  computed: {
    totalAttractions() {
      return this.attractions.length;
    },
    pagedAttractions() {
      const startIndex = (this.currentPage - 1) * this.size;
      const endIndex = startIndex + this.size;
      return this.attractions.slice(startIndex, endIndex);
    },
  },
  methods: {
    handleSearch(attractions) {
      this.attractions = attractions;
      this.currentPage=1;
    }, 
    changePage(page){
      this.currentPage=page;
    }
  },
  watch:{
    currentPage(){
      
    }
  }
};
</script>

<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.component1 {
  margin-bottom: 20px;
}

.component2 {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%
}

.sub-container {
  display: flex;
  flex: 1;
}

.component-gap {
  width: 20px; /* 원하는 간격 값으로 조정 */
}

.map-component,
.list-component {
  flex: 1;
}
</style>
