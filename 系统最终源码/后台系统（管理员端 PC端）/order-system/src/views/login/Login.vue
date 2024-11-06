<template>
  <div class="login-container">
    <div class="form-container">
      <div class="left-side">
        <div class="overlay">
          <h2>民以食为天</h2>
          <p>Food first, ethical niceties second</p>
        </div>
      </div>
      <div class="right-side">
        <h2>Login</h2>
        <form @submit.prevent="login">
          <div class="input-group">
            <input
              type="text"
              id="username"
              v-model="username"
              placeholder="Username"
              required
              class="login-input"
            />
          </div>
          <div class="input-group">
            <input
              type="password"
              id="password"
              v-model="password"
              placeholder="Password"
              required
              class="login-input"
            />
          </div>
          <button type="submit" :disabled="isLoading" class="login-btn">
            {{ isLoading ? "登录中..." : "登录" }}
          </button>
        </form>
      </div>
    </div>

    <div class="kimi-sidebar" :class="{ 'sidebar-expanded': isSidebarExpanded }">
      <div class="sidebar-toggle" @click="toggleSidebar">
        <i class="toggle-icon">{{ isSidebarExpanded ? '›' : '‹' }}</i>
        <span v-if="!isSidebarExpanded" class="toggle-label">AI 助手</span>
      </div>

      <div class="sidebar-content">
        <div class="chat-header">
          <h3>Kimi AI 助手</h3>
        </div>

        <div class="chat-messages" ref="chatContainer">
          <div v-for="(msg, index) in messages"
               :key="index"
               :class="['message', msg.role]">
            {{ msg.content }}
          </div>
        </div>

        <div class="chat-input">
          <input
            v-model="kimiInput"
            @keyup.enter="askKimi"
            placeholder="有什么可以帮您的吗？"
            class="chat-input-field"
          />
          <button @click="askKimi" :disabled="isKimiLoading" class="kimi-btn">
            {{ isKimiLoading ? "..." : "发送" }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, nextTick } from 'vue';
import { useRouter } from "vue-router";
import { useUserStore } from "@/store/user/index.js";
import axios from 'axios';

const store = useUserStore();
const router = useRouter();
const username = ref("");
const password = ref("");
const isLoading = ref(false);

// 添加 Kimi 相关的状态
const kimiInput = ref("");
const messages = ref([{
  role: 'assistant',
  content: '您好！我是 Kimi AI 助手，有什么可以帮您的吗？'
}]);
const isKimiLoading = ref(false);

// 添加自动滚动功能
watch(() => messages.value.length, async () => {
  await nextTick();
  const container = chatContainer.value;
  if (container) {
    container.scrollTop = container.scrollHeight;
  }
});

// 添加 ref
const chatContainer = ref(null);

const login = async () => {
  if (isLoading.value) return;
  isLoading.value = true;
  try {
    const response = await store.login(username.value, password.value);
    localStorage.setItem("token", response.token);
    router.push("/dashboard");
  } catch (error) {
    console.error("登录失败", error);
    alert("登录失败，请检查用户名和密码");
  } finally {
    isLoading.value = false;
  }
};

// 添加与 Kimi 对话的方法
const askKimi = async () => {
  if (!kimiInput.value.trim() || isKimiLoading.value) return;

  isKimiLoading.value = true;
  const userMessage = kimiInput.value.trim();

  messages.value.push({
    role: 'user',
    content: userMessage
  });

  try {
    console.log('Sending request to Kimi...');
    const response = await axios.post('/api/kimi/chat', {
      message: userMessage
    });

    // 从响应中提取实际的消息内容
    const assistantMessage = response.data.choices[0].message.content;

    messages.value.push({
      role: 'assistant',
      content: assistantMessage
    });
  } catch (error) {
    console.error('Error details:', error);
    let errorMessage = '抱歉，服务暂时不可用。';

    if (error.response) {
      console.error('Error response:', error.response);
      errorMessage += ` (${error.response.status}: ${error.response.data})`;
    }

    messages.value.push({
      role: 'assistant',
      content: errorMessage
    });
  } finally {
    isKimiLoading.value = false;
    kimiInput.value = '';
  }
};

// 添加侧边栏状态
const isSidebarExpanded = ref(false);

// 添加切换侧边栏的方法
const toggleSidebar = () => {
  isSidebarExpanded.value = !isSidebarExpanded.value;
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-image: url('@/assets/food.jpg'); /* 添加背景图片 */
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.form-container {
  display: flex;
  width: 800px;
  height: 400px; /* 将这里改为 500px */
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border-radius: 15px;
  overflow: hidden;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.2);
  position: relative;
  z-index: 1;
  margin-right: 60px;
}

.left-side {
  flex: 1;
  position: relative;
  background-image: url('@/assets/food.jpg'); /* 添加左侧图片 */
  background-size: cover;
  background-position: center;
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: rgba(0, 0, 0, 0.4); /* 半透明遮罩 */
  color: white;
  padding: 40px;
}


.right-side {
  flex: 1;
  padding: 40px;
  display: flex;
  flex-direction: column;
  background: rgba(0, 0, 0, 0.6); /* 添加半透明背景 */
}

h2 {
  margin-bottom: 40px;
  font-size: 28px;
  color: white;
  text-align: center;
  font-weight: 300;
}

p {
  color: rgba(255, 255, 255, 0.8);
  text-align: center;
  margin-bottom: 20px;
}

.input-group {
  margin-bottom: 20px;
  width: 100%; /* 确保input-group占满容器宽度 */
  display: flex;
  justify-content: center; /* 居中对齐 */
}

.login-input {
  width: 280px;
  padding: 12px;
  background: rgba(255, 255, 255, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.2);
  border-radius: 8px;
  font-size: 16px;
  color: white;
  transition: all 0.3s ease;
}

.login-input::placeholder {
  color: rgba(255, 255, 255, 0.6);
}

.login-input:focus {
  outline: none;
  border-color: rgba(255, 255, 255, 0.5);
  background: rgba(255, 255, 255, 0.15);
}

.login-btn {
  width: 240px; /* 与输入框相同的宽度 */
  padding: 12px;
  background: rgba(255, 255, 255, 0.2);
  border: 1px solid rgba(255, 255, 255, 0.3);
  border-radius: 58px;
  color: white;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  margin: 0 auto; /* 居中对齐 */
  display: block; /* 使margin: 0 auto生效 */
}

.login-btn:hover {
  background: rgba(255, 255, 255, 0.3);
  transform: translateY(-2px);
}

.login-btn:disabled {
  opacity: 0.7;
  cursor: not-allowed;
  transform: none;
}

@media (max-width: 768px) {
  .form-container {
    width: 90%;
    flex-direction: column;
    height: auto;
  }

  .left-side, .right-side {
    padding: 20px;
  }

  .left-side {
    min-height: 200px; /* 确保在移动端有足够的高度显示图片 */
  }
}

/* 添加侧边栏相关样式 */
.kimi-sidebar {
  position: fixed;
  right: 0;
  top: 0;
  bottom: 0;
  width: 60px;
  background: rgba(0, 0, 0, 0.8);
  backdrop-filter: blur(10px);
  transition: all 0.3s ease;
  display: flex;
  z-index: 1000;
  box-shadow: -2px 0 10px rgba(0, 0, 0, 0.2);
}

.sidebar-expanded {
  width: 380px;
}

.sidebar-toggle {
  position: absolute;
  left: -30px;
  top: 50%;
  transform: translateY(-50%);
  width: 30px;
  height: 60px;
  background: rgba(0, 0, 0, 0.8);
  border-radius: 8px 0 0 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  color: white;
  transition: all 0.3s ease;
  box-shadow: -2px 0 10px rgba(0, 0, 0, 0.2);
}

.toggle-icon {
  font-size: 24px;
  font-weight: bold;
  transition: transform 0.3s ease;
}

.toggle-label {
  writing-mode: vertical-rl;
  text-orientation: mixed;
  font-size: 14px;
  letter-spacing: 2px;
  color: white;
  opacity: 0.8;
}

.sidebar-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  opacity: 0;
  visibility: hidden;
  transition: all 0.3s ease;
  padding: 20px;
  width: 100%;
}

.sidebar-expanded .sidebar-content {
  opacity: 1;
  visibility: visible;
}

.chat-header {
  padding-bottom: 15px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
  margin-bottom: 15px;
}

.chat-header h3 {
  color: white;
  font-size: 18px;
  margin: 0;
  font-weight: 500;
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
  padding: 10px;
  margin: 10px 0;
  background: rgba(255, 255, 255, 0.05);
  border-radius: 8px;
}

.message {
  margin: 8px 0;
  padding: 10px 12px;
  border-radius: 12px;
  max-width: 85%;
  word-break: break-word;
  font-size: 14px;
  line-height: 1.4;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.user {
  background: rgba(79, 70, 229, 0.2);
  margin-left: auto;
  color: white;
  border-bottom-right-radius: 4px;
}

.assistant {
  background: rgba(255, 255, 255, 0.1);
  margin-right: auto;
  color: rgba(255, 255, 255, 0.9);
  border-bottom-left-radius: 4px;
}

.chat-input {
  display: flex;
  gap: 8px;
  padding: 10px 0;
}

.chat-input-field {
  flex: 1;
  padding: 10px 12px;
  background: rgba(255, 255, 255, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.2);
  border-radius: 8px;
  color: white;
  font-size: 14px;
}

.kimi-btn {
  padding: 8px 16px;
  background: rgba(79, 70, 229, 0.2);
  border: 1px solid rgba(79, 70, 229, 0.3);
  border-radius: 8px;
  color: white;
  cursor: pointer;
  transition: all 0.3s ease;
  font-weight: 500;
  min-width: 60px;
}

/* 自定义滚动条样式 */
.chat-messages::-webkit-scrollbar {
  width: 5px;
}

.chat-messages::-webkit-scrollbar-track {
  background: rgba(255, 255, 255, 0.05);
}

.chat-messages::-webkit-scrollbar-thumb {
  background: rgba(255, 255, 255, 0.2);
  border-radius: 3px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .form-container {
    margin-right: 40px;
  }

  .kimi-sidebar {
    width: 0;
  }

  .sidebar-expanded {
    width: 100%;
  }

  .sidebar-toggle {
    left: -40px;
    width: 40px;
  }
}

.sidebar-expanded .toggle-icon {
  transform: rotate(180deg);
}
</style>