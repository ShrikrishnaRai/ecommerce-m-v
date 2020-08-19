<template>
  <div
    id="app"
    class="back"
  >
    <v-app id="inspire">
      <v-content>
        <v-container
          class="fill-height"
          fluid
        >
          <v-row
            align="center"
            justify="center"
          >
            <v-col
              cols="12"
              sm="8"
              md="4"
            >
              <v-card class="elevation-12">
                <v-toolbar
                  color="primary"
                  dark
                  flat
                >
                  <v-toolbar-title>ecommerce_m_v Login</v-toolbar-title>
                  <v-spacer />
                  <v-tooltip bottom>
                    <span>Source</span>
                  </v-tooltip>
                </v-toolbar>
                <v-card-text>
                  <v-text-field
                    v-model="temp.email"
                    label="Login"
                    name="login"
                    prepend-icon="person"
                    type="text"
                    outlined
                  />

                  <v-text-field
                    v-model="temp.password"
                    outlined
                    label="Password"
                    name="password"
                    prepend-icon="lock"
                    type="password"
                  />
                  <v-card-actions>
                    <v-spacer />
                    <v-btn color="primary">
                      SignUp
                    </v-btn>
                    <v-btn
                      color="primary"
                      @click="login"
                    >
                      Login
                    </v-btn>
                  </v-card-actions>
                </v-card-text>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-content>
    </v-app>
  </div>
</template>

<script>
  export default {
    name: 'Login',
    data () {
      return {
        temp: this.$store.state.login.temp,
      }
    },
    methods: {
      login () {
        this.$store.dispatch('login/doLogin', this.temp)
          .then(() => {
            if (localStorage.getItem('user-role') === 'admin') {
              this.$router.push('/adminLayout')
            } if (localStorage.getItem('user-role') === 'user') {
              this.$router.push('/customerDashboard')
            } if (localStorage.getItem('user-role') === 'superAdmin') {
              this.$router.push('/superAdminLayout')
            } if (localStorage.getItem('user-role') === 'deliveryBoy') {
              this.$router.push('/deliveryBoy')
            }
          })
      },
    },
  }
</script>
<style>
  .back {
    background-image: url(
    https://images.unsplash.com/photo-1512436991641-6745cdb1723f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80
    );
    background-size: cover;
  }
  #inspire {
    background: none;
  }
</style>
