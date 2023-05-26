<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >질문 작성하기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="light">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-item
              v-for="article in pagedarticles"
              :key="article.boardId"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>

    <b-pagination
      v-model="currentPage"
      :total-rows="articles.length"
      :per-page="size"
    ></b-pagination>

  </b-container>
</template>

<script>
import boardRestApi from '@/util/http-common.js'
import BoardListItem from "@/components/board/item/BoardListItem";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      currentPage: 1,
      size: 7,
    };
  },
  created() {
    console.log("create")
    boardRestApi.get(`/enjoytrip/board`).then(({ data }) => {
      this.articles = data;
    });
  },
  computed: {
    totalArticles() {
      return this.articles.length;
    },
    pagedarticles() {
      const startIndex = (this.currentPage - 1) * this.size;
      const endIndex = startIndex + this.size;
      return this.articles.slice(startIndex, endIndex);
    },
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    handleSearch(articles) {
      this.articles = articles;
      this.currentPage=1;
    }, 
    changePage(page){
      this.currentPage=page;
    }
  },
};
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
</style>
