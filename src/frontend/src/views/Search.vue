<template>
  <v-row>
      <v-col>
            <v-row class="ml-16">
              <v-row class="ml-16">
                <h1 class="mt-6 ml-16 font-weight-light">Search for posts:</h1>
              </v-row>
            </v-row>
            <v-row class="ml-16">
                    <v-col
                        cols="4"
                        sm="6"
                        md="3"
                        class="ml-16"
                        >
                        <v-text-field class="ml-16"
                            label="Keyword"
                            type="text"
                            v-model="key"
                        >
                        <template v-slot:append-outer>
                        <v-btn small class="ml-5" text v-on:click="searchForPosts()">Search</v-btn> 
                        </template>
                        </v-text-field>
                    </v-col>
              </v-row>
          <v-row v-if="startedSearch">
                  <PostSection />
          </v-row>
        </v-col>
</v-row>
           
</template>


<script>
import PostSection from '@/components/PostSection.vue'

export default {
  name: 'Search',
   data() {
      return {
        key: '',
        startedSearch: false,  
        posts: []
      }
    },
    components: {
      PostSection
    },
    methods: {
        searchForPosts() {
            this.startedSearch = true;
        }
    }
}
</script>