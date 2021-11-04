<template>
  <v-form 
  class="mx-16 d-flex flex-column justify-center"
  >
  <v-row
  style="width:50%"
  class="mt-10">
      <v-text-field
          v-model="postTitle"
          :counter="30"
          label="Title"
          required
      ></v-text-field>
      </v-row>
      <v-row
      style="width:50%">
      <v-select
          v-model="postCategory"
          :items="categories"
          label="Category"
          required
      ></v-select>
    </v-row>
    <v-row
      style="width:75%">
      <wysiwyg v-model="postContent" class="mt-10" />
    </v-row>
    <v-row
      style="width:75%">
      <v-checkbox
      v-model="postIsFeatured"
      :label="'Feature post?'"
      class="mt-5"
      ></v-checkbox>
      </v-row>
      <v-btn
          style="width:15%"
          color="black"
          class="ma-2 mt-5 white--text"
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
      postIsFeatured: false,
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
        body:JSON.stringify({title:this.postTitle, content:this.postContent, category:this.postCategory, creatorId: this.$keycloak.idTokenParsed.sub, creatorUsername: this.$keycloak.idTokenParsed.preferred_username, featured: this.postIsFeatured})
      }).then(response => response.text())
        .then((response) => {
            console.log(response);
            window.alert(response);
            document.location.replace("/");
        })
        .catch(err => console.log(err));
    }
  }
}
</script>

