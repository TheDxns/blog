<template>
  <v-container class="mx-16">
    <v-row class="mx-16">
      <v-col cols="12" class="mx-16">
        <h1 class="mt-6 ml-16">Publish a new post</h1>
            <v-form 
            class="mx-16 d-flex flex-column justify-center"
            >
            <v-row
            style="width:50%"
            class="mt-10">
                <v-text-field
                    v-model="post.title"
                    :counter="30"
                    label="Title"
                    required
                ></v-text-field>
                </v-row>
                <v-row
                style="width:50%">
                <v-select
                    v-model="post.category"
                    :items="categories"
                    label="Category"
                    required
                ></v-select>
                </v-row>
                <v-row
                style="width:75%">
                <wysiwyg v-model="post.content" class="mt-10" />
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
                    color="teal"
                    class="ma-2 mt-5 white--text"
                    @click="updatePost"
                >
                    Update post
                </v-btn>
            </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import "vue-wysiwyg\\dist\\vueWysiwyg.css";

export default {
  name: 'EditPost',
  data() {
      return {
        post: null,
        postIsFeatured: false,
        categories: [
        'Test',
        'Other'
      ]
      }
  },
  created() {
      this.fetchPost();
  },
  methods: {
    getPostId() {
        let splitted = window.location.href.split('/');
        return splitted[splitted.length-1];
      },
      fetchPost() {
        fetch("/api/posts/" + this.getPostId())
          .then((response) => response.json())
          .then((data) => {
            this.post = data;
          });
      },
      updatePost() {
          fetch("/api/posts/"+ this.post.id, {
        method: 'put',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body:JSON.stringify({id:this.post.id, title:this.post.title, content:this.post.content, category:this.post.category, creatorId: this.post.creatorId, creatorUsername: this.post.creatorUsername, featured: this.postIsFeatured})
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