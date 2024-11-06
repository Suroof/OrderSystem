<template>
  <MenuLogo></MenuLogo>
  <el-menu
    router
    :collapse="collapse"
    :default-active="activeIndex"
    class="el-menu-vertical-demo iphone-style-menu"
  >
    <el-menu-item index="/dashboard">
      <el-icon><HomeFilled /></el-icon>
      <template #title>首页</template>
    </el-menu-item>

    <el-menu-item index="/user">
      <el-icon><User /></el-icon>
      <template #title>用户管理</template>
    </el-menu-item>

    <el-menu-item index="/category">
      <el-icon><Grid /></el-icon>
      <template #title>菜品分类</template>
    </el-menu-item>

    <el-menu-item index="/goods">
      <el-icon><Food /></el-icon>
      <template #title>菜品管理</template>
    </el-menu-item>

    <el-menu-item index="/order">
      <el-icon><List /></el-icon>
      <template #title>订单管理</template>
    </el-menu-item>

    <el-menu-item index="/banner">
      <el-icon><Picture /></el-icon>
      <template #title>广告管理</template>
    </el-menu-item>

    <el-menu-item index="/comment">
      <el-icon><ChatDotRound /></el-icon>
      <template #title>评论管理</template>
    </el-menu-item>

    <el-menu-item index="/rider">
      <el-icon><Van /></el-icon>
      <template #title>骑手管理</template>
    </el-menu-item>
  </el-menu>
</template>

<script setup lang="ts">
import { collapseStore } from "@/store/collapse";
import MenuLogo from '@/layout/MenuLogo.vue'
import {
  HomeFilled,
  User,
  Grid,
  Food,
  List,
  Picture,
  ChatDotRound,
  Van
} from '@element-plus/icons-vue'
import { computed } from 'vue'
import { useRoute } from 'vue-router';

const route = useRoute();
const activeIndex = computed(() => route.path);
const store = collapseStore();
const collapse = computed(() => store.getCollapse);
</script>

<style scoped lang="scss">
.iphone-style-menu {
  background: rgba(255, 255, 255, 0.95) !important; // 降低背景透明度
  backdrop-filter: blur(8px); // 增加模糊效果
  border-radius: 16px;
  margin: 10px;
  padding: 10px 0;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.327); // 减小阴影
  border: 1px solid rgba(255, 255, 255, 0.1); // 减小边框透明度

  &:not(.el-menu--collapse) {
    width: 220px;
    min-height: calc(100vh - 80px);
  }

  .el-menu-item {
    margin: 5px 10px;
    border-radius: 12px;
    height: 50px;
    line-height: 50px;
    color: rgba(0, 0, 0, 0.8); // 调整文字颜色
    font-weight: 500;
    transition: all 0.3s ease;
    background: transparent !important; // 确保菜单项背景透明

    &:hover {
      background: rgba(255, 255, 255, 0.3) !important; // 调整悬浮背景
      transform: translateY(-1px);
    }

    &.is-active {
      background: rgba(0, 122, 255, 0.3) !important; // 调整激活状态背景
      color: white !important;
      box-shadow: 0 4px 10px rgba(0, 122, 255, 0.1);
    }

    .el-icon {
      font-size: 20px;
      margin-right: 10px;
      vertical-align: middle;
    }
  }
}

// 折叠状态下的样式
.el-menu--collapse {
  .el-menu-item {
    margin: 5px;

    .el-icon {
      margin: 0;
      font-size: 22px;
    }
  }
}

// 深度选择器，覆盖 element-plus 的默认样式
:deep(.el-menu) {
  border-right: none;
  background: transparent !important; // 确保菜单背景透明
}

:deep(.el-sub-menu__title) {
  color: rgba(0, 0, 0, 0.8) !important;
}

:deep(.el-menu-item:hover) {
  background: rgba(255, 255, 255, 0.3) !important;
}

// 暗色模式适配
@media (prefers-color-scheme: dark) {
  .iphone-style-menu {
    background: rgba(0, 0, 0, 0.2) !important; // 暗色模式下的背景

    .el-menu-item {
      color: rgba(255, 255, 255, 0.9);

      &:hover {
        background: rgba(255, 255, 255, 0.1) !important;
      }

      &.is-active {
        background: rgba(0, 122, 255, 0.2) !important;
      }
    }
  }
}
</style>