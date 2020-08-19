<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="headers"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Offers</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search Brand's"
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
              hide-overlay
              fullscreen
              transition="dialog-bottom-transition"
              max-width="500px"
            >
              <v-card tile>
                <v-toolbar
                  flat
                  dark
                  color="primary"
                >
                  <v-btn
                    icon
                    dark
                    @click="dialog = false"
                  >
                    <v-icon>mdi-close</v-icon>
                  </v-btn>
                  <v-toolbar-title>Product Offers</v-toolbar-title>
                  <v-spacer />
                  <v-toolbar-items>
                    <v-btn
                      dark
                      text
                      @click="close"
                    >
                      Cancel
                    </v-btn>
                    <v-btn
                      dark
                      text
                      @click="save"
                    >
                      Save
                    </v-btn>
                  </v-toolbar-items>
                </v-toolbar>
                <v-card>
                  <v-card-text>
                    <v-container>
                      <v-card-title
                        class="mx-auto"
                      >
                        <span class="headline">{{ formTitle }}</span>
                      </v-card-title>
                      <v-row>
                        <v-col
                          cols="12"
                          sm="4"
                          md="4"
                        >
                          <v-text-field
                            v-model="editedItem.offerName"
                            outlined
                            label="Offer Name"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="4"
                          md="4"
                        >
                          <v-text-field
                            v-model="editedItem.discountType"
                            outlined
                            label="Discount Type"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="4"
                          md="4"
                        >
                          <v-text-field
                            v-model="editedItem.discountValue"
                            outlined
                            label="Discount Value"
                          />
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col
                          cols="12"
                          sm="4"
                          md="4"
                        >
                          <v-text-field
                            v-model="editedItem.minimumOrder"
                            outlined
                            label="Minimum Order"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="4"
                          md="4"
                        >
                          <v-text-field
                            v-model="editedItem.productMergerDTOList"
                            outlined
                            label="Products"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="4"
                          md="4"
                        >
                          <v-text-field
                            v-model="editedItem.vendorMergerDTO"
                            outlined
                            label="Vendors"
                          />
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col
                          cols="12"
                          sm="4"
                          md="4"
                        >
                          <v-select
                            v-model="editedItem.status"
                            prepend-inner-icon="stay_current_portrait"
                            :items="statusList"
                            outlined
                            label="Status"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="4"
                          md="4"
                        >
                          <v-menu
                            ref="menu"
                            v-model="menu"
                            :close-on-content-click="false"
                            :return-value.sync="date"
                            transition="scale-transition"
                            offset-y
                            min-width="290px"
                          >
                            <template v-slot:activator="{ on }">
                              <v-text-field
                                v-model="date"
                                label="Start Date"
                                prepend-icon="event"
                                readonly
                                v-on="on"
                              />
                            </template>
                            <v-date-picker
                              v-model="date"
                              no-title
                              scrollable
                            >
                              <v-spacer />
                              <v-btn
                                text
                                color="primary"
                                @click="menu = false"
                              >
                                Cancel
                              </v-btn>
                              <v-btn
                                text
                                color="primary"
                                @click="$refs.menu.save(date)"
                              >
                                OK
                              </v-btn>
                            </v-date-picker>
                          </v-menu>
                        </v-col>
                        <v-col
                          cols="12"
                          sm="6"
                          md="4"
                        >
                          <v-menu
                            ref="menu2"
                            v-model="menu2"
                            :close-on-content-click="false"
                            :return-value.sync="endDate"
                            transition="scale-transition"
                            offset-y
                            min-width="290px"
                          >
                            <template v-slot:activator="{ on }">
                              <v-text-field
                                v-model="endDate"
                                label="End Date"
                                prepend-icon="event"
                                readonly
                                v-on="on"
                              />
                            </template>
                            <v-date-picker
                              v-model="endDate"
                              no-title
                              scrollable
                            >
                              <v-spacer />
                              <v-btn
                                text
                                color="primary"
                                @click="menu2 = false"
                              >
                                Cancel
                              </v-btn>
                              <v-btn
                                text
                                color="primary"
                                @click="$refs.menu2.save(endDate)"
                              >
                                OK
                              </v-btn>
                            </v-date-picker>
                          </v-menu>
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col
                          cols="12"
                          sm="12"
                          md="12"
                        >
                          <v-textarea
                            v-model="editedItem.description"
                            outlined
                            label="Description"
                          />
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>
                </v-card>
              </v-card>
              <template v-slot:activator="{on}">
                <v-btn
                  color="primary"
                  dark
                  class="mb-2"
                  v-on="on"
                >
                  Add new Offer
                </v-btn>
              </template>
            </v-dialog>
          </v-toolbar>
        </template>
        <template v-slot:item.action="{item}">
          <v-icon
            class="mr-2"
            small
            @click="exploreItem(item)"
          >
            explore
          </v-icon>
          <v-icon
            class="mr-2"
            small
            @click="editItem(item)"
          >
            menu: false,
            modal: false,
            menu2: false,
            edit
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
    name: 'Offers',

    data () {
      return {
        // date: null,
        // menu: false,
        // modal: false,
        statusList: ['Publish', 'Pending', 'Expired'],
        search: '',
        editedItem: this.$store.state.offers.offerEditedItem,
        editedIndex: this.$store.state.offers.editedIndex,
        dialog: this.$store.state.offers.dialog,
        defaultItem: this.$store.state.offers.offerDefaultItem,
        date: new Date().toISOString().substr(0, 10),
        endDate: new Date().toISOString().substr(0, 10),
        menu: false,
        modal: false,
        menu2: false,
        endMenu: false,
      }
    },

    created () {
      this.$store.dispatch('offers/doGetOfferLists')
    },
    computed: {
      ...mapGetters('offers', {
        headers: 'offerHeader',
        offerList: 'offerList',
      }),
      formTitle () {
        return this.editedIndex === -1 ? 'Insert Offer Information' : 'Edit Offer'
      },
      isLoading () {
        return this.$store.state.offers.isLoading
      },

    },
    watch: {
      dialog (val) {
        val || this.close()
      },

    },
    methods: {
      exploreItem (item) {
        console.log(item)
      },
      close () {
        this.dialog = false
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 100)
      },
      save () {
        if (this.editedIndex > -1) {
          const tempData = Object.assign(this.offerList[this.editedIndex], this.editedItem)
          this.$store.dispatch('offers/doUpdateOffer', tempData)
          console.log(this.editedItem, 'update')
        } else {
          this.$store.dispatch('offers/doCreateOffer', this.editedItem)
          console.log(this.editedItem, 'created')
        }
        this.close()
      },
      editItem (item) {
        this.editedIndex = this.offerList.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },
      deleteItem (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('offers/doDeleteOffer', item)
      },

    },
  }
</script>

<style scoped>

</style>
