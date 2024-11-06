<template>
  <div class="container">
    <div class="card-grid">
      <div v-for="(card, index) in cards" :key="index" class="card">
        <div class="card-content">
          <div class="card-icon">
            <el-icon :size="24">
              <component :is="card.icon"></component>
            </el-icon>
          </div>
          <div class="card-info">
            <h3>{{ card.title }}</h3>
            <p>{{ card.value }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="chart-container">
    <el-form class="form-container" label-width="80px" size="normal">
      <el-form-item>
        <el-button class="custom-button" @click="charts('0')">日统计</el-button>
        <el-button
          class="custom-button"
          @click="charts('1')"
          type="primary"
          plain
          >月统计</el-button
        >
        <el-button
          class="custom-button"
          @click="charts('2')"
          type="success"
          plain
          >年统计</el-button
        >
      </el-form-item>
    </el-form>
    <el-row :gutter="20" class="chart-row">
      <el-col :span="12">
        <div class="chart-card">
          <div ref="myChart" :style="{ width: '100%', height: '380px' }"></div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="chart-card">
          <div ref="myChart1" :style="{ width: '100%', height: '380px' }"></div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref } from "vue";
import useInstance from "@/hooks/useInstance";
import { getTotalApi } from "@/api/order/index";
import {
  Money,
  ShoppingCart,
  User,
  CircleCheck,
  CircleClose,
  Van
} from '@element-plus/icons-vue'
const { global } = useInstance();
const myChart = ref<HTMLElement>();
const myChart1 = ref<HTMLElement>();
const cards = ref([
  {
    title: "营业额",
    value: "¥5589",
    icon: Money
  },
  {
    title: "有效订单",
    value: "20",
    icon: ShoppingCart
  },
  {
    title: "新增用户",
    value: "3",
    icon: User
  },
  {
    title: "已完成",
    value: "82",
    icon: CircleCheck
  },
  {
    title: "已取消",
    value: "4",
    icon: CircleClose
  },
  {
    title: "待派送",
    value: "15",
    icon: Van
  },
]);
//柱状图
const charts = async (type: string) => {
  const echartInstance = global.$echarts.init(myChart.value);
  const echartInstance1 = global.$echarts.init(myChart1.value);

  // 定义通用的颜色列表
  const colorList = [
    "#749f83", "#c23531", "#2f4554", "#61a0a8",
    "#d48265", "#91c7ae", "#ca8622", "#bda29a",
    "#6e7074", "#546570", "#c4ccd3", "#4ECDC4",
    "#00A3E0", "#FFA100", "#6574cd", "#ffc0cb"
  ];

  let option = reactive({
    title: {
      text: "直方图",
      textStyle: {
        color: "#fff",
      },
    },
    xAxis: {
      type: "category",
      data: [],
      axisLabel: {
        //x轴文字的配置
        show: true,
        color: "#fff",
        // interval: 0, //使x轴文字显示全
      },
    },
    yAxis: {
      type: "value",
      axisLabel: {
        color: "#fff",
      },
    },
    series: [
      {
        data: [],
        type: "bar",
        itemStyle: {
          normal: {
            //这里是颜色
            color: function (params: any) {
              return colorList[params.dataIndex % colorList.length];
            },
          },
        },
      },
    ],
    tooltip: {
      // 鼠标悬浮提示框显示 X和Y 轴数据
      trigger: "axis",
      backgroundColor: "rgba(32, 33, 36,.7)",
      borderColor: "rgba(32, 33, 36,0.20)",
      borderWidth: 1,
      textStyle: {
        // 文字提示样式
        color: "#fff",
        fontSize: "12",
      },
    },
  });

  let option1 = reactive({
    title: {
      text: "折线图",
      textStyle: {
        color: "#fff",
      },
    },
    xAxis: {
      type: "category",
      data: [],
      axisLabel: {
        //x轴文字的配置
        show: true,
        // interval: 0, //使x轴文字显示全
        color: "#fff",
      },
    },
    yAxis: {
      type: "value",
      axisLabel: {
        color: "#fff",
      },
    },
    series: [
      {
        data: [],
        type: "line",
        lineStyle: {
          color: "#000000", // 设置线的颜色为白色
        },
        itemStyle: {
          color: "#f7caca", // 设置数据点的颜色为白色
        },
      },
    ],
    tooltip: {
      // 鼠标悬浮提示框显示 X和Y 轴数据
      trigger: "axis",
      backgroundColor: "rgba(32, 33, 36,.7)",
      borderColor: "rgba(32, 33, 36,0.20)",
      borderWidth: 1,
      textStyle: {
        // 文字提示样式
        color: "#fff",
        fontSize: "12",
      },
    },
  });
  //动态获取数据
  let res = await getTotalApi(type);
  if (res && res.data) {
    console.log(res);
    option.xAxis.data = res.data.names;
    option.series[0].data = res.data.values;
    option1.xAxis.data = res.data.names;
    option1.series[0].data = res.data.values;
  }
  echartInstance.setOption(option);
  echartInstance1.setOption(option1);
};
onMounted(() => {
  charts("0");
});
</script>

<style lang="scss" scoped>
.card-content {
  position: relative;
  padding: 0.6rem 1rem;
  color: white;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;

  .card-icon {
    flex-shrink: 0;
    margin-right: 0;
    background: rgba(255, 255, 255, 0.2);
    border-radius: 50%;
    width: 42px;
    height: 42px;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.3s ease;

    .el-icon {
      color: white;
      font-size: 20px;
    }
  }

  .card-info {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    text-align: left;

    h3 {
      font-size: 0.85rem;
      margin-bottom: 0.2rem;
      opacity: 0.9;
    }

    p {
      margin: 0;
      font-size: 1.5rem;
      font-weight: 600;
      line-height: 1.2;
    }
  }
}

.card:hover {
  transform: scale(1.05);

  .card-icon {
    background: rgba(255, 255, 255, 0.3);
    transform: rotate(12deg);
  }
}

.el-row {
  display: flex;
  flex-wrap: wrap;
}

.el-col {
  flex: 1;
}

.el-col:nth-child(1) {
  flex: 2;
}
.container {
  min-height: 15vh;
  background: #ffffff;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0.2rem 1rem;
  max-width: 1400px;
  margin: 0 auto;
  width: 100%;
  margin-bottom: 0.3rem;
}

.card-grid {
  display: grid;
  gap: 0.8rem;
  width: 100%;
  padding: 0 0.8rem;
  margin-bottom: 0.8rem;

  // 使用更不规则的网格布局
  grid-template-columns: repeat(12, 1fr);
  grid-template-areas:
    "card1 card1 card1 card1 card2 card2 card2 card3 card3 card4 card4 card4"
    "card5 card5 card5 card5 card5 card6 card6 card6 card6 card6 card6 card6";
}

.card {
  position: relative;
  overflow: hidden;
  border-radius: 0.8rem;
  transition: transform 0.3s ease;

  // 为不同卡片分配不同的大小和区域
  &:nth-child(1) {
    grid-area: card1;
    min-height: 80px;
  }
  &:nth-child(2) {
    grid-area: card2;
    min-height: 80px;
  }
  &:nth-child(3) {
    grid-area: card3;
    min-height: 80px;
  }
  &:nth-child(4) {
    grid-area: card4;
    min-height: 80px;
  }
  &:nth-child(5) {
    grid-area: card5;
    min-height: 80px;
  }
  &:nth-child(6) {
    grid-area: card6;
    min-height: 80px;
  }

  &::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: linear-gradient(135deg, #7e22ce, #3b82f6);
    opacity: 0.9;
    backdrop-filter: blur(10px);
    border: 1px solid rgba(255, 255, 255, 0.2);
    border-radius: inherit;
  }
}

.card:hover {
  transform: scale(1.05);
}

.card-content {
  position: relative;
  padding: 0.6rem;
  color: white;
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;

  h3 {
    font-size: 0.85rem;
    margin-bottom: 0.2rem;
  }

  p {
    margin: 0;
    line-height: 1.2;
  }
}

@media (max-width: 768px) {
  .card-grid {
    grid-template-columns: 1fr;
    grid-template-areas:
      "card1"
      "card2"
      "card3"
      "card4"
      "card5"
      "card6";

    .card {
      min-height: 100px !important; // 在移动端统一高度
    }
  }
}
.chart-container {
  min-height: auto;
  background: #ffffff;
  padding: 0 0.8rem;
  margin-top: -0.3rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  flex-grow: 1;
}

.form-container {
  background: linear-gradient(135deg, #7e22ce, #3b82f6);
  backdrop-filter: blur(10px);
  border-radius: 15px;
  padding: 0.15rem;
  margin-bottom: 1rem;
  width: 100%;
  max-width: 500px;
  display: flex;
  justify-content: center;
}

.el-form-item {
  margin-bottom: 0;
  width: 100%;
  display: flex;
  justify-content: center;
}

.custom-button {
  padding: 0 15px;
  height: 26px;
  line-height: 26px;
  background: rgba(255, 255, 255, 0.2);
  border: 1px solid rgba(255, 255, 255, 0.3);
  color: white;
  transition: all 0.3s ease;
}

.custom-button:hover {
  background: rgba(255, 255, 255, 0.3);
  transform: translateY(-2px);
}

.chart-row {
  width: 100%;
  height: calc(100vh - 320px);
  display: flex;
  align-items: stretch;
  margin-top: 0;
}

.chart-card {
  background: linear-gradient(135deg, #7e22ce, #3b82f6);
  backdrop-filter: blur(10px);
  border-radius: 15px;
  padding: 0.3rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
  width: 100%;
  height: 280px;
  display: flex;
  flex-direction: column;
}

.chart-card:hover {
  transform: translateY(-5px);
}

/* 覆盖 Element Plus 默认样式 */
:deep(.el-button--primary.is-plain) {
  background-color: rgba(64, 158, 255, 0.2);
  border-color: rgba(64, 158, 255, 0.3);
  color: #fff;
}

:deep(.el-button--success.is-plain) {
  background-color: rgba(103, 194, 58, 0.2);
  border-color: rgba(103, 194, 58, 0.3);
  color: #fff;
}

:deep(.el-form-item__content) {
  justify-content: center;
  margin-left: 0 !important;
}

@media (max-width: 768px) {
  .chart-row {
    flex-direction: column;
  }

  .chart-card {
    margin-bottom: 1rem;
  }

  .custom-button {
    padding: 0 20px;
    margin: 0 0.25rem;
  }
}

:deep(.el-row) {
  margin: 0 !important;
}

.el-col {
  padding: 0 10px !important;
}
</style>
