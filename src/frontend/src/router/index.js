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
      title: "About"
    }
  },
  {
    path: '/contact',
    name: 'Contact',
    component: () => import(/* webpackChunkName: "contact" */ '../views/Contact.vue'),
    meta: {
      title: "Contact"
    }
  },
  {
    path: '/new-post',
    name: 'NewPost',
    component: () => import(/* webpackChunkName: "new-post" */ '../views/NewPost.vue'),
    meta: {
      title: "Create a new post",
      requiresAuth: true
    }
  },
  {
    path: '/posts/*',
    name: 'PostView',

    component: () => import(/* webpackChunkName: "new-post" */ '../views/PostView.vue'),
    meta: {
      title: "Show post"
    }
  },
  {
    path: '/edit/posts/*',
    name: 'PostEdit',

    component: () => import(/* webpackChunkName: "new-post" */ '../views/PostEdit.vue'),
    meta: {
      title: "Edit post"
    }
  },
  {
    path: '/unauthorized',
    name: 'Unauthorized',
    meta: {
      title: "Not authorized"
    },
    component: () => import('../views/Unauthorized.vue')
  },
  {
    path: '/search',
    name: 'Search',
    meta: {
      title: "Search for posts"
    },
    component: () => import('../views/Search.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms))
}

router.beforeEach(async (to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // We wait for Keycloak init, then we can call all methods safely
    while (router.app.$keycloak.createLoginUrl === null) {
      await sleep(100)
    }
    if (router.app.$keycloak.authenticated) {
      next()
    } else {
      const loginUrl = router.app.$keycloak.createLoginUrl()
      window.location.replace(loginUrl)
    }
  } else {
    document.title = to.meta.title;
    next()
  }
})

export default router