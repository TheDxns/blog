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
      <wysiwyg v-model="postContent" />
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
import "vue-wysiwyg\\dist\\vueWysiwyg.css";

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
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body:JSON.stringify({title:this.postTitle, content:this.postContent, category:this.postCategory, creatorId: this.$keycloak.idTokenParsed.sub})
      }).then(response => response.text())
        .then((response) => {
            console.log(response);
            window.alert(response);
            document.location.replace("/");
        })
        .catch(err => console.log(err));
    }
  },
}
</script>

