import jwtDecode from "jwt-decode";
import router from "@/router";
import {
  login,
  regist,
  findById,
  tokenRegeneration,
  logout,
  userDelete,
  userUpdate,
} from "@/api/member";

import {
  listConnectionNames,
  maybeEnableButtons,
  AuthHandler,
  listUpcomingEvents,
  createCalenderEvent,
} from "@/api/oAuth";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    isRegist: false,
    isRegistError: false,
    isLeaveError: false,
    userInfo: null,
    isValidToken: false,
    isOAuth: false,
    oAuthToken: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
    isOAuthToken: async function (state) {
      return state.isOAuth;
    },
    OAuthToken: async function (state) {
      return state.oAuthToken;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_REGIST: (state, isRegist) => {
      state.isRegist = isRegist;
    },
    SET_IS_REGIST_ERROR: (state, isRegistError) => {
      state.isRegistError = isRegistError;
    },
    SET_IS_LEAVE_ERROR: (state, isLeaveError) => {
      state.isLeaveError = isLeaveError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_OAUTH_TOKEN: (state, user) => {
      state.isLogin = true;
      state.userInfo = user;
    },
  },
  actions: {
    // google oauth login

    listCalenderEvents() {
      return listUpcomingEvents();
    },

    // getUserInfo(){
    //   return listConnectionNames();
    // },

     callAuthHandler({commit}) {
      AuthHandler();

      listConnectionNames().then(info => {
        let user = {
          id : info.result.emailAddresses[0].value,
          name : info.result.names[0].displayName,
        }
        console.log(user);
        commit("SET_OAUTH_TOKEN", user);
      })
    },

    oAuthLoginEvent() {
      if (maybeEnableButtons()) {
        return true;
      }
      return false;
    },

    // resservce
    addCalenderEvent({commit}, params) {
      console.log(params);
      createCalenderEvent(params.start, params.id);
    },

    // JWT login & function
    async userConfirm({
      commit
    }, user) {
      await login(
        user,
        ({
          data
        }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            console.log("login success token created!!!! >> ", accessToken, refreshToken);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("SET_IS_VALID_TOKEN", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async userRegist({
      commit
    }, user) {
      await regist(
        user,
        ({
          data
        }) => {
          if (data.message === "success") {
            commit("SET_IS_REGIST", true);
            commit("SET_IS_REGIST_ERROR", false);
          } else {
            commit("SET_IS_REGIST", false);
            commit("SET_IS_REGIST_ERROR", true);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async userLeave({
      commit
    }, userid) {
      await userDelete(
        userid,
        ({
          data
        }) => {
          if (data.message === "success") {
            commit("SET_IS_LEAVE_ERROR", false);
          } else {
            commit("SET_IS_LEAVE_ERROR", true);
          }
        },
        (error) => {
          console.log(error);
          commit("SET_IS_LEAVE_ERROR", true);
        }
      )
    },
    async getUserInfo({
      commit,
      dispatch
    }, token) {
      let decodeToken = jwtDecode(token);
      // console.log("2. getUserInfo() decodeToken :: ", decodeToken);
      await findById(
        decodeToken.userid,
        ({
          data
        }) => {
          if (data.message === "success") {
            commit("SET_USER_INFO", data.userInfo);
            // console.log("3. getUserInfo data >> ", data);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log("getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ", error.response.status);
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },
    async tokenRegeneration({
      commit,
      state
    }) {
      console.log("토큰 재발급 >> 기존 토큰 정보 : {}", sessionStorage.getItem("access-token"));
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({
          data
        }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.userInfo.userid,
              ({
                data
              }) => {
                if (data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({
                  name: "login"
                });
              },
              (error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              }
            );
          }
        }
      );
    },
    async userLogout({
      commit
    }, userid) {
      await logout(
        userid,
        ({
          data
        }) => {
          if (data.message === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default memberStore;