<template>
  <article id="AptInfo-list-wrapper">
    <section id="AptInfo-list">
      <b-table striped hover :items="infos" :fields="fields"> </b-table>
    </section>
  </article>
</template>

<script>

export default {
  props: {
    fields: Array,
    infos: Array,
  },
};
</script>

<style scoped>
#AptInfo-wrapper {
  display: block;
  height: 100%;
  width: 300px;
}

#AptInfo-list {
  height: 25vh;
  overflow: auto;
}

#AptInfo-list{
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
#AptInfo-list::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
</style>
