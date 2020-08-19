<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="cancelOrderDataTableHeaders"
        :items="cancelOrderList"
        sort-by=""
        class="elevation-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title> CancelOrders</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search CancelOrder's"
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
            @click="exploreCancelOrder(item)"
          >
            explore
          </v-icon>
          <v-icon
            small
            class="mr-2"
            @click="onEditAction(item)"
          >
            edit
          </v-icon>
          <v-icon
            color="red"
            small
            @click="deleteCancelOrderInStore(item)"
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
    name: 'CancelOrder',
    data () {
      return {
        search: '',
        statusList: ['Published', 'Pending', 'Draft'],
        dialog: this.$store.state.cancelOrder.dialog,
        persistIndex: this.$store.state.cancelOrder.persistIndex,
        temp: this.$store.state.cancelOrder.temp,
        defaultItem: this.$store.state.cancelOrder.defaultItem,
      }
    },
    computed: {
      ...mapGetters('cancelOrder', {
        cancelOrderDataTableHeaders: 'cancelOrderHeader',
        cancelOrderList: 'cancelOrderList',
      }),
      getProductMergerListFromProductStore () {
        return this.$store.state.product.productMergerList
      },
      getFormTitle () {
        return this.persistIndex === -1 ? 'Insert CancelOrder Information' : 'Edit CancelOrder'
      },
      getPersistTitle () {
        return this.persistIndex === -1 ? 'Save' : 'Save Changes'
      },
      isLoading () {
        return this.$store.state.cancelOrder.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('cancelOrder/doGetCancelOrderList')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      exploreCancelOrder (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deleteCancelOrderInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('cancelOrder/doDeleteCancelOrder', item)
      },
      closeDialogBox () {
        this.dialog = false
        this.persistIndex = -1
        this.resetDialogField()
      },
      resetDialogField () {
        this.persistIndex = -1
        this.temp = Object.assign({}, this.defaultItem)
      },
      onPersistAction () {
        if (this.persistIndex > -1) {
          this.updateCancelOrderToStore()
        } else {
          this.createCancelOrderInStore()
        }
        this.closeDialogBox()
      },
      updateCancelOrderToStore () {
        const tempData = Object.assign(this.cancelOrder[this.persistIndex], this.temp)
        this.$store.dispatch('cancelOrder/doUpdateCancelOrder', [tempData, this.temp.cancelOrderId])
      },
      createCancelOrderInStore () {
        this.$store.dispatch('cancelOrder/doCreateCancelOrder', this.temp)
      },
    },
  }
</script>
