<template>
  <b-tr>
    <b-td>{{ boardId }}</b-td>
    <th class="text-left">
      <router-link :to="{ name: 'teamBoardDetail', params: { teamBoardId: boardId } }" @click="moveToDetail(boardId)">{{ title }}</router-link>
</th>
    <b-td>{{ nickname }}</b-td>
    <b-td>{{ createdDate | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    boardId: Number,
    nickname: String,
    title: String,
    content:String,
    createdDate: String,
  },
  filters: {
    dateFormat(createdDate) {
      return moment(new Date(createdDate)).format("YYYY-MM-DD");
    },
  },
  methods:{
    moveToDetail(boardId){
      console.log("boardId" + boardId)
      this.$store.dispatch('updateBoardId', boardId)
    }
  }
};
</script>

<style></style>
