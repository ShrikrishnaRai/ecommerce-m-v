<template>
  <div>
    <v-app id="inspire">
      <div class="app-wrapper">
        <router-view />
      </div>
    </v-app>
  </div>
</template>

<script>
  export default {
    props: {
      source: String,
      drawer: {
        type: Boolean,
        default: false,
      },
    },
    data: () => ({
      topPosition: 'md-top-left',
      bottomPosition: 'md-bottom-left',
    }),
    created () {
    },
  }
</script>
