<template>
    <el-main class="category-container">
        <!-- 搜索栏 -->
        <div class="search-wrapper">
            <el-form :model="listParm" :inline="true" size="default" class="search-form">
                <el-form-item>
                    <el-input
                        placeholder="请输入分类名称"
                        v-model="listParm.categoryName"
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
                <el-table-column label="分类名称" prop="categoryName"></el-table-column>
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
    <!-- 新增、编辑弹框 -->
    <AddCategory ref="addRef" @onFresh="getList"></AddCategory>
</template>

<script setup lang="ts">
import AddCategory from '@/views/category/AddCategory.vue'
import {Edit,Plus,Delete,Search,Close} from '@element-plus/icons-vue'
import useTable from '@/composables/category/useTable'
import useCategory from '@/composables/category/useCategory'
//表格相关的操作
const {listParm,getList,searchBtn,resetBtn,tableList,sizeChange,currentChange,tableHeight} = useTable()
//增删改
const {addBtn,editBtn,deleteBtn,addRef} = useCategory(getList)
</script>

<style scoped lang="scss">
.category-container {
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

.custom-pagination {
    margin-top: 8px;
    padding: 8px 20px;
    background: white;
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

// 添加操作按钮样式
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
</style>