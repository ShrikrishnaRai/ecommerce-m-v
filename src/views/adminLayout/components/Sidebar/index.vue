<template>
  <v-navigation-drawer
    v-model="drawer"
    app
    clipped
  >
    <v-list dense>
      <v-list-item
        v-for="link in links"
        :key="link.text"
        link
        router
        :to="link.route"
        active-class="border"
      >
        <v-list-item-action>
          <v-icon>{{ link.icon }}</v-icon>
        </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>{{ link.text }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
  export default {
    data: () => ({
      admins: [
        ['Management', 'people_outline', '/adminLayout/brand'],
        ['Settings', 'settings'],
      ],
      cruds: [
        ['Create', 'add'],
        ['Read', 'insert_drive_file'],
        ['Update', 'update'],
        ['Delete', 'delete'],
      ],
      drawer: true,
      links: [
        { icon: 'home', text: 'Home', route: '/adminLayout/home' },
        { icon: 'mdi-view-dashboard', text: 'Products', route: '/adminLayout/product' },
        { icon: 'mdi-view-dashboard', text: 'Brands', route: '/adminLayout/brand' },
        { icon: 'mdi-view-dashboard', text: 'Pre Booking', route: '/adminLayout/preBooking' },
        { icon: 'mdi-view-dashboard', text: 'Wishlist', route: '/adminLayout/wishlist' },
        { icon: 'mdi-view-dashboard', text: 'Order', route: '/adminLayout/order' },
        { icon: 'mdi-view-dashboard', text: 'Enquiry', route: '/adminLayout/enquiry' },
        { icon: 'mdi-view-dashboard', text: 'Product Bundle', route: '/adminLayout/productBundle' },
        { icon: 'local_offer', text: 'Offers', route: '/adminLayout/offers' },
        { icon: 'insert_drive_file', text: 'Invoice', route: '/adminLayout/invoice' },
        { icon: 'location_on', text: 'Maps', route: '/adminLayout/maps' },
      ],
    }),
  }
</script>
