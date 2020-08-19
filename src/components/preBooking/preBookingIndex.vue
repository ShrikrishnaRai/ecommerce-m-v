<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="headers"
        :items="preBookingList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Pre Booking items</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search Prebooking's item"
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
                  Add new pre booking
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline">{{ getFormTitle(persistIndex,'Pre Booking') }}</span>
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
                          v-model="temp.price"
                          class="ma-1"
                          outlined
                          label="Price"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          v-model="temp.quantity"
                          class="ma-1"
                          outlined
                          label="Quantity"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          v-model="temp.totalPrice"
                          class="ma-1"
                          outlined
                          label="Total price"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="12"
                        md="6"
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
                          :options="getProductMergerListFromProductStore()"
                          :multiple="true"
                        />
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
                    {{ getPersistTitle() }}
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
            @click="explorePreBooking(item)"
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
            @click="deletePreBookingInStore(item)"
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
  import utils from '@/mixins/utils'
  import merger from '@/mixins/merger'

  export default {
    name: 'PreBooking',
    components: { Multiselect },
    mixins: [utils, merger],
    data () {
      return {
        search: '',
        statusList: ['Active', 'Cancelled'],
        dialog: this.$store.state.preBooking.dialog,
        persistIndex: this.$store.state.preBooking.persistIndex,
        temp: this.$store.state.preBooking.temp,
        defaultItem: this.$store.state.preBooking.defaultItem,
      }
    },
    computed: {
      ...mapGetters('preBooking', {
        headers: 'preBookingHeaders',
        preBookingList: 'preBookingList',
      }),
      isLoading () {
        return this.$store.state.preBooking.isLoading
      },
    },
    created () {
      this.$store.dispatch('preBooking/doGetPreBookingList')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      explorePreBooking (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deletePreBookingInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('preBooking/doDeletePreBooking', item)
      },
      onPersistAction () {
        if (this.persistIndex > -1) {
          this.updatePreBookingToStore()
        } else {
          this.createPreBookingInStore()
        }
        this.closeDialogBox()
      },
      updatePreBookingToStore () {
        const tempData = Object.assign(this.brandList[this.persistIndex], this.temp)
        this.$store.dispatch('preBooking/doUpdatePreBooking', [tempData, this.temp.brandId])
      },
      createPreBookingInStore () {
        this.$store.dispatch('preBooking/doCreatePreBooking', this.temp)
      },
    },
  }
</script>

<style scoped>

</style>
