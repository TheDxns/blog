<template>
  <v-row justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        max-width="600px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
            href=""
            v-bind="attrs"
            v-on="on"
            text
        >
            <span class="mr-2 font-weight-light">Sign up/Log in</span>
            <v-icon>mdi-account</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">Log in</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                    v-model="email"
                    label="Email"
                    required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                    v-model="password"
                    label="Password"
                    type="password"
                    required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="blue darken-1"
              text
              @click="dialog = false"
          >
            Close
          </v-btn>
          <v-btn
              color="blue darken-1"
              text
              @click="logIn"
          >
            Log in
          </v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  name: 'LoginForm',
  data: () => ({
    dialog: false,
    email: '',
    password: ''
  }),
  methods: {
    logIn() {
      fetch("http://localhost:8180/auth/realms/Blog/protocol/openid-connect/token", {
        method: 'post',
        body: new URLSearchParams({
          client_id:'blog-client', username:this.email, password:this.password, grant_type:'password'
        }),
        mode: 'cors',
        credentials: 'same-origin'
      }).then((response) => response.json())
          .then((responseData) => {
            alert(JSON.stringify(responseData))
            document.cookie = "access_token=" + JSON.stringify(responseData.access_token) + "; expires=" + new Date(Date.now()+ 300000).toUTCString();
            document.cookie = "refresh_token=" + JSON.stringify(responseData.refresh_token);
          })
    }
  }
}
</script>