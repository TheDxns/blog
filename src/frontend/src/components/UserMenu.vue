<template>
      <v-menu
        bottom
        min-width="200px"
        rounded
        offset-y
      >
        <template v-slot:activator="{ on }">
          <v-btn
            class="mr-5 mt-5"
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
      logOut() {},
      getUserInfo() {
        setTimeout(() => {this.user.username = this.$userInfo.preferred_username; 
          this.user.initials = "U1";
          this.user.email = "user1@gmail.com";
          console.log(this.$test);
          //TODO: check if the data is loaded, if not wait some more
          this.dataLoading = false;
        }, 200);
      }
    },
    mounted() {
      this.getUserInfo();
    }
  }
</script>

