<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="deliveryBoyWalletHistoryDataTableHeaders"
        :items="deliveryBoyWalletHistoryList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title> DeliveryBoyWalletHistorys</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search DeliveryBoyWalletHistory's"
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
            @click="exploreDeliveryBoyWalletHistory(item)"
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
            @click="deleteDeliveryBoyWalletHistoryInStore(item)"
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
    name: 'DeliveryBoyWalletHistory',
    data () {
      return {
        search: '',
        statusList: ['Published', 'Pending', 'Draft'],
        dialog: this.$store.state.deliveryBoyWalletHistory.dialog,
        persistIndex: this.$store.state.deliveryBoyWalletHistory.persistIndex,
        temp: this.$store.state.deliveryBoyWalletHistory.temp,
        defaultItem: this.$store.state.deliveryBoyWalletHistory.defaultItem,
      }
    },
    computed: {
      ...mapGetters('deliveryBoyWalletHistory', {
        deliveryBoyWalletHistoryDataTableHeaders: 'deliveryBoyWalletHistoryHeader',
        deliveryBoyWalletHistoryList: 'deliveryBoyWalletHistoryList',
      }),
      getProductMergerListFromProductStore () {
        return this.$store.state.product.productMergerList
      },
      getFormTitle () {
        return this.persistIndex === -1 ? 'Insert DeliveryBoyWalletHistory Information' : 'Edit DeliveryBoyWalletHistory'
      },
      getPersistTitle () {
        return this.persistIndex === -1 ? 'Save' : 'Save Changes'
      },
      isLoading () {
        return this.$store.state.deliveryBoyWalletHistory.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('deliveryBoyWalletHistory/doGetDeliveryBoyWalletHistoryList')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      exploreDeliveryBoyWalletHistory (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deleteDeliveryBoyWalletHistoryInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('deliveryBoyWalletHistory/doDeleteDeliveryBoyWalletHistory', item)
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
          this.updateDeliveryBoyWalletHistoryToStore()
        } else {
          this.createDeliveryBoyWalletHistoryInStore()
        }
        this.closeDialogBox()
      },
      updateDeliveryBoyWalletHistoryToStore () {
        const tempData = Object.assign(this.deliveryBoyWalletHistory[this.persistIndex], this.temp)
        this.$store.dispatch('deliveryBoyWalletHistory/doUpdateDeliveryBoyWalletHistory', [tempData, this.temp.deliveryBoyWalletHistoryId])
      },
      createDeliveryBoyWalletHistoryInStore () {
        this.$store.dispatch('deliveryBoyWalletHistory/doCreateDeliveryBoyWalletHistory', this.temp)
      },
    },
  }
</script>
