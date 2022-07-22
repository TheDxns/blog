<template>
  <v-container>
    <v-row>
      <v-col cols="10">
        <h1 class="mt-6 ml-16 font-weight-light">About the blog:</h1>
          <h2 class="mt-6 ml-16 font-weight-light">{{content}}</h2>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  data: () => ({
    content: 'Set the content of this page in the page management settings.'
  }),
  methods: {

  }
}
</script>