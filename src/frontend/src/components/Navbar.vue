<template>
  <nav>
    <v-toolbar dark extended app>
      <a href="/" style="text-decoration: none;">
      <v-toolbar-title class="headline text-uppercase grey--text mt-10">
        <span class="font-weight-light">A Blog by </span>
        <span>TheDxns</span>
      </v-toolbar-title>
      </a>
      <v-spacer></v-spacer>
      <div class="mt-16">
        <v-btn href="/" text>
          <span class="mr-2 font-weight-light">Home</span>
        </v-btn>
        <v-btn href="/about" text>
          <span class="mr-2 font-weight-light">About</span>
        </v-btn>
        <v-btn href="/redactors" text>
          <span class="mr-2 font-weight-light">Redactors</span>
        </v-btn>
        <v-btn href="/contact" text>
          <span class="mr-2 font-weight-light">Contact</span>
        </v-btn>
        <v-btn href="https://github.com/TheDxns/blog" target="_blank" text>
          <span class="mr-2 font-weight-light">Github repo</span>
        </v-btn>
      </div>
      <LoginForm/>
    </v-toolbar>
  </nav>
</template>

<script>
import LoginForm from '@/components/LoginForm.vue'

export default {
  name: 'Navbar',
  components: {
    LoginForm
  }
}
</script>