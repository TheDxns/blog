<template>
  <v-form 
  class="mx-16 d-flex flex-column justify-center"
  style="width:50%"
  >
      <v-text-field
          v-model="postTitle"
          :counter="30"
          label="Title"
          required
      ></v-text-field>
      <v-select
          v-model="postCategory"
          :items="categories"
          label="Category"
          required
      ></v-select>
      <v-textarea
          v-model="postContent"
          :counter="50"
          label="Content"
          required
      ></v-textarea>
      <v-btn
          style="width:15%"
          color="teal"
          class="ma-2 white--text"
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
    getCookie(name) {
      let value = "; " + document.cookie;
      let parts = value.split("; " + name + "=");
      if (parts.length == 2) return parts.pop().split(";").shift();
    }, 
    doPost() {
      fetch("/api/posts", {
        method: 'post',
        headers: {
          'Authorization': 'bearer ' + this.getCookie("KEYCLOAK_IDENTITY") + ''
        },
        data:{title:this.postTitle, content:this.postContent, category:this.postCategory}
      }).then((response) => {
        console.log(response.data);
      })
    }
  },
}
</script>