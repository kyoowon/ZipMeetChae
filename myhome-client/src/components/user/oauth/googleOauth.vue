<template>
  <article>
    <b-button
      type="button"
      class="btn btn-light"
      id="authorize_button"
      @click="handleAuthClick()"
      v-if="isAuth"
    >
      <font-awesome-icon icon="fa-brands fa-google" />
      {{ authTest }}
    </b-button>
  </article>
</template>

<script>
import { mapActions } from "vuex";
let memberStore = "memberStore";

export default {
  data() {
    return {
      isAuth: true,
      authTest: "google 로그인",
    };
  },
  created() {
    this.oAuthLoginEvent();
  },
  methods: {
    ...mapActions(memberStore, ["oAuthLoginEvent", "callAuthHandler"]),
    /**
     *  Sign in the user upon button click.
     */
    async handleAuthClick() {
      this.callAuthHandler();
      this.$router.push({ name: "Main" });
      this.authTest = "다시 인증하기";
    },
  },
};
</script>

<style lang="scss" scoped>
article {
  width: 100%;
  height: 100px;

  button {
    width: 100%;
  }
}
</style>
