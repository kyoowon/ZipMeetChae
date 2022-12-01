<template>
  <b-container id="Deal-wrapper">
    <h3>일정보기</h3>
    <!-- <b-nav tabs align="left">
      <b-nav-item title="임차인"
        ><router-link :to="{ name: 'Buyer' }" class="link"
          >임차인</router-link
        ></b-nav-item
      >
      <b-nav-item title="임대인"
        ><router-link :to="{ name: 'Dealer' }" class="link"
          >임대인</router-link
        ></b-nav-item
      >
    </b-nav> -->
    <router-view />
  </b-container>
</template>

<script>
export default {
  name: "DealView",
  data() {
    return {};
  },
  components: {},
  methods: {},
};
</script>

<style scoped>
#Deal-wrapper {
  display: block;
  height: 85vh;
  padding: 20px 0;
}

</style>
