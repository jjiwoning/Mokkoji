<template>
  <b-card>
    <b-row>
      <b-col cols="6"><strong class="underline-hotpink">관광지</strong></b-col>
      <b-col cols="6"><strong class="underline-hotpink">사진</strong></b-col>
    </b-row>
    <hr/>
    <ul class="list-unstyled" v-if="attractions.length">
      <li class="mb-2" v-for="attraction in attractions" :key="attraction.contentId" v-bind="attraction">
        <b-row>
          <b-col cols="6">{{ attraction.title }}</b-col>
          <b-col cols="6"><img alt="" :src="attraction.firstImage" style="width: 100px; height: 100px"></b-col>
          <b-col cols="6"><b-button>추가</b-button></b-col>
        </b-row>
        <hr class="row-divider"/>
      </li>
    </ul>
    <div class="centered-content" v-else>
      <span>어디로 떠나볼까요? <br/> 관광지를 검색해보세요 </span>
    </div>
  </b-card>
</template>

<script>
export default {
    name: "AttractionListVue",
    props:['attractions','size'],
    data() {
      return {
        
      };
    },
  };
  </script>

<style>
.row-divider {
    border: 1px solid darkgrey;
    margin: 10px 0;
}
.underline-hotpink {
    display: inline-block;
    background: linear-gradient(
            180deg,
            rgba(255, 255, 255, 0) 70%,
            rgba(231, 27, 139, 0.3) 30%
    );
}
.centered-content {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 70%;
    margin: auto;
}

</style>
