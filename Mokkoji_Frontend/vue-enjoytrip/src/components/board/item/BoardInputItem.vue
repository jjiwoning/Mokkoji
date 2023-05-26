<template>
 
</template>

<script>
import api from '@/util/http-common.js'

export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        userId: "6",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  created(){
    
    api.get(`enjoytrip/board/${this.$route.params.boardId}`).then(({ data }) => {
            this.article = data;
        });
  },
  props: {
    type: { type: String },
  },

  methods: {
    onSubmit(event) {
     event.preventDefault();
     

      // let err = true;
      // let msg = "";
      // !this.article.userid &&
      //   ((msg = "작성자 입력해주세요"),
      //   (err = false),
      //   this.$refs.userId.focus());
      // err &&
      //   !this.article.subject &&
      //   ((msg = "제목 입력해주세요"),
      //   (err = false),
      //   this.$refs.title.focus());
      // err &&
      //   !this.article.content &&
      //   ((msg = "내용 입력해주세요"),
      //   (err = false),
      //   this.$refs.content.focus());

      // if (!err) alert(msg);
      // else
      alert('수정하시겠어요?')
        
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.$router.push({ name: "boardList" });
    },
    registArticle() {

      api
        .post(`/enjoytrip/board/write`, {
          boardId:this.article.boardId,
          title: this.article.title,
          content: this.article.content,
        })
        .then(() => {
          alert('게시글을 수정하였습니다.')
        })
        .catch(error=>{
          alert(error)
        });
          // this.moveList();
        },
    modifyArticle() {
      api
        .patch(`/board/${this.article.boardId}`, {
          boardId: this.article.boardId,
          userid: this.article.userid,
          title: this.article.title,
          content: this.article.content,
        })
        .then(({ data }) => {
          if (data === "success") {
           alert("수정이 완료되었습니다.");
          }
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "boardList" });
        });
    },
  }
}
    // moveList() {
    //   this.$router.push({ name: "boardList" });
    // },

</script>

<style></style>
