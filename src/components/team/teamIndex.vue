<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :headers="headers"
        :items="teamList"
        sort-by=""
        class="elevation-1 ma-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Teams</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search Team's"
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
                  Add new team
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
                          v-model="editedItem.brandName"
                          outlined
                          label="Team Name"
                        />
                      </v-col>
                      <v-col
                        cols="12"
                        sm="6"
                        md="6"
                      >
                        <v-select
                          v-model="editedItem.status"
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
                          v-model="editedItem.deliveryBoyMergerDTO"
                          style="height: 60px"
                          :options="deliveryBoyList"
                          :multiple="true"
                          :group-select="true"
                          placeholder="Add delivery boy's"
                          track-by="deliveryBoyId"
                          label="name"
                        >
                          <span slot="noResult">Oops! No elements found. Consider changing the search query.</span>
                        </multiselect>
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
            class="mr-2"
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
    name: 'Team',
    data () {
      return {
        search: '',
        isLoading: this.$store.state.team.isLoading,
        statusList: ['Publish', 'Pending', 'Draft'],
        dialog: this.$store.state.team.dialog,
        editedIndex: this.$store.state.team.editedIndex,
        editedItem: this.$store.state.team.editedItem,
        defaultItem: this.$store.state.team.defaultItem,
      }
    },
    created () {
      this.$store.dispatch('team/doGetTeamList')
      this.$store.dispatch('deliveryBoy/doGetDeliveryBoysLists')
      console.log(this.deliveryBoyList)
    },
    mounted () {

    },
    computed: {
      ...mapGetters('team', {
        headers: 'teamHeader',
        teamList: 'teamList',
      }),
      formTitle () {
        return this.editedIndex === -1 ? 'Insert Team Information' : 'Edit Team'
      },
      deliveryBoyList () {
        const arrBoy = []
        this.$store.state.deliveryBoy.deliveryBoyList.map(x => {
          arrBoy.push({
            // "username":x.username,
            deliveryBoyId: x.deliveryBoyId,
            name: x.name,

          })
        })
        return arrBoy
        // return this.$store.state.deliveryBoyIndex.deliveryBoyList
      },
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    methods: {
      editItem (item) {
        this.editedIndex = this.teamList.indexOf(item)
        this.editItem = Object.assign({}, item)
        this.dialog = true
      },
      deleteItem (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('team/doDeleteTeam', item)
      },
      close () {
        this.dialog = false
        setTimeout(() => {
          this.editItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 300)
      },
      save () {
        if (this.editedIndex > -1) {
          const tempData = Object.assign(this.teamList[this.editedIndex], this.editedItem)
          this.$schema.dispatch('team/doUpdateTeam', tempData)
        } else {
          this.$store.dispatch('team/doCreateTeam', this.editItem)
          console.log(this.editedItem, 'created')
        }
      },
    },
  }

</script>

<style scoped>

</style>
