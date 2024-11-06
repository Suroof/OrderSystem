<template>
  <el-breadcrumb class="bred" separator="/">
     <!-- <el-breadcrumb-item v-for="item in tabs">首页</el-breadcrumb-item>
      <el-breadcrumb-item v-for="item in tabs">用户管理</el-breadcrumb-item> -->
    <el-breadcrumb-item v-for="item in tabs">{{ item.meta.title }}</el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup lang="ts">
// 注意 要记得引入
import { onMounted, ref, Ref, watch } from "vue";
import { useRoute, RouteLocationMatched } from "vue-router";

//获取当前路由
const route = useRoute();
//定义面包屑导航数据 tabs对象封装了面包屑导航数据  大写的Ref是定义数据的类型，是RouteLocationMatched类型的    小写的ref是定义响应式的数据，默认值为空，响应式显示数据
const tabs: Ref<RouteLocationMatched[]> = ref([]);
//监听（监测）当前路由 有没有发生变化
watch(
  () => route.path,
  () => getBredcrumb()  //调用getBredcrumb方法监测路由有没有发生变化，然后获取 面包屑导航的数据
);
//获取面包屑导航数据 即获取名称（title属性值），返回放到面包屑导航的数据中
const getBredcrumb = () => {
    //找出有title的数据   即路由mete对象的属性的值  例如  title:'首页'
    let mached = route.matched.filter((item)=>item.meta && item.meta.title);
    // console.log(mached)
    const first = mached[0]
    console.log(first)
    // 第一个应该是首页，其他的菜单栏点哪一个就显示哪一个，判断一下 as 否则
    if(first.path !== '/dashboard'){
      //
        mached = [{path:'/dashboard',meta:{title:'首页'}} as any].concat(mached)
    }
    // 返回 获取到面包屑导航数据
    tabs.value = mached
};

// 路由的path发生变化，然后面包屑导航数据
onMounted(()=>{
    getBredcrumb()
})
</script>


<style scoped lang="scss">
:deep(.el-breadcrumb__inner) {
  color: #fff !important;
}
.bred {
  margin-left: 20px;
}
// 修改字体大小
// :deep(.el-breadcrumb__item) {
//   font-size: 16px !important;
// }
</style>
