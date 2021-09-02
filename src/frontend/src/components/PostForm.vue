<template>
  <v-form>
    <v-text-field
        v-model="postTitle"
        :counter="10"
        label="Title"
        required
    ></v-text-field>
    <v-select
        v-model="postCategory"
        :categories="categories"
        label="Category"
        required
    ></v-select>
    <v-text-field
        v-model="postContent"
        :counter="10"
        label="Content"
        required
    ></v-text-field>
    <v-btn
        color="warning"
        @click="doPost"
    >
      Publish post
    </v-btn>
  </v-form>
</template>
<script>
export default {
  name: 'PostForm',
  data() {
    return {
      postTitle: '',
      postContent: '',
      postCategory: '',
      categories: [
        'Test',
        'Other'
      ]
    }
  },
  methods: {
    doPost() {
      fetch("/api/posts", {
        method: 'post',
        data:{title:this.postTitle, content:this.postContent, category:this.postCategory}
      }).then((response) => {
        console.log(response.data);
      })
    }
  },
}
</script>