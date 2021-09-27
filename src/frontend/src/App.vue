<template>
  <v-app>
    <v-main>
      <v-row>
        <v-col>
          <Navbar/>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols=2>
        </v-col>
        <v-col cols=8>
          <router-view/>
        </v-col>
        <v-col cols=2>
        </v-col>
      </v-row>
      <v-row class="mt-16 mb-16">
      </v-row>
      <v-row class="mt-16">
          <Footer/>
      </v-row>
    </v-main>
  </v-app>
</template>

<script>
import Navbar from '@/components/Navbar.vue'
import Footer from '@/components/Footer.vue'

export default {
  name: 'App',
  components: {
    Navbar,
    Footer
  }
}
</script>
