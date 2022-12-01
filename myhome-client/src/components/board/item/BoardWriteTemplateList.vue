<template>
    <section id="AptInfo-list-centent" class="visible-scrollbar">
      <b-table striped hover :items="infos" :fields="fields" id="board-select-box">
        <template #cell(apartmentName)="data">
          <a href="#" @click="sendAptInfo(data.item)">
            {{ data.item.apartmentName }}
          </a>
   </template>
      </b-table>
    </section>
</template>


<script>
import { mapActions, mapState } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";


export default {
  props: {
    infos: Array,
    fields : Object,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, ["setFavoriteApt", "removeFavoriteApt"]),
    sendAptInfo(data){
      this.$emit("getAptInfo", {aptCode: data.aptCode, apartmentName: data.apartmentName});
    }
 },
};
</script>


<style lang="scss" scoped>

</style>
