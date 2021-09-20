import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import Keycloak from 'keycloak-js'
import Logger from 'vuejs-logger'

Vue.config.productionTip = false
Vue.use(Logger);

let initOptions = {
  url: 'http://localhost:8180/auth', realm: 'Blog', clientId: 'blog-client', onLoad: 'login-required'
}

let keycloak = Keycloak(initOptions);

keycloak.init({ onLoad: initOptions.onLoad }).then((auth) => {
  if (!auth) {
    window.location.reload();
  } else {
    Vue.$log.info("Authenticated");
    
    new Vue({
      vuetify,
      router,
      render: h => h(App, 
        { 
          props: 
          { 
            keycloak: keycloak 
          } 
        })
    }).$mount('#app')
  }


//Token Refresh
  setInterval(() => {
    keycloak.updateToken(70).then((refreshed) => {
      if (refreshed) {
        Vue.$log.info('Token refreshed' + refreshed);
      } else {
        Vue.$log.warn('Token not refreshed, valid for '
          + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
      }
    }).catch(() => {
      Vue.$log.error('Failed to refresh token');
    });
  }, 6000)

}).catch(() => {
  Vue.$log.error("Authenticated Failed");
});



