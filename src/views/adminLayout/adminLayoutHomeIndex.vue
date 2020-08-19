<template>
  <v-app id="inspire">
    <div class="app-wrapper">
      <sidebar class="sidebar-container" />
      <div class="main-container">
        <div class="main-container">
          <div>
            <navbar />
            <sidebar class="sidebar-container" />
          </div>
          <app-main />
        </div>
      </div>
    </div>
  </v-app>
</template>

<script>
  import { Navbar, Sidebar, AppMain } from '@/views/adminLayout/components'

  export default {
    components: {
      Navbar,
      Sidebar,
      AppMain,
    },
    props: {
      source: String,
    },
    data: () => ({
      drawer: null,
    }),
    created () {
    },
  }
</script>
