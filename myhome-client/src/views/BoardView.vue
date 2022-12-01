<template>
  <b-container class="Main" id="Board-wrapper">
    <h3>매매 게시판</h3>
    <router-view></router-view>
  </b-container>
</template>

<script>

export default {
  name: "BoardView",
  components: {
  },
  data() {
    return {
    };
  },
};
</script>

<style scoped>
#Board-wrapper {
  display: block;
  height: 100%;
  padding: 20px 0;
}
</style>
