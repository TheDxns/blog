<template>
  <v-container>
    <v-row class="mx-16">
      <v-col cols="10" class="mx-16">
          <h1 class="mt-6 mx-16 font-weight-light">{{ post.title }}</h1>
          <v-chip
          link
          class="ma-2 ml-16"
          color="white"
          >
            <v-avatar left color="blue-grey white--text">
              {{ initials }}
            </v-avatar>
            {{ creator.firstName }} {{ creator.lastName}}
          </v-chip>
          <v-chip
          label
          class=""
          color="white"
          >
                Published on 20.09.2021, 10:31
          </v-chip>
      </v-col>
    </v-row>
    <v-row class="">
      <v-col cols="1">
      </v-col>
      <v-col cols="10" class="mx-16">
        <p class="font-weight-light" style="word-wrap: break-word;"  v-html="post.content" />
      </v-col>
      <v-col cols="1">
      </v-col>
    </v-row>
    <v-row class="ml-16">
      <v-col cols="12" class="ml-16 mt-10">
      </v-col>
    </v-row>
    <v-row class="mx-16">
      <v-col cols="12" class="mt-5">
          <h3 class="mt-16">
            <Disqus shortname='thedxnsblog' :pageConfig="pageConfig"/>
          </h3>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { Disqus } from 'vue-disqus'

export default {
  name: 'PostView',
  components: {
    Disqus
  },
    data() {
      return {
        post: null,
        creator: null,
        initials: '',
        dateCreated: "20.09.2021",
         pageConfig: {
          title: 'My custom title',
          category_id: 'sports',
          url: window.location.href,
          identifier: window.location.href
        }
      }
    },
    methods: {
      getPostId() {
        let splitted = window.location.href.split('/');
        return splitted[splitted.length-1];
      },
      fetchPost() {
        fetch("/api/posts/" + this.getPostId())
          .then((response) => response.json())
          .then((data) => {
            this.post = data;
          });
      },
      fetchUser() {
        setTimeout(() => {
        fetch("http://localhost:3000/api/users/" + this.post.creator)
              .then((response) => response.json())
              .then((data) => {
                this.creator = data;
                this.initials = this.creator.firstName.charAt(0).concat(this.creator.lastName.charAt(0));
              })}, 200);
      }
    },
    mounted() {
     this.fetchPost();
     this.fetchUser();     
    }
  }
</script>


