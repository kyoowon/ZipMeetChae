<template>
  <article id="AptInfo-list-wrapper">
    <section id="AptInfo-title">{{title}}</section>
    <section id="AptInfo-list-centent" class="visible-scrollbar">
      <b-table striped hover :items="infos" :fields="fields">
        <template #cell(apartmentName)="data">
          <router-link
            :to="{
              name: 'detailInfo',
              params: { aptinfo: data.item },
            }"
          >
            {{ data.item.apartmentName }}
          </router-link>
        </template>
        <template #cell(fovarite)="data">
          <div
            :class="{ heart: true, isActive: data.item.fovarite }"
            @click="likeEvent($event, data.item)"
          ></div>
        </template>
      </b-table>
    </section>
  </article>
</template>

<script>
import { mapActions, mapState } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  props: {
    title: String,
    fields: Array,
    infos: Array,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, ["setFavoriteApt", "removeFavoriteApt"]),
    likeEvent(event, aptInfo) {
      console.log(aptInfo);
      if (event.target.classList.toggle("isActive")) {
        console.log(this.userInfo.id);
        this.setFavoriteApt({
          aptCode: aptInfo.aptCode,
          id: this.userInfo.id,
        });
        if (this.error) {
          event.target.classList.toggle("isActive");
        }
      } else {
        this.removeFavoriteApt({
          aptCode: aptInfo.aptCode,
          id: this.userInfo.id,
        });
        if (this.error) {
          event.target.classList.toggle("isActive");
        }
      }
      this.$emit("changeFav");
    },
  },
};
</script>

<style lang="scss" scoped>
#AptInfo-list-wrapper {
  display: block;
  margin: 10px;
  height: 100%;
  width: 90%;
}

#AptInfo-list-centent {
  width: 100%;
  height: 25vh;
  overflow: auto;
}

#AptInfo-list-centent {
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
#AptInfo-list-centent::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}

.heart {
  position: relative;
  left: 20px;
  align-self: center;
  width: 35px;
  height: 35px;
  background: url("https://cssanimation.rocks/images/posts/steps/heart.png")
    no-repeat;
  background-position: -30px -35px;
  cursor: pointer;
  // transition: background-position 1s steps(28);
  // transition-duration: 0s;

  &.isActive {
    // transition-duration: 1s;
    background-position: -2830px -35px;
  }
}
</style>
