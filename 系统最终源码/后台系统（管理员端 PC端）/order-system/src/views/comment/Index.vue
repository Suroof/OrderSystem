<template>
  <el-main class="comment-container">
    <!-- 搜索栏 -->
    <div class="search-wrapper">
      <el-form :model="listParm" :inline="true" size="default" class="search-form">
        <el-form-item>
          <el-input
            placeholder="请输入商品名称"
            v-model="listParm.goodsName"
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
        <el-table-column label="名称" prop="goodsName"></el-table-column>
        <el-table-column label="商品图片" prop="goodsImage">
          <template #default="scope">
            <el-image
              :src="scope.row.goodsImage.split(',')[0]"
              class="goods-image"
            ></el-image>
          </template>
        </el-table-column>
        <el-table-column label="昵称" prop="nickName"></el-table-column>
        <el-table-column label="头像" prop="avatarUrl">
          <template #default="scope">
            <el-image
              :src="imgUrl+scope.row.avatarUrl"
              class="avatar-image"
            ></el-image>
          </template>
        </el-table-column>
        <el-table-column label="时间" prop="createTime"></el-table-column>
        <el-table-column label="操作" width="220" align="center">
          <template #default="scope">
            <el-button
              :icon="Delete"
              type="danger"
              class="action-button delete-btn"
              @click="deleteBtn(scope.row.commentId)"
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
  <!-- 新增、编辑弹框 -->
  <AddComment ref="addRef" @onFresh="getList"></AddComment>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { Delete, Plus, Search, Close } from "@element-plus/icons-vue"
import useCommentTable from "@/composables/comment/useCommentTable"
import useComment from "@/composables/comment/useComment"
import AddComment from './AddComment.vue'

const imgUrl = ref('http://localhost:8089/')

// 表格相关的操作
const { listParm, getList, searchBtn, resetBtn, tableList, sizeChange, currentChange, tableHeight } = useCommentTable()

// 增删改操作
const { addRef, addBtn, deleteBtn } = useComment(getList)
</script>

<style scoped lang="scss">
.comment-container {
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
  padding: 12px 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
  flex-shrink: 0;

  .search-form {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    gap: 10px;
  }

  .search-input {
    width: 220px;
    :deep(.el-input__wrapper) {
      background-color: #f8fafc;
      border-radius: 4px;

      &:hover, &.is-focus {
        border-color: #7e22ce;
        box-shadow: 0 0 0 2px rgba(126, 34, 206, 0.1);
      }
    }
  }
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
      box-shadow: 0 4px 12px rgba(126, 34, 206, 0.2);
    }
  }

  &.add-btn {
    background: linear-gradient(135deg, #7e22ce, #3b82f6);
    border: none;
    color: white;

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
  :deep(.el-table__body-wrapper) {
    max-height: calc(100vh - 320px);
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

.goods-image, .avatar-image {
  height: 60px;
  width: 60px;
  border-radius: 8px;
  object-fit: cover;
  transition: all 0.3s ease;

  &:hover {
    transform: scale(1.05);
  }
}

.action-button {
  padding: 6px 16px;
  margin: 0 4px;
  transition: all 0.3s ease;
  border-radius: 6px;

  &:hover {
    transform: translateY(-2px);
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
  margin-top: 8px;
  padding: 8px 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.custom-dialog {
  :deep(.el-dialog__header) {
    margin-right: 0;
    border-bottom: 1px solid #e5e7eb;
    padding: 16px 24px;
  }

  :deep(.el-dialog__body) {
    padding: 24px;
  }

  :deep(.el-dialog__footer) {
    border-top: 1px solid #e5e7eb;
    padding: 16px 24px;
  }
}
</style>
