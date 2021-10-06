import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import authentication from "@/plugins/authentication"

Vue.config.productionTip = false
Vue.use(authentication)

Vue.$keycloak
  .init({ onLoad: 'check-sso', checkLoginIframe: false })
  .then(() => {
    Vue.$keycloak.loadUserInfo().then(function(data) {
      Vue.prototype.$userInfo = data;
      console.log(data);
    }); 
    
    new Vue({
      vuetify,
      router,
      render: h => h(App, 
        {
          props: {
            
          }
        })
    }).$mount('#app')
  })