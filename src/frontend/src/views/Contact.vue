<template>
  <v-container>
    <v-row class="mx-16">
      <v-col cols="10" class="mx-16">
        <h1 class="mt-6 ml-16 font-weight-light">Contact</h1>
        <v-row class="mt-7">
          <v-col>
            <h2 class="ml-16 font-weight-light">Contact form:</h2>
          <v-card
    class="mx-auto mt-10"
    max-width="500"
  >
    <v-card-title class="text-h6 font-weight-regular justify-space-between">
      <span>{{ currentTitle }}</span>
      <v-avatar
        color="secondary lighten-2"
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
            v-model="name"
          ></v-text-field>
          <v-text-field
            label="Email"
            v-model="email"
          ></v-text-field>
          <v-textarea
            :counter="50"
            label="Message"
            v-model="message"
          ></v-textarea>
        </v-card-text>
      </v-window-item>

      <v-window-item :value="2">
        <div class="pa-4 text-center">
          <v-icon
            x-large
          >mdi-email-fast</v-icon>
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
        :disabled="step === 2"
        text
        @click="step--"
      >
        Back
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn
        :disabled="step === 2"
        color="secondary"
        depressed
        @click="step++, sendMail()"
      >
        Next
      </v-btn>
    </v-card-actions>
  </v-card>
  </v-col>
  <v-col>
        <h2 class="font-weight-light">Alternative contact methods:</h2>
        <div class="mt-10">
        <v-icon x-large>mdi-email</v-icon> <span class="text-h6">denis.lukasczyk@gmail.com </span><br />
        <v-icon x-large>mdi-linkedin</v-icon> <span class="text-h6">LinkedIn account </span><br />
        <v-icon x-large>mdi-github</v-icon> <span class="text-h6">GitHub account </span><br />
        </div>
        
  </v-col>
  </v-row>
        </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    data: () => ({
      step: 1,
      name: 'John Doe',
      email: 'john@example.com',
      message: 'Hi! I like your blog. Can I join as an redactor?'
    }),
    computed: {
      currentTitle () {
        switch (this.step) {
          case 1: return 'Contact form'
          default: return 'The message was sent'
        }
      },
    }, methods: {
      sendMail() {
        fetch("/api/contact", {
        method: 'post',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body:JSON.stringify({subject:this.email, content:this.message, recipient:this.name})
      }).then(response => response.text())
        .then((response) => {
            console.log(response);
            window.alert(response);
            this.step = 2;
        })
        .catch(err => console.log(err));
      }
    }
  }
</script>