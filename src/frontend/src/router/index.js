import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: {
      title: "Home"
    }
  },
  {
    path: '/about',
    name: 'About',
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
    meta: {
      title: "About",
      isAuthenticated: false
    }
  },
  {
    path: '/redactors',
    name: 'Redactors',
    component: () => import(/* webpackChunkName: "redactors" */ '../views/Redactors.vue'),
    meta: {
      title: "Redactors",
      isAuthenticated: false
    }
  },
  {
    path: '/contact',
    name: 'Contact',
    component: () => import(/* webpackChunkName: "contact" */ '../views/Contact.vue'),
    meta: {
      title: "Contact",
      isAuthenticated: false
    }
  },
  {
    path: '/new-post',
    name: 'NewPost',
    component: () => import(/* webpackChunkName: "new-post" */ '../views/NewPost.vue'),
    meta: {
      title: "Create a new post",
      isAuthenticated: true
    }
  },
  {
    path: '/posts/*',
    name: 'PostView',

    component: () => import(/* webpackChunkName: "new-post" */ '../views/PostView.vue'),
    meta: {
      title: "Show post",
      isAuthenticated: false
    }
  },
  {
    path: '/unauthorized',
    name: 'Unauthorized',
    meta: {
      isAuthenticated: false
    },
    component: () => import('../views/Unauthorized.vue')
  },
  {
    path: '/authorized',
    name: 'Home',
    meta: {
      isAuthenticated: true
    },
    component: () => import('../views/Home.vue')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.isAuthenticated) {
    if (!Vue.$keycloak.authenticated) {
      // The page is protected and the user is not authenticated. Force a login.
      Vue.$keycloak.login({ redirectUri: "http://localhost:3000/" + to.path })
    } else if (Vue.$keycloak.hasResourceRole('user')) {
      // The user was authenticated, and has the app role
      Vue.$keycloak.updateToken(70)
        .then(() => {
          next()
        })
        .catch(err => {
          console.error(err)
        })
    } else {
      // The user was authenticated, but did not have the correct role
      // Redirect to an error page
      next({ name: 'Unauthorized' })
    }
  } else {
    // This page did not require authentication
    document.title = to.meta.title;
    next()
  }
})

export default router