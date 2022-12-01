<template>
  <b-container fluid>
    <article class="board-search-content">
      <section>
        시 / 도<br />
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </section>
      <section>
        구 / 군<br />
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </section>
      <section>
        동<br />
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="getAptDongCode"
        ></b-form-select>
      </section>
    </article>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";

export default {
  components: { },
  name: "HouseSearchBar",
  data() {
    return {
      sidoName: "검색",
      gugunName: "",
      dongName: "",
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_APT_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_APT_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    getAptDongCode() {
      console.log(this.dongCode);
      if (this.dongCode)
        this.$emit("getAptDongCode", this.dongCode);
    },
  },
};
</script>

<style lang="scss" scoped>
.board-search-content {
  width: 100%;
  flex: 1;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

  section {
    text-align: left;
    margin: 0, 0, 10px 0;
    width: 100%;

    select{
      width: 90%;
    }
  }
}
</style>
