<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="headers"
        :items="vendorList"
        sort-by=""
        class="elevation-1 ma-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Vendor's</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search Vendor's"
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
                  v-on="on"
                >
                  Add new vendor
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline">{{ formTitle }}</span>
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
                          v-model="editedItem.brandName"
                          outlined
                          label="Brand Name"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          v-model="editedItem.companyName"
                          outlined
                          label="Company Name"
                        />
                      </v-col>
                      <!--                                            <v-col cols="12" sm="12" md="12">-->
                      <!--                                                <v-select-->
                      <!--                                                        prepend-inner-icon="stay_current_portrait"-->
                      <!--                                                        :items="statusList"-->
                      <!--                                                        v-model="editedItem.status"-->
                      <!--                                                        label="Status"-->
                      <!--                                                        outlined-->
                      <!--                                                ></v-select>-->
                      <!--                                            </v-col>-->
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
                          placeholder="Select brand icon"
                          prepend-icon="mdi-paperclip"
                          outlined
                          :show-size="1000"
                        >
                          <template v-slot:selection="{ index, text }">
                            <v-chip
                              v-if="vendorIndex < 2"
                              color="deep-purple accent-4"
                              dark
                              label
                              small
                            >
                              {{ text }}
                            </v-chip>

                            <span
                              v-else-if="vendorIndex === 2"
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
                    @click="close"
                  >
                    Cancel
                  </v-btn>
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="save"
                  >
                    Save
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
            @click="exploreItem(item)"
          >
            explore
          </v-icon>
          <v-icon
            small
            class="mr-2"
            @click="editItem(item)"
          >
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
    name: 'Vendor',
    data () {
      return {
        search: '',
        dialog: this.$store.state.vendor.dialog,
        editedIndex: this.$store.state.vendor.editedIndex,
        editedItem: this.$store.state.vendor.editedItem,
        defaultItem: this.$store.state.vendor.defaultItem,
        address: {
          area: '',
          country: '',
          district: '',
          state: '',
          street: '',
          zipCode: '',
        },
        businessType: '',
        commissionAmount: 0,
        commissionMergerDTO: {
          commissionDescription: '',
          commissionTypeId: 0,
        },
        contact: {
          contactNo: '',
          email: '',
        },
        id: 0,
        image: '',
        latitude: '',
        longitude: '',
        panNo: '',
        password: '',
        status: '',
        taxClearanceNo: '',
        username: '',
        vendorName: '',
        vendorType: '',
      }
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    created () {
      this.$store.dispatch('vendor/doGetVendorsLists')
    },
    methods: {
      save () {
        if (this.editedIndex > -1) {
          const tempData = Object.assign(this.vendorList[this.editedIndex], this.editedItem)
          this.$store.dispatch('vendor/doUpdateVendor', tempData)
        } else {
          this.$store.dispatch('vendor/doCreateVendor', this.editedItem)
        }
        this.close()
      },
      close () {
        this.dialog = false
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 100)
      },
      deleteItem (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('vendor/doDeleteVendor', item)
      },
    },
    computed: {
      ...mapGetters('vendor', {
        headers: 'vendorHeader',
        vendorList: 'vendorList',
      }),
      vendorList () {
        return this.$store.state.vendor.vendorList
      },
      formTitle () {
        return this.editedIndex === -1 ? 'Insert Vendor Information' : 'Edit Vendor'
      },
      isLoading () {
        return this.$store.state.vendor.isLoading
      },
    },
  }
</script>
