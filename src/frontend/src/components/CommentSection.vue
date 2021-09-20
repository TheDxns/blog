<template>
    <v-row>
    <v-col
      cols="12"
      sm="6"
      offset-sm="3"
      class="rounded-lg blue-grey darken-4 white--text"
    >
      <h3 class="font-weight-light">Comments:</h3>
        <v-row  v-for="comment in comments" :key="comment.content" class="mt-2">
          <v-col cols=12>
            <Comment v-bind:comment="comment"/>
            <p class="px-4 font-weight-light caption">Like &ensp; Reply</p>
          </v-col>
        </v-row>

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