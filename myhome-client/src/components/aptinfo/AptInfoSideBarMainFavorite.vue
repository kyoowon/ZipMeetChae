<template>
  <b-card id="side-bar">
    <hr />
    <apt-info-template-list title="거래 정보" :fields="fields" :infos="getFieldFavorite" @changeFav="setFavoriteApt" />
  </b-card>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
import AptInfoTemplateList from "@/components/aptinfo/item/AptInfoTemplateList.vue";
import AptInfoSearchBar from "@/components/aptinfo/AptInfoSearchBar.vue";
import AptInfoChart from "@/components/aptinfo/item/AptInfoChart.vue";
const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  data() {
    return {
      fields: [
        { key: "apartmentName", label: "이름", tdClass: "tdClass" },
        { key: "buildtype", label: "신축", tdClass: "tdClass" },
        { key: "fovarite", label: "관심 등록", tdClass: "tdClass" },
      ],
    };
  },
  components: {
    AptInfoTemplateList,
    AptInfoSearchBar,
    AptInfoChart,
  },
  created() {
    this.setFavoriteApt();
  },
  computed: {
    ...mapGetters(houseStore, ["getStateChart", "getFieldFavorite"]),
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(houseStore, ["favorite"]),
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList", "getFavoriteApt"]),
    searchApt(dongCode) {
      if (dongCode) this.getHouseList(dongCode);
    },
    setFavoriteApt(){
      this.getFavoriteApt(this.userInfo.id);
    }
  },
};
</script>

<style></style>
