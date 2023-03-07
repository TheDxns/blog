module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    port: 80,
    proxy: {
      '^/api/': {
        target: "http://api:9000",
        changeOrigin: true
      }
    }
  }
}
