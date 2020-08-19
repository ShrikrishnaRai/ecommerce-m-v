<template>
  <div>
    subscriber
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  export default {
    name: 'Subscriber',
    data () {
      return {
        search: '',
        dialog: this.$store.state.subscriber.dialog,
        editedIndex: this.$store.subscriber.editedIndex,
        editedItem: this.$store.subscriber.editedItem,
        defaultItem: this.$store.subscriber.defaultItem,
      }
    },
    computed: {
      ...mapGetters('subscriber', {
        headers: 'subscriberHeader',
        subscriberList: 'subscriberList',
      }),
      isLoading () {
        return this.$store.state.subscriber.isLoading
      },
    },
  }
</script>

<style scoped>

</style>
