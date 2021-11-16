<template>
  <v-container>
    <v-row class="mx-16">
      <v-col cols="12" class="mx-16">
        <h1 class="mt-6 ml-16 font-weight-light">Contact</h1>
        <v-row class="mt-7">
          <v-col class="ml-16">
            <v-row class="ml-16">
              <h2 class="ml-16 font-weight-light">Contact form:</h2>
            </v-row>
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
                    :counter="500"
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
                  @click="resetForm()"
                >
                Clear
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn
                  :disabled="step === 2"
                  color="secondary"
                  depressed
                  @click="step++, sendMail()"
                >
                  Send
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
          <v-col>
            <h2 class="font-weight-light">Alternative contact methods:</h2>
            <div class="mt-10">
              <v-icon x-large class="mb-2">mdi-email</v-icon> <a href="mailto:denis.lukasczyk@gmail.com" class="text-h6 black--text" style="text-decoration: none;">denis.lukasczyk@gmail.com </a><br />
              <v-icon x-large class="mb-2">mdi-linkedin</v-icon> <a href="https://www.linkedin.com/in/denislukasczyk" class="text-h6 black--text" style="text-decoration: none;">LinkedIn account </a><br />
              <v-icon x-large class="mb-2">mdi-github</v-icon> <a href="https://github.com/TheDxns" class="text-h6 black--text" style="text-decoration: none;">GitHub account </a><br />
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
          case 1: return ''
          default: return 'The message was sent'
        }
      },
    }, 
    methods: {
      sendMail() {
        if(!(this.name.length === 0) && !(this.email.length === 0) && !(this.message.length === 0)) 
        {
          if(this.message.length < 15) {
            this.step = 1;
            window.alert("Message to short. Please use at least 15 characters.")
          } else {
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
                  this.step = 2;
              }).catch(err => console.log(err));
          }
        } else {
          this.step = 1;
          window.alert("Please fill all fields and try again.")
        }
      },
      resetForm() {
        this.name = '';
        this.email = '';
        this.message = '';
      }
    }
  }
</script>