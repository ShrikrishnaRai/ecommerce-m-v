<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="headers"
        :items="deliveryBoysList"
        sort-by=""
        class="elevation-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Delivery Boy</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search Delivery Boy's"
              single-line
              hide-details
            />
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-dialog
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
                    @click="dialog=false"
                  >
                    <v-icon>mdi-close</v-icon>
                  </v-btn>
                  <v-toolbar-title>Delivery Boy</v-toolbar-title>
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
                          sm="3"
                          md="3"
                        >
                          <v-text-field
                            outlined
                            label="Delivery Boy Name"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="3"
                          md="3"
                        >
                          <v-text-field
                            outlined
                            label="Email"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="3"
                          md="3"
                        >
                          <v-text-field
                            outlined
                            label="Citizenship No"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="3"
                          md="3"
                        >
                          <v-text-field
                            outlined
                            label="License No"
                          />
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col
                          cols="12"
                          sm="3"
                          md="3"
                        >
                          <v-text-field
                            outlined
                            label="Vehicle Number Plate"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="3"
                          md="3"
                        >
                          <v-text-field
                            outlined
                            label="Password"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="3"
                          md="3"
                        >
                          <v-text-field
                            outlined
                            label="Permanent Address"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="3"
                          md="3"
                        >
                          <v-text-field
                            outlined
                            label="Phone"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="3"
                          md="3"
                        >
                          <v-text-field
                            outlined
                            label="Temporary Address"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="3"
                          md="3"
                        >
                          <v-select
                            prepend-inner-icon="stay_current_portrait"
                            :items="statusList"
                            outlined
                            label="Status"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="3"
                          md="3"
                        >
                          <v-select
                            prepend-inner-icon="stay_current_portrait"
                            :items="statusList"
                            outlined
                            label="Vechicle type"
                          />
                        </v-col>
                        <v-col
                          cols="12"
                          sm="3"
                          md="3"
                        >
                          <v-select
                            prepend-inner-icon="stay_current_portrait"
                            :items="statusList"
                            outlined
                            label="Vechicle type"
                          />
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col
                          cols="12"
                          sm="12"
                          md="12"
                        >
                          <v-textarea
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
                  Add new Delivery Boy
                </v-btn>
              </template>
            </v-dialog>
          </v-toolbar>
        </template>
      </v-data-table>
    </v-app>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  //   import {DELETE_WARNING} from "@/utils/values";

  export default {
    name: 'DeliveryBoy',
    data () {
      return {
        tab: null,
        search: '',
        statusList: [],
        defaultItem: this.$store.state.deliveryBoy.defaultItem,
        items: [
          { tab: 'Document', content: 'deliveryBoyDocumentDetails' },
          { tab: 'Payment', content: 'deliveryBoyPayment' },
        ],
      }
    },
    created () {
      this.$store.dispatch('deliveryBoy/doGetDeliveryBoysLists')
    },
    computed: {
      ...mapGetters('deliveryBoy', {
        headers: 'deliveryHeaders',
        deliveryBoyList: 'deliveryBoyList',
      }),
      deliveryBoysList () {
        return this.$store.state.deliveryBoy.deliveryBoyList
      },
      formTitle () {
        return this.editedIndex === -1 ? 'Insert Delivery Boy Information' : 'Edit Delivery Boy Information'
      },
      isLoading () {
        return this.$store.state.deliveryBoy.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    methods: {
      deleteItem () {
      },
      save () {
      },
      close () {
        this.dialog = false
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 100)
      },
    },
  }
</script>

<style scoped>

</style>
