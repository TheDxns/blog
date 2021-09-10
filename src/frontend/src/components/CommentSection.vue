<template>
    <v-row>
    <v-col
      cols="12"
      sm="6"
      offset-sm="3"
    >
        <Comment v-for="comment in comments" :key="comment.content" v-bind:comment="comment"/>
    </v-col>
  </v-row>
</template>

<script>
import Comment from '@/components/Comment.vue'

  export default {
    name: 'CommentSection',
    data() {
      return {
        comments: []
      }
    },
    components: {
      Comment
    },
    mounted() {
      fetch("/api/comments?sort=id,desc")
          .then((response) => response.json())
          .then((data) => {
            this.comments = data;
          })
    }
  }
</script>