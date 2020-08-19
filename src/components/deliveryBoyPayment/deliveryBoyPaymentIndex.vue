<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="deliveryBoyPaymentDataTableHeaders"
        :items="deliveryBoyPaymentList"
        sort-by=""
        class="elevation-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title> DeliveryBoyPayments</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search DeliveryBoyPayment's"
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
            @click="exploreDeliveryBoyPayment(item)"
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
            @click="deleteDeliveryBoyPaymentInStore(item)"
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
    name: 'DeliveryBoyPayment',
    data () {
      return {
        search: '',
        statusList: ['Published', 'Pending', 'Draft'],
        dialog: this.$store.state.deliveryBoyPayment.dialog,
        persistIndex: this.$store.state.deliveryBoyPayment.persistIndex,
        temp: this.$store.state.deliveryBoyPayment.temp,
        defaultItem: this.$store.state.deliveryBoyPayment.defaultItem,
      }
    },
    computed: {
      ...mapGetters('deliveryBoyPayment', {
        deliveryBoyPaymentDataTableHeaders: 'deliveryBoyPaymentHeader',
        deliveryBoyPaymentList: 'deliveryBoyPaymentList',
      }),
      getProductMergerListFromProductStore () {
        return this.$store.state.product.productMergerList
      },
      getFormTitle () {
        return this.persistIndex === -1 ? 'Insert DeliveryBoyPayment Information' : 'Edit DeliveryBoyPayment'
      },
      getPersistTitle () {
        return this.persistIndex === -1 ? 'Save' : 'Save Changes'
      },
      isLoading () {
        return this.$store.state.deliveryBoyPayment.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('deliveryBoyPayment/doGetDeliveryBoyPaymentList')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      exploreDeliveryBoyPayment (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deleteDeliveryBoyPaymentInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('deliveryBoyPayment/doDeleteDeliveryBoyPayment', item)
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
          this.updateDeliveryBoyPaymentToStore()
        } else {
          this.createDeliveryBoyPaymentInStore()
        }
        this.closeDialogBox()
      },
      updateDeliveryBoyPaymentToStore () {
        const tempData = Object.assign(this.deliveryBoyPayment[this.persistIndex], this.temp)
        this.$store.dispatch('deliveryBoyPayment/doUpdateDeliveryBoyPayment', [tempData, this.temp.deliveryBoyPaymentId])
      },
      createDeliveryBoyPaymentInStore () {
        this.$store.dispatch('deliveryBoyPayment/doCreateDeliveryBoyPayment', this.temp)
      },
    },
  }
</script>
