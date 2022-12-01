<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <b-container id="board-search-wrapper">
              <article class="board-search-content">
                <section>
                  <div class="search-content-title">매물 선택</div>
                  <b-form-group id="aptName-group" label-for="aptName">
                    <b-form-input
                      id="aptName"
                      v-model="article.apartmentName"
                      type="text"
                      required
                      placeholder="매물 선택..."
                      readonly
                    ></b-form-input>
                  </b-form-group>
                </section>
                <section>
                  <div class="search-content-title">금액</div>
                  <b-form-group id="price-group" label-for="price">
                    <b-form-input
                      id="price"
                      v-model="article.price"
                      type="number"
                      required
                      placeholder="금액 입력 (만원)"
                      readonly
                    ></b-form-input>
                  </b-form-group>
                </section>
                <section>
                  <div class="search-content-title">층</div>
                  <b-form-group id="floor-group" label-for="floor">
                    <b-form-input
                      id="floor"
                      v-model="article.floor"
                      type="number"
                      required
                      placeholder="층 입력..."
                      readonly
                    ></b-form-input>
                  </b-form-group>
                </section>
                <section>
                  <div class="search-content-title">m^2</div>
                  <b-form-group id="area-group" label-for="area">
                    <b-form-input
                      id="area"
                      v-model="article.area"
                      type="number"
                      required
                      placeholder="면적 입력..."
                      readonly
                    ></b-form-input>
                  </b-form-group>
                </section>
              </article>
              <article>
                <div class="search-content-title">일정 선택</div>
                <v-date-picker
                  :available-dates="available"
                  v-model="selectDate"
                  mode="dateTime"
                  :model-config="modelConfig"
                  update-on-input
                />
              </article>
              <article class="board-search-content">
                <section>
                  <b-form-group
                    id="startTime-group"
                    label="시작 시간 선택"
                    label-for="startTime"
                  >
                    <b-form-input
                      id="startTime"
                      v-model="available.start"
                      type="text"
                      required
                      placeholder="시작 시간 선택..."
                      readonly
                    ></b-form-input>
                  </b-form-group>
                </section>
                <section>
                  <b-form-group
                    id="endTime-group"
                    label="종료 시간 선택"
                    label-for="endTime"
                  >
                    <b-form-input
                      id="endTime"
                      v-model="available.end"
                      type="text"
                      required
                      placeholder="종료 시간 선택..."
                      readonly
                    ></b-form-input>
                  </b-form-group>
                </section>
                <section>
                  <b-form-group
                    id="endTime-group"
                    label="비대면 시간 선택"
                    label-for="endTime"
                  >
                    <b-form-input
                      id="endTime"
                      v-model="selectDate"
                      type="text"
                      required
                      placeholder="비대면 시간 선택..."
                      readonly
                    ></b-form-input>
                  </b-form-group>
                </section>
                <b-button
                  type="button"
                  id="show-btn"
                  class="btn btn-primary"
                  @click="$bvModal.show('bv-modal-example')"
                  >일정 선택하기</b-button
                >
              </article>
            </b-container>
            <div v-html="article.content"></div>
          </b-card-body>
        </b-card>
        <b-modal id="bv-modal-example" hide-footer>
          <template #modal-title> 이 일정으로 예약하시겠습니까? </template>
          <div class="d-block">
            <div id="Reservation-info">
              <span>매물명 : {{ article.apartmentName }}</span>
              <span>금액 : {{ article.price }}천원</span>
              <span>예약 일정 : {{ selectDate }}</span>
              <span>예약자 id : {{ userInfo.id }}</span>
              <span>예약자 이름 : {{ userInfo.name }}</span>
              <span>예약자 연락처 : {{ userInfo.phone }}</span>
              <b-button
                type="button"
                class="btn btn-primary"
                @click="setReserve"
                >예약하기</b-button
              ><br />
            </div>
            해당 정보를 매매자에게 전달됩니다.
          </div>
          <b-button
            class="mt-3"
            block
            @click="$bvModal.hide('bv-modal-example')"
            >닫기</b-button
          >
        </b-modal>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import moment from "moment";
import _axios from "@/plugins/axios";
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {
        apartmentName: "",
        articleno: 0,
        subject: "",
        content: "",
        aptCode: "",
        price: 0,
        floor: "",
        area: 0,
      },
      selectDate: moment(new Date()).format("YYYY-MM-DD:HH:mm:ss"),
      available: {
        start: "",
        end: "",
      },
      modelConfig: {
        type: "string",
        mask: "YYYY-MM-DD:HH:mm:ss", // Uses 'iso' if missing
      },
      fields: [{ key: "apartmentName", label: "이름", tdClass: "tdClass" }],
      isUserid: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    _axios({
      url: `/board/${this.$route.params.articleno}`,
    }).then((info) => {
      console.log(info);
      this.article = info.data;
      this.available.start = info.data.startTime;
      this.available.end = info.data.endTime;
    });
  },
  updated() {
    console.log(this.selectDate);
  },
  methods: {
    ...mapActions(memberStore, ["addCalenderEvent"]),
    async setReserve() {
      await this.addCalenderEvent({start : new Date(this.selectDate), id: "kyoowon95@gmail.com"});
      this.$router.push({ name: "Buyer" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { articleno: this.article.articleno },
      });
      this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { articleno: this.article.articleno },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
    },
  },
};
</script>

<style lang="scss" scoped>
#board-search-wrapper {
  display: flex;
  width: 100%;
  min-height: 45vh;
  height: 100%;
  padding: 0;
  .board-search-content {
    flex: 1;
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    section {
      text-align: left;
      width: 90%;
      height: 100%;
    }
  }
}

.search-content-title {
  width: 100%;
  text-align: left;
}

#select-input-box {
  display: flex;
  flex-direction: row;
  justify-content: center;

  button {
    margin: 0 10px;
    width: 60px;
    height: 38px;
  }
}

#Reservation-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  // align-items: center;

  span {
    margin: 5px;
  }
  button {
    align-self: center;
    margin: 10px;
    width: 150px;
  }
}
</style>
