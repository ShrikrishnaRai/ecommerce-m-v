<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :headers="headers"
        :items="customerWalletList"
        sort-by=""
        class="elevation-1 ma-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Customer Wallet</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search Customer Wallet's"
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
    name: 'CustomerWallet',
    data () {
      return {
        search: '',
        editedIndex: this.$store.state.customerWallet.editedIndex,
        editedItem: this.$store.state.customerWallet.editedItem,
        defaultItem: this.$store.state.customerWallet.defaultItem,
        dialog: this.$store.state.customerWallet.dialog,
      }
    },
    computed: {
      ...mapGetters('customerWallet', {
        headers: 'headers',
        customerWalletList: 'customerWalletList',
      }),
      isLoading () {
        return this.$store.state.customerWallet.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    created () {
      this.$store.dispatch('customerWallet/doGetCustomerWalletList')
    },
    methods: {},
  }
</script>

<style scoped>

</style>
