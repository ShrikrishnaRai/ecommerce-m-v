<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="headers"
        :items="taskList"
        sort-by=""
        class="elevation-1 ma-5"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title>Driver Task</v-toolbar-title>
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
                  Add new task
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
                        <multiselect
                          v-model="editedItem.deliveryBoyMergerDTO"
                          deselect-label="Can't remove this value"
                          track-by="deliveryBoyId"
                          label="name"
                          placeholder="Select Delivery Boy"
                          :options="deliveryBoyList"
                          :searchable="false"
                          :allow-empty="false"
                        >
                          <template
                            slot="singleLabel"
                            slot-scope="{ option }"
                          >
                            <span>{{ option.name }}</span>
                          </template>
                        </multiselect>

                        <!--                              <multiselect v-model="editedItem.deliveryBoyMergerDTO"-->
                        <!--                                           :options="deliveryBoyList" :multiple="true"-->
                        <!--                                           :group-select="true"-->
                        <!--                                           placeholder="Type to search"-->
                        <!--                                            track-by="deliveryBoyId"-->
                        <!--                                           label="name">-->
                        <!--                     <span slot="noResult">Oops! No elements found. Consider changing the search query.</span></multiselect>-->
                      </v-col>
                      <v-col
                        cols="12"
                        sm="12"
                        md="12"
                      >
                        <v-select
                          v-model="editedItem.status"
                          :items="statusList"
                          outlined
                          label="Status"
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
  import { mapGetters } from 'vuex'
  import { DELETE_WARNING } from '@/utils/CONST'
  import Multiselect from 'vue-multiselect'

  // import deliveryBoyIndex from "@/store/modules/deliveryBoyIndex";

  export default {
    name: 'Task',
    components: {
      Multiselect,
    },
    data () {
      return {
        search: '',
        statusList: ['Assigned', 'Unassigned', 'Complete', 'Incomplete'],
        dialog: this.$store.state.task.dialog,
        editedIndex: this.$store.state.task.editedIndex,
        editedItem: this.$store.state.task.editedItem,
        defaultItem: this.$store.state.task.defaultItem,
      }
    },
    created () {
      this.$store.dispatch('task/doGetTaskList')
      this.$store.dispatch('deliveryBoy/doGetDeliveryBoysLists')
      console.log(this.deliveryBoyList)
    },
    mounted () {

    },
    computed: {
      ...mapGetters('task', {
        headers: 'taskHeader',
        taskList: 'taskList',
      }),
      formTitle () {
        return this.editedIndex === -1 ? 'Insert Task Information' : 'Edit Task '
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
      isLoading () {
        return this.$store.state.task.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    methods: {
      editItem (item) {
        this.editedIndex = this.taskList.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },
      deleteItem (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('task/doDeleteTask', item)
      },
      save () {
        if (this.editedIndex > -1) {
          const tempData = Object.assign(this.taskList[this.editedIndex], this.editedItem)
          this.$store.dispatch('task/doUpdateTask', tempData)
          console.log(this.editedItem, 'update')
        } else {
          this.$store.dispatch('task/doCreateTask', this.editedItem)
          console.log(this.editedItem, 'created')
        }
      },
      close () {
        this.dialog = false
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 300)
      },
    },
  }
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style scoped>

</style>
