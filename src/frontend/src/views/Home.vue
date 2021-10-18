<template>
  <v-row>
    <v-col cols=1>
      </v-col>
      <v-col cols=7>
          <PostSection/>
      </v-col>
      <v-col cols=3>
          <v-row>
            <v-col
            cols:12>
              <h1 class="mt-10 ml-5 font-weight-light">Worth reading:</h1>
            </v-col>
          </v-row>
          <v-row>
            <v-col
            cols:12>
              <ul>
                <li v-for="post in posts" :key="post.title" v-bind:post="post" style="overflow:hidden;">
                <a style="text-decoration:none;" class="blue-grey--text" :href="'http://localhost:3000/posts/' + post.id" >{{post.title}}</a>
                </li>
              </ul>
              
            </v-col>
          </v-row>
          <v-row>
            <v-col
            cols:12>
              <h1 class="mt-10 ml-5 font-weight-light">Most popular:</h1>
            </v-col>
          </v-row>
          <v-row>
            <v-col
            cols:12>
              <ul>
                <li style="overflow:hidden;">
                <a style="text-decoration:none;" class="blue-grey--text" href="/">Dolor proident ullamco laborum aliquip sint laborum</a><br />
                <a style="text-decoration:none;" class="blue-grey--text" href="/">Et Lorem anim ea laborum Lorem Lorem</a><br />
                <a style="text-decoration:none;" class="blue-grey--text" href="/">Et fugiat eu ad laborum excepteur eu est sint voluptate</a><br />
                <a style="text-decoration:none;" class="blue-grey--text" href="/">A new lorem ipsum post</a><br />
                </li>
              </ul>
              
            </v-col>
          </v-row>
      </v-col>
  </v-row>
</template>

<script>
import PostSection from '@/components/PostSection.vue'

export default {
  name: 'Home',
   data() {
      return {
        posts: []
      }
    },
    components: {
      PostSection
    },
    mounted() {
      fetch("/api/posts/featured")
          .then((response) => response.json())
          .then((data) => {
            this.posts = data;
          })
    },
}
</script>