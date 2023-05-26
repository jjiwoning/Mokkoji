<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글 내용</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button v-if="isWriter"
            variant="outline-info"
            size="sm"
            @click="moveModifyArticle"
            class="mr-2"
        >글수정
        </b-button
        >
        <b-button v-if="isWriter" variant="outline-danger" size="sm" @click="deleteArticle"
        >글삭제
        </b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
            :header-html="`<h3>${article.boardId}.
          ${article.title}</h3><div><h6> 작성자: ${article.nickname}</div><div>작성일: ${dateFormat(article.createdDate)} </h6></div>`"
            class="mb-2"
            border-variant="dark"
            no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>

        <div v-for="image in article.imageList" :key="image.id">
          <b-row class="d-flex justify-content-center align-items-center">
            <img :src="getImageUrl(image.storedFileName)" :alt="image.alt" crossorigin="anonymous"/>
            <div class="gap"/>
          </b-row>
        </div>

        <b-row class="mb-4">
          <b-col></b-col>
        </b-row>
        <CommentList :boardId="this.article.boardId" v-if="article"></CommentList>
      </b-col>
    </b-row>
  </b-container>

</template>

<script>
import api from '@/util/http-common.js'
import CommentList from '../comment/commentList.vue';
import moment from "moment";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},

    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    api.get(`enjoytrip/board/${this.$route.params.boardId}`).then(({data}) => {
      this.article = data;
    });
    api.get(`enjoytrip/board/${this.$route.params.boardId}/validWriter`).then((response) => {
      if(response.data===true)
        this.$store.dispatch('updateIsWriter', true);
      else
      this.$store.dispatch('updateIsWriter', false);
    });
  
  },
  methods: {
    listArticle() {
      this.$router.push({name: "boardList"});
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: {boardId: this.article.boardId},
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "boardDelete",
          params: {boardId: this.article.boardId},
        });
      }
    },
    dateFormat(createdDate) {
      return moment(new Date(createdDate)).format("YYYY-MM-DD");
    },
    getImageUrl(filename) {
      // 이미지 파일의 경로를 반환하는 메서드
      console.log(filename);
      return api.defaults.baseURL + `/enjoytrip/board/images/${filename}`;  // 실제 경로에 맞게 수정
    },
  },
  components: {CommentList}
};
</script>

<style>
.gap {
    height: 50px;
}
</style>
