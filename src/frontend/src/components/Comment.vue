<template>
    <v-card flat class="px-5 py-3">
       <v-chip
       link
        color="white"
        >
            <v-avatar left>
              <img
                  :src=avatarUrl
                  alt=""
              >
            </v-avatar>
            {{author}}
          </v-chip>
          <v-chip
          label
          class=""
          color="white"
          >
                {{dateCreated}} 10:31
          </v-chip>
      <h6 class="font-weight-light ml-2">{{comment.content}} (ID: {{comment.id}})</h6>
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
        dateCreated: "20.09.2021",
        avatarUrl: "https://cdn.vuetifyjs.com/images/john.jpg"
      }
    },
}
</script>