<template>
  <v-container>
    <v-row class="mx-16">
      <v-col cols="12" class="mx-16">
        <h1 class="mt-6 ml-16 font-weight-light">Redactors:</h1>
        <v-row  v-for="redactor in redactors" :key="redactor.id" class="mt-2 mx-16">
          <v-col class="mt-2 mx-16" cols=8>
            <Redactor v-bind:redactor="redactor"/>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import Redactor from '@/components/Redactor.vue'

export default {
  name: 'Redactors',
  components: {
    Redactor
  },
    data() {
      return {
        redactors: [
          {
            id: 1,
            firstname: "Sam",
            surname: "Smith",
            avatarUrl: "https://avatars0.githubusercontent.com/u/9064066?v=4&s=460"
          },
          {
            id: 2,
            firstname: "John",
            surname: "Doe",
            avatarUrl: "https://cdn.vuetifyjs.com/images/john.jpg"
          }
        ]
      }
    },
    methods: {
      
      }
    }/*,
    mounted() {
      fetch("/api/redactors/")
          .then((response) => response.json())
          .then((data) => {
            this.redactors = data;
          })
    }
  }*/
</script>