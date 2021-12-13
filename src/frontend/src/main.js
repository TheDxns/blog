import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import VueKeycloakJs from '@dsb-norge/vue-keycloak-js'
import VueLogger from 'vuejs-logger';
import wysiwyg from "vue-wysiwyg";

Vue.config.productionTip = false

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
    onLoad: 'check-sso',
    checkLoginFrame: false
  },
  config: {
    url: 'http://localhost:8080/auth/',
    realm: 'Blog',
    clientId: 'blog-client'
  },
  onReady (keycloak) {
    new Vue({
      vuetify,
      router,
      render: h => h(App, {
        props: {
          'keycloakData': keycloak
        }
      }),
      }).$mount('#app')
  }
})