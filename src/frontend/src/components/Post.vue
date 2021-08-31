<template>
      <v-card
      class="mx-auto mt-10"
      max-width="344"
      outlined
      tile
    >
      <v-card-text>
        <div>{{this.post.category}}</div>
        <p class="text-h4 text--primary">
          {{ this.post.title }}
        </p>
        <p>{{ this.post.creator }}</p>
        <div class="text--primary">
          {{this.post.sneakPeak}}
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
  
      <v-expand-transition>
        <v-card
          v-if="reveal"
          class="transition-fast-in-fast-out v-card--reveal"
          style="height: 100%;"
        >
          <v-card-text class="pb-0">
            <p class="text-h4 text--primary">
              Origin
            </p>
            <p>late 16th century (as a noun denoting a place where alms were distributed): from medieval Latin eleemosynarius, from late Latin eleemosyna ‘alms’, from Greek eleēmosunē ‘compassion’ </p>
          </v-card-text>
          <v-card-actions class="pt-0">
            <v-btn
              text
              color="teal accent-4"
              @click="reveal = false"
            >
              Close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-expand-transition>
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