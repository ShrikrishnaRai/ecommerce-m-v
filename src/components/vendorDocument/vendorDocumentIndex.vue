<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="vendorDocumentDataTableHeaders"
        :items="vendorDocumentList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title> VendorDocuments</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search VendorDocument's"
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
                  Add new vendor Document
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline">{{ getFormTitle }}</span>
                </v-card-title>
                <v-card-text>
                  <v-container>
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
                          placeholder="Select PAN document"
                          prepend-icon="mdi-paperclip"
                          outlined
                          :show-size="1000"
                        >
                          <template v-slot:selection="{ index, text }">
                            <v-chip
                              v-if="vendorDocumentIndex < 2"
                              color="deep-purple accent-4"
                              dark
                              label
                              small
                            >
                              {{ text }}
                            </v-chip>

                            <span
                              v-else-if="vendorDocumentIndex === 2"
                              class="overline grey--text text--darken-3 mx-2"
                            >
                              +{{ files.length - 2 }} File(s)
                            </span>
                          </template>
                        </v-file-input>
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
                          placeholder="Select Tax Document"
                          prepend-icon="mdi-paperclip"
                          outlined
                          :show-size="1000"
                        >
                          <template v-slot:selection="{ index, text }">
                            <v-chip
                              v-if="vendorDocumentIndex < 2"
                              color="deep-purple accent-4"
                              dark
                              label
                              small
                            >
                              {{ text }}
                            </v-chip>

                            <span
                              v-else-if="vendorDocumentIndex === 2"
                              class="overline grey--text text--darken-3 mx-2"
                            >
                              +{{ files.length - 2 }} File(s)
                            </span>
                          </template>
                        </v-file-input>
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
                          placeholder="Select VAT Document"
                          prepend-icon="mdi-paperclip"
                          outlined
                          :show-size="1000"
                        >
                          <template v-slot:selection="{ index, text }">
                            <v-chip
                              v-if="vendorDocumentIndex < 2"
                              color="deep-purple accent-4"
                              dark
                              label
                              small
                            >
                              {{ text }}
                            </v-chip>

                            <span
                              v-else-if="vendorDocumentIndex === 2"
                              class="overline grey--text text--darken-3 mx-2"
                            >
                              +{{ files.length - 2 }} File(s)
                            </span>
                          </template>
                        </v-file-input>
                      </v-col>
                    </v-row>
                    <v-row>
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
            @click="exploreVendorDocument(item)"
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
            @click="deleteVendorDocumentInStore(item)"
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
    name: 'VendorDocument',
    components: { Multiselect },
    data () {
      return {
        search: '',
        statusList: ['Published', 'Pending', 'Draft'],
        dialog: this.$store.state.vendorDocument.dialog,
        persistIndex: this.$store.state.vendorDocument.persistIndex,
        temp: this.$store.state.vendorDocument.temp,
        defaultItem: this.$store.state.vendorDocument.defaultItem,
      }
    },
    computed: {
      ...mapGetters('vendorDocument', {
        vendorDocumentDataTableHeaders: 'vendorDocumentHeader',
        vendorDocumentList: 'vendorDocumentList',
      }),
      getProductMergerListFromProductStore () {
        return this.$store.state.product.productMergerList
      },
      getFormTitle () {
        return this.persistIndex === -1 ? 'Insert VendorDocument Information' : 'Edit VendorDocument'
      },
      getPersistTitle () {
        return this.persistIndex === -1 ? 'Save' : 'Save Changes'
      },
      isLoading () {
        return this.$store.state.vendorDocument.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('vendorDocument/doGetVendorDocumentList')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      exploreVendorDocument (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deleteVendorDocumentInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('vendorDocument/doDeleteVendorDocument', item)
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
          this.updateVendorDocumentToStore()
        } else {
          this.createVendorDocumentInStore()
        }
        this.closeDialogBox()
      },
      updateVendorDocumentToStore () {
        const tempData = Object.assign(this.vendorDocumentList[this.persistIndex], this.temp)
        this.$store.dispatch('vendorDocument/doUpdateVendorDocument', [tempData, this.temp.vendorDocumentId])
      },
      createVendorDocumentInStore () {
        this.$store.dispatch('vendorDocument/doCreateVendorDocument', this.temp)
      },
    },
  }
</script>
