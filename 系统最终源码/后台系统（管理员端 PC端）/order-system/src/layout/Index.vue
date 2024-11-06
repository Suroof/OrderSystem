<template>
  <el-container class="layout">
    <el-aside width="auto"
              class="asside">
      <Menu></Menu>
    </el-aside>
    <el-container>
      <el-header class="header">
        <Header></Header>
      </el-header>
      <el-main class="main">
            <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script setup lang="ts">
import Menu from './Menu.vue'

import Header from './header/Header.vue'
</script>

<style scoped lang="scss">
.layout {
  height: 100%;
  .asside {
    background-color: #ffffff;
    border-right: none;
    overflow: hidden;
    display: flex;
    flex-direction: column;
  }
  .header {
    padding: 0;
    border-bottom: none !important;
    display: flex;
    align-items: center;
    justify-content: space-between;
    background: linear-gradient(220.55deg, #FFBB89 0%, #7B6AE0 100%);
  }
  .main {
    padding: 20px;
    height: calc(100vh - 60px);
    overflow-y: auto;
    overflow-x: hidden;
  }
}

:deep(.el-container) {
  overflow: hidden;
}

:deep(.el-aside) {
  overflow: hidden;
}
</style>