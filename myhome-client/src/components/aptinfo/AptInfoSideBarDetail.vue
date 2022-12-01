<template>
  <b-card>
    <h2>{{ aptInfo.apartmentName }}</h2>
    <div id="detail-btn">
      <font-awesome-icon
        id="return-btn"
        @click="movePage"
        icon="fa-solid fa-arrow-left"
        class="fa-xl"
      />
      <div class="heart" @click="likeEvent"></div>
    </div>
    <hr />
    <apt-info-chart :info="getChartDataformat" />
    <hr />
    <section id="AptInfo-board-title">매물 거래</section>
    <section id="AptInfo-board">
      <board-template-list :fields="fields_deal" :infos="boards"  />
    </section>
    <section id="AptInfo-list-title">거래 정보</section>
    <section id="AptInfo-list">
      <default-list :fields="fields" :infos="deals" />
    </section>
    <hr />
  </b-card>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
import AptInfoSearchBar from "@/components/aptinfo/AptInfoSearchBar.vue";
import AptInfoChart from "@/components/aptinfo/item/AptInfoChart.vue";
import DefaultList from "@/components/aptinfo/item/DefaultList.vue";
import BoardTemplateList from "../board/item/BoardTemplateList.vue";
import _axios from "@/plugins/axios";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  data() {
    return {
      aptInfo: null,
      boards: [],
      fields: [
        { key: "floor", label: "층", tdClass: "tdClass", sortable: true },
        { key: "area", label: "m^2", tdClass: "tdClass", sortable: true },
        {
          key: "dealAmount",
          label: "거래액",
          tdClass: "tdClass",
          sortable: true,
        },
        { key: "dealYear", label: "년", tdClass: "tdClass", sortable: true },
        { key: "dealMonth", label: "월", tdClass: "tdClass", sortable: true },
      ],
      fields_deal: [
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
      ],
    };
  },
  components: {
    AptInfoSearchBar,
    AptInfoChart,
    DefaultList,
    BoardTemplateList,
  },
  created() {
    this.aptInfo = this.$route.params.aptinfo;
    console.log(this.aptInfo);
    console.log(this.boards);
    if (this.aptInfo) this.getDealList(this.aptInfo.aptCode);
    else {
      this.$router.push({
        name: "mainaptInfo",
      });
    }
    _axios({
      url: "/board",
    }).then((info) => {
      this.boards = info.data.list;
    });
  },
  computed: {
    ...mapGetters(houseStore, ["getChartDataformat"]),
    ...mapState(houseStore, ["deals", "error"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  mounted() {
    if(this.aptInfo.fovarite){
      let element = document.querySelector(".heart");
      element.classList.toggle("is-active");
    }
  },
  methods: {
    ...mapActions(houseStore, [
      "getHouseList",
      "getDealList",
      "setFavoriteApt",
      "removeFavoriteApt",
    ]),
    movePage() {
      this.$router.push({
        name: "mainaptInfo",
      });
    },
    likeEvent() {
      let element = document.querySelector(".heart");
      if (element.classList.toggle("is-active")) {
        console.log(this.userInfo.id);
        this.setFavoriteApt({
          aptCode: this.aptInfo.aptCode,
          id: this.userInfo.id,
        });
        if (this.error) {
          element.classList.toggle("is-active");
        }
      } else {
        this.removeFavoriteApt({
          aptCode: this.aptInfo.aptCode,
          id: this.userInfo.id,
        });
        if (this.error) {
          element.classList.toggle("is-active");
        }
      }
    },
  },
};
</script>

<style lang="scss" scoped>
#detail-btn {
  z-index: 6;
  position: absolute;
  width: 120px;
  height: 100px;
  right: -5px;
  top: -10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

#return-btn {
  cursor: pointer;
}

.heart {
  width: 100px;
  height: 100px;
  background: url("https://cssanimation.rocks/images/posts/steps/heart.png")
    no-repeat;
  background-position: 0 -1px;
  cursor: pointer;
  transition: background-position 1s steps(28);
  transition-duration: 0s;

  &.is-active {
    transition-duration: 1s;
    background-position: -2800px -1px;
  }
}

body {
  background: #000000;
}

.placement {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
