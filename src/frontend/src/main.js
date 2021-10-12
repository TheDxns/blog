import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import VueKeycloakJs from '@dsb-norge/vue-keycloak-js'
import VueLogger from 'vuejs-logger';
import wysiwyg from "vue-wysiwyg";



Vue.config.productionTip = false

//Vue logger config
const isProduction = process.env.NODE_ENV === 'production';

const vueLoggerOptions = {
  isEnabled: true,
  logLevel : isProduction ? 'error' : 'debug',
  stringifyArguments : false,
  showLogLevel : true,
  showMethodName : true,
  separator: '|',
  showConsoleColors: true
};
Vue.use(VueLogger, vueLoggerOptions);

Vue.use(wysiwyg, {}); 

Vue.use(VueKeycloakJs, {
  init: {
    // Use 'login-required' to always require authentication
    // If using 'login-required', there is no need for the router guards in router.js
    onLoad: 'check-sso',
    checkLoginFrame: false
  },
  config: {
    url: 'http://localhost:8180/auth/',
    realm: 'Blog',
    clientId: 'blog-client'
  },
  onReady (keycloak) {
    console.log(keycloak);

    new Vue({
      vuetify,
      router,
      render: h => h(App),
      }).$mount('#app')
  }
})



/*
Vue.$keycloak
  .init({ onLoad: 'check-sso', checkLoginIframe: false })
  .then(() => {
    Vue.$keycloak.loadUserInfo().then(function(data) {
      Vue.prototype.$userInfo = data;
      console.log(data);
    }).catch((error) => {
      Vue.prototype.$userInfo = "undefined";
      console.error(error);
    }); 
    
    
    
    setInterval(() => {
      Vue.$keycloak.updateToken(70).then((refreshed) => {
        if (refreshed) {
          Vue.$log.info('Token refreshed' + refreshed);
        } else {
          Vue.$log.warn('Token not refreshed, valid for '
            + Math.round(Vue.$keycloak.tokenParsed.exp + Vue.$keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
        }
      }).catch(() => {
        Vue.$log.error('Failed to refresh token');
      });
    }, 6000)
  
  }).catch(() => {
    Vue.$log.error("Authenticated Failed");
  });
  */