<template>
    <v-row>
    <v-col
      cols="12"
      sm="6"
      offset-sm="3"
      class="rounded-lg blue-grey darken-4 white--text"
    >
      <h3>Comment section</h3>
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
    methods: {
      getPostId() {
        let splitted = window.location.href.split('/');
        return splitted[splitted.length-1];
      }
    },
    mounted() {
      fetch("/api/comments/" + this.getPostId())
          .then((response) => response.json())
          .then((data) => {
            this.comments = data;
          })
    }
  }
</script>