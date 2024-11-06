<template>
  <el-dropdown @command="handleCommand">
    <span class="el-dropdown-link">
      <img class="uimage" src="@/assets/user.jpg"/>
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item command="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script setup lang="js">
import { useRouter } from 'vue-router'
import { ElMessageBox, ElMessage } from 'element-plus'
import { useUserStore } from '@/store/user/index.js'

const router = useRouter()
const userStore = useUserStore()

const handleCommand = (command) => {
  if (command === 'logout') {
    handleLogout()
  }
}

const handleLogout = () => {
  ElMessageBox.confirm(
    '确定要退出登录吗？',
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
    try {
      // 调用 store 中的退出方法
      await userStore.logout()

      // 显示成功消息
      ElMessage({
        type: 'success',
        message: '退出成功',
        duration: 2000
      })

      // 跳转到登录页
      router.push('/login')
    } catch (error) {
      console.error('退出失败:', error)
      ElMessage({
        type: 'error',
        message: '退出失败，请重试',
        duration: 2000
      })
    }
  }).catch(() => {
    // 用户取消退出，不做处理
  })
}
</script>

<style scoped>
.uimage {
  height: 45px;
  width: 45px;
  border-radius: 50%;
  cursor: pointer;
  transition: transform 0.3s ease;
}

.uimage:hover {
  transform: scale(1.05);
}

.el-dropdown-link {
  display: flex;
  align-items: center;
}
</style>