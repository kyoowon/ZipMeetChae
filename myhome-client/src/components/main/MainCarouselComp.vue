<template>
  <div>
    <b-carousel
      id="carousel-fade"
      style="text-shadow: 0px 0px 2px #000"
      fade
      indicators
      img-width="1024"
      img-height="480"
    >
      <b-carousel-slide
        caption=""
        img-src="@/assets/img/main-img1.jpg"
      ></b-carousel-slide>
      <b-carousel-slide
        caption=""
        img-src="@/assets/img/main-img2.jpg"
      ></b-carousel-slide>
      <b-carousel-slide
        caption=""
        img-src="@/assets/img/main-img3.jpg"
      ></b-carousel-slide>
    </b-carousel>
  </div>
</template>

<script>
export default {
};
</script>

<style scoped>
#carousel-fade{
  overflow: hidden;
  height: 60vh;
  border-radius: 20px;
}
</style>
