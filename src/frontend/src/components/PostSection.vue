<template>
  <v-container>
        <h1 class="mt-6 ml-16 font-weight-light">Latest posts:</h1>
        <ul>
          <v-card 
          class="d-flex flex-wrap mx-16"
          flat
          tile>
            <Post v-for="post in paginate" :key="post.content" v-bind:post="post"/>
          </v-card>
        </ul>
        <div class="text-center">
        <v-pagination
          color="black"
          v-model="currentPage"
          :length="totalPages"
          prev-icon="mdi-menu-left"
          next-icon="mdi-menu-right"
        ></v-pagination>
        </div>
  </v-container>
</template>

<script>
import Post from '@/components/Post.vue'

  export default {
    name: 'PostSection',
    data() {
      return {
        currentPage: 1,
        itemsPerPage: 6,
        resultCount: 0,
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
    },
    computed: {
            totalPages: function() {
                return Math.ceil(this.posts.length / this.itemsPerPage)
            },
            paginate: function() {
                let index = this.currentPage * this.itemsPerPage - this.itemsPerPage
                return this.posts.slice(index, index + this.itemsPerPage)
            }
        },
        methods: {
          
        }
  }
</script>