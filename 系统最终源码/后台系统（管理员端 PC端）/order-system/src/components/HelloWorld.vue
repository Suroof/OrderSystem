<script setup lang="ts">
//局部使用的方式，需要单独引入
import { Edit } from '@element-plus/icons-vue'

import { testStore } from '@/store/test/index'
import { computed } from "vue";

const store = testStore()

// computed取值的方式
const count = computed(() => {
  return store.count
  // return store.getCount
})

const addBtn = () => {
  //第一种改变数据的方式
  // store.count++
  //第二种改变数据的方式
  store.setCount(++store.count)
  //第三种改变数据的方式
  // store.$patch({
  //   count: ++store.count,
  // });
  //第四种改变数据的方式
  // store.$patch((state) => {
  //   state.count = ++state.count
  // })
}
</script>

<template>
  <!-- <h1>{{ msg }}</h1> -->
  <!-- <h1>666</h1> -->
  <!-- <h1>{{ store.count }}</h1> -->
  <h2>{{ count }}</h2>
  <el-row class="mb-4">
    <!-- <el-button type="primary">Primary</el-button> -->
    <el-button type="primary"
               size="default"
               @click="addBtn">新增</el-button>
  </el-row>
  <!-- 或者独立使用它，不从父级获取属性 -->
  <el-icon>
    <Edit />
  </el-icon>

</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>

