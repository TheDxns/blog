<template>
  <v-container>
    <v-row class="mx-16">
      <v-col cols="10" class="mx-16">
          <h1 class="mt-6 mx-16 font-weight-light">{{ post.title }}</h1>
          <v-chip
          class="ma-2 ml-16"
          color="white"
          >
            <v-avatar left color="blue-grey white--text">
              {{ initials }}
            </v-avatar>
            {{ creator.firstName }} {{ creator.lastName}}
          </v-chip>
          <span style="font-size:13px;">Published on {{post.createdOn | formatDateShort}}</span><span v-if="post.updatedOn != null" style="font-size:13px; color: grey;"> (Updated: {{post.updatedOn | formatDateLong}})</span>
          <v-btn
          v-if="isAdmin()"
          x-small
          text
          @click="editPost()"
          class="ml-3"
          >
          Edit post
          </v-btn>
          <v-btn
          v-if="isAdmin()"
          x-small
          text
          color="red"
          @click="deletePost()"
          >
          Delete post
          </v-btn>
      </v-col>
    </v-row>
    <v-row class="">
      <v-col cols="1">
      </v-col>
      <v-col cols="10" class="mx-16">
        <p class="font-weight-light" style="word-wrap: break-word;"  v-html="post.content" />
      </v-col>
      <v-col cols="1">
      </v-col>
    </v-row>
    <v-row class="ml-16">
      <v-col cols="12" class="ml-16 mt-10">
      </v-col>
    </v-row>
    <v-row class="mx-16">
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'PostView',
  components: {
  },
    data() {
      return {
        post: null,
        creator: null,
        initials: '',
        dateCreated: "20.09.2021",
         pageConfig: {
          title: 'My custom title',
          category_id: 'sports',
          url: "thedxnsblog.disqus.com",
          identifier: "thedxnsblog.disqus.com",
        }
      }
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
      fetchUser() {
        setTimeout(() => {
        fetch("http://localhost:80/api/users/" + this.post.creatorId)
              .then((response) => response.json())
              .then((data) => {
                this.creator = data;
                this.initials = this.creator.firstName.charAt(0).concat(this.creator.lastName.charAt(0));
              })}, 200);
      },
      editPost() {
        document.location.replace('/posts/edit/' + this.post.id);
      },
      deletePost() {
        fetch("/api/posts/" + this.post.id, { method: 'delete' })
        .then(response => response.text())
        .then((response) => {
            console.log(response);
            window.alert(response);
            document.location.replace("/");
        })
        .catch(err => console.log(err));
      },
      isAdmin() {
        try {
          if(this.$keycloak.idTokenParsed.roles.includes('admin')) {
            return true;
          } else {
            return false;
          }
        } catch(e) {
          return false;
        }
      }
    },
    created() {
     this.fetchPost();
     this.fetchUser();     
    },
    filters: {
      formatDateShort: function(date) {
        let newDate = new Date(date);
        return newDate.toLocaleDateString("en", {
          year: "numeric",
          month: "long",
          day: "numeric" });
      },
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


