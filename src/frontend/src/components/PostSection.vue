<template>
  <v-container>
        <h1 class="mt-6 ml-16 font-weight-light">Latest posts:</h1>
        <v-row v-if="setFilter" class="ml-16 mt-10">
            <p>Sorting by {{sortingType}}: <b>{{filter}}</b></p><v-btn small class="ml-5 red--text" text v-on:click="removeFilter()">Reset</v-btn> 
        </v-row>
        <ul>
          <v-card 
          class="d-flex flex-wrap mx-16"
          flat
          tile
          >
            <Post v-for="post in paginate" :key="post.content" v-bind:post="post" @setfilteruser="filterPostsByUser"
          @setfiltercategory="filterPostsByCategory" />
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
        posts: [],
        setFilter: false,
        sortingType: "",
        filter: "",
        filterUsername: "",
      }
    },
    components: {
      Post
    },
    mounted() {
      this.getPosts();
      
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
        getPosts() {
          if (this.setFilter == false) {
            fetch("/api/posts?sort=id,desc")
                .then((response) => response.json())
                .then((data) => {
                  this.posts = data;
                })
          } else if (this.sortingType === "user") {
            fetch("/api/posts/user/" + this.filterUsername)
                .then((response) => response.json())
                .then((data) => {
                  this.posts = data;
                })
          } else {
            fetch("/api/posts/category/" + this.filter)
                .then((response) => response.json())
                .then((data) => {
                  this.posts = data;
                })
          }
        },
        filterPostsByUser(name, surname, username) {
          this.sortingType = "user";
          this.filter = name + " " + surname;
          this.filterUsername = username;
          this.setFilter = true;
          this.getPosts();
        },
        filterPostsByCategory(category) {
          this.sortingType = "category";
          this.filter = category;
          this.setFilter = true;
          this.getPosts();
        },
        removeFilter() {
          this.setFilter = false;
          this.getPosts();
        },
      }
  }
</script>