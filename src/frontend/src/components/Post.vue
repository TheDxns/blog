<template>
      <v-card
      class="mx-auto mt-5 mb-5 d-flex flex-column"
      max-width="440"
      min-width="440"
      min-height="200"
      max-height="500"
      outlined
      tile
    >
      <v-card-text class="font-weight-light">
        <v-chip
        link
        color="white"
        >
            <v-avatar left color="blue-grey white--text">
              {{ initials }}
            </v-avatar>
            {{ this.user.firstName }} {{this.user.lastName}}
          </v-chip>
          <v-chip
          label
          class=""
          color="white"
          >
                {{this.post.createdOn}} {{this.post.category}}
          </v-chip>
        <p class="text-h5 text--primary mt-auto mx-2">
          {{ this.post.title }}
        </p>
        <div class="text--primary mx-2 mt-auto" v-html=post.sneakPeak />
      </v-card-text>
      <v-card-actions class="mt-auto">
        <v-btn
          text
          color="blue-grey accent-4"
          :href="'http://localhost:3000/posts/' + this.post.id"
          class="mx-2"
          
        >
          Read post
        </v-btn>
      </v-card-actions>
    </v-card>
</template>

<script>
export default {
  name: 'Post',
  props: ['post'],
  data() {
    return {
      user: null,
      initials: ''
    }
  },
  methods: {
    fetchUser() {
      fetch("http://localhost:3000/api/users/" + this.post.creator)
          .then((response) => response.json())
          .then((data) => {
            this.user = data;
            this.initials = this.user.firstName.charAt(0).concat(this.user.lastName.charAt(0));
          })
    }
  }, 
  mounted() {
    this.fetchUser();
  }
}
</script>