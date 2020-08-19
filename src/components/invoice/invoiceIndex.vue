<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :headers="headers"
        :items="invoiceList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Invoice</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-spacer />
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
                  Add new invoice
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
                          outlined
                          label="Company Name"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-select
                          :items="statusList"
                          label="Status"
                          outlined
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          outlined
                          label="Slug"
                        />
                      </v-col>
                    </v-row>
                    <v-row no-gutters>
                      <v-col
                        cols="12"
                        sm="12"
                      >
                        <v-file-input
                          v-model="files"
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
                              v-if="invoiceIndex < 2"
                              color="deep-purple accent-4"
                              dark
                              label
                              small
                            >
                              {{ text }}
                            </v-chip>

                            <span
                              v-else-if="invoiceIndex === 2"
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
        <template v-slot:item.action="{item}">
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
  import { DELETE_WARNING } from '@/utils/CONST'
  import { mapGetters } from 'vuex'

  export default {
    name: 'Invoice',
    data () {
      return {
        dialog: this.$store.invoice.dialog,
        editedItem: this.$store.state.invoice.editedItem,
        defaultItem: this.$store.state.invoice.defaultItem,
      }
    },
    created () {
      this.$store.dispatch('invoice/doGetInvoiceList')
    },
    mounted () {
    },
    computed: {
      ...mapGetters('invoice', {
        headers: 'invoiceHeader',
        invoiceList: 'invoiceList',
      }),
      invoiceList () {
        return this.$store.state.invoice.invoiceList
      },
      formTitle () {
        return this.editedIndex === -1 ? 'Insert Invoice Information' : 'Edit Invoice'
      },
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    methods: {
      editItem (item) {
        this.editedIndex = this.invoiceList.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },
      deleteItem (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('invoice/doDeleteInvoice', item)
      },
      close () {
        this.dialog = false
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 300)
      },
      save () {
        if (this.editedIndex > -1) {
          const tempData = Object.assign(this.invoiceList[this.editedIndex], this.editedItem)
          this.$store.dispatch('invoice/doUpdateInvoice', tempData)
          console.log(this.editedItem, 'update')
        } else {
          this.$store.dispatch('invoice/doCreateInvoice', this.editedItem)
          console.log(this.editedItem, 'created')
        }
        this.close()
      },
    },
  }
</script>

<style scoped>

</style>
