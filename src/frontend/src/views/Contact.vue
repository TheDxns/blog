<template>
  <v-container>
    <v-row class="mx-16">
      <v-col cols="10" class="mx-16">
        <h1 class="mt-6 ml-16 font-weight-light">Contact:</h1>
          <v-card
    class="mx-auto"
    max-width="500"
  >
    <v-card-title class="text-h6 font-weight-regular justify-space-between">
      <span>{{ currentTitle }}</span>
      <v-avatar
        color="primary lighten-2"
        class="subheading white--text"
        size="24"
        v-text="step"
      ></v-avatar>
    </v-card-title>

    <v-window v-model="step">
      <v-window-item :value="1">
        <v-card-text>
          <v-text-field
            label="Full name"
            value="John Doe"
          ></v-text-field>
          <v-text-field
            label="Email"
            value="john@example.com"
          ></v-text-field>
          <v-textarea
            :counter="50"
            label="Message"
            value="Hi! I like your blog. Can I join as an redactor?"
          ></v-textarea>
        </v-card-text>
      </v-window-item>

      <v-window-item :value="2">
        <div class="pa-4 text-center">
          <v-img
            class="mb-4"
            contain
            height="128"
            src="https://cdn.vuetifyjs.com/images/logos/v.svg"
          ></v-img>
          <h3 class="text-h6 font-weight-light mb-2">
            The message has been sent. 
          </h3>
          <span class="text-caption grey--text">The blog author will contact you soon.</span>
        </div>
      </v-window-item>
    </v-window>

    <v-divider></v-divider>

    <v-card-actions>
      <v-btn
        :disabled="step === 1"
        text
        @click="step--"
      >
        Back
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn
        :disabled="step === 2"
        color="primary"
        depressed
        @click="step++"
      >
        Next
      </v-btn>
    </v-card-actions>
  </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    data: () => ({
      step: 1,
    }),

    computed: {
      currentTitle () {
        switch (this.step) {
          case 1: return 'Contact form'
          default: return 'The message was sent'
        }
      },
    },
  }
</script>