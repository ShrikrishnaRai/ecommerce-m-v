<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="orderServiceDataTableHeaders"
        :items="orderServiceList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title> OrderServices</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search OrderService's"
              single-line
              hide-details
            />
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-dialog
              v-model="dialog"
              max-width="500px"
            >
              <template v-slot:activator="{ on }">
                <v-btn
                  color="primary"
                  dark
                  class="mb-2"
                  @click="dispatchProductMergerList"
                  v-on="on"
                >
                  Add new orderService
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline">{{ getFormTitle }}</span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          v-model="temp.orderServiceName"
                          class="ma-1"
                          outlined
                          label="OrderService Name"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          v-model="temp.companyName"
                          class="ma-1"
                          outlined
                          label="Company Name"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="12"
                        md="12"
                      >
                        <v-select
                          v-model="temp.status"
                          class="ma-1"
                          prepend-inner-icon="stay_current_portrait"
                          :items="statusList"
                          label="Status"
                          outlined
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="12"
                        md="12"
                      >
                        <multiselect
                          v-model="temp.productMergerDTOList"
                          placeholder="Search products"
                          label="productName"
                          track-by="productId"
                          :options="getProductMergerListFromProductStore"
                          :multiple="true"
                        />
                      </v-col>
                    </v-row>
                    <v-row no-gutters>
                      <v-col
                        cols="12"
                        sm="12"
                      >
                        <v-file-input
                          color="deep-purple accent-4"
                          counter
                          label="File input"
                          multiple
                          placeholder="Select orderService icon"
                          prepend-icon="mdi-paperclip"
                          outlined
                          :show-size="1000"
                        >
                          <template v-slot:selection="{ index, text }">
                            <v-chip
                              v-if="orderServiceIndex < 2"
                              color="deep-purple accent-4"
                              dark
                              label
                              small
                            >
                              {{ text }}
                            </v-chip>

                            <span
                              v-else-if="orderServiceIndex === 2"
                              class="overline grey--text text--darken-3 mx-2"
                            >
                              +{{ files.length - 2 }} File(s)
                            </span>
                          </template>
                        </v-file-input>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>
                <v-card-actions>
                  <v-spacer />
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="closeDialogBox"
                  >
                    Cancel
                  </v-btn>
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="onPersistAction"
                  >
                    {{ getPersistTitle }}
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>
        <template v-slot:item.action="{ item }">
          <v-icon
            class="mr-2"
            small
            @click="exploreOrderService(item)"
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
            @click="deleteOrderServiceInStore(item)"
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
  import Multiselect from 'vue-multiselect'

  export default {
    name: 'OrderService',
    components: { Multiselect },
    data () {
      return {
        search: '',
        statusList: ['Published', 'Pending', 'Draft'],
        dialog: this.$store.state.orderService.dialog,
        persistIndex: this.$store.state.orderService.persistIndex,
        temp: this.$store.state.orderService.temp,
        defaultItem: this.$store.state.orderService.defaultItem,
      }
    },
    computed: {
      ...mapGetters('orderService', {
        orderServiceDataTableHeaders: 'orderServiceHeader',
        orderServiceList: 'orderServiceList',
      }),
      getProductMergerListFromProductStore () {
        return this.$store.state.product.productMergerList
      },
      getFormTitle () {
        return this.persistIndex === -1 ? 'Insert OrderService Information' : 'Edit OrderService'
      },
      getPersistTitle () {
        return this.persistIndex === -1 ? 'Save' : 'Save Changes'
      },
      isLoading () {
        return this.$store.state.orderService.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('orderService/doGetOrderServiceList')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      exploreOrderService (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deleteOrderServiceInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('orderService/doDeleteOrderService', item)
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
          this.updateOrderServiceToStore()
        } else {
          this.createOrderServiceInStore()
        }
        this.closeDialogBox()
      },
      updateOrderServiceToStore () {
        const tempData = Object.assign(this.orderServiceList[this.persistIndex], this.temp)
        this.$store.dispatch('orderService/doUpdateOrderService', [tempData, this.temp.orderServiceId])
      },
      createOrderServiceInStore () {
        this.$store.dispatch('orderService/doCreateOrderService', this.temp)
      },
    },
  }
</script>
