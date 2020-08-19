<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="colorDataTableHeaders"
        :items="colorList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Colors</v-toolbar-title>
            <v-spacer />
            <v-divider
              class="mx-2"
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
                  Add new color
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
                          v-model="temp.colorName"
                          class="ma-1"
                          outlined
                          label="Color Name"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-text-field
                          v-model="temp.colorCode"
                          class="ma-1"
                          outlined
                          label="Color code"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="12"
                        md="12"
                      >
                        <multiselect
                          v-model="temp.productMergerDTOS"
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
            @click="exploreColor(item)"
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
            @click="deleteColorInStore(item)"
          >
            delete
          </v-icon>
        </template>
      </v-data-table>
    </v-app>
  </div>
</template>

<script>
  import Multiselect from 'vue-multiselect'
  import { DELETE_WARNING } from '@/utils/CONST'
  import { mapGetters } from 'vuex'

  export default {
    name: 'Color',
    components: { Multiselect },

    data () {
      return {
        search: '',
        dialog: this.$store.state.color.dialog,
        persistIndex: this.$store.state.color.persistIndex,
        temp: this.$store.state.color.temp,
        defaultItem: this.$store.state.color.defaultItem,
      }
    },
    computed: {
      ...mapGetters('color', {
        colorDataTableHeaders: 'colorHeader',
        colorList: 'colorList',
      }),
      getProductMergerListFromProductStore () {
        return this.$store.state.product.productMergerList
      },
      getFormTitle () {
        return this.persistIndex === -1 ? 'Insert Color Information' : 'Edit Color'
      },
      getPersistTitle () {
        return this.persistIndex === -1 ? 'Save' : 'Save Changes'
      },
      isLoading () {
        return this.$store.state.color.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('color/doGetColorList')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      exploreColor (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deleteColorInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('color/doDeleteColor', item)
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
          this.updateColorToStore()
        } else {
          this.createColorInStore()
        }
        this.closeDialogBox()
      },
      updateColorToStore () {
        const tempData = Object.assign(this.colorList[this.persistIndex], this.temp)
        this.$store.dispatch('color/doUpdateColor', [tempData, this.temp.colorId])
      },
      createColorInStore () {
        this.$store.dispatch('color/doCreateColor', this.temp)
      },
    },

  }

</script>

<style scoped>

</style>
