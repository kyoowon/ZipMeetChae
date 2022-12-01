<template>
  <article id="board-list-wrapper">
    <b-container class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-container>
    <b-container id="board-table">
    <b-table
      striped
      hover
      :items="boards"
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
    </b-container>
    <b-pagination
      v-model="page.current"
      :total-rows="page.total"
      :per-page="page.per"
      aria-controls="my-table"
      @page-click="changePage"
    ></b-pagination>
  </article>
</template>

<script>
import _axios from "@/plugins/axios";

export default {
  name: "BoardList",
  data() {
    return {
      boards: [],
      page: {
        per: 1,
        total: 1,
        current: 1,
      },
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { articleno: article.articleno },
      });
    },
    async changePage(bvEvent, page) {
      console.log(bvEvent, page);
      try {
        const response = await _axios({
          url: "/board",
          params: {
            page,
          },
        });
        console.log(this.boards);
        console.log(response.data);
        this.boards = response.data.list;
        this.page.current = response.data.current;
        this.page.total = response.data.total;
        this.page.per = response.data.per;
        console.log(this.page);
      } catch (e) {
        console.log(e);
      }
    },
  },
  created() {
    _axios({
      url: "/board",
    }).then((info) => {
      this.boards = info.data.list;
      this.page.current = info.data.current;
      this.page.total = info.data.total;
      this.page.per = info.data.per;
    });
  },
};
</script>

<style lang="scss" scoped>

#board-list-wrapper{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#board-table{
  margin: 20px 0;
  height: 60vh;
  width: 100%;
}
</style>
