<template>
  <v-container class="mx-16">
    <v-row class="mx-16">
      <v-col cols="12" class="mx-16">
        <h1 class="mt-6 ml-16">Latest posts:</h1>
        <ul>
          <v-card 
          class="d-flex flex-wrap mx-16"
          flat
          tile>
            <Post v-for="post in posts" :key="post.content" v-bind:post="post"/>
          </v-card>
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
      fetch("/api/posts?sort=id,desc")
          .then((response) => response.json())
          .then((data) => {
            this.posts = data;
          })
    }
  }
</script>
lorem