<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="headers"
        :items="serviceList"
        sort-by=""
        class="elevation-1 ma-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Merchant Services</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search service"
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
                  Add new service
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
                        sm="12"
                        md="12"
                      >
                        <v-text-field
                          v-model="editedItem.serviceName"
                          outlined
                          label="Service Name"
                        />
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-textarea
                          v-model="editedItem.description"
                          outlined
                          label="Service Description"
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
        <template v-slot:item.action="{item}">
          <v-icon
            small
            class="mr-2"
            @click="editItem(item)"
          >
            edit
          </v-icon>
          <v-icon
            small
            color="red"
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
    name: 'Service',
    data () {
      return {
        search: '',
        serviceName: '',
        serviceDescription: '',
        dialog: this.$store.state.merchantService.dialog,
        editedItem: this.$store.state.merchantService.editedItem,
        editedIndex: this.$store.state.merchantService.editedIndex,
        defaultItem: this.$store.state.merchantService.defaultItem,
      }
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    created () {
      this.$store.dispatch('merchantService/doGetServiceList')
    },
    computed: {
      ...mapGetters('merchantService', {
        headers: 'serviceHeaders',
        serviceList: 'serviceList',
      }),
      isLoading () {
        return this.$store.state.merchantService.isLoading
      },
      formTitle () {
        return this.editedIndex === -1 ? 'Insert Service Information' : 'Edit Service'
      },
    },
    methods: {
      editItem (item) {
        this.editedIndex = this.serviceList.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },
      deleteItem (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('merchantService/doDeleteService', item)
      },
      close () {
        this.dialog = false
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 100)
      },
      save () {
        if (this.editedIndex > -1) {
          const tempData = Object.assign(this.serviceList[this.editedIndex], this.editedItem)
          this.$store.dispatch('merchantService/doUpdateService', tempData)
        } else {
          this.$store.dispatch('merchantService/doCreateService', this.editedItem)
        }
        this.close()
      },
    },
  }
</script>
