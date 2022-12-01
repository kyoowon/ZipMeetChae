<template>
  <div id="app">
    <div id="body-wrapper">
      <the-header-navbar id="main-header"></the-header-navbar>
      <div id="main-body">
        <router-view />
      </div>
      <the-footer id="main-footer" />
    </div>
  </div>
</template>

<script>
import TheHeaderNavbar from "@/components/common/TheHeaderNavbar.vue";
import TheFooter from "@/components/common/TheFooter.vue";

export default {
  name: "App",
  components: {
    TheHeaderNavbar,
    TheFooter,
  },
};
</script>

<style lang="scss">
@import url("https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css");
@import url("https://fonts.googleapis.com/css2?family=Rubik+Bubbles&family=Source+Code+Pro:ital,wght@0,200;0,300;0,400;0,500;0,600;0,800;0,900;1,200;1,300;1,400;1,500;1,700;1,800&display=swap");

#app {
  margin: 0;
  padding: 0;
  height: 100%;
  /* font-family: 'NanumSquareLight';
  font-family: 'NanumSquare'; */
  /* font-family: "NanumSquareBold"; */
  font-family: "NanumSquareExtraBold";
  /* font-family: 'NanumSquareAcb';
  font-family: 'NanumSquareAceb';
  font-family: 'NanumSquareAcl';
  font-family: 'NanumSquareAcr'; */
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  background-color: white;
  display: flex;
  flex-direction: column;
}

#app > #body-wrapper {
  display: flex;
  flex-direction: column;
}

#app > footer {
  width: 100%;
  height: 10vh; /* footer의 높이 */
  position: fixed;
  bottom: 0;
  left: 0;
}
</style>
