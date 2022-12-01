"use strict";

import axios from "axios";

let config = {
  baseURL: process.env.VUE_APP_BASE_URL,
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
};

const _axios = axios.create(config);

export default _axios;
