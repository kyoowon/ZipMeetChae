<template>
  <b-container id="update-wrapper">
    <b-card id="update-content">
      <div id="update-form-title">update</div>
      <b-form id="update-form">
        <b-form-group label="아이디:" label-for="userid">
          <b-form-input
            type="email"
            id="userid"
            v-model="user.id"
            required
            placeholder="아이디 입력...."
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
        <b-form-group label="이름:" label-for="name">
          <b-form-input
            id="name"
            v-model="user.name"
            required
            placeholder="이름 입력...."
          ></b-form-input>
        </b-form-group>
        <b-form-group label="전화번호:" label-for="phone">
          <b-form-input
            id="phone"
            v-model="user.phone"
            required
            placeholder="전화번호 입력...."
          ></b-form-input>
        </b-form-group>
        <b-form-group label="주소:" label-for="address">
          <b-form-input
            id="address"
            v-model="user.address"
            required
            placeholder="주소 입력...."
          ></b-form-input>
        </b-form-group>
        <b-alert show variant="danger" v-if="isupdateError"
          >아이디 또는 비밀번호를 확인하세요.</b-alert
        >
        <!-- Button trigger modal -->
        <button
          type="button"
          class="btn btn-primary"
          data-bs-toggle="modal"
          data-bs-target="#exampleModal"
        >
          수정하기
        </button>

        <!-- Modal -->
        <div
          class="modal fade"
          id="exampleModal"
          tabindex="-1"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">
                  Modal title
                </h1>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">...</div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  Close
                </button>
                <button type="button" class="btn btn-primary">
                  Save changes
                </button>
              </div>
            </div>
          </div>
        </div>
      </b-form>
    </b-card>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";

export default {
  name: "Userupdate",
  data() {
    return {
      user: {
        id: null,
        password: null,
        name: null,
        phone: null,
        address: null,
      },
    };
  },
  computed: {},
  methods: {
    async confirm() {},
  },
};
</script>

<style lang="scss" scoped>
#update-form-title {
  color: #f0a663;
  font-family: "Rubik Bubbles", cursive !important;
  font-size: 20px;
  text-align: center;
  margin: 10px 0;
}

#update-wrapper {
  width: 100%;
  height: 100%;
  font-family: "NanumSquareBold";
  display: flex;
  align-items: center;
  // justify-content: center;
}

#update-content {
  margin: 50px 0;
  text-align: left;
  width: 100%;
  height: 80%;
  border-radius: 10px;

  .card-body {
    width: 100%;
    max-height: 500px;

    label {
      font-weight: bold;
    }
  }
}

#update-form {
  width: 100%;
  display: flex;
  padding: 0 20px;
  font-family: "NanumSquareBold";
  flex-direction: column;
  justify-content: center;
}
</style>
