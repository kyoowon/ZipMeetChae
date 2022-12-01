<template>
  <b-card>
    <div id="sidebar-main-title">
      <span
        ><router-link :to="{ name: 'Content' }">{{ sidoName }}</router-link>
      </span>
      <span
        ><router-link :to="{ name: 'Favorite' }">관심목록</router-link></span
      >
    </div>
    <router-view />
  </b-card>
</template>

<script>
export default {
  data() {
    return {
      sidoName: "검색",
      gugunName: "",
      dongName: "",
    };
  },
  components: {},
};
</script>

<style lang="scss" scoped>
#sidebar-main-title{
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: center;

  span{
    font-size: 25px;
    margin: 10px;
  }
}
</style>
