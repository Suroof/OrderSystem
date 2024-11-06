<template>
  <div class="header-container">
    <div class="left-header">
      <Collapse></Collapse>
      <BreadCrumb></BreadCrumb>
    </div>
    <div class="right-header">
      <div class="btn-box screen-box" @click="fullScreenFun" v-if="!isFullScreen">
        <div class="btn full-screen-btn"></div>
      </div>
      <div class="btn-box screen-box" @click="exitScreenFun" v-else>
        <div class="btn exit-full-screen-btn"></div>
      </div>
      <LoginOut></LoginOut>
    </div>
  </div>
</template>

<script setup lang="ts">
import BreadCrumb from "./BreadCrumb.vue";
import Collapse from "./Collapse.vue";
import LoginOut from "./LoginOut.vue";
import { ref } from 'vue';
import { fullScreen, exitScreen } from "@/utils/myConfirm";
const isFullScreen = ref(false);

const fullScreenFun = () => {
  fullScreen();
  isFullScreen.value = true;
};

const exitScreenFun = () => {
  exitScreen();
  isFullScreen.value = false;
};
</script>

<style scoped lang="scss">
.header-container {
  position: relative;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 60px;
  padding: 0 20px;

  background: linear-gradient(220.55deg, #FFBB89 0%, #7B6AE0 100%);
  backdrop-filter: blur(10px);
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border: none;
  z-index: 100;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    backdrop-filter: blur(10px);
    opacity: 0.9;
    z-index: -1;
    border: none;
  }
}

.left-header {
  display: flex;
  align-items: center;
  color: white;
  margin-left: 0;
}

.right-header {
  display: flex;
  align-items: center;
  gap: 20px;
}

.btn-box {
  cursor: pointer;
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-2px);
  }
}

.full-screen-btn {
  width: 30px;
  height: 30px;
  background-image: url('@/assets/icons/全屏.svg');
  background-size: cover;
  filter: brightness(0) invert(1);
}

.exit-full-screen-btn {
  width: 30px;
  height: 30px;
  background-image: url('@/assets/icons/取消全屏.svg');
  background-size: cover;
}
</style>
