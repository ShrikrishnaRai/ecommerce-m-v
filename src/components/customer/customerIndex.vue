<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="headers"
        :items="customerList"
        sort-by=""
        class="elevation-1 ma-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Customer</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search Customer"
              single-line
              hide-details
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
    name: 'Customer',
    data () {
      return {
        search: '',
      }
    },
    created () {
      this.$store.dispatch('customer/doGetCustomersLists')
    },
    computed: {
      ...mapGetters('customer', {
        headers: 'customerHeader',
        customerList: 'customerList',
      }),

      customersList () {
        return this.$store.state.customer.customerList
      },
      isLoading () {
        return this.$store.state.customer.isLoading
      },
    },
  }
</script>
