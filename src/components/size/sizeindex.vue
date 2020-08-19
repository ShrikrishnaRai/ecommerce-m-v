<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="sizesDataTableHeaders"
        :items="sizeList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title> Sizes</v-toolbar-title>
            <v-spacer />
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
                  Add size
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
                        sm="12"
                        md="12"
                      >
                        <v-text-field
                          v-model="temp.size"
                          class="ma-1"
                          outlined
                          label="Size Name"
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
            @click="exploreSize(item)"
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
            @click="deleteSizeInStore(item)"
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
    name: 'Size',
    data () {
      return {
        search: '',
        statusList: ['Published', 'Pending', 'Draft'],
        dialog: this.$store.state.size.dialog,
        persistIndex: this.$store.state.size.persistIndex,
        temp: this.$store.state.size.temp,
        defaultItem: this.$store.state.size.defaultItem,
      }
    },
    computed: {
      ...mapGetters('size', {
        sizesDataTableHeaders: 'sizeHeader',
        sizeList: 'sizeList',
      }),
      getProductMergerListFromProductStore () {
        return this.$store.state.product.productMergerList
      },
      getFormTitle () {
        return this.persistIndex === -1 ? 'Insert Size Information' : 'Edit Size'
      },
      getPersistTitle () {
        return this.persistIndex === -1 ? 'Save' : 'Save Changes'
      },
      isLoading () {
        return this.$store.state.size.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('size/doGetSizeList')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      exploreSize (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deleteSizeInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('sizes/doDeleteSize', item)
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
          this.updateSizeToStore()
        } else {
          this.createSizeInStore()
        }
        this.closeDialogBox()
      },
      updateSizeToStore () {
        const tempData = Object.assign(this.sizeList[this.persistIndex], this.temp)
        this.$store.dispatch('size/doUpdateSize', [tempData, this.temp.sizeId])
      },
      createSizeInStore () {
        this.$store.dispatch('size/doCreateSize', this.temp)
      },
    },
  }
</script>
