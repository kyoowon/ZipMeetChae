<template>
  <!-- navbar component start -->
  <header>
    <div id="header-wrapper">
      <div>
        <div class="title">
          <router-link class="logo" :to="{ name: 'Main' }">ZipMeetChae</router-link>
          <span>구글밋(Meet)으로 구하는 집(house) 한 채</span>
        </div>
      </div>
      <b-navbar toggleable="lg">
        <b-collapse id="nav-collapse" is-nav>
          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto nav-bar">
            <router-link :to="{ name: 'Aptinfo' }">매물 정보</router-link>
            <router-link :to="{ name: 'Deal' }">일정 확인</router-link>
            <router-link :to="{ name: 'Board' }">매물 거래</router-link>
          </b-navbar-nav>
          <!-- after login -->
          <b-navbar-nav class="ml-auto" v-if="userInfo">
            <router-link :to="{ name: 'mypage' }" class="link align-self-center">
              <b-avatar variant="primary" v-text="userInfo.id.charAt(0).toUpperCase()"></b-avatar>
            </router-link>
            <b-nav-item class="align-self-center link" @click.prevent="onClickLogout">logout</b-nav-item>
          </b-navbar-nav>
          <!-- before login -->
          <b-navbar-nav class="ml-auto" v-else>
            <b-nav-item-dropdown right>
              <b-dropdown-item href="#">
                <router-link :to="{ name: 'join' }" class="link"> 회원가입 </router-link>
              </b-dropdown-item>
              <b-dropdown-item href="#">
                <router-link :to="{ name: 'login' }" class="link"> 로그인 </router-link>
              </b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      </b-navbar>
    </div>
    <hr />
  </header>
  <!-- navbar component end -->
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userid);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "Main" });
    },
  },
};
</script>

<style scoped>
header {
  height: 100%;
  width: 100%;
  background-color: #fefdfc;
}

#header-wrapper {
  width: 100%;
  display: flex;
  margin: 20px 0px;
  align-items: center;
  justify-content: space-between;
  padding: 0px 50px;
}

.title {
  font-size: 50px;
  margin: 20px;
  display: flex;
  flex-direction: column;
}

.title .logo {
  color: #f0a663;
  font-family: "Rubik Bubbles", cursive;
  text-decoration: none;
}

.title > span {
  font-size: 15px;
  font-family: "NanumSquareExtraBold";
  color: #999999;
}

.nav-bar {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}

.nav-bar a {
  font-size: 20px;
  margin: 0px 20px;
  color: #f0a663;
}

.link {
  width: 100%;
}
</style>
