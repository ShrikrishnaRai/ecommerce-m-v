<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="deliveryBoyWalletDataTableHeaders"
        :items="deliveryBoyWalletList"
        sort-by=""
        class="elevation-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title> DeliveryBoyWallets</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search DeliveryBoyWallet's"
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
            @click="exploreDeliveryBoyWallet(item)"
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
            @click="deleteDeliveryBoyWalletInStore(item)"
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
    name: 'DeliveryBoyWallet',
    data () {
      return {
        search: '',
        statusList: ['Published', 'Pending', 'Draft'],
        dialog: this.$store.state.deliveryBoyWallet.dialog,
        persistIndex: this.$store.state.deliveryBoyWallet.persistIndex,
        temp: this.$store.state.deliveryBoyWallet.temp,
        defaultItem: this.$store.state.deliveryBoyWallet.defaultItem,
      }
    },
    computed: {
      ...mapGetters('deliveryBoyWallet', {
        deliveryBoyWalletDataTableHeaders: 'deliveryBoyWalletHeader',
        deliveryBoyWalletList: 'deliveryBoyWalletList',
      }),
      getProductMergerListFromProductStore () {
        return this.$store.state.product.productMergerList
      },
      getFormTitle () {
        return this.persistIndex === -1 ? 'Insert DeliveryBoyWallet Information' : 'Edit DeliveryBoyWallet'
      },
      getPersistTitle () {
        return this.persistIndex === -1 ? 'Save' : 'Save Changes'
      },
      isLoading () {
        return this.$store.state.deliveryBoyWallet.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('deliveryBoyWallet/doGetDeliveryBoyWalletList')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      exploreDeliveryBoyWallet (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deleteDeliveryBoyWalletInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('deliveryBoyWallet/doDeleteDeliveryBoyWallet', item)
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
          this.updateDeliveryBoyWalletToStore()
        } else {
          this.createDeliveryBoyWalletInStore()
        }
        this.closeDialogBox()
      },
      updateDeliveryBoyWalletToStore () {
        const tempData = Object.assign(this.deliveryBoyWallet[this.persistIndex], this.temp)
        this.$store.dispatch('deliveryBoyWallet/doUpdateDeliveryBoyWallet', [tempData, this.temp.deliveryBoyWalletId])
      },
      createDeliveryBoyWalletInStore () {
        this.$store.dispatch('deliveryBoyWallet/doCreateDeliveryBoyWallet', this.temp)
      },
    },
  }
</script>
