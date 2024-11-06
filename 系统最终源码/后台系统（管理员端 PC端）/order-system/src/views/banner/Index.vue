<template>
  <el-main class="banner-container">
    <!-- 搜索栏 -->
    <div class="search-wrapper">
      <el-form :model="listParm" :inline="true" size="default" class="search-form">
        <el-form-item>
          <el-input
            placeholder="请输入标题"
            v-model="listParm.title"
            class="search-input"
            :prefix-icon="Search"
          ></el-input>
        </el-form-item>
        <el-form-item class="btn-group">
          <el-button
            :icon="Search"
            @click="searchBtn"
            class="custom-button search-btn"
          >搜索</el-button>
          <el-button
            :icon="Close"
            type="danger"
            plain
            @click="resetBtn"
            class="custom-button reset-btn"
          >重置</el-button>
          <el-button
            :icon="Plus"
            type="primary"
            @click="addBtn"
            class="custom-button add-btn"
          >新增</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 表格 -->
    <div class="table-wrapper">
      <el-table
        :height="tableHeight"
        :data="tableList"
        border
        stripe
        class="custom-table"
      >
        <el-table-column label="广告图片" prop="images">
          <template #default="scope">
            <el-image
              :src="scope.row.images ? scope.row.images.split(',')[0] : ''"
              class="banner-image"
            ></el-image>
          </template>
        </el-table-column>
        <el-table-column label="标题" prop="title"></el-table-column>
        <el-table-column label="是否上架" prop="title">
          <template #default="scope">
            <el-tag
              :type="scope.row.status == '0' ? 'danger' : 'success'"
              class="status-tag"
            >
              {{ scope.row.status == '0' ? '否' : '是' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="序号" prop="orderNum"></el-table-column>
        <el-table-column label="操作" width="220" align="center">
          <template #default="scope">
            <el-button
              :icon="Edit"
              type="primary"
              class="action-button edit-btn"
              @click="editBtn(scope.row)"
            >编辑</el-button>
            <el-button
              :icon="Delete"
              type="danger"
              class="action-button delete-btn"
              @click="deleteBtn(scope.row)"
            >删除</el-button>
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
      :page-sizes="[10,20, 40, 80, 100]"
      :page-size="listParm.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="listParm.total"
      background
    >
    </el-pagination>
  </el-main>

  <!-- 新增、编辑 -->
  <add-banner ref="addRef" @onFresh="getList"></add-banner>
</template>

<script setup lang="ts">
import AddBanner from "@/views/banner/AddBanner.vue";
import { Edit, Plus, Delete, Search, Close } from "@element-plus/icons-vue";
import useBannerTable from "@/composables/banner/useBannerTable";
import useBanner from "@/composables/banner/useBanner";
//表格业务
const { getList, listParm, resetBtn, searchBtn, tableList,sizeChange ,currentChange,tableHeight} = useBannerTable();
//增删改业务
const { addBtn, editBtn, deleteBtn, addRef } = useBanner(getList);
</script>

<style scoped lang="scss">
.banner-container {
  height: calc(100vh - 120px);
  padding: 10px;
  background: #f5f7fa;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.search-wrapper {
  margin-bottom: 10px;
  padding: 10px 20px;
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
  padding: 10px;
  margin-bottom: 10px;
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
    overflow-y: auto;
  }
}

.banner-image {
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
  border-radius: 6px;

  &:hover {
    transform: translateY(-2px);
  }

  &.edit-btn {
    background: linear-gradient(135deg, #7e22ce, #3b82f6);
    border: none;
    color: white;

    &:hover {
      box-shadow: 0 4px 12px rgba(126, 34, 206, 0.2);
    }
  }

  &.delete-btn {
    background: #dc2626;
    border: none;
    color: white;

    &:hover {
      box-shadow: 0 4px 12px rgba(220, 38, 38, 0.2);
    }
  }
}

.custom-pagination {
  margin-top: 10px;
  padding: 10px 20px;
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
