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
    path: '/profile-settings',
    name: 'ProfileSettings',
    component: () => import(/* webpackChunkName: "new-post" */ '../views/ProfileSettings.vue'),
    meta: {
      title: "Profile settings"
    }
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
    document.title = to.meta.title;
    next()
  })

export default router