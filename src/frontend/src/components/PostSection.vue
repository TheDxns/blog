<template>
  <v-container>
        <h1 class="mt-6 ml-16 font-weight-light">Latest posts:</h1>
        <ul>
          <v-card 
          class="d-flex flex-wrap mx-16"
          flat
          tile>
            <Post v-for="post in posts" :key="post.content" v-bind:post="post"/>
          </v-card>
        </ul>
  </v-container>
</template>

<script>
import Post from '@/components/Post.vue'

  export default {
    name: 'PostSection',
    data() {
      return {
        posts: []
      }
    },
    components: {
      Post
    },
    mounted() {
      fetch("/api/posts?sort=id,desc")
          .then((response) => response.json())
          .then((data) => {
            this.posts = data;
          })
    }
  }
</script>