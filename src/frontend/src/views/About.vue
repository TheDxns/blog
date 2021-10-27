<template>
  <v-container>
    <v-row class="mx-16">
      <v-col cols="10" class="mx-16">
        <h1 class="mt-6 ml-16 font-weight-light">About the blog:</h1>
          <h2 class="mt-6 ml-16 font-weight-light">A personal blog created with use of <span class="green--text font-weight-bold">Vue.js</span><img src="https://img.icons8.com/color/30/000000/vue-js.png"/> and 
          <span class="blue--text font-weight-bold">Vuetify</span><img style="height:30px;" src="https://cdn.vuetifyjs.com/images/logos/v-alt.svg"/> for the frontend and 
          <span class="light-green--text font-weight-bold"> Spring Boot</span> <img src="https://img.icons8.com/color/30/000000/spring-logo.png"/> on the server side.</h2>
          <h2 class="mt-6 ml-16 font-weight-light"><span class="grey--text font-weight-bold">Keycloak</span><img style="height:30px;" src="https://upload.wikimedia.org/wikipedia/commons/2/29/Keycloak_Logo.png"/> is used as an authorization server with the OpenID Connect protocol.</h2>
          <h2 class="mt-6 ml-16 font-weight-light">The blog also implements <span class="blue--text font-weight-bold" >SendGrid</span> <img style="height:30px;" src="https://sendgrid.com/wp-content/uploads/2017/04/SendGrid-Logomark-150x150.png" /> as a e-mail delivery service.</h2>
      </v-col>
    </v-row>
    <v-row class="mx-16 mt-16">
      <v-col cols="10" class="mx-16">
        <v-btn
          href="https://icons8.com/icon/90519/spring-logo"
          text
          x-small
          class="mx-16 mt-16"
        >
        Icons by Icons8
        </v-btn>
      </v-col>
    </v-row>
    
  </v-container>
</template>