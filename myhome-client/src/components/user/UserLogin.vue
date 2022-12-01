<template>
  <b-container id="login-wrapper">
    <b-card id="login-content">
      <div id="login-img"><img src="@/assets/img/main-img1.jpg" /></div>
      <b-form id="login-form">
        <div id="login-form-title">sign in</div>
        <b-form-group label="아이디:" label-for="userid">
          <b-form-input
            id="userid"
            v-model="user.id"
            required
            placeholder="아이디 입력...."
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form-group>
        <b-form-group label="비밀번호:" label-for="userpwd">
          <b-form-input
            type="password"
            id="userpwd"
            v-model="user.password"
            required
            placeholder="비밀번호 입력...."
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form-group>
        <b-alert show variant="danger" v-if="isLoginError"
          >아이디 또는 비밀번호를 확인하세요.</b-alert
        >
        <b-button type="button" variant="primary" class="m-1" @click="confirm"
          >로그인</b-button
        >
        <b-button type="button" variant="success" class="m-1" @click="movePage"
          >회원가입</b-button
        >
        <hr>
      <google-oauth />
      </b-form>
    </b-card>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import googleOauth from './oauth/googleOauth.vue';

const memberStore = "memberStore";

export default {
  components: { googleOauth },
  name: "UserLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        id: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "Main" });
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>

<style lang="scss" scoped>
#login-wrapper {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

#login-content {
  margin: 50px;
  width: 80%;
  max-height: 500px;
  border-radius: 10px;

  .card-body {
    width: 100%;
    height: 400px;
    display: flex;
    text-align: left;

    #login-img {
      flex: 1;
      width: 100%;
      height: 100%;
      padding: 10px;
      display: flex;
      img {
        max-width: 1024px;
        max-height: 426px;
        border-radius: 5%;
        text-align: center;
      }
    }

    #login-form {
      flex: 1;
      width: 100%;
      display: flex;
      padding: 30px;
      font-family: "NanumSquareBold";
      flex-direction: column;
      justify-content: center;

      #login-form-title {
        width: 100%;
        font-size: 20px;
        font-family: "NanumSquareExtraBold";
        text-align: center;
      }
    }

    #login-form-title {
      color: #f0a663;
      font-family: "Rubik Bubbles", cursive !important;
      font-size: 20px;
    }
  }
}
</style>
