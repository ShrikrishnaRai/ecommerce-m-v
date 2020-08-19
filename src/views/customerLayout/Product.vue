<template>
  <div>
    <v-container>
      <div class="row">
        <div class="col-md-5 col-sm-5 col-xs-12">
          <v-carousel>
            <v-carousel-item
              :src="require('@/assets/img/home/slider4.jpg')"
            />
            <v-carousel-item
              :src="require('@/assets/img/home/slider2.jpg')"
            />
            <v-carousel-item
              :src="require('@/assets/img/home/slider3.jpg')"
            />
            <v-carousel-item
              :src="require('@/assets/img/home/slider1.jpg')"
            />
          </v-carousel>
        </div>
        <div class="col-md-7 col-sm-7 col-xs-12">
          <v-breadcrumbs
            class="pb-0"
            :items="breadcrums"
          />
          <div
            v-if="isLoading"
            class="content-wrapper"
          >
            <facebook-loader
              class="pa-md-4"
              :speed="2"
            /><facebook-loader
              class="pa-md-4"
              :speed="2"
            />
          </div>
          <div
            v-else
            class="pl-6"
          >
            <p class="display-1 mb-0">
              <!--              Modern Black T-Shirt-->
              {{ product.productName }}
            </p>
            <v-card-actions class="pa-0">
              <p class="headline font-weight-light pt-3">
                ${{ product.salePrice }} <del
                  style=""
                  class="subtitle-1 font-weight-thin"
                >${{ product.purchasePrice }}</del>
              </p>
              <v-spacer />
              <v-rating
                v-model="rating"
                class=""
                background-color="warning lighten-3"
                color="warning"
                dense
              />
              <span class="body-2 font-weight-thin">   {{ getProductReviewNumber }} REVIEWS</span>
            </v-card-actions>
            <p class="subtitle-1 font-weight-thin">
              {{ product.description }}
              <!--              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Tincidunt arcu non sodales neque sodales ut etiam. Lectus arcu bibendum at varius vel pharetra. Morbi tristique senectus et netus et malesuada.-->
            </p>
            <p class="title">
              SIZE
            </p>
            <v-radio-group v-model="radioGroup">
              <v-radio
                v-for="size in product.productDetailDTOS.sizeEntity"
                :key="size.sizeId"
                :label="`${size.size}`"
                :value="n"
              />
            </v-radio-group>
            <p class="title">
              ITEMS
            </p>
            <v-text-field
              v-model="quantity"
              outlined
              style="width:100px"
              dense
            />
            <v-btn
              class="primary white--text"
              outlined
              tile
              dense
              @click="addProductToCart"
            >
              <v-icon>mdi-cart</v-icon> ADD TO CART
            </v-btn>
            <v-btn
              class="ml-4"
              outlined
              tile
            >
              ADD TO WISHLIST
            </v-btn>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12">
          <v-tabs>
            <v-tab>Description</v-tab>
            <v-tab>Materials</v-tab>
            <v-tab
              @click="fetchProductReviews"
            >
              REVIEWS
            </v-tab>
            <v-tab-item>
              <p class="pt-10 subtitle-1 font-weight-thin">
                Brand:{{ product.brandMergerDTO.brandName }}
                {{ product.description }}
              </p>
            </v-tab-item>
            <v-tab-item>
              <p class="pt-10 subtitle-1 font-weight-thin">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ultricies mi eget mauris pharetra et. Vel pretium lectus quam id leo in vitae turpis
                massa. Orci dapibus ultrices in iaculis nunc. At auctor urna nunc id cursus metus. Integer feugiat
                scelerisque varius morbi enim nunc. Aliquam sem et tortor consequat id porta nibh venenatis cras.
                Pellentesque pulvinar pellentesque habitant morbi tristique senectus et netus. Malesuada nunc vel risus
                commodo viverra maecenas. Neque volutpat ac tincidunt vitae semper quis.
              </p>
            </v-tab-item>

            <v-tab-item>
              <v-list
                three-line="true"
                avatar="true"
              >
                <v-list-item-group
                  v-model="productReviewList.size"
                  color="primary"
                >
                  <v-list-item
                    v-for="productReview in productReviewList"
                    :key="productReview.id"
                    inactive="true"
                  >
                    <v-list-item-avatar>
                      <!--                      <v-img :src="productReview.customerMergerDTO.customerImageUrl" />-->
                    </v-list-item-avatar>
                    <v-list-item-content>
                      <v-list-item-title v-html="productReview.review" /><v-rating
                        v-model="rating"
                        class=""
                        background-color="warning lighten-3"
                        color="warning"
                        dense
                      />
                      <v-list-item-subtitle v-html="productReview.reply" />
                    </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-tab-item>
          </v-tabs>

          <v-card-text
            class="pa-0 pt-4"
            tile
            outlined
          >
            <p class="subtitle-1 font-weight-light pt-3 text-center">
              YOU MIGHT ALSO LIKE
            </p>
            <v-divider />
            <div

              class="row text-center"
            >
              <div
                v-for="pro in productList"
                :key="pro.id"
                class="col-md-2 col-sm-4 col-xs-12 text-center"
              >
                <v-hover
                  v-slot:default="{ hover }"
                  open-delay="200"
                >
                  <v-card
                    :elevation="hover ? 16 : 2"
                  >
                    <v-img
                      class="white--text align-end"
                      height="200px"
                      :src="pro.productImageUrl"
                    >
                      <v-card-title>{{ pro.productName }} </v-card-title>
                    </v-img>

                    <v-card-text class="text--primary text-center">
                      <div>{{ pro.productHeader }}</div>
                    </v-card-text>

                    <div class="text-center">
                      <v-btn
                        class="ma-2"
                        outlined
                        color="info"
                        :to="'/product/'+pro.product_id"
                      >
                        Explore
                      </v-btn>
                    </div>
                  </v-card>
                </v-hover>
              </div>
            </div>
          </v-card-text>
        </div>
      </div>
    </v-container>
    <v-card class="accent">
      <v-container>
        <v-row no-gutters>
          <v-col class="col-12 col-md-4 col-sm-12">
            <v-row>
              <v-col class="col-12 col-sm-3 pr-4 hidden-sm-only align">
                <v-icon class="display-2">
                  mdi-truck
                </v-icon>
              </v-col>
              <v-col class="col-12 col-sm-9 pr-4">
                <h3 class="font-weight-light">
                  FREE SHIPPING & RETURN
                </h3>
                <p class="font-weight-thin">
                  Free Shipping over $300
                </p>
              </v-col>
            </v-row>
          </v-col>
          <v-col class="col-12 col-md-4 col-sm-12">
            <v-row>
              <v-col class="col-12 col-sm-3 pr-4 align">
                <v-icon class="display-2">
                  mdi-cash-usd
                </v-icon>
              </v-col>
              <v-col class="col-12 col-sm-9 pr-4">
                <h3 class="font-weight-light">
                  MONEY BACK GUARANTEE
                </h3>
                <p class="font-weight-thin">
                  30 Days Money Back Guarantee
                </p>
              </v-col>
            </v-row>
          </v-col>
          <v-col class="col-12 col-md-4 col-sm-12">
            <v-row>
              <v-col class="col-12 col-sm-3 pr-4 align">
                <v-icon class="display-2">
                  mdi-headset
                </v-icon>
              </v-col>
              <v-col class="col-12 col-sm-9 pr-4">
                <h3 class="font-weight-light">
                  020-800-456-747
                </h3>
                <p class="font-weight-thin">
                  24/7 Available Support
                </p>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>
<script>
  import { mapGetters } from 'vuex'

  export default {
    props: ['id'],
    data () {
      return {
        rating: 4.5,
        quantity: 1,
      }
    },
    computed: {
      ...mapGetters('shoppingCart', {
        cartItem: 'shoppingCartTemp',
      }),
      ...mapGetters('product', {
        product: 'product',
        productList: 'productList',
      }),
      ...mapGetters('productReview', {
        productReviewList: 'productReviewList',
        productReview: 'productReviewById',
      }),
      getProductReviewNumber () {
        return this.product.productReviewMergerDTOS.length
      },
    },
    created () {
      this.$store.dispatch('product/doGetProductById', this.id)
    },
    mounted () {
      this.$store.dispatch('product/doGetProductListByCategory', this.product.categoryMergerDTO.id)
    },
    methods: {
      addProductToCart () {
        if (localStorage.getItem('user-role') === 'user') {
          this.cartItem.product = this.id
          this.cartItem.customerMergerDTO.id = localStorage.getItem('user-id')
          this.cartItem.customerMergerDTO.username = localStorage.getItem('user-name')
          this.cartItem.quantity = this.quantity
          this.cartItem.grandTotal = this.product.salePrice
          this.cartItem.productMergerDTO.productId = this.id
          this.cartItem.quantity = this.id
          this.cartItem.productMergerDTO.productName = this.product.productName
          this.$store.dispatch('shoppingCart/addToShoppingCart', this.cartItem)
        } else {
          alert('Not Authorized users please login')
        }
      },

      fetchProductReviews () {
        this.$store.dispatch('productReview/doGetProductReviewByProductId', this.id)
      },
    },
  }
</script>
