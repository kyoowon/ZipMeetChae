<template>
  <div id="main-wrapper" class="container">
    <main-carousel-comp id="carousel-comp" />
    <div class="title">
      <h1 class="title logo">ZipMeetChae</h1>
    </div>
    <main-team-intro />
  </div>
</template>

<script>
import MainCarouselComp from "../components/main/MainCarouselComp.vue";
import MainSearchbar from "../components/main/MainSearchbar.vue";
import MainTeamIntro from "../components/main/MainProjectIntro.vue";
export default {
  name: "MainView",
  components: {
    MainCarouselComp,
    MainSearchbar,
    MainTeamIntro,
  },
};
</script>

<style scoped>
.title {
  font-size: 50px;
  margin: 20px 0 40px 0;
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

#main-wrapper {
  display: block;
  align-items: center;
  justify-content: center;
  height: 100%;
}

#carousel-comp {
  margin: 20px 0;
  width: 100%;
}
</style>
