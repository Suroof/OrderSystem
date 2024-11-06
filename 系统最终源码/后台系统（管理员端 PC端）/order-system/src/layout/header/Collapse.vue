<template>
  <el-icon :size="26" style="cursor: pointer" @click="iconClick">
    <!-- 动态组件的方式显示图标；动态组件：有些场景会需要在两个组件间来回切换 -->
    <component :is="status ? Expand :  Fold"></component>
  </el-icon>
</template>

<script setup lang="ts">
import { collapseStore } from "@/store/collapse";
import { Fold, Expand } from "@element-plus/icons-vue";
import { computed } from "vue";

//获取store
const store = collapseStore();
//获取状态
const status = computed(() => {
  return store.getCollapse;
});
//图标切换点击事件
const iconClick = ()=>{
    store.setCollapse(!store.collapse)
}
</script>

<style scoped></style>