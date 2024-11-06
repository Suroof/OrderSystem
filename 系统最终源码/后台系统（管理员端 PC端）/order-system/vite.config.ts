import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'
import { defineConfig } from 'vite'
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    host: '0.0.0.0', //解决控制台 ：Network: use --host to expose
    port: 8080, //配置端口号
    hmr: true, //开启热更新
    open: true, //启动在浏览器打开

    //跨域配置，跨域配置代表的是所有 /api开头的请求都会被转发到 http://localhost:8089这个地址上
    //本地是8080，目标后端的端口是8089，需要配置跨域
    proxy: {
      '/api': {
        target: 'http://localhost:8089',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }


  },


  resolve: {
    alias: [
      {
        find: '@',   // 别名
        replacement: resolve(__dirname, 'src')   // 别名对应地址
      }
    ]
  },

  define: {
    'process.env': {

      // 'BASE_API': "http://10.69.64.135:8089"  //本机 ip   ip可能变化，实时查询  最新地址   ipconfig
      'BASE_API': "http://192.168.80.1:8089"  //本机 ip   ip可能变化，实时查询  最新地址   ipconfig
      // 'BASE_API': "http://10.64.80.102:8089"  //本机 ip
      // 'BASE_API': "http://10.64.53.124:8089"  //本机 ip
      // 'BASE_API':"http://localhost:8089"
    }
  },

  build:{
    chunkSizeWarningLimit:1500
  }



})
