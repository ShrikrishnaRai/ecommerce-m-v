<template>
  <div>
    <template>
      <v-card>
        <v-tabs
          v-model="tab"
        >
          <v-tab
            v-for="item in items"
            :key="item.tab"
          >
            {{ item.tab }}
          </v-tab>
        </v-tabs>
        <v-tabs-items v-model="tab">
          <v-tab-item
            v-for="item in items"
            :key="item.tab"
          >
            <v-card flat>
              <v-card-text>
                <component :is="item.content" />
              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card>
    </template>
  </div>
</template>

<script>
  import deliveryBoy from '@/components/deliveryBoy/deliveryBoyIndex'
  import deliveryBoyDocumentDetails from '@/components/deliveryBoyDocumentDetails/deliveryBoyDocumentDetailsIndex'
  import deliveryBoyPayment from '@/components/deliveryBoyPayment/deliveryBoyPaymentIndex'
  import deliveryBoyWallet from '@/components/deliveryBoyWallet/deliveryBoyWalletIndex'
  import deliveryBoyBankDetails from '@/components/deliveryBoyBankDetails/deliveryBoyBankDetailsIndex'

  export default {
    name: 'DeliveryBoyLayout',
    components: {
      deliveryBoy,
      deliveryBoyDocumentDetails,
      deliveryBoyPayment,
      deliveryBoyBankDetails,
      deliveryBoyWallet,
    },
    data () {
      return {
        tab: null,
        items: [
          { tab: 'Delivery Boy', content: 'deliveryBoy' },
          { tab: 'Document', content: 'deliveryBoyDocumentDetails' },
          { tab: 'Payment', content: 'deliveryBoyPayment' },
          { tab: 'Bank Details', content: 'deliveryBoyBankDetails' },
          { tab: 'Wallet', content: 'deliveryBoyWallet' },
        ],
      }
    },
  }
</script>

<style scoped>

</style>
