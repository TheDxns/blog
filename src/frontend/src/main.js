import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import authentication from "@/plugins/authentication"

Vue.config.productionTip = false
Vue.use(authentication)

Vue.$keycloak
  .init({ checkLoginIframe: false })
  .then(() => {
    let userInfo = Vue.$keycloak.loadUserInfo();
    new Vue({
      vuetify,
      router,
      render: h => h(App, 
        {
          props: {
            username: userInfo
          }
        })
    }).$mount('#app')
  })