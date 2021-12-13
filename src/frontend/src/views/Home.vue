<template>
  <v-row>
    <v-col cols=1>
      </v-col>
      <v-col cols=7>
          <div class="ml-12">
            <h1 class="mt-6 ml-16 font-weight-light">Latest posts:</h1>
          </div>
          <PostSection/>
      </v-col>
      <v-col cols=3>
          <v-row>
            <v-col
            cols:12>
              <h1 class="mt-10 ml-13 font-weight-light">Worth reading:</h1>
            </v-col>
          </v-row>
          <v-row>
            <v-col
            cols:12>
              <ul>
                <li class="mb-3" v-for="post in posts" :key="post.title" v-bind:post="post" style="overflow:hidden;">
                <a style="text-decoration:none;" class="blue-grey--text" :href="'http://localhost:80/posts/show/' + post.id" >{{post.title}}</a>
                </li>
              </ul>
            </v-col>
          </v-row>
          <v-row class="mt-5" style="height:570px">
            <v-col
            cols:12>
              <h1 class="ml-13 font-weight-light">Newsletter</h1>
              <iframe frameBorder="0" style="width: 100%; height:100%; padding-right:90px;" src="https://cdn.forms-content.sg-form.com/15645814-46c4-11ec-b6ca-4200ccaabc3a"/>
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

