<template>
  <section id="map-wrapper">
    <div id="map"></div>
  </section>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";
import img from "@/assets/img/pin.png";
import img2 from "@/assets/img/pin2.png";

export default {
  name: "AptMap",
  data() {
    return {
      map: null,
      marker: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "favorite"]),
  },
  methods: {
    initMap() {
      var mapContainer = document.getElementById("map"); // 지도를 표시할 div
      let mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };

      // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
      this.map = new kakao.maps.Map(mapContainer, mapOption);
    },
    updateMap(houses) {
      if (houses.length > 0) {
        console.log(houses);
        let mapContainer = document.getElementById("map"); // 지도를 표시할 div
        let mapOption = {
          center: new kakao.maps.LatLng(houses[0].lat, houses[0].lng), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };

        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        this.map = new kakao.maps.Map(mapContainer, mapOption);

        // 마커를 표시할 위치와 title 객체 배열입니다
        let positions = [];

        houses.forEach((items) => {
          positions.push({
            title: items.apartmentName,
            latlng: new kakao.maps.LatLng(items.lat, items.lng),
          });
        });

        console.log(positions);
        // 마커 이미지의 이미지 주소입니다
        let imageSrc = img2;

        for (let i = 0; i < positions.length; i++) {
          // 마커 이미지의 이미지 크기 입니다
          let imageSize = new kakao.maps.Size(35, 46);

          // 마커 이미지를 생성합니다
          let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커를 생성합니다
          this.marker = new kakao.maps.Marker({
            map: this.map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
          });
        }
      }
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_MAP_KEY}`;
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    } else {
      this.initMap();
    }
  },
  watch: {
    houses(val){
      if(val)
        this.updateMap(val);
    }
  }
};
</script>

<style>
#map-wrapper {
  display: block;
  width: 100%;
  height: 100%;
  overflow: hidden;
}
#map {
  display: block;
  width: 100%;
  height: 100%;
}
</style>
