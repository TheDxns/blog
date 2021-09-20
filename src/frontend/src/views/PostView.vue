<template>
  <v-container class="mx-16">
    <v-row class="mx-16">
      <v-col cols="12" class="mx-16">
        <a href="http://localhost:3000/" class="mt-6 mx-16">Return to home</a>
          <h2 class="mt-6 mx-16 red--text">{{dateCreated}} - {{post.title}} (ID: {{post.id}})</h2>
      </v-col>
    </v-row>
    <v-row class="mx-16">
      <v-col cols="9" class="mx-16">
          <h3 class="mt-5 mx-16">{{post.content}}</h3>
      </v-col>
    </v-row>
    <v-row class="mx-16">
      <v-col cols="12" class="mt-16">
          <h3 class="mt-16">
            <CommentSection/>
          </h3>
      </v-col>
    </v-row>
    
  </v-container>
</template>


<script>
import CommentSection from '@/components/CommentSection.vue'

export default {
  name: 'PostView',
  components: {
    CommentSection
  },
    data() {
      return {
        post: null,
        dateCreated: "20.09.2021"
      }
    },
    methods: {
      getPostId() {
        let splitted = window.location.href.split('/');
        return splitted[splitted.length-1];
      }
    },
    mounted() {
      fetch("/api/posts/" + this.getPostId())
          .then((response) => response.json())
          .then((data) => {
            this.post = data;
          })
    }
  }
</script>
