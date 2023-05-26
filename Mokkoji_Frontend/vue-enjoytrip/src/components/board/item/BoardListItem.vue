<template>
  <b-tr>
    <b-td>{{ boardId }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'boardDetail', params: { boardId: boardId } }"
        >{{ title }}</router-link
      >
    </b-th>
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
};
</script>

<style></style>
