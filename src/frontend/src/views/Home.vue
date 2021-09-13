<template>
  <div class="home">
    <PostSection/>
  </div>
</template>

<script>
// @ is an alias to /src
import PostSection from '@/components/PostSection.vue'

export default {
  name: 'Home',
  components: {
    PostSection
  }
}
</script>
