<template>
  <div>
    <div
      v-if="isLoading"
      class="content-wrapper"
    >
      <facebook-loader
        class="pa-md-4"
        :speed="2"
      /><facebook-loader
        class="pa-md-4"
        :speed="2"
      />
    </div>
    <div v-else>
      <v-app id="inspire">
        <v-data-table
          :search="search"
          :loading="isLoading"
          :headers="headers"
          :items="productList"
          sort-by=""
          class="elevation-1 ma-2"
        >
          <template v-slot:top>
            <v-toolbar
              flat
              color="white"
            >
              <v-toolbar-title>Products</v-toolbar-title>
              <v-divider
                class="mx-4"
                inset
                vertical
              />
              <v-text-field
                v-model="search"
                outlined
                append-icon="mdi-magnify"
                label="Search Product's"
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
                max-width="100px"
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
                    <v-toolbar-title>Product </v-toolbar-title>
                    <v-spacer />
                    <v-toolbar-items>
                      <v-btn
                        dark
                        text
                        @click="closeDialogBox"
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
                            sm="3"
                            md="2"
                          >
                            <v-text-field
                              v-model="defaultItem.productName"
                              outlined
                              label="Product Name"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <multiselect
                              v-model="temp.brandMergerDTO"
                              placeholder="Search brand"
                              label="brandName"
                              track-by="brandId"
                              :options="getBrandMergerListFromBrandStore"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-select
                              v-model="defaultItem.productType"
                              :items="productType"
                              outlined
                              label="Product Type"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-text-field
                              v-model="defaultItem.productCode"
                              outlined
                              label="Product Code"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-text-field
                              v-model="defaultItem.purchasePrice"
                              outlined
                              type="number"
                              label="Purchase price"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-text-field
                              v-model="defaultItem.salePrice"
                              type="number"
                              outlined
                              label="Sale price"
                            />
                          </v-col>
                        </v-row>
                        <v-row>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-text-field
                              v-model="defaultItem.productStockMergerDTO.totalQuantity"
                              type="number"
                              outlined
                              label="Quantity"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-text-field
                              v-model="defaultItem.productStockMergerDTO.remainingQuantity"
                              type="number"
                              outlined
                              label="Remaining Quantity"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <multiselect
                              v-model="temp.productBundle"
                              placeholder="Add to Bundle"
                              label="productBundleName"
                              track-by="productBundleId"
                              :options="getProductBundleMergerListFromBundleStore"
                              :multiple="true"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <multiselect
                              v-model="temp.offerMergerDTO"
                              placeholder="Add to Offer"
                              label="offerName"
                              track-by="offerId"
                              :options="getProductOfferMergerListFromOfferStore"
                              :multiple="true"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-select
                              v-model="defaultItem.discountType"
                              :items="discountType"
                              outlined
                              label="Discount Type"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-select
                              prepend-inner-icon="stay_current_portrait"
                              :items="statusList"
                              outlined
                              label="Status"
                            />
                          </v-col>
                        </v-row>
                        <v-row>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-text-field
                              v-model="defaultItem.discountValue"
                              type="number"
                              outlined
                              label="Discount Value"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-text-field
                              v-model="defaultItem.packagingFee"

                              type="number"
                              outlined
                              label="Packaging Fee"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-text-field
                              v-model="defaultItem.unit"

                              type="number"
                              outlined
                              label="Unit"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-text-field
                              v-model="defaultItem.quality"
                              outlined
                              label="Quality"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <multiselect
                              v-model="temp.brandMergerDTO"
                              placeholder="Search brand"
                              label="brandName"
                              track-by="brandId"
                              :options="getBrandMergerListFromBrandStore"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <multiselect
                              v-model="temp.colorMergerDTOList"
                              placeholder="Add color"
                              label="colorName"
                              track-by="colorId"
                              :options="getColorMergerListFromCategoryStore"
                              :multiple="true"
                            />
                          </v-col>
                        </v-row>
                        <v-row>
                          <template v-if="temp.taxable">
                            <v-col>
                              <v-text-field
                                v-model="temp.taxAmount"
                                type="number"
                                outlined
                                label="Tax Amount"
                              />
                            </v-col>
                            <v-col>
                              <v-select
                                v-model="temp.taxType"
                                :items="taxType"
                                outlined
                                label="Tax Type"
                              />
                            </v-col>
                          </template>
                          <template v-if="temp.prebooking">
                            <v-col>
                              <v-text-field
                                v-model="temp.preBookingMergerDTOS.quantity"

                                type="number"
                                outlined
                                label="Prebooking Quantity"
                              />
                            </v-col>
                          </template>
                        </v-row>
                        <v-row>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-checkbox
                              v-model="temp.taxable"
                              outlined
                              label="Taxable"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <v-checkbox
                              v-model="temp.prebooking"
                              outlined
                              label="Prebooking"
                            />
                          </v-col>

                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <multiselect
                              v-model="temp.sizeMergerDTOList"
                              placeholder="Add size"
                              label="sizeName"
                              track-by="sizeId"
                              :options="getSizeMergerListFromSizeStore"
                              :multiple="true"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="3"
                            md="2"
                          >
                            <multiselect
                              v-model="temp.category"
                              placeholder="Search category"
                              label="categoryName"
                              track-by="categoryId"
                              :options="getCategoryMergerListFromCategoryStore"
                            />
                          </v-col>
                          <v-col
                            cols="12"
                            sm="6"
                            md="4"
                          >
                            <v-textarea
                              v-model="temp.description"
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
                    Add new Product
                  </v-btn>
                </template>
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
              @click="deleteProductInStore(item)"
            >
              delete
            </v-icon>
          </template>
        </v-data-table>
        <template>
          <v-row>
            <v-col
              md="7"
              sm="12"
            >
              <brand />
            </v-col> <v-col
              md="5"
              sm="12"
            >
              <size />
            </v-col>
          </v-row>
          <v-row>
            <v-col
              md="6"
              sm="12"
            >
              <color />
            </v-col>
          </v-row>
        </template>
      </v-app>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { DELETE_WARNING } from '@/utils/CONST'
  import Color from '@/components/color/colorIndex'
  import Brand from '@/components/brands/brandIndex'
  import Size from '@/components/size/sizeindex'
  import Multiselect from 'vue-multiselect'
  import { FacebookLoader } from 'vue-content-loader'

  export default {
    name: 'Product',
    components: { Color, Brand, Multiselect, Size, FacebookLoader },
    data () {
      return {
        discountType: ['Percent', 'Fixed'],
        productType: ['Digital Product', 'Physical Product'],
        statusList: ['Available', 'Out of stock'],
        taxType: ['Percent', 'Fixed'],
        search: '',
        dialog: this.$store.state.product.dialog,
        persistIndex: this.$store.state.product.persistIndex,
        temp: this.$store.state.product.temp,
        defaultItem: this.$store.state.product.defaultItem,
      }
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('product/doGetProductsLists')
    },
    computed: {
      ...mapGetters('product', {
        headers: 'productHeader',
        productList: 'productList',
      }),
      getBrandMergerListFromBrandStore () {
        return this.$store.state.brands.brandList
      },
      getCategoryMergerListFromCategoryStore () {
        return this.$store.state.category.categoryList
      },
      getColorMergerListFromCategoryStore () {
        return this.$store.state.color.colorList
      },
      getSizeMergerListFromSizeStore () {
        return this.$store.state.size.sizeList
      },
      getProductBundleMergerListFromBundleStore () {
        return this.$store.state.productBundle.productBundleList
      },
      getProductOfferMergerListFromOfferStore () {
        return this.$store.state.offers.offerList
      },
      productsList () {
        return this.$store.state.product.productList
      },
      isLoading () {
        return this.$store.state.product.isLoading
      },
      getFormTitle () {
        return this.editedIndex === -1 ? 'Insert Product Information' : 'Edit Product'
      },
      getPersistTitle () {
        return this.persistIndex === -1 ? 'Save' : 'Save Changes'
      },
    },
    methods: {
      getProductWithId () {
        this.$store.dispatch('product/fetchProductWithId')
      },
      exploreItem (item) {
        console.log(item)
      },
      editItem (item) {
        this.editedItem = this.productList.indexOf(item)
        this.editedIndex = Object.assign({}, item)
        this.dialog = true
      },
      deleteProductInStore (item) {
        confirm(DELETE_WARNING) && this.$store.dispatch('products/doDeleteProducts', item)
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
    },
  }
</script>
