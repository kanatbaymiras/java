import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import ShoppingCart from "@/views/ShoppingCart.vue";
import Order from "@/views/Order.vue";

const routes = [
  {
    path: "/",
    redirect: "/home",
  },
  {
    name: "home",
    path: "/home",
    component: Home,
  },
  {
    name: "shoppingCart",
    path: "/shopping-cart",
    component: ShoppingCart,
  },
  {
    name: "order",
    path: "/order",
    component: Order,
  },
];

const router = createRouter({ history: createWebHistory(), routes });

export default router;
