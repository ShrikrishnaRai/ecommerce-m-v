<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :loading="isLoading"
        :headers="headers"
        :items="vendorsReviewList"
        sort-by=""
        class="elevation-1 ma-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Vendor Review</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              outlined
              append-icon="mdi-magnify"
              label="Search Review's"
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
        <template v-slot:item.action="{ item }">
          <v-icon
            class="mr-2"
            small
            @click="exploreItem(item)"
          >
            explore
          </v-icon>
          <v-icon
            small
            class="mr-2"
            @click="editItem(item)"
          >
            edit
          </v-icon>
          <v-icon
            color="red"
            small
            @click="deleteItem(item)"
          >
            delete
          </v-icon>
        </template>
      </v-data-table>
    </v-app>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'

  export default {
    name: 'VendorReview',
    computed: {
      ...mapGetters('vendorReview', {
        headers: 'vendorReviewHeader',
        vendorsReviewList: 'vendorsReviewList',
      }),
      isLoading () {
        return this.$store.state.vendorReview.isLoading
      },
    },
    methods: {
      save () {
      },
      close () {
        this.dialog = false
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 100)
      },
    },
  }
</script>

<style scoped>

</style>
