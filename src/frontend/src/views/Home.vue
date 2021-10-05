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
              <a style="text-decoration:none;" class="blue-grey--text" href="/">1) Dolor proident ullamco laborum aliquip sint laborum</a><br />
              <a style="text-decoration:none;" class="blue-grey--text" href="/">2) Et Lorem anim ea laborum Lorem Lorem</a><br />
              <a style="text-decoration:none;" class="blue-grey--text" href="/">3) Et fugiat eu ad laborum excepteur eu est sint voluptate</a><br />
              <a style="text-decoration:none;" class="blue-grey--text" href="/">4) A new lorem ipsum post</a><br />
            </v-col>
          </v-row>
          <v-row>
            <v-col
            cols:12>
              <h1 class="mt-10 font-weight-light">Top rated posts:</h1>
            </v-col>
          </v-row>
          <v-row class="mt-5">
            <v-col
            cols:12>
              <a style="text-decoration:none;" class="blue-grey--text" href="/">1) Dolor proident ullamco laborum aliquip sint laborum</a><br />
              <a style="text-decoration:none;" class="blue-grey--text" href="/">2) Et Lorem anim ea laborum Lorem Lorem</a><br />
              <a style="text-decoration:none;" class="blue-grey--text" href="/">3) Et fugiat eu ad laborum excepteur eu est sint voluptate</a><br />
              <a style="text-decoration:none;" class="blue-grey--text" href="/">4) A new lorem ipsum post</a><br />
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