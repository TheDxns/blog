<template>
  <nav>
    <v-toolbar dark extended app>
      <v-toolbar-title class="headline text-uppercase grey--text mt-10">
        <span class="font-weight-light">A Blog by </span>
        <span>TheDxns</span>
      </v-toolbar-title>
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
        <v-btn href="https://github.com/TheDxns" target="_blank" text>
          <span class="mr-2 font-weight-light">Github</span>
        </v-btn>
      </div>
      <v-spacer></v-spacer>
      <v-btn href="#" text>
        <span class="mr-2 font-weight-light">Sign up/Log in</span>
        <v-icon>mdi-account</v-icon>
      </v-btn>
    </v-toolbar>
  </nav>
</template>

<script>
export default {
  name: 'Navbar'
}
</script>