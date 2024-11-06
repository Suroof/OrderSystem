import Layout from "@/layout/Index.vue";
import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/login", // 修改默认重定向到登录页面
  },
  {
    path: "/",
    redirect: "/dashboard",
    component: Layout,
    children: [
      {
        path: "/dashboard",
        name: "dashboard",
        component: () => import("@/views/dashboard/Index.vue"),
        meta: {
          title: "首页",
          requiresAuth: true,
        },
      },
      {
        path: "/banner",
        name: "banner",
        component: () => import("@/views/banner/Index.vue"),
        meta: {
          title: "广告管理",
          requiresAuth: true,
        },
      },
      {
        path: "/category",
        name: "category",
        component: () => import("@/views/category/Index.vue"),
        meta: {
          title: "菜品分类",
          requiresAuth: true,
        },
      },
      {
        path: "/comment",
        name: "comment",
        component: () => import("@/views/comment/Index.vue"),
        meta: {
          title: "评论管理",
          requiresAuth: true,
        },
      },
      {
        path: "/goods",
        name: "goods",
        component: () => import("@/views/goods/Index.vue"),
        meta: {
          title: "菜品管理",
          requiresAuth: true,
        },
      },
      {
        path: "/order",
        name: "order",
        component: () => import("@/views/order/Index.vue"),
        meta: {
          title: "订单管理",
          requiresAuth: true,
        },
      },
      {
        path: "/user",
        name: "user",
        component: () => import("@/views/user/Index.vue"),
        meta: {
          title: "用户管理",
          requiresAuth: true,
        },
      },
      {
        path: "/rider",
        name: "rider",
        component: () => import("@/views/rider/Index.vue"),
        meta: {
          title: "骑手管理",
          requiresAuth: true,
        },
      },
    ],
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/login/Login.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem("token");
  if (to.matched.some((record) => record.meta.requiresAuth) && !token) {
    next("/login");
  } else {
    next();
  }
});

export default router;
