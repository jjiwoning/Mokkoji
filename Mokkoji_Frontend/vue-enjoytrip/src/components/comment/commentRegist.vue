<template>
    <div>
      <textarea v-model="content" rows="3"></textarea>
      <button @click="submitComment">댓글 등록</button>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        content: '',
      };
    },
    methods: {
      submitComment() {
        
      },
    },
  };
  </script>