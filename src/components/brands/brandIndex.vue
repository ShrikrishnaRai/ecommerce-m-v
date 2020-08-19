<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="brandsDataTableHeaders"
        :items="brandList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title> Brands</v-toolbar-title>
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
                  Add new brand
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline">{{ getFormTitle(persistIndex,'Brand') }}</span>
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
                          v-model="temp.brandName"
                          class="ma-1"
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
                          :options="getProductMergerListFromProductStore()"
                          :multiple="true"
                        />
                      </v-col>
                    </v-row>
                    <v-row no-gutters>
                      <v-col
                        cols="12"
                        sm="12"
                      >
                        <img
                          v-if="imageUrl"
                          :src="imageUrl"
                          height="150"
                        >
                        <v-text-field
                          v-model="imageName"
                          label="Select Image"
                          prepend-icon="attach_file"
                          @click="pickFile"
                        />
                        <input
                          ref="image"
                          type="file"
                          style="display: none"
                          accept="image/*"
                          @change="onFilePicked"
                        >
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
            @click="exploreBrand(item)"
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
            @click="deleteBrandInStore(item)"
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
    name: 'Brand',
    components: { Multiselect },
    mixins: [utils, merger],
    data () {
      return {
        files: [],
        search: '',
        statusList: ['Published', 'Pending', 'Draft'],
        dialog: this.$store.state.brands.dialog,
        persistIndex: this.$store.state.brands.persistIndex,
        temp: this.$store.state.brands.temp,
        defaultItem: this.$store.state.brands.defaultItem,
        image: null,

        imageName: '',
        imageUrl: '',
        imageFile: '',
      }
    },
    computed: {
      ...mapGetters('brands', {
        brandsDataTableHeaders: 'brandHeader',
        brandList: 'brandList',
      }),
      isLoading () {
        return this.$store.state.brands.isLoading
      },
    },
    created () {
      this.$store.dispatch('brands/doGetBrandList')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      exploreBrand (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deleteBrandInStore (brandId) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('brands/doDeleteBrand', brandId)
      },
      onPersistAction () {
        if (this.persistIndex > -1) {
          this.updateBrandToStore()
        } else {
          this.createBrandInStore()
        }
        this.closeDialogBox()
      },
      updateBrandToStore () {
        const tempData = Object.assign(this.brandList[this.persistIndex], this.temp)
        this.$store.dispatch('brands/doUpdateBrand', [tempData, this.temp.brandId])
      },
      createBrandInStore () {
        alert(this.temp.fileDTO.base64.toString())
        this.$store.dispatch('brands/doCreateBrand', this.temp)
      },

      pickFile () {
        this.$refs.image.click()
      },

      onFilePicked (e) {
        const files = e.target.files
        if (files[0] !== undefined) {
          this.imageName = files[0].name
          if (this.imageName.lastIndexOf('.') <= 0) {
            return
          }
          const fr = new FileReader()
          fr.readAsDataURL(e.target.files[0])
          fr.addEventListener('load', () => {
            this.imageUrl = fr.result
            this.temp.fileDTO.base64 = fr.result.split(',')[1]
            this.temp.fileDTO.type = this.imageName.split('.').pop()
            this.temp.fileDTO.name = this.imageName.replace(/\.[^/.]+$/, '')
          })
        }
      },
    },
  }
</script>
