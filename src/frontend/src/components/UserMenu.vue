<template>
      <v-menu
        bottom
        min-width="200px"
        rounded
        offset-x
        offset-y
      >
        <template v-slot:activator="{ on }">
          <v-row>
          <v-col
          cols="6">
          </v-col>
          <v-col
          cols="6">
          <v-btn
            class="ml-3 mt-10"
            icon
            x-large
            v-on="on"
          >
            <v-avatar
              color="blue-grey"
              size="48"
              v-if="!dataLoading"
            >
              <!--TODO: Replace icon with user avatar-->
              <v-icon>mdi-account</v-icon>
            </v-avatar>
            <v-avatar
              v-else
              color="blue-grey"
              size="48"
            >
              <v-progress-circular
                indeterminate
                color="white"
              ></v-progress-circular>
            </v-avatar>
          </v-btn>
        </v-col>
        </v-row>
        </template>
        <v-card>
          <v-list-item-content class="justify-center">
            <div class="mx-auto text-center">
              <v-avatar
                color="blue-grey"
              >
                <!--TODO: Replace icon with user avatar-->
                <v-icon color="white">mdi-account</v-icon>
              </v-avatar>
              <h3 class="mt-2">{{ user.username }}</h3>
              <p class="text-caption mt-1">
                {{ user.email }}
              </p>
              <v-divider class="my-3"></v-divider>
              <v-btn
                href="/new-post"
                depressed
                rounded
                text
              >
                New post
              </v-btn>
              <v-divider class="my-3"></v-divider>
              <v-btn
                href="/profile-settings"
                depressed
                rounded
                text
              >
                Edit Account
              </v-btn>
              <v-divider class="my-3"></v-divider>
              <v-btn
                
                @click="logOut()"
                depressed
                rounded
                text
              >
                Log out
              </v-btn>
            </div>
          </v-list-item-content>
        </v-card>
      </v-menu>
</template>

<script>
  export default {
    name: "UserMenu",
     data() {
      return {
        dataLoading: true,
        user: {
          initials: '',
          email: '',
          username: ''
        }
    }
    },
    methods: {
      logOut() {
          document.location.replace("http://localhost:8180/auth/realms/Blog/protocol/openid-connect/logout?redirect_uri=http://localhost:3000/");
          this.getUserInfo();
      },
      getUserInfo() {
        setTimeout(() => {
          this.user.username = this.$userInfo.preferred_username; 
          this.user.roles = this.$userInfo.preferred_username
          this.user.initials = "U1";
          this.user.email = "user1@gmail.com";
          //TODO: check if the data is loaded, if not wait some more
          this.dataLoading = false;
        }, 300);
      }
    },
    mounted() {
      this.getUserInfo();
    }
  }
</script>

