import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import authentication from "@/plugins/authentication"
import VueLogger from 'vuejs-logger';

Vue.config.productionTip = false
Vue.use(authentication)
const isProduction = process.env.NODE_ENV === 'production';
const options = {
  isEnabled: true,
  logLevel : isProduction ? 'error' : 'debug',
  stringifyArguments : false,
  showLogLevel : true,
  showMethodName : true,
  separator: '|',
  showConsoleColors: true
};

Vue.use(VueLogger, options);

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
    
    new Vue({
      vuetify,
      router,
      render: h => h(App, 
        {
          props: {
            
          }
        })
    }).$mount('#app')

    
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