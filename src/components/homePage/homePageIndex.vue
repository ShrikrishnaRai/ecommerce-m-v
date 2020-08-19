<!--<template>-->
<!--  <div>-->
<!--    <div>-->
<!--      <div>-->
<!--        <div>-->
<!--          <v-card>-->
<!--            <v-app-bar-->
<!--              dense-->
<!--              absolute-->
<!--              color="green"-->
<!--              dark-->
<!--              shrink-on-scroll-->
<!--              prominent-->
<!--              scroll-target="#scrolling-techniques"-->
<!--            >-->
<!--              <v-container>-->
<!--                <v-row class="mb-6">-->
<!--                  <v-col md="1">-->
<!--                    <v-icon>-->
<!--                      storefront-->
<!--                    </v-icon>-->
<!--                  </v-col>-->
<!--                  <v-col-->
<!--                    md="6"-->
<!--                    sm="12"-->
<!--                  >-->
<!--                    <v-text-field-->
<!--                      flat-->
<!--                      solo-inverted-->
<!--                      hide-details-->
<!--                      prepend-inner-icon="mdi-magnify"-->
<!--                      label="Search"-->
<!--                      class="hidden-sm-and-down"-->
<!--                    />-->
<!--                  </v-col>-->
<!--                  <v-col md="1">-->
<!--                    Cart-->
<!--                    <v-icon>-->
<!--                      shopping_cart-->
<!--                    </v-icon>-->
<!--                  </v-col>-->
<!--                  <v-col md="1">-->
<!--                    Sign in-->
<!--                    <v-icon>-->
<!--                      account_circle-->
<!--                    </v-icon>-->
<!--                  </v-col>-->
<!--                </v-row>-->
<!--              </v-container>-->
<!--            </v-app-bar>-->
<!--            <v-sheet-->
<!--              id="scrolling-techniques"-->
<!--              class="overflow-y-auto"-->
<!--              max-height="600"-->
<!--            />-->
<!--          </v-card>-->
<!--          <div>-->
<!--            <v-carousel height="350">-->
<!--              <v-carousel-item-->
<!--                v-for="(item,i) in items"-->
<!--                :key="i"-->
<!--                :src="item.src"-->
<!--                reverse-transition="fade-transition"-->
<!--                transition="fade-transition"-->
<!--              />-->
<!--            </v-carousel>-->
<!--          </div>-->
<!--          <v-row>-->
<!--            <v-col-->
<!--              class="ma-2"-->
<!--              md="3"-->
<!--            >-->
<!--              <v-hover-->
<!--                v-slot:default="{ hover }"-->
<!--                close-delay="200"-->
<!--              >-->
<!--                <v-card-->
<!--                  :elevation="hover ? 16 : 2"-->
<!--                >-->
<!--                  <v-img-->
<!--                    src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg"-->
<!--                  />-->
<!--                  <v-card-text>-->
<!--                    Jeans-->
<!--                  </v-card-text>-->
<!--                  <v-card-actions>-->
<!--                    <v-btn-->
<!--                      text-->
<!--                      color="deep-purple accent-4"-->
<!--                    >-->
<!--                      Read-->
<!--                    </v-btn>-->
<!--                    <v-btn-->
<!--                      text-->
<!--                      color="deep-purple accent-4"-->
<!--                    >-->
<!--                      Bookmark-->
<!--                    </v-btn>-->
<!--                    <v-spacer />-->
<!--                  </v-card-actions>-->
<!--                </v-card>-->
<!--              </v-hover>-->
<!--            </v-col>-->
<!--            <v-col-->
<!--              class="ma-2"-->
<!--              md="3"-->
<!--            >-->
<!--              <v-hover-->
<!--                v-slot:default="{ hover }"-->
<!--                close-delay="200"-->
<!--              >-->
<!--                <v-card-->
<!--                  :elevation="hover ? 16 : 2"-->
<!--                >-->
<!--                  <v-img-->
<!--                    src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg"-->
<!--                  />-->
<!--                  <v-card-text>-->
<!--                    Jeans-->
<!--                  </v-card-text>-->
<!--                  <v-card-actions>-->
<!--                    <v-btn-->
<!--                      text-->
<!--                      color="deep-purple accent-4"-->
<!--                    >-->
<!--                      Read-->
<!--                    </v-btn>-->
<!--                    <v-btn-->
<!--                      text-->
<!--                      color="deep-purple accent-4"-->
<!--                    >-->
<!--                      Bookmark-->
<!--                    </v-btn>-->
<!--                    <v-spacer />-->
<!--                  </v-card-actions>-->
<!--                </v-card>-->
<!--              </v-hover>-->
<!--            </v-col>-->
<!--            <v-col-->
<!--              class="ma-2"-->
<!--              md="3"-->
<!--            >-->
<!--              <v-hover-->
<!--                v-slot:default="{ hover }"-->
<!--                close-delay="200"-->
<!--              >-->
<!--                <v-card-->
<!--                  :elevation="hover ? 16 : 2"-->
<!--                >-->
<!--                  <v-img-->
<!--                    src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg"-->
<!--                  />-->
<!--                  <v-card-text>-->
<!--                    Jeans-->
<!--                  </v-card-text>-->
<!--                  <v-card-actions>-->
<!--                    <v-btn-->
<!--                      text-->
<!--                      color="deep-purple accent-4"-->
<!--                    >-->
<!--                      Read-->
<!--                    </v-btn>-->
<!--                    <v-btn-->
<!--                      text-->
<!--                      color="deep-purple accent-4"-->
<!--                    >-->
<!--                      Bookmark-->
<!--                    </v-btn>-->
<!--                    <v-spacer />-->
<!--                  </v-card-actions>-->
<!--                </v-card>-->
<!--              </v-hover>-->
<!--            </v-col>-->
<!--          </v-row>-->
<!--          <home-footer />-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--  import HomeFooter from '@/customerLayout/homeFooter/index'-->
<!--  export default {-->
<!--    name: 'HomePage',-->
<!--    customerLayout: { HomeFooter },-->
<!--    data () {-->
<!--      return {-->
<!--        items: [-->
<!--          {-->
<!--            src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',-->
<!--          },-->
<!--          {-->
<!--            src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',-->
<!--          },-->
<!--          {-->
<!--            src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',-->
<!--          },-->
<!--          {-->
<!--            src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',-->
<!--          },-->
<!--        ],-->
<!--      }-->
<!--    },-->
<!--  }-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
<template>
  <div>
    <v-carousel hide-delimiters>
      <v-carousel-item
        :src="require('@/assets/img/home/slider4.jpg')"
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <div class="display-2 white--text pl-5 pr-5 hidden-sm-only">
            <strong>Upto 60% + Extra 10%</strong>
          </div><br>
        </v-row>
      </v-carousel-item>
      <v-carousel-item
        :src="require('@/assets/img/home/slider2.jpg')"
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <div class="display-2 white--text pl-5 pr-5 hidden-sm-only">
            <strong>Upto 60% + Extra 10%</strong>
          </div><br>
        </v-row>
      </v-carousel-item>
      <v-carousel-item
        :src="require('@/assets/img/home/slider3.jpg')"
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <div class="display-2 white--text pl-5 pr-5 hidden-sm-only">
            <strong>Upto 60% + Extra 10%</strong>
          </div><br>
        </v-row>
      </v-carousel-item>
      <v-carousel-item
        :src="require('@/assets/img/home/slider1.jpg')"
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <div class="display-2 white--text pl-5 pr-5 hidden-sm-only">
            <strong>Upto 60% + Extra 10%</strong>
          </div><br>
        </v-row>
      </v-carousel-item>
    </v-carousel>
    <div class="pl-4 pr-4 row">
      <div
        class="col-md-6 col-sm-6 col-xs-12"
      >
        <v-card>
          <v-img
            :src="require('@/assets/img/home/slider2.jpg')"
            class="white--text align-center"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="400px"
          >
            <h1 class="text-center font-size">
              Top Picks
            </h1>
            <div class="text-center">
              <v-btn
                href="/shop"
                class="white--text "
                outlined
              >
                SHOP NOW
              </v-btn>
            </div>

            <!--            <v-expand-transition>-->
            <!--              <div-->
            <!--                v-if="hover"-->
            <!--                class="d-flex transition-fast-in-fast-out orange darken-2 v-card&#45;&#45;reveal display-3 white&#45;&#45;text"-->
            <!--                style="height: 100%;"-->
            <!--              >-->

            <!--                <h3>Top Picks</h3><br/>-->
            <!--                <h3>sdfs</h3>-->
            <!--              </div>-->
            <!--            </v-expand-transition>-->
          </v-img>
        </v-card>
        <!--        </v-hover>-->
      </div>
      <div
        class="col-md-6 col-sm-6 col-xs-12"
      >
        <v-card>
          <v-img
            :src="require('@/assets/img/home/slider3.jpg')"
            class="white--text align-center"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="400px"
          >
            <h1 class="text-center font-size">
              New Arrivals
            </h1>
            <div class="text-center">
              <v-btn
                href="/shop"
                class="white--text"
                outlined
              >
                SHOP NOW
              </v-btn>
            </div>
          </v-img>
        </v-card>
      </div>
    </div>
    <div class="pl-4 pr-4 row">
      <div
        class="col-md-4 col-sm-4 col-xs-12"
      >
        <v-card outlined>
          <v-img
            :src="require('@/assets/img/home/deal2.jpg')"
            class="white--text align-center"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="300px"
          >
            <h1 class="text-center font-size">
              Lorem & Ipsum
            </h1>
            <div class="text-center mt-2">
              <v-btn
                class="white--text caption"
                href="/shop"
                text
              >
                SHOP NOW <v-icon class="white--text caption">
                  mdi-arrow-right
                </v-icon>
              </v-btn>
            </div>
          </v-img>
        </v-card>
      </div>
      <div
        class="col-md-4 col-sm-4 col-xs-12"
      >
        <v-card outlined>
          <v-img
            :src="require('@/assets/img/home/deal3.jpg')"
            class="white--text align-center"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="300px"
          >
            <h1 class="text-center font-size">
              Lorem & Ipsum
            </h1>
            <div class="text-center mt-2">
              <v-btn
                class="white--text caption"
                href="/shop"
                text
              >
                SHOP NOW <v-icon class="white--text caption">
                  mdi-arrow-right
                </v-icon>
              </v-btn>
            </div>
          </v-img>
        </v-card>
      </div>
      <div
        class="col-md-4 col-sm-4 col-xs-12"
      >
        <v-card outlined>
          <v-img
            :src="require('@/assets/img/home/deal4.jpg')"
            class="white--text align-center"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="300px"
          >
            <h1 class="text-center font-size">
              Lorem & Ipsum
            </h1>
            <div class="text-center mt-2">
              <v-btn
                class="white--text caption"
                href="/shop"
                text
              >
                SHOP NOW <v-icon class="white--text caption">
                  mdi-arrow-right
                </v-icon>
              </v-btn>
            </div>
          </v-img>
        </v-card>
      </div>
    </div>
    <v-container>
      <v-row no-gutters>
        <v-col :cols="12">
          <v-card-text
            class=""
            tile
            outlined
          >
            <v-card-title class="subheading ">
              Deals of the Day
            </v-card-title>
            <v-divider />
            <div class="row">
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center">
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
                      :src="require('@/assets/img/home/deal1.jpg')"
                    >
                      <v-card-title>Bags & Purses </v-card-title>
                    </v-img>

                    <v-card-text class="text--primary text-center">
                      <div>Upto 60% + Extra 10%</div>
                      <div>Baggit, Zara, Fossil</div>
                    </v-card-text>

                    <div class="text-center">
                      <v-btn
                        href="/shop"
                        class="ma-2"
                        outlined
                        color="info"
                      >
                        Explore
                      </v-btn>
                    </div>
                  </v-card>
                </v-hover>
              </div>
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center">
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
                      :src="require('@/assets/img/home/deal2.jpg')"
                    >
                      <v-card-title>T-Shirt </v-card-title>
                    </v-img>

                    <v-card-text class="text--primary text-center">
                      <div>Upto 50%</div>
                      <div>Zara, Selected, Celio</div>
                    </v-card-text>

                    <div class="text-center">
                      <v-btn
                        href="/shop"
                        class="ma-2"
                        outlined
                        color="info"
                      >
                        Explore
                      </v-btn>
                    </div>
                  </v-card>
                </v-hover>
              </div>
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center">
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
                      :src="require('@/assets/img/home/deal3.jpg')"
                    >
                      <v-card-title>Jeans </v-card-title>
                    </v-img>

                    <v-card-text class="text--primary text-center">
                      <div>Upto 60% + Extra 10%</div>
                      <div>Jack & Jones, Levis</div>
                    </v-card-text>

                    <div class="text-center">
                      <v-btn
                        href="/shop"
                        class="ma-2"
                        outlined
                        color="info"
                      >
                        Explore
                      </v-btn>
                    </div>
                  </v-card>
                </v-hover>
              </div>
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center">
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
                      :src="require('@/assets/img/home/deal4.jpg')"
                    >
                      <v-card-title>Shoes </v-card-title>
                    </v-img>

                    <v-card-text class="text--primary text-center">
                      <div>Upto 60% + Extra 10%</div>
                      <div>Nike, Adidas, Puma</div>
                    </v-card-text>

                    <div class="text-center">
                      <v-btn
                        href="/shop"
                        class="ma-2"
                        outlined
                        color="info"
                      >
                        Explore
                      </v-btn>
                    </div>
                  </v-card>
                </v-hover>
              </div>
            </div>
          </v-card-text>
        </v-col>
      </v-row>
    </v-container>
    <v-card class="accent">
      <v-container>
        <v-row no-gutters>
          <v-col class="col-12 col-md-4 col-sm-12">
            <v-row>
              <v-col class="col-12 col-sm-3 pr-4 align">
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
  // export default {
    data () {
      return {
        items: [
          { title: 'Click Me' },
          { title: 'Click Me' },
          { title: 'Click Me' },
          { title: 'Click Me 2' },
        ],
        activeBtn: 1,
        colors: [
          'indigo',
          'warning',
          'pink darken-2',
          'red lighten-1',
          'deep-purple accent-4',
        ],
        slides: [
          'First',
          'Second',
          'Third',
          'Fourth',
          'Fifth',
        ],
      }
    },
  }
</script>
<style>
  .v-card--reveal {
    align-items: center;
    bottom: 0;
    justify-content: center;
    opacity: .5;
    position: absolute;
    width: 100%;
  }
</style>
