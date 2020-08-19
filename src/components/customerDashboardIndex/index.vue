<template>
  <div>
    <v-btn
      @click="pushToHomePage"
    >
      HomePage
    </v-btn>
  </div>
</template>

<script>
  import utils from '@/mixins/utils'
  export default {
    name: 'CustomerDashboardIndex',
    mixins: [utils],
  }
</script>

<style scoped>

</style>
