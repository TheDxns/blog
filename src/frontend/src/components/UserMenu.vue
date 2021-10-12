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
              <span class="white--text text-h5">{{ initials }}</span>
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
                <span class="white--text text-h5">{{ initials }}</span>
              </v-avatar>
              <h3 class="mt-2">{{ $keycloak.idTokenParsed.name }}</h3>
              <p class="text-caption mt-1">
                {{ $keycloak.idTokenParsed.email }}
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
                href="http://localhost:8180/auth/realms/Blog/account/#/personal-info"
                target="_blank"
                depressed
                rounded
                text
              >
                Edit Account
              </v-btn>
              <v-divider class="my-3"></v-divider>
              <v-btn
                href="http://localhost:8180/auth/realms/Blog/account/#/security/signingin"
                target="_blank"
                depressed
                rounded
                text
              >
                Security Options
              </v-btn>
              <v-divider class="my-3"></v-divider>
              <v-btn
                
                @click="$keycloak.logoutFn"
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
        dataLoading: false,
        initials: this.$keycloak.idTokenParsed.given_name.charAt(0).concat(this.$keycloak.idTokenParsed.family_name.charAt(0))
        }
    },
    methods: {
    },
    mounted() {
    }
  }
</script>

