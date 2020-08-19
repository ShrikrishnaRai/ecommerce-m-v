<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="headers"
        :items="productBundlesList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Product Bundle</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search Product bundle's"
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
              <template v-slot:activator="{on}">
                <v-btn
                  color="primary"
                  dark
                  class="mb-2"
                  v-on="on"
                >
                  Add new Product Bundle
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
                          v-model="temp.productBundleCode"
                          outlined
                          label="Product Bundle Code"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          v-model="temp.productBundleName"
                          outlined
                          label="Product Bundle Name"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          v-model="temp.productBundlePrice"
                          outlined
                          label="Product Bundle price"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          v-model="temp.remainingQuantity"
                          outlined
                          label="Remaining Quantity"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-select
                          v-model="temp.status"
                          prepend-inner-icon="stay_current_potrait"
                          :items="statusList"
                          outlined
                          label="Status"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          v-model="temp.tag"
                          outlined
                          label="Tag"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          v-model="temp.taxAmount"
                          outlined
                          label="Tax Amount"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          v-model="temp.totalQuantity"
                          outlined
                          label="Quantity"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-checkbox
                          v-model="temp.taxable"
                          outlined
                          label="Taxable"
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
      </v-data-table>
    </v-app>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'

  export default {
    name: 'ProductBundle',
    data () {
      return {
        search: '',
        statusList: ['Available', 'Out of stock'],
        dialog: this.$store.state.productBundle.dialog,
        temp: this.$store.state.productBundle.temp,
        persistIndex: this.$store.state.productBundle.persistIndex,
        defaultItem: this.$store.state.productBundle.defaultItem,
      }
    },
    created () {
      this.$store.dispatch('productBundle/doGetProductBundleLists')
    },
    methods: {
      close () {
        this.dialog = false
        setTimeout(() => {
          this.temp = Object.assign({}, this.defaultItem)
          this.persistIndex = -1
        }, 100)
      },
    },
    computed: {
      ...mapGetters('productBundle', {
        headers: 'productBundleHeader',
        productBundleList: 'productBundleList',
      }),
      productBundlesList () {
        return this.$store.state.productBundle.list
      },
      formTitle () {
        return this.persistIndex === -1 ? 'Insert Product Bundle Information' : 'Edit Product Bundle Informaion'
      },
      isLoading () {
        return this.$store.state.productBundle.isLoading
      },
    },
  }
</script>
