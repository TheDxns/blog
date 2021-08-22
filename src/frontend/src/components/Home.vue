<template>
  <v-container>
    <v-row>
      <v-col cols="12" class="pl-16">
        <h1>Blog</h1>
        <h3>Post list:</h3>
        <ul>
            <li v-for="post in posts" :key="post.content">{{post.id}}, {{post.title}}, {{post.content}}</li>
            <br />
        </ul>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    name: 'Home',
    data() {
      return {
        posts: []
      }
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
