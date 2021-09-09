<template>
  <v-container class="mx-16">
    <v-row class="mx-16">
      <v-col cols="12" class="mx-16">
        <h1 class="mt-6 ml-16">About the post:</h1>
          <h2 class="mt-6 ml-16">{{post.id}}</h2>
          <h2 class="mt-6 ml-16">{{post.title}}</h2>
          <h3 class="mt-16 ml-16 red--text">{{post.content}}</h3>
      </v-col>
    </v-row>
  </v-container>
</template>


<script>

export default {
  name: 'PostView',
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
