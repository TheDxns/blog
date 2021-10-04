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
            >
              <span class="white--text text-h5">{{ user.initials }}</span>
            </v-avatar>
          </v-btn>
        </template>
        <v-card>
          <v-list-item-content class="justify-center">
            <div class="mx-auto text-center">
              <v-avatar
                color="blue-grey"
              >
                <span class="white--text text-h5">{{ user.initials }}</span>
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
      user: {
        initials: 'JD',
        email: 'john.doe@doe.com',
        username: 'John Doe'
      },
    }
    },
    methods: {
        logOut() {
        }
    }
  }
</script>