<template>
  <div>
    <div>
      <v-container>
        <p>
          SHOPPING CART
        </p>
        <v-row>
          <v-col
            :cols="12"
            md="9"
            sm="12"
          >
            <v-simple-table>
              <template v-slot:default>
                <thead>
                  <tr>
                    <th class="text-center">
                      ITEM
                    </th>
                    <th class="text-center">
                      PRICE
                    </th>
                    <th class="text-center">
                      QUANTITY
                    </th>
                    <th class="text-center">
                      TOTAL
                    </th>
                    <th class="text-center" />
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="shoppingCart in shoppingCartList"
                    :key="shoppingCart.shoppingCartId"
                    @click="displayOrder(shoppingCart.shoppingCartId)"
                  >
                    <td>
                      <v-list-item
                        key="1"
                      >
                        <v-list-item-avatar>
                          <v-img :src="require('@/assets/img/shop/1.jpg')" />
                        </v-list-item-avatar>

                        <v-list-item-content>
                          <v-list-item-title>{{ shoppingCart.productResponseMergedDTO.productName }}</v-list-item-title>
                          <v-list-item-subtitle>{{ shoppingCart.productResponseMergedDTO.categoryMergerDTO.categoryName }}</v-list-item-subtitle>
                        </v-list-item-content>
                      </v-list-item>
                    </td>
                    <td>{{ shoppingCart.productResponseMergedDTO.price }}</td>
                    <td>
                      <v-text-field
                        class="pt-10"
                        label="Outlined"
                        style="width: 80px;"
                        single-line
                        outlined
                        value="2"
                        type="number"
                      />
                    </td>
                    <td>$80.00</td>
                    <td @click="deleteCartInStore(shoppingCart.shoppingCartId)">
                      <a>X</a>
                    </td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </v-col>

          <v-col
            v-if="orderDisplay"
            :cols="12"
            md="3"
            sm="12"
            style="background-color: lightgray"
          >
            <p class="headline">
              Order Summary
            </p>
            <p class="overline">
              Shipping and additional costs are calculated based on values you have entered.
            </p>
            <v-simple-table>
              <template v-slot:default>
                <tbody>
                  <tr>
                    <td>Order Subtotal</td>
                    <td
                      class="text-right"
                      style="width: 50px;"
                    >
                      $160.00
                    </td>
                  </tr>
                  <tr>
                    <td>Shipping Charges</td>
                    <td
                      class="text-right"
                      style="width: 50px;"
                    >
                      $10.00
                    </td>
                  </tr>
                  <tr>
                    <td>Tax</td>
                    <td
                      class="text-right"
                      style="width: 50px;"
                    >
                      $5.00
                    </td>
                  </tr>
                  <tr>
                    <td>Total</td>
                    <td
                      class="text-right"
                      style="width: 50px;"
                    >
                      <b>$175.00</b>
                    </td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
            <div class="text-center">
              <v-btn
                class="primary white--text mt-5"
                outlined
              >
                PROCEED TO PAY
              </v-btn>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { DELETE_WARNING } from '@/utils/CONST'

  export default {
    name: 'ShoppingCartIndex',
    data () {
      return {
        search: '',
        orderDisplay: false,
        rating: 4.5,
        breadcrums: [
          {
            text: 'Home',
            disabled: false,
            href: 'breadcrumbs_home',
          },
          {
            text: 'Clothing',
            disabled: false,
            href: 'breadcrumbs_clothing',
          },
          {
            text: 'T-Shirts',
            disabled: true,
            href: 'breadcrumbs_shirts',
          },
        ],

      }
    },
    watch: {
      orderDisplay () {
        if (!this.orderDisplay) {
          this.orderDisplay = true
        }
      },
    },
    computed: {
      ...mapGetters('shoppingCart', {
        shoppingCartHeaders: 'shoppingCartHeader',
        shoppingCartList: 'shoppingCartList',
      }),
      isLoading () {
        return this.$store.state.shoppingCart.isLoading
      },
    },
    created () {
      this.$store.dispatch('shoppingCart/doGetShoppingCartList', localStorage.getItem('user-id'))
    },
    methods: {
      deleteCartInStore (cartId) {
        alert(cartId)
        confirm(DELETE_WARNING) &&
          this.$store.dispatch('shoppingCart/doDeleteProductFromCart', cartId)
      },
      displayOrder (shoppingCartId) {
        this.orderDisplay = true
      },
    },
  }
</script>

<style scoped>

</style>
