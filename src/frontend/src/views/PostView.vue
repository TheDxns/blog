<template>
  <v-container class="mx-16">
    <v-row class="mx-16">
      <v-col cols="12" class="mx-16">
        <h1 class="mt-6 mx-16">About the post:</h1>
          <h2 class="mt-6 mx-16 red--text">#{{post.id}} | {{post.title}}</h2>
      </v-col>
    </v-row>
    <v-row class="mx-16">
      <v-col cols="9" class="mx-16">
          <h3 class="mt-16 mx-16">{{post.content}}</h3>
      </v-col>
    </v-row>
    <CommentSection/>
  </v-container>
</template>


<script>
import CommentSection from '@/components/CommentSection.vue'

export default {
  name: 'PostView',
  components: {
    CommentSection
  },
    data() {
      return {
        post: null
      }
    },
    methods: {
      getPostId() {
        let splitted = window.location.href.split('/');
        return splitted[splitted.length-1];
      }
    },
    mounted() {
      fetch("/api/posts/" + this.getPostId())
          .then((response) => response.json())
          .then((data) => {
            this.post = data;
          })
    }
  }
</script>
