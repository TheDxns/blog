<template>
    <v-card>
      #{{comment.id}} - {{comment.content}}
      </v-card>
</template>

<script>
export default {
  name: 'Comment',
  props: ['comment'],
  data() {
      return {
        name: ""
      }
    },
}
</script>