<template>
  <v-content>
    <router-view />
  </v-content>
</template>

<script>
  export default {
    name: 'AppMain',
    computed: {
      key () {
        return this.$route.path
      },
    },
  }
</script>
