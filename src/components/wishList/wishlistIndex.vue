<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="headers"
        :items="wishList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Wishlist</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search WishList's"
              single-line
              hide-details
            />
            <v-divider
              class="mx-4"
              inset
              vertical
            />
          </v-toolbar>
        </template>
      </v-data-table>
    </v-app>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'

  export default {
    name: 'WishList',
    data () {
      return {
        search: '',
        editedIndex: this.$store.state.wishlist.dialog,
        editedItem: this.$store.state.wishlist.editedItem,
        defaultItem: this.$store.state.wishlist.defaultItem,
      }
    },
    created () {
      this.$store.dispatch('wishlist/doGetWishList')
      console.log('created called')
    },
    computed: {
      ...mapGetters('wishlist', {
        headers: 'wishListHeader',
        wishList: 'wishList',
      }),
      isLoading () {
        return this.$store.state.wishlist.isLoading
      },
    },
  }
</script>

<style scoped>

</style>
