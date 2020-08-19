<template>
  <v-navigation-drawer
    v-model="drawer"
    app
    clipped
  >
    <v-list dense>
      <v-list-group
        prepend-icon="account_circle"
        value="true"
      >
        <template v-slot:activator>
          <v-list-item-title>Users</v-list-item-title>
        </template>

        <v-list-group
          no-action
          sub-group
          value="true"
        >
          <template v-slot:activator>
            <v-list-item-content>
              <v-list-item-title>Admin</v-list-item-title>
            </v-list-item-content>
          </template>

          <v-list-item
            v-for="(admin, i) in admins"
            :key="i"
            link
          >
            <v-list-item-title v-text="admin[0]" />
            <v-list-item-icon>
              <v-icon v-text="admin[1]" />
            </v-list-item-icon>
          </v-list-item>
        </v-list-group>

        <v-list-group
          sub-group
          no-action
        >
          <template v-slot:activator>
            <v-list-item-content>
              <v-list-item-title>Actions</v-list-item-title>
            </v-list-item-content>
          </template>
          <v-list-item
            v-for="(crud, i) in cruds"
            :key="i"
          >
            <v-list-item-title v-text="crud[0]" />
            <v-list-item-action>
              <v-icon v-text="crud[1]" />
            </v-list-item-action>
          </v-list-item>
        </v-list-group>
      </v-list-group>
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
        ['Management', 'people_outline', '/superAdminLayout/brand'],
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
        { icon: 'home', text: 'Home', route: '/superAdminLayout/home' },
        { icon: 'directions_bike', text: 'Delivery Boy', route: '/superAdminLayout/deliveryBoyIndex' },
        { icon: 'category', text: 'Categories', route: '/superAdminLayout/category' },
        { icon: 'mdi-view-dashboard', text: 'Pre Booking', route: '/superAdminLayout/preBooking' },
        { icon: 'mdi-view-dashboard', text: 'Wishlist', route: '/superAdminLayout/wishlist' },
        { icon: 'mdi-view-dashboard', text: 'Customer Wallet', route: '/superAdminLayout/customerWallet' },
        { icon: 'mdi-view-dashboard', text: 'Service', route: '/superAdminLayout/service' },
        { icon: 'mdi-view-dashboard', text: 'Order service', route: '/superAdminLayout/orderService' },
        { icon: 'mdi-view-dashboard', text: 'Enquiry', route: '/superAdminLayout/enquiry' },
        { icon: 'person_pin', text: 'Customer', route: '/superAdminLayout/customer' },
        { icon: 'local_shipping', text: 'Vendor', route: '/superAdminLayout/vendor' },
        { icon: 'local_offer', text: 'Offers', route: '/superAdminLayout/offers' },
        { icon: 'insert_drive_file', text: 'Invoice', route: '/superAdminLayout/invoice' },
        { icon: 'assignment', text: 'Task', route: '/superAdminLayout/task' },
        { icon: 'group', text: 'Team', route: '/superAdminLayout/team' },
      ],
    }),
  }
</script>
