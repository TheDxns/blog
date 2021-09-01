<template>
      <v-card
      class="mx-auto mt-10"
      max-width="500"
      outlined
      tile
    >
      <v-card-text>
        <div>{{ this.post.category }}</div>
        <p class="text-h4 text--primary">
          {{ this.post.title }}
        </p>
        <p>{{ this.post.creator }}</p>
        <div class="text--primary">
          {{ this.post.sneakPeak }}
        </div>
      </v-card-text>
      <v-card-actions>
        <v-btn
          text
          color="teal accent-4"
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
        name: "Denis Lukasczyk"
      }
    },
}
</script>