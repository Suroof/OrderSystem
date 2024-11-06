<template>
  <el-main class="order-container">
    <!-- 搜索栏 -->
    <div class="search-wrapper">
      <el-form :model="listParm" :inline="true" size="default" class="search-form">
        <el-form-item>
          <el-input
            v-model="listParm.userName"
            placeholder="请输入姓名"
            class="search-input"
            :prefix-icon="Search"
          ></el-input>
        </el-form-item>
        <el-form-item class="btn-group">
          <el-button :icon="Search" @click="searchBtn" class="custom-button search-btn">搜索</el-button>
          <el-button @click="resetBtn" type="danger" plain :icon="Close" class="custom-button reset-btn">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 表格 -->
    <div class="table-wrapper">
      <el-table
        row-key="orderId"
        :height="tableHeight"
        :data="tableList"
        border
        stripe
        class="custom-table"
      >
        <el-table-column type="expand">
          <template #default="scope">
            <el-table :data="scope.row.goodsList" border stripe class="sub-table">
              <el-table-column label="菜品图片" prop="goodsImage">
                <template #default="scope">
                  <el-image
                    :src="scope.row.goodsImage.split(',')[0]"
                    class="goods-image"
                  ></el-image>
                </template>
              </el-table-column>
              <el-table-column label="名称" prop="goodsName"></el-table-column>
              <el-table-column label="价格" prop="price"></el-table-column>
              <el-table-column label="规格" prop="specsName"></el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column label="姓名" prop="userName"></el-table-column>
        <el-table-column label="电话" prop="phone"></el-table-column>
        <el-table-column label="地址" prop="address"></el-table-column>
        <el-table-column label="价格" prop="price"></el-table-column>
        <el-table-column label="下单时间" prop="createTime"></el-table-column>
        <el-table-column label="发货状态" prop="status">
          <template #default="scope">
            <el-tag
              :type="scope.row.status == '0' ? 'danger' : 'success'"
              class="status-tag"
            >
              {{ scope.row.status == '0' ? '待发货' : scope.row.status == '1' ? '已发货' : '已收货' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200" align="center">
          <template #default="scope">
            <el-button
              type="primary"
              @click="sendOrder(scope.row.orderId)"
              :icon="Edit"
              class="action-button edit-btn"
            >发货</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页 -->
    <el-pagination
      class="custom-pagination"
      @size-change="sizeChange"
      @current-change="currentChange"
      :current-page.sync="listParm.currentPage"
      :page-sizes="[10, 20, 40, 80, 100]"
      :page-size="listParm.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="listParm.total"
      background
    >
    </el-pagination>
  </el-main>
</template>

<script setup lang="ts">
import { Search, Close, Plus, Edit, Delete } from "@element-plus/icons-vue";
import useOrderTable from "@/composables/order/useOrderTable";
//表格业务
const {
  tableList,
  sendOrder,
  listParm,
  resetBtn,
  searchBtn,
  sizeChange,
  currentChange,
  tableHeight,
} = useOrderTable();
</script>

<style scoped lang="scss">
.order-container {
  height: calc(100vh - 140px);
  padding: 10px;
  background: #f5f7fa;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.search-wrapper {
  margin-bottom: 8px;
  padding: 8px 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
  flex-shrink: 0;
}

.custom-button {
    padding: 8px 20px;
    transition: all 0.3s ease;
    border-radius: 6px;
    font-weight: 500;

    &:hover {
        transform: translateY(-2px);
    }

    &.search-btn {
        background: linear-gradient(135deg, #7e22ce, #3b82f6);
        border: none;
        color: white;

        &:hover {
            box-shadow: 0 4px 12px rgba(126, 34, 206, 0.2);
        }
    }

    &.reset-btn {
        &:hover {
            box-shadow: 0 4px 12px rgba(220, 38, 38, 0.1);
        }
    }

    &.add-btn {
        background: linear-gradient(135deg, #7e22ce, #3b82f6);
        border: none;

        &:hover {
            box-shadow: 0 4px 12px rgba(126, 34, 206, 0.2);
        }
    }
}


.table-wrapper {
  flex: 1;
  background: white;
  border-radius: 8px;
  padding: 8px;
  margin-bottom: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.custom-table {
  flex: 1;
  :deep(.el-table__inner-wrapper) {
    height: 100%;
  }

  :deep(.el-table__body-wrapper) {
    max-height: calc(100vh - 280px);
  }

  :deep(.el-table__header) {
    background: #f8fafc;
  }

  :deep(.el-table__row) {
    transition: all 0.3s ease;

    &:hover {
      background-color: #f5f7fa !important;
    }
  }
}

.sub-table {
  margin: 10px;
  border-radius: 4px;
  overflow: hidden;
}

.goods-image {
  height: 60px;
  width: 60px;
  border-radius: 8px;
  object-fit: cover;
  transition: all 0.3s ease;

  &:hover {
    transform: scale(1.05);
  }
}

.status-tag {
  padding: 4px 12px;
  border-radius: 4px;
}

.action-button {
  padding: 6px 16px;
  margin: 0 4px;
  transition: all 0.3s ease;

  &.edit-btn {
    background: linear-gradient(135deg, #7e22ce, #3b82f6);
    border: none;

    &:hover {
      transform: translateY(-2px);
      box-shadow: 0 4px 12px rgba(126, 34, 206, 0.2);
    }
  }
}

.custom-pagination {
  margin-top: 8px;
  padding: 8px 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

// 响应式设计
@media (max-width: 768px) {
  .search-form {
    flex-direction: column;
    align-items: stretch;

    .el-form-item {
      margin-bottom: 10px;
    }
  }

  .search-input {
    width: 100%;
  }
}
</style>
