<template>
  <div class="logo" :class="{ 'collapsed': store.getCollapse }">
    <div class="glass-effect"></div>
    <div class="content">
      <img :src="MenuLogo" class="logo-image" />
      <span v-if="show" class="logo-title">{{ title }}</span>
    </div>
  </div>
</template>

<script setup lang="ts">
import { watch, ref } from "vue";
import { collapseStore } from "@/store/collapse/index";

const store = collapseStore();
const MenuLogo = ref("https://tse2-mm.cn.bing.net/th/id/OIP-C.4LCVtDPhBIOqkD8AXfI3UAHaHa?rs=1&pid=ImgDetMain");
const title = ref("食尚乐享后台");
const show = ref(true);

watch(
  () => store.getCollapse,
  (collapsed: boolean) => {
    if (!collapsed) {
      setTimeout(() => {
        show.value = !collapsed;
      }, 300);
    } else {
      show.value = !collapsed;
    }
  }
);
</script>

<style lang="scss" scoped>
.logo {
  position: relative;
  width: 100%;
  height: 60px;
  overflow: hidden;
  cursor: pointer;
  transition: all 0.3s ease;
  border-right: none;

  .glass-effect {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: linear-gradient(220.55deg, #7B6AE0  0%, #FFBB89 100%);
    opacity: 0.9;
    backdrop-filter: blur(10px);
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    border: none;
    border-right: none;
    z-index: 1;
  }

  .content {
    position: relative;
    z-index: 2;
    display: flex;
    align-items: center;
    height: 100%;
    padding: 0 20px;
  }

  .logo-image {
    width: 36px;
    height: 36px;
    margin-right: 12px;
    transition: all 0.3s ease;
    filter: brightness(1.2);
  }

  .logo-title {
    color: #ffffff;
    font-weight: 600;
    font-size: 20px;
    font-family: sans-serif;
    transition: opacity 0.3s ease;
    text-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  }

  &.collapsed {
    .logo-image {
      margin-right: 0;
    }
  }

  &::before {
    display: none;
  }
}

.logo:hover {
  .glass-effect {
    opacity: 1;
    background: linear-gradient(220.55deg, #7B6AE0  0%, #FFBB89 100%);
  }

  .logo-image {
    transform: scale(1.05);
  }
}
</style>