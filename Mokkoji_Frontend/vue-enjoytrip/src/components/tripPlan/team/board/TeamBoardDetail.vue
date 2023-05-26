


<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row>
          <h3 class="underline-pink">
        <i class="bi bi-people-fill"></i> 팀 게시판  
        </h3>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-left">
          <b-button variant="outline-primary" @click="listArticle">목록</b-button>
        </b-col>
        <b-col class="text-right">
          <b-button v-if="isWriter" variant="outline-info" size="sm" @click="moveModifyArticle" class="mr-2">글수정</b-button>
          <b-button v-if="isWriter" variant="outline-danger" size="sm" @click="deleteArticle">글삭제</b-button>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col>
          <b-card class="mb-2" border-variant="dark">
            <b-card-header>
              <h3>{{ article.teamBoardId }}. {{ article.title }}</h3>
              <div class="text-right">
                <h6>작성자: {{ article.nickname }}</h6>
                <h6>작성일: {{ dateFormat(article.createdDate) }}</h6>
              </div>
            </b-card-header>
            <b-card-body class="text-left">
              <div v-html="message"></div>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
      <b-row class="mb-4">
        <b-col></b-col>
      </b-row>
      <team-comment-list v-if="article"></team-comment-list>
    </b-container>
  </div>
</template>

  
  <script>
  import api from '@/util/http-common.js'
  import moment from "moment";
  import TeamCommentList from './comment/TeamCommentList.vue';
  
  export default {
    name: "TeamBoardDetail",
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
      tripTeamId(){
        return this.$store.getters.getTripTeamId;
      },
      isWriter(){
        return this.$store.getters.getIsWriter;
      },
        boardId() {
            console.log(this.$store.getters.getBoardId);
        return this.$store.getters.getBoardId;
      },
      userId(){
        return this.$store.getters.getBoardId;
      }

    },
    
    created() {
      
      api.get(`/enjoytrip/TripTeam/${this.tripTeamId}/boards/${this.$route.params.teamBoardId}`).then(({data}) => {
        this.article = data;
      });

      api.get(`/enjoytrip/TripTeam/${this.tripTeamId}/boards/${this.$route.params.teamBoardId}/validWriter`).then((response) => {
        if(response.data===true)
          this.$store.dispatch('updateIsWriter', true);
        else
        this.$store.dispatch('updateIsWriter', false);
      });
    },
    methods: {
      listArticle() {
        this.$router.push({name: "team"});
      },
      moveModifyArticle() {
        this.$router.replace({
          name: "teamBoardModify",
          params: {teamBoardId: this.article.teamBoardId},
        });
        //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
      },
      deleteArticle() {
        if (confirm("정말로 삭제하시겠습니까?")) {
          this.$router.replace({
            name: "teamBoardDelete",
            params: {teamBoardId: this.article.teamBoardId},
          });
        }
      },
      dateFormat(createdDate) {
        return moment(new Date(createdDate)).format("YYYY-MM-DD");
      },
     
    },
    components: {TeamCommentList}
  };
  </script>
  
  <style>
  .gap {
      height: 50px;
  }
  </style>
  