<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-jumbotron>
      <template #header>My Page</template>

      <template #lead> 내 정보 확인페이지입니다. </template>

      <hr class="my-4" />

      <b-container class="mt-4">
        <b-row>
          <b-col cols="2"></b-col>
          <b-col cols="2" align-self="end">아이디</b-col
          ><b-col cols="4" align-self="start">{{ userInfo.id }}</b-col>
          <b-col cols="2"></b-col>
        </b-row>
        <b-row>
          <b-col cols="2"></b-col>
          <b-col cols="2" align-self="end">이름</b-col
          ><b-col cols="4" align-self="start">{{ userInfo.name }}</b-col>
          <b-col cols="2"></b-col>
        </b-row>
        <b-row>
          <b-col cols="2"></b-col>
          <b-col cols="2" align-self="end">이메일</b-col
          ><b-col cols="4" align-self="start">{{ userInfo.address }}</b-col>
          <b-col cols="2"></b-col>
        </b-row>
        <b-row>
          <b-col cols="2"></b-col>
          <b-col cols="2" align-self="end">가입일</b-col
          ><b-col cols="4" align-self="start">{{ userInfo.joinDate }}</b-col>
          <b-col cols="2"></b-col>
        </b-row>
      </b-container>
      <hr class="my-4" />

      <b-button variant="primary" href="#" class="mr-1" @click="userUpdate"
        >정보수정</b-button
      >
      <b-button variant="danger" href="#" @click="userDelete"
        >회원탈퇴</b-button
      >
    </b-jumbotron>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo", "isLeaveError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout", "userLeave"]),
    userUpdate() {},
    userDelete() {
      if (confirm("정말 서비스를 탈퇴하시겠습니까?")) {
        console.log(this.userInfo.userid);
        this.userLeave(this.userInfo.id);
        this.userLogout(this.userInfo.userid);
        let msg = "회원탈퇴를 실패했습니다. 다시 시도해주세요.";
        if (!this.isLeaveError){
          msg = "회원탈퇴에 성공했습니다. 서비스를 이용해주셔서 감사합니다.";
          sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
          sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
        }
        alert(msg);
        if (this.$route.path != "/") this.$router.push({ name: "Main" });
      }
    },
  },
};
</script>

<style></style>
