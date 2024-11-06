import { defineStore } from "pinia";
import axios from "axios";

export const useUserStore = defineStore("user", {
  state: () => ({
    user: null,
    token: localStorage.getItem('token') || null,
  }),
  actions: {
    async login(username, password) {
      try {
        const response = await axios.post(
          "http://localhost:8089/api/user/login",
          { username, password }
        );
        this.user = response.data;
        this.token = response.data.token;
        localStorage.setItem('token', response.data.token);
        return response.data;
      } catch (error) {
        console.error("Login failed:", error);
        throw error;
      }
    },
    async logout() {
      try {
        // 调用退出登录接口
        const response = await axios.post(
          "http://localhost:8089/api/user/logout",
          {},
          {
            headers: {
              'Authorization': `Bearer ${this.token}`,
              'Content-Type': 'application/json'
            }
          }
        );

        // 判断退出是否成功
        if (response.data.code === 200) {
          // 清除状态
          this.user = null;
          this.token = null;
          // 清除本地存储
          localStorage.removeItem("token");
          return Promise.resolve();
        } else {
          return Promise.reject(new Error(response.data.msg));
        }
      } catch (error) {
        console.error("Logout failed:", error);
        return Promise.reject(error);
      }
    },
  },
});
