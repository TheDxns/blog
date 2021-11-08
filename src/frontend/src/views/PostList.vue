<template>
    <v-container class="mx-16">
        <v-row class="mx-16">
            <v-col cols="12" class="mx-16">
                <h1 class="mt-6 ml-16 font-weight-light" >Post management</h1>
                <v-row class="mb-5 mt-10">
                    <v-col class="ml-16">
                        <b>Creation date</b>
                    </v-col>
                    <v-col>
                        <b>Creator</b>
                    </v-col>
                    <v-col>
                        <b>Title</b>
                    </v-col>
                    <v-col>
                        <b>&emsp;&emsp;Options</b>
                    </v-col>
                </v-row>
                <v-row v-for="post in posts" :key="post.content" v-bind:post="post">
                    <v-col class="ml-16">
                        {{post.createdOn | formatDateLong}}
                    </v-col>
                    <v-col>
                        {{post.creatorUsername}}
                    </v-col>
                    <v-col>
                        {{post.title}}
                    </v-col>
                    <v-col>
                        <v-btn
                        @click="editPost(post.id)"
                        small
                        color="black white--text"
                        >
                        Edit
                        </v-btn>&emsp;
                        <v-btn
                        @click="deletePost(post.id)"
                        small
                        color="black white--text">
                        Delete
                        </v-btn>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    name: 'PostList',
    data() {
      return {
        posts: []
      }
    },
    created() {
      this.getPosts();
    },
    methods: {
        getPosts() {
            fetch("/api/posts")
              .then((response) => response.json())
              .then((data) => {
                  this.posts = data;
            })
        },
        editPost(id) {
            window.open('/posts/edit/' + id), '_blank';
        },
        deletePost(id) {
            fetch("/api/posts/" + id, { method: 'delete' })
            .then(response => response.text())
            .then((response) => {
                console.log(response);
                window.alert(response);
                document.location.reload();
            })
            .catch(err => console.log(err));
        }
    },
    filters: {
      formatDateLong: function(date) {
        let newDate = new Date(date);
        return newDate.toLocaleDateString("en", {
          year: "numeric",
          month: "numeric",
          day: "numeric",
          hour: "numeric",
          minute: "numeric" });
      }
    }
}
</script>