// import { createApp } from 'vue'
// // import './style.css'
// import App from './App.vue'
// createApp(App).mount('#app')


import * as ElementPlusIconsVue from '@element-plus/icons-vue';
//引入echarts
import * as echarts from 'echarts';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
//引入Pinia构造函数
import { createPinia } from 'pinia';
import { createApp } from 'vue';
//import './style.css'
import App from './App.vue';
import router from './router';
import myConfirm from './utils/myConfirm';


// 实例化 Pinia
const pinia = createPinia()
const app = createApp(App);
app.use(router).use(ElementPlus).use(pinia).mount('#app')

//全局注册图标组件，在页面就可以直接使用，不需使用局部，局部需要在每个页面都引入，较为繁琐
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

//全局注册 挂载  那怎么获取$myconfirm?  在useInstance.ts获取全局属性
app.config.globalProperties.$myconfirm = myConfirm

app.config.globalProperties.$echarts = echarts;