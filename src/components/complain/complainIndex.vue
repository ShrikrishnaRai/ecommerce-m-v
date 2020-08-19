<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="complainDataTableHeaders"
        :items="complainList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title> Complain's</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search Complain's"
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
            color="red"
            small
            @click="deleteBrandInStore(item)"
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
  import { DELETE_WARNING } from '@/utils/CONST'

  export default {
    name: 'Brand',
    data () {
      return {
        search: '',
      }
    },
    computed: {
      ...mapGetters('complain', {
        complainDataTableHeaders: 'complainHeader',
        complainList: 'complainList',
      }),
      getProductMergerListFromProductStore () {
        return this.$store.state.product.productMergerList
      },
      isLoading () {
        return this.$store.state.complain.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('complain/doGetComplainList')
    },
    methods: {
      dispatchCustomerMergerList () {
        this.$store.dispatch('customer/doGetCustomersLists')
      },
      deleteBrandInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('complain/doDeleteComplain', item)
      },
    },
  }
</script>
