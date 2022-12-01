import Vue from "vue";
import VueRouter from "vue-router";
import MainView from "@/views/MainView";

import store from "@/store";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  const OAuthToken = store.getters["memberStore/OAuthToken"];
  const isOAuthToken = store.getters["memberStore/isOAuthToken"];

  let token = sessionStorage.getItem("access-token");

  // console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    // console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if ((!checkToken || checkUserInfo === null) && !isOAuthToken) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({
      name: "login"
    });
  } else {
    // console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [{
    path: "/",
    name: "Main",
    component: MainView,
  },
  {
    path: "/user",
    name: "User",
    component: () => import("@/views/UserView"),
    children: [{
        path: "join",
        name: "join",
        component: () => import("@/components/user/UserRegister"),
      },
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/UserLogin"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/UserMyPage"),
      },
    ],
  },
  {
    path: "/board",
    name: "Board",
    component: () => import("@/views/BoardView"),
    redirect: "/board/list",
    children: [{
        path: "list",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "wirte",
        name: "boardwrite",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "view/:articleno",
        name: "boardview",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardView"),
      },
      {
        path: "modify/:articleno",
        name: "boardmodify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify"),
      },
      {
        path: "delete/:articleno",
        name: "boarddelete",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDelete"),
      },
    ],
  },
  {
    path: "/deal",
    name: "Deal",
    redirect: "/deal/buyer",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/DealView"),
    children: [{
        path: "buyer",
        name: "Buyer",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/deal/BuyerScehdule"),
      },
    ],
  },
  {
    path: "/aptinfo",
    name: "Aptinfo",
    component: () => import("@/views/AptinfoView"),
    redirect: "/aptinfo/main",
    children: [{
        path: "main",
        name: "mainaptInfo",
        redirect: "/aptinfo/main/content",
        component: () => import("@/components/aptinfo/AptInfoSideBarMain"),
        children: [{
            path: "content",
            name: "Content",
            component: () => import("@/components/aptinfo/AptInfoSideBarMainContent"),
          },
          {
            path: "favorite",
            name: "Favorite",
            component: () => import("@/components/aptinfo/AptInfoSideBarMainFavorite"),
          },
        ]
      },
      {
        path: "detail",
        name: "detailInfo",
        component: () => import("@/components/aptinfo/AptInfoSideBarDetail"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;