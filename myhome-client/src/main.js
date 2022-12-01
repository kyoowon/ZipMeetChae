import "@babel/polyfill";
import "mutationobserver-shim";
import Vue from "vue";
import "./plugins/axios";
import "./plugins/bootstrap-vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import '@/api/lib/fontAwesomeIcon';
import '@/api/lib/calender';
import '@/api/lib/Editor';
import Calendar from "v-calendar/lib/components/calendar.umd";
import DatePicker from "v-calendar/lib/components/date-picker.umd";

// Register components in your 'main.js'
Vue.component("v-calendar", Calendar);
Vue.component("v-date-picker", DatePicker);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
