<template>
  <article id="AptInfo-list-wrapper">
    <section id="AptInfo-list-centent" class="visible-scrollbar">
      <b-table
        striped
        hover
        :items="infos"
        :fields="fields"
        @row-clicked="viewArticle"
      >
        <template #cell(subject)="data">
          <router-link
            :to="{
              name: 'boardview',
              params: { articleno: data.item.articleno },
            }"
          >
            {{ data.item.subject }}
          </router-link>
        </template>
      </b-table>
    </section>
  </article>
</template>

<script>

export default {
  props: {
    title: String,
    fields: Array,
    infos: Array,
  },
  computed: {
  },
  methods: {
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { articleno: article.articleno },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
#AptInfo-list-wrapper {
  display: block;
  margin: 10px;
  height: 100%;
  width: 100%;
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
