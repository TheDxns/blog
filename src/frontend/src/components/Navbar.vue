<template>
  <nav>
    <v-toolbar dark extended>
      <v-row justify="start">
        <v-col>
          <a href="/" style="text-decoration: none;">
            <v-toolbar-title class="headline text-uppercase grey--text mt-14 px-5">
              <span class="font-weight-light">A Blog by </span>
              <span>TheDxns</span>
            </v-toolbar-title>
            </a>
        </v-col>
      <v-spacer></v-spacer>
      <v-col
      cols="4">
      <div class="mt-16 pr-5">
        <v-btn href="/" text>
          <span class="mr-2 font-weight-light">Home</span>
        </v-btn>
        <v-btn href="/about" text>
          <span class="mr-2 font-weight-light">About</span>
        </v-btn>
        <v-btn href="/search" text>
          <span class="mr-2 font-weight-light">Search</span>
        </v-btn>
        <v-btn href="/contact" text>
          <span class="mr-2 font-weight-light">Contact</span>
        </v-btn>
        <v-btn href="https://github.com/TheDxns/blog" target="_blank" text>
          <span class="mr-2 font-weight-light">Source code</span>
        </v-btn>
      </div>
      </v-col>
      <v-spacer></v-spacer>
      <v-col
      cols="2">
      <div v-if="$keycloak.ready">
        <div v-if="$keycloak.authenticated">
          <UserMenu />
        </div>
        <div v-else>
          <v-btn
            @click="$keycloak.login"
            class="mt-10 ml-16"
            text
          >
            <span class="ml-2 font-weight-light">Sign up/Log in</span>
            <v-icon>mdi-account</v-icon>
          </v-btn>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-toolbar>
</nav>
</template>
<script>
import UserMenu from '@/components/UserMenu.vue'

export default {
  name: 'Navbar',
  components: {
    UserMenu
  },
  data: () => ({
      categories: [
        { title: 'Test' },
        { title: 'Other' },
        { title: 'Lorem Ipsum' },
        { title: 'Web dev' },
      ],
    }),
  }
</script>