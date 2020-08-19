<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="serviceReviewDataTableHeaders"
        :items="serviceReviewList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title> Service Review</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search ServiceReview's"
              single-line
              hide-details
            />
            <v-divider
              class="mx-4"
              inset
              vertical
            />
          </v-toolbar>
        </template>
        <template v-slot:item.action="{ item }">
          <v-icon
            class="mr-2"
            small
            @click="exploreServiceReview(item)"
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
            @click="deleteServiceReviewInStore(item)"
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
    name: 'ServiceReview',
    data () {
      return {
        search: '',
        statusList: ['Published', 'Pending', 'Draft'],
        dialog: this.$store.state.serviceReview.dialog,
        persistIndex: this.$store.state.serviceReview.persistIndex,
        temp: this.$store.state.serviceReview.temp,
        defaultItem: this.$store.state.serviceReview.defaultItem,
      }
    },
    computed: {
      ...mapGetters('serviceReview', {
        serviceReviewDataTableHeaders: 'serviceReviewDataTableHeaders',
        serviceReviewList: 'serviceReviewList',
      }),
      getProductMergerListFromProductStore () {
        return this.$store.state.product.productMergerList
      },
      getFormTitle () {
        return this.persistIndex === -1 ? 'Insert ServiceReview Information' : 'Edit ServiceReview'
      },
      getPersistTitle () {
        return this.persistIndex === -1 ? 'Save' : 'Save Changes'
      },
      isLoading () {
        return this.$store.state.serviceReview.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('serviceReview/doGetServiceReviewList')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      exploreServiceReview (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deleteServiceReviewInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('serviceReview/doDeleteServiceReview', item)
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
          this.updateServiceReviewToStore()
        } else {
          this.createServiceReviewInStore()
        }
        this.closeDialogBox()
      },
      updateServiceReviewToStore () {
        const tempData = Object.assign(this.serviceReviewList[this.persistIndex], this.temp)
        this.$store.dispatch('serviceReview/doUpdateServiceReview', [tempData, this.temp.serviceReviewId])
      },
      createServiceReviewInStore () {
        this.$store.dispatch('serviceReview/doCreateServiceReview', this.temp)
      },
    },
  }
</script>
