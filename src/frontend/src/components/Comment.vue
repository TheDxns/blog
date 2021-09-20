<template>
    <v-card class="px-5 py-3">
      <h3>{{author}} - {{dateCreated}}</h3>
      {{comment.content}} (ID: {{comment.id}})
      </v-card>
</template>

<script>
export default {
  name: 'Comment',
  props: ['comment'],
  data() {
      return {
        name: "",
        author: "Denis Lukasczyk",
        dateCreated: "20.09.2021"
      }
    },
}
</script>