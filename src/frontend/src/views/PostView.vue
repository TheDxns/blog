<template>
  <v-container class="mx-16">
    <v-row class="mx-16">
      <v-col cols="12" class="mx-16">
          <h1 class="mt-6 mx-16 font-weight-light">{{post.title}} (ID: {{post.id}})</h1>
          <v-chip
          link
          class="ma-2 ml-16"
          color="white"
          >
            <v-avatar left>
              <img
                  :src=creator[0].avatarUrl
                  alt=""
              >
            </v-avatar>
            {{creator[0].firstname}} {{creator[0].surname}}
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
    <v-row class="mx-16">
      <v-col cols="12" class="mx-16">
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
        dateCreated: "20.09.2021",
        creator: [
          {
            id: 1,
            firstname: "Sam",
            surname: "Smith",
            avatarUrl: "https://avatars0.githubusercontent.com/u/9064066?v=4&s=460"
          }
        ]
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