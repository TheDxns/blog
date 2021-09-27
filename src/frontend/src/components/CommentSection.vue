<template>
    <v-row>
    <v-col
      cols="12"
      sm="8"
      offset-sm="2"
      class="rounded-lg"
    >
      <v-divider></v-divider>
      <h3 class="font-weight-light mt-5">Comments:</h3>
      <h5 class="mt-10" v-if="noComments()">
              No one has commented yet.
      </h5>
        <v-row v-for="comment in comments" :key="comment.content" class="mt-2">
          <v-col cols=7 >
            <Comment v-bind:comment="comment"/>
            <p class="px-5 font-weight-light blue-grey--text caption">Like &ensp; Reply</p>
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
      },
      noComments() {
        if(this.comments.length == 0) {
          return true;
        } else {
          return false;
        }
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