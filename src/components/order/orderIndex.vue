<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="headers"
        :items="orderList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Orders</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search Order's"
              single-line
              hide-details
            />
          </v-toolbar>
        </template>
        <template v-slot:item.action="{ item }">
          <v-icon
            class="mr-2"
            small
            @click="exploreBrand(item)"
          >
            explore
          </v-icon>
          <v-icon
            color="red"
            small
            @click="deleteOrderInStore(item)"
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
    name: 'Order',
    data () {
      return {
        search: '',
        dialog: this.$store.state.order.dialog,
        editedIndex: this.$store.state.order.editedIndex,
        defaultItem: this.$store.state.order.defaultItem,
        editedItem: this.$store.state.order.editedItem,
      }
    },

    computed: {
      ...mapGetters('order', {
        headers: 'orderHeader',
        orderList: 'orderList',
      }),
      isLoading () {
        return this.$store.state.order.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    created () {
      this.$store.dispatch('order/doGetOrderListByCustomerId', localStorage.getItem('user-id'))
    },
    methods: {
      deleteOrderInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('order/doDeleteOrder', item)
      },
    },
  }
</script>

<style scoped>

</style>
