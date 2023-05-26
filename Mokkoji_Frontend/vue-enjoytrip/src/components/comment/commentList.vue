<template>
  <div class="commentItem">

    <h4 class="underline-hotpink">댓글 목록</h4>

    <b-card>
      <!-- 댓글 목록 -->
      <ul class="list-unstyled">
        <b-row>
          <b-col cols="4"><strong>닉네임</strong></b-col>
          <b-col cols="4"><strong>내용</strong></b-col>
          <b-col cols="2"><strong>작성일</strong></b-col>
          <b-col cols="2"></b-col>
        </b-row>
        <hr class="row-divider"/>
        <li class="mb-2" v-for="comment in comments" :key="comment.comentId">
          <b-row>
            <b-col cols="4">{{ comment.nickname }}</b-col>
            <b-col cols="4">
              <span v-if="editMode === comment.commentId">
                <input type="text" v-model="updatedComment"/>
              </span>
              <span v-else>
                {{ comment.content }}
              </span>
            </b-col>
            <b-col cols="2">{{ comment.createdDate | dateFormat }}</b-col>
            <b-col cols="2">
              <span v-if="editMode === comment.commentId">
                <b-button @click="updateComment(comment.commentId, comment.content)" size="sm"
                          variant="primary">저장</b-button>
              </span>
              <span v-else>
                <b-button @click="toggleEditMode(comment.commentId, comment.content)" size="sm"
                          variant="secondary">수정</b-button>
              </span>
              <b-button @click="deleteComment(comment.commentId)" size="sm" variant="danger">삭제</b-button>
            </b-col>
          </b-row>
        </li>
      </ul>
    </b-card>
    <div class="gap"/>
    <div>
      <h4 class="underline-hotpink">댓글 등록</h4>
      <!--      <div class="box_total">댓글 등록</div>-->
      <div class="form-group">
        <textarea class="form-control" v-model="inputComment" rows="3"></textarea>
      </div>
      <button class="btn btn-primary" @click="submitComment">댓글 등록</button>
    </div>
  </div>
</template>

<script>

import api from '@/util/http-common.js'
import moment from "moment";

export default {
  name: "CommentList",
  props: ['boardId'],
  data() {
    return {
      comments: [],
      editMode: null,
      updatedComment: '',
      inputComment: '',
    };
  },

  mounted() { //댓글 목록 받아오기
    api.get(`enjoytrip/board/${this.$route.params.boardId}/comments`).then(({data}) => {
      this.comments = data
    })
  },
  filters: {
    dateFormat(createdDate) {
      return moment(new Date(createdDate)).format("YYYY-MM-DD");
    },
  },
  computed: {},
  methods: {
    toggleEditMode(commentId, content) {
      // 수정 모드를 토글하고 수정된 댓글 내용을 초기화
      if (this.editMode === commentId) {
        this.editMode = null;
        this.updatedComment = "";
      } else {
        this.editMode = commentId;
        this.updatedComment = content // 해당 댓글의 내용으로 초기화하거나 필요한 경우 이전 댓글 내용을 설정
      }
    },
    loadComments() { //댓글 목록 받아오기
      api.get(`/enjoytrip/board/${this.$route.params.boardId}/comments`).then(({data}) => {
        this.comments = data
      })
      console.log("댓글 목록: " + this.comments);
    },

    updateComment(commentId, content) {
      api.patch(`/enjoytrip/board/${this.$route.params.boardId}/comments/${commentId}`, {
        content: this.updatedComment
      }).then(({data}) => {
        this.comments = data
        this.loadComments()
      })
      this.toggleEditMode(commentId, content); // 수정 모드를 해제
    },

    deleteComment(commentId) {
      api.delete(`/enjoytrip/board/${this.$route.params.boardId}/comments/${commentId}`).then(() => {
        alert('댓글이 삭제되었습니다.')
        this.loadComments()
      });
    },
    submitComment() {
      api.post(`/enjoytrip/board/${this.$route.params.boardId}/comments/write`, {
        content: this.inputComment
      })
          .then(() => {
            alert('댓글이 등록되었습니다. ')
            this.inputComment = ""
            this.loadComments()
          });
    },
  }
}
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

.gap {
    height: 50px;
}

.underline-hotpink {
    display: inline-block;
    background: linear-gradient(
            180deg,
            rgba(255, 255, 255, 0) 70%,
            rgba(231, 27, 139, 0.3) 30%
    );
}

.row-divider {
    border: 1px solid black;
    margin: 10px 0;
}
</style>
  