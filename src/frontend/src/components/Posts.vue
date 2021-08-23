<template>
  <v-container class="mx-16">
    <v-row class="mx-16">
      <v-col cols="12" class="mx-16">
        <h1 class="mt-10">Latest posts:</h1>
        <ul>
            <Post v-for="post in posts" :key="post.content" v-bind:post="post"/>
        </ul>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import Post from '@/components/Post.vue'

  export default {
    name: 'Posts',
    data() {
      return {
        posts: []
      }
    },
    components: {
      Post
    },
    mounted() {
      fetch("/api/posts")
          .then((response) => response.json())
          .then((data) => {
            this.posts = data;
          })
    }
  }
</script>
