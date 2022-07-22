<template>
  <v-footer
      absolute
      dark
      padless
      
  >
    <v-card
        class="flex"
        flat
        tile
    >
      <v-card-title class="font-weight-light">
        <strong class="subheading"></strong>
        <v-spacer></v-spacer>
          <v-btn
              class="mx-4"
              dark
              icon
              href="https://github.com/TheDxns" 
              target="_blank"
          >
            <v-icon size="24px">
              mdi-github
            </v-icon>
          </v-btn>
          <v-btn 
              class="mx-4"
              dark
              icon
              href="https://www.linkedin.com/in/denislukasczyk" 
              target="_blank"
          >
            <v-icon size="24px">
              mdi-linkedin
            </v-icon>
          </v-btn>
        <v-card-text class="py-0  white--text text-center">
        App by <strong>TheDxns</strong>
        </v-card-text>
      </v-card-title>

      
    </v-card>
  </v-footer>
</template>

<script>
export default {
  name: 'Navbar',
}
</script>