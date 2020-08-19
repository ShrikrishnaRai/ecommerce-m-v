<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="deliveryBoyBankDataTableHeaders"
        :items="deliveryBoyBankList"
        sort-by=""
        class="elevation-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title> DeliveryBoyBanks</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search DeliveryBoyBank's"
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
            @click="exploreDeliveryBoyBank(item)"
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
            @click="deleteDeliveryBoyBankInStore(item)"
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
    name: 'DeliveryBoyBankDetails',
    data () {
      return {
        search: '',
        statusList: ['Published', 'Pending', 'Draft'],
        dialog: this.$store.state.deliveryBoyBankDetails.dialog,
        persistIndex: this.$store.state.deliveryBoyBankDetails.persistIndex,
        temp: this.$store.state.deliveryBoyBankDetails.temp,
        defaultItem: this.$store.state.deliveryBoyBankDetails.defaultItem,
      }
    },
    computed: {
      ...mapGetters('deliveryBoyBankDetails', {
        deliveryBoyBankDataTableHeaders: 'deliveryBoyBankDetailsHeader',
        deliveryBoyBankList: 'deliveryBoyBankDetailsList',
      }),
      getProductMergerListFromProductStore () {
        return this.$store.state.product.productMergerList
      },
      getFormTitle () {
        return this.persistIndex === -1 ? 'Insert DeliveryBoyBank Information' : 'Edit DeliveryBoyBank'
      },
      getPersistTitle () {
        return this.persistIndex === -1 ? 'Save' : 'Save Changes'
      },
      isLoading () {
        return this.$store.state.deliveryBoyBankDetails.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('deliveryBoyBankDetails/doGetDeliveryBoysDetailsLists')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      exploreDeliveryBoyBank (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deleteDeliveryBoyBankInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('doDeleteDeliveryBoyBankDetails/doDeleteDeliveryBoyBank', item)
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
          this.updateDeliveryBoyBankToStore()
        } else {
          this.createDeliveryBoyBankInStore()
        }
        this.closeDialogBox()
      },
      updateDeliveryBoyBankToStore () {
        const tempData = Object.assign(this.deliveryBoyBank[this.persistIndex], this.temp)
        this.$store.dispatch('deliveryBoyBank/doUpdateDeliveryBoyBank', [tempData, this.temp.deliveryBoyBankId])
      },
      createDeliveryBoyBankInStore () {
        this.$store.dispatch('deliveryBoyBankDetails/doCreateDeliveryBoyBank', this.temp)
      },
    },
  }
</script>
