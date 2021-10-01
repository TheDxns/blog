<template>
  <v-row>
    <v-col cols=1>
      </v-col>
      <v-col cols=7>
          <PostSection/>
      </v-col>
      <v-col cols=3>
          <v-row>
            <v-col
            cols:12>
              <h1 class="mt-10 font-weight-light">Most popular posts:</h1>
            </v-col>
          </v-row>
          <v-row class="mt-5">
            <v-col
            cols:12>
              <a href="/">Dolor proident ullamco laborum aliquip sint laborum</a><br />
              <a href="/">Et Lorem anim ea laborum Lorem Lorem</a><br />
              <a href="/">Et fugiat eu ad laborum excepteur eu est sint voluptate</a><br />
              <a href="/">A new lorem ipsum post</a><br />
            </v-col>
          </v-row>
          <v-row>
            <v-col
            cols:12>
              <h1 class="mt-10 font-weight-light">Top rated:</h1>
            </v-col>
          </v-row>
          <v-row class="mt-5">
            <v-col
            cols:12>
              <a href="/">Dolor proident ullamco laborum aliquip sint laborum</a><br />
              <a href="/">Et Lorem anim ea laborum Lorem Lorem</a><br />
              <a href="/">Et fugiat eu ad laborum excepteur eu est sint voluptate</a><br />
              <a href="/">A new lorem ipsum post</a><br />
            </v-col>
          </v-row>
      </v-col>
  </v-row>
</template>

<script>
import PostSection from '@/components/PostSection.vue'

export default {
  name: 'Home',
  components: {
    PostSection
  }
}
</script>