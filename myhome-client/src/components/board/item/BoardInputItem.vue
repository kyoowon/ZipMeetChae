<template>
  <b-container style="text-align: left" fluid>
    <b-form @submit="onSubmit" @reset="onReset">
      <section>
        <div class="search-content-title">제목</div>
        <b-form-group
          id="subject-group"
          label-for="subject"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>
      </section>
      <b-container id="board-search-wrapper">
        <article class="board-search-content">
          <section>
            <div class="search-content-title">금액</div>
            <b-form-group
              id="price-group"
              label-for="price"
              description="금액 입력(만원)하세요."
            >
              <b-form-input
                id="price"
                v-model="article.price"
                type="number"
                required
                placeholder="금액 입력 (만원)"
              ></b-form-input>
            </b-form-group>
          </section>
          <section>
            <div class="search-content-title">층</div>
            <b-form-group
              id="floor-group"
              label-for="floor"
              description="층을 입력하세요."
            >
              <b-form-input
                id="floor"
                v-model="article.floor"
                type="number"
                required
                placeholder="층 입력..."
              ></b-form-input>
            </b-form-group>
          </section>
          <section>
            <div class="search-content-title">m^2</div>
            <b-form-group
              id="area-group"
              label-for="area"
              description="면적를 입력하세요."
            >
              <b-form-input
                id="area"
                v-model="article.area"
                type="number"
                required
                placeholder="면적 입력..."
              ></b-form-input>
            </b-form-group>
          </section>
        </article>
        <article>
          <div class="search-content-title">일정 선택</div>
          <v-date-picker v-model="range" :model-config="modelConfig" is-range />
        </article>
        <article class="board-search-content">
          <section>
            <div class="search-content-title">매물 선택</div>
          </section>
          <section id="select-input-box">
            <b-form-group
              id="aptName-group"
              label-for="aptName"
              description="매물을 선택해주세요"
            >
              <b-form-input
                id="aptName"
                v-model="article.apartmentName"
                type="text"
                required
                placeholder="매물 선택..."
                readonly
              ></b-form-input>
            </b-form-group>
            <b-button id="show-btn" @click="$bvModal.show('bv-modal-example')"
              >선택</b-button
            >
          </section>
          <section>
            <b-form-group
              id="startTime-group"
              label="시작 시간 선택"
              label-for="startTime"
              description="시작 시간을 선택해주세요"
            >
              <b-form-input
                id="startTime"
                v-model="range.start"
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
              description="종료 시간을 선택해주세요"
            >
              <b-form-input
                id="endTime"
                v-model="range.end"
                type="text"
                required
                placeholder="종료 시간 선택..."
                readonly
              ></b-form-input>
            </b-form-group>
          </section>
        </article>
        <b-modal id="bv-modal-example" hide-footer>
          <template #modal-title> 유사 매물 정보 </template>
          <div class="d-block text-center">
            <board-search-bar @getAptDongCode="searchApt" />
            <board-write-template-list
              :fields="fields"
              :infos="getFieldsAptInfo"
              @getAptInfo="setAptInfo"
            />
          </div>
          <b-button
            class="mt-3"
            block
            @click="$bvModal.hide('bv-modal-example')"
            >닫기</b-button
          >
        </b-modal>
      </b-container>

      <b-form-group id="content-group" label-for="content">
        <v-editor
          id="editor"
          api-key="process.env.VUE_APP_EDIER_API_KEY"
          :init="{
            height: 500,
            menubar: false,
            plugins: [
              'advlist autolink lists link image charmap print preview anchor',
              'searchreplace visualblocks code fullscreen',
              'insertdatetime media table paste code help wordcount',
            ],
            toolbar:
              'undo redo | formatselect | bold italic backcolor | \
           alignleft aligncenter alignright alignjustify | \
           bullist numlist outdent indent | removeformat | help',
          }"
        />
      </b-form-group>

      <b-button
        type="submit"
        variant="primary"
        class="m-1"
        v-if="this.type === 'register'"
        >글작성</b-button
      >
      <b-button type="submit" variant="primary" class="m-1" v-else
        >글수정</b-button
      >
      <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
    </b-form>
  </b-container>
</template>

<script>
import _axios from "@/plugins/axios";
import moment from "moment";
// import Editor from "@tinymce/tinymce-vue";
import BoardSearchBar from "../BoardSearchBar.vue";
import { mapState, mapActions, mapGetters } from "vuex";
import BoardWriteTemplateList from "./BoardWriteTemplateList.vue";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  components: {
    BoardSearchBar,
    // editor: Editor,
    BoardWriteTemplateList,
  },
  name: "BoardInputItem",
  data() {
    return {
      range: {
        start: moment(new Date()).format("YYYY-MM-DD:HH:mm:ss"),
        end: moment(new Date()).format("YYYY-MM-DD:HH:mm:ss"),
      },
      modelConfig: {
        type: "string",
        mask: "YYYY-MM-DD:HH:mm:ss", // Uses 'iso' if missing
      },
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
      fields: [{ key: "apartmentName", label: "이름", tdClass: "tdClass" }],
      isUserid: false,
    };
  },
  filters: {
    dateCell(value) {
      let dt = new Date(value);

      return dt.getDate();
    },
    date(val) {
      return val ? val.toLocaleString() : "";
    },
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapGetters(houseStore, ["getStateChart", "getFieldsAptInfo"]),
  },
  mounted() {
    if (this.type === "modify") {
      _axios.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
        console.log(data);
        this.article = data;
        this.isUserid = true;
        this.setTinyContent();
      });
    }
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList"]),
    setAptInfo(aptInfo) {
      this.article.aptCode = aptInfo.aptCode;
      this.article.apartmentName = aptInfo.apartmentName;
    },
    async setTinyContent() {
      await tinymce.activeEditor.setContent(this.article.content);
      console.log(this.article.content);
    },
    onSubmit(event) {
      event.preventDefault();
      this.article.content = tinymce.get("editor").getContent(self.value);

      console.log(this.article);
      console.log(this.range);

      this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    searchApt(dongCode) {
      if (dongCode) this.getHouseList(dongCode);
    },
    onReset(event) {
      event.preventDefault();
      tinymce.get("editor").setContent("");
      (this.article.apartmentName = ""), (this.article.articleno = 0);
      this.article.subject = "";
      this.article.content = "";
      this.article.price = "";
      this.article.floor = 0;
      this.article.area = 0;
      this.article.aptCode = "";
      this.moveList();
    },
    getAptCode() {},
    registArticle() {
      _axios
        .post(`/board`, {
          apartmentName: this.article.apartmentName,
          userid: this.userInfo.id,
          subject: this.article.subject,
          content: this.article.content,
          aptCode: this.article.aptCode,
          floor: this.article.floor,
          area: this.article.area,
          price: this.article.price,
          startTime: this.range.start,
          endTime: this.range.end,
        })
        .then(({ data }) => {
          console.log("checkckckck", data.message);
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data.message === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        })
        .catch((e) => console.log(e));
    },
    modifyArticle() {
      _axios
        .put(`/board`, {
          apartmentName: this.article.apartmentName,
          articleno: this.article.articleno,
          userid: this.userInfo.id,
          subject: this.article.subject,
          content: this.article.content,
          aptCode: this.article.aptCode,
          floor: this.article.floor,
          area: this.article.area,
          price: this.article.price,
          startTime: this.range.start,
          endTime: this.range.end,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data.message === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        })
        .catch((e) => console.log(e));
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style lang="scss" scoped>
#board-search-wrapper {
  display: flex;
  width: 100%;
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

.slot {
  background-color: #aaa;
  padding: 0.5rem;
  color: white;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.text-black {
  color: #000;
}
</style>
