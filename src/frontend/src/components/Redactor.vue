<template>
    <v-card class="px-5 py-3">
        <v-avatar>
            <img
                :src=redactor.avatarUrl
                alt=""
            >
        </v-avatar>
      <h3>{{redactor.firstname}} {{redactor.surname}}</h3>
    </v-card>
</template>

<script>
export default {
  name: 'Redactor',
  props: ['redactor'],
  data() {
      return {
        
      }
    },
}
</script>