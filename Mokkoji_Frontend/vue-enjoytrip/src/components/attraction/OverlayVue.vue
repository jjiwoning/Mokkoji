<template>
  <div>
    <!-- 오버레이 템플릿 -->
    <div class="overlay-template">
      <!-- 오버레이 콘텐츠 -->
      <div class="overlay-content">
        <!-- 오버레이 내용 -->
        <h4>{{ attraction.title }}</h4>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'OverlayVue',
  props: ['attraction', 'marker'],

  methods: {
    openOverlay() {
      this.$el.style.display = 'block';
    },
    closeOverlay() {
      this.$el.style.display = 'none';
    },
  },
  mounted() {
    window.kakao.maps.event.addListener(this.marker, 'click', () => {
      this.openOverlay();
    });
  },
}
</script>

<style>
.overlay-template {
  display: none;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 9999;
}

.overlay-content {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
  border-radius: 5px;
}
</style>