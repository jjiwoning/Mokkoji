<template>
    <div>
      <div id="map" style="width: 500px; height: 600px"></div>
      <div ref="overlayContainer"></div>
      <div id="myModal" class="modal">
        <div class="modal-content">
          <span class="close" @click="closeModal">&times;</span>
          <div v-if="selectedAttraction">
            <h2>{{ selectedAttraction.title }}</h2>
            <p>지역: {{ selectedAttraction.sidoName }} {{ selectedAttraction.gugunName }}</p>
            <p>우편번호: {{ selectedAttraction.zipcode }}</p>
            <p>주소: {{ selectedAttraction.addr1 }}, {{ selectedAttraction.addr2 }}</p>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "KakaoMap2",
    data() {
      return {
        map: null,
        markers: [],
        selectedAttraction: null,
        planAttractions:[],
      };
    },
    mounted() {
       //카카오 객체가 있고, 카카오 맵 그릴 준비가 되어 있다면 맵 실행 
       if(window.kakao && window.kakao.maps){
            this.loadMap();
        }
        else{ //없다면 카카오 스크립트 추가 후 맵 실행
            this.loadScript();
        }
    },
    methods: {
      loadScript() {
        const script = document.createElement("script");
        script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=68c3809edf06ceb324823cdfae4722a0&libraries=services,clusterer,drawing&autoload=false";
        script.onload = () => window.kakao.maps.load(this.loadMap);
        document.head.appendChild(script);
      },
      loadMap() {
        const container = document.getElementById("map");
        const options = {
          center: new window.kakao.maps.LatLng(37.501269, 127.039614),
          level: 3,
        };
        this.map = new window.kakao.maps.Map(container, options);
      },
      loadMarker() {
 
        var bounds = new window.kakao.maps.LatLngBounds();
        this.planAttraction.forEach((attraction) => {
          const markerPosition = new window.kakao.maps.LatLng(attraction.latitude, attraction.longitude);
          const marker = new window.kakao.maps.Marker({
            position: markerPosition,
          });
          window.kakao.maps.event.addListener(marker, 'click', () => {
            this.selectedAttraction = attraction;
            this.openModal();
          });
          marker.setMap(this.map);
          bounds.extend(markerPosition);
          this.markers.push(marker);
        });
        this.map.setBounds(bounds);
      },
      openModal() {
        const modal = document.getElementById("myModal");
        modal.style.display = "block";
      },
      closeModal() {
        const modal = document.getElementById("myModal");
        modal.style.display = "none";
        this.selectedAttraction = null;
      },

    },
    created(){
        console.log(this.planAttraction)
    },
    watch:{
        planAttraction(){
            this.loadMarker();
        }
    },
   computed:{
        planAttraction(){
      return this.$store.getters.getPlanAttraction;
    }
   },
  };
  </script>
  
  <style scoped>
  #map {
    width: 100%;
    height: 400px;
  }
  
  .modal {
  display: none;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.7);
}

.modal-content {
  background-color: #fff;
  margin: 10% auto;
  padding: 20px;
  border-radius: 4px;
  width: 30%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
}

.close {
  color: #888;
  position: absolute;
  top: 15px;
  right: 15px;
  font-size: 24px;
  font-weight: bold;
  cursor: pointer;
}

.close:hover {
  color: #555;
}

h2 {
  font-size: 24px;
  margin-bottom: 10px;
}

p {
  font-size: 16px;
  margin-bottom: 5px;
}


  </style>
  