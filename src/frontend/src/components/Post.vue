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
                20.09.2021, 10:31
          </v-chip>
        <p class="text-h5 text--primary mt-auto mx-2">
          {{ this.post.title }}
        </p>
        <p>{{ this.post.creator }}</p>
        <div class="text--primary mx-2 mt-auto">
          {{ this.post.sneakPeak }}
        </div>
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
        name: "Denis Lukasczyk",
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
}
</script>