<template>
  <v-app-bar
    :clipped-left="$vuetify.breakpoint.lgAndUp"
    app
    color="blue darken-3"
    dark
  >
    <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
    <v-toolbar-title
      style="width: 300px"
      class="ml-0 pl-4"
    >
      <span class="hidden-sm-and-down">ecommerce_m_v</span>
    </v-toolbar-title>
    <v-text-field
      flat
      solo-inverted
      hide-details
      prepend-inner-icon="mdi-magnify"
      label="Search"
      class="hidden-sm-and-down"
    />
    <v-spacer />
    <v-btn icon>
      <v-icon>mdi-apps</v-icon>
    </v-btn>
    <v-btn icon>
      <v-icon>mdi-bell</v-icon>
    </v-btn>
    <v-menu
      bottom
      left
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          dark
          icon
          v-bind="attrs"
          v-on="on"
        >
          <v-avatar
            size="32px"
            item
          >
            <v-img
              :src="imgSrc"
              alt="Vuetify"
            />
          </v-avatar>
          <!--          <v-icon>mdi-dots-vertical</v-icon>-->
        </v-btn>
      </template>

      <v-list>
        <v-list-item
          @click="routeToMyProfile"
        >
          <span class="material-icons">
            account_circle
          </span>
          <v-list-item-title>My Profile</v-list-item-title>
        </v-list-item><v-list-item
          @click="logOut"
        >
          <span class="material-icons">
            exit_to_app
          </span>
          <v-list-item-title>
            Log Out
          </v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </v-app-bar>
</template>

<script>
  import utils from '@/mixins/utils'

  export default {
    mixins: [utils],
    props: {
      source: String,
    },
    data () {
      return {
        dialog: false,
        drawer: null,
        items: [
          { title: 'Click Me', image: 'https://cdn.vuetifyjs.com/images/logos/logo.svg' },
          { title: 'Click Me' },
          { title: 'Click Me' },
          { title: 'Click Me 2' },
        ],
        closeOnClick: true,
        imgSrc: '',
        username: '',
      }
    },
    created () {
      this.imgSrc = localStorage.getItem('user-profile-image')
      this.username = localStorage.getItem('user-name')
    },
    methods: {
      routeToMyProfile () {
        this.$router.push('/customerDashboard/profile')
      },
    },

  }
</script>
