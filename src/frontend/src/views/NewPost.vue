<template>
  <v-container class="mx-16">
    <v-row class="mx-16">
      <v-col cols="12" class="mx-16">
        <h1 class="mt-6 ml-16">Publish a new post</h1>
        <PostForm/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import PostForm from '@/components/PostForm.vue'

export default {
  name: 'NewPost',
  components: {
    PostForm
  }
}
</script>