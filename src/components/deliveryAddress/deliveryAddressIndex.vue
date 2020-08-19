<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="headers"
        :items="deliveryAddressList"
        sort-by=""
        class="elevation-1 ma-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>DeliveryAddress</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search delivery address"
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
            @click="exploreItem(item)"
          >
            explore
          </v-icon>
          <v-icon
            color="red"
            small
            @click="deleteItem(item)"
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
    name: 'DeliveryAddress',
    data () {
      return {
        search: '',
        dialog: this.$store.state.deliveryAddress.dialog,
        editedIndex: this.$store.state.deliveryAddress.editedIndex,
        editedItem: this.$store.state.deliveryAddress.editedItem,
        defaultItem: this.$store.state.deliveryAddress.defaultItem,
      }
    },
    created () {
      this.$store.dispatch('deliveryAddress/doGetDeliveryAddressList')
    },
    mounted () {
    },
    computed: {
      ...mapGetters('deliveryAddress', {
        headers: 'deliveryAddressHeader',
        deliveryAddressList: 'deliveryAddressList',
      }),
      isLoading () {
        return this.$store.state.deliveryAddress.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    methods: {
      close () {
        this.dialog = false
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 100)
      },
      deleteItem (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('deliveryAddress/doDeleteDeliveryAddress', item)
      },
    },
  }
</script>

<style scoped>

</style>
