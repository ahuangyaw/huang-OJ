import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ArcoVue from "@arco-design/web-vue";
import "@arco-design/web-vue/dist/arco.css";
import "@/plugins/axios";
import "@/access/index";
import "bytemd/dist/index.css";

createApp(App).use(store).use(router).use(ArcoVue).mount("#app");

const debounce = (fn: any, delay: any) => {
  let timer: any = null;
  return (...args: any[]) => {
    if (timer) {
      clearTimeout(timer);
    }
    timer = setTimeout(() => {
      fn(...args);
    }, delay);
  };
};

const _ResizeObserver = window.ResizeObserver;
window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
  constructor(callback: any) {
    callback = debounce(callback, 200);
    super(callback);
  }
};
