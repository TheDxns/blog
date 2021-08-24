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
            v-for="icon in icons"
            :key="icon"
            class="mx-4"
            dark
            icon
        >
          <v-icon size="24px">
            {{ icon }}
          </v-icon>
        </v-btn>
        <v-card-text class="py-0  white--text text-center">
        © {{ new Date().getFullYear() }} — <strong>TheDxns</strong>
        </v-card-text>
      </v-card-title>

      
    </v-card>
  </v-footer>
</template>

<script>
export default {
  name: 'Navbar',
  data: () => ({
    icons: [
      'mdi-github',
      'mdi-linkedin',
    ],
  }),
}
</script>