<template>
  <div>
    <v-app id="inspire">
      <v-data-table
        :search="search"
        :loading="isLoading"
        :headers="taskHistoryDataTableHeaders"
        :items="taskHistoryList"
        sort-by=""
        class="elevation-1 ma-2"
      >
        <template v-slot:top>
          <v-toolbar
            flat
            color="white"
          >
            <v-toolbar-title> TaskHistorys</v-toolbar-title>
            <v-divider
              class="mx-4"
              inset
              vertical
            />
            <v-text-field
              v-model="search"
              outlined
              append-icon="mdi-magnify"
              label="Search TaskHistory's"
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
            @click="exploreTaskHistory(item)"
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
            @click="deleteTaskHistoryInStore(item)"
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
    name: 'TaskHistory',
    data () {
      return {
        search: '',
        dialog: this.$store.state.taskHistory.dialog,
        persistIndex: this.$store.state.taskHistory.persistIndex,
        temp: this.$store.state.taskHistory.temp,
        defaultItem: this.$store.state.taskHistory.defaultItem,
      }
    },
    computed: {
      ...mapGetters('taskHistory', {
        taskHistoryDataTableHeaders: 'taskHistoryHeader',
        taskHistoryList: 'taskHistoryList',
      }),
      getProductMergerListFromProductStore () {
        return this.$store.state.product.productMergerList
      },
      getFormTitle () {
        return this.persistIndex === -1 ? 'Insert TaskHistory Information' : 'Edit TaskHistory'
      },
      getPersistTitle () {
        return this.persistIndex === -1 ? 'Save' : 'Save Changes'
      },
      isLoading () {
        return this.$store.state.taskHistory.isLoading
      },
    },
    watch: {
      dialog (val) {
        val || this.closeDialogBox()
      },
    },
    created () {
      this.$store.dispatch('taskHistory/doGetTaskHistoryList')
    },
    methods: {
      dispatchProductMergerList () {
        this.$store.dispatch('product/doGetProductMergerList')
      },
      exploreTaskHistory (item) {
        console.log(item)
      },
      onEditAction (item) {
        this.persistIndex++
        this.temp = Object.assign({}, item)
        this.dialog = true
      },
      deleteTaskHistoryInStore (item) {
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('taskHistory/doDeleteTaskHistory', item)
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
          this.updateTaskHistoryToStore()
        } else {
          this.createTaskHistoryInStore()
        }
        this.closeDialogBox()
      },
      updateTaskHistoryToStore () {
        const tempData = Object.assign(this.taskHistory[this.persistIndex], this.temp)
        this.$store.dispatch('taskHistory/doUpdateTaskHistory', [tempData, this.temp.taskHistoryId])
      },
      createTaskHistoryInStore () {
        this.$store.dispatch('taskHistory/doCreateTaskHistory', this.temp)
      },
    },
  }
</script>
