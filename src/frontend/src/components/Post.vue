<template>
      <v-card
          class="mx-auto my-10"
          dark
          width="1000"
      >
        <v-card-title class="ml-5">
          <span class="headline font-weight-bold">{{ this.post.title }}</span>
        </v-card-title>

        <v-card-text class="font-weight-light ml-5">
          {{ this.post.sneakPeak }}
        </v-card-text>

        <v-card-actions>
          <v-list-item class="grow ml-5">
            <v-list-item-avatar color="grey darken-3">
              <v-img
                  class="elevation-6"
                  src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
              ></v-img>
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>Evan You</v-list-item-title>
            </v-list-item-content>

            <v-row
                align="center"
                justify="end"
                class="mr-10"
            >
              <v-icon class="mr-1">mdi-heart</v-icon>
              <span class="subheading mr-2">256</span>
              <span class="mr-1">·</span>
              <v-icon class="mr-1">mdi-comment</v-icon>
              <span class="subheading">45</span>
            </v-row>
          </v-list-item>
        </v-card-actions>
      </v-card>
</template>

<script>
export default {
  name: 'Post',
  props: ['post']
}
</script>