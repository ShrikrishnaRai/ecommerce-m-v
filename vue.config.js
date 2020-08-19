module.exports = {
  publicPath: process.env.NODE_ENV === 'production'?'/bazzar/':'/',
  // devServer:{
  //   proxy: 'http://ec2-107-23-74-171.compute-1.amazonaws.com/ecommerce_m_v-0.0.1/'
  // },
  transpileDependencies: [
    'vuetify',
  ],
}
