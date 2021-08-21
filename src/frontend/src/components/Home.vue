<template>
  <div class="home">
    <h1>Blog</h1>
    <h3>Post list:</h3>
    <ul>
      <template v-for="post in posts" :key="post.content">
        <li>{{post.id}}, {{post.title}}, {{post.content}}</li>
        <br />
      </template>
    </ul>
  </div>
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
