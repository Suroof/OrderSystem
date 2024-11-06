<template>
  <SysDialog
    :title="dialog.title"
    :height="dialog.height"
    :width="dialog.width"
    :visible="dialog.visible"
    @onClose="onClose"
    @onConfirm="commit"
  >
    <template v-slot:content>
      <el-form
        :model="addModel"
        ref="addFormRef"
        :rules="rules"
        label-width="80px"
        :inline="false"
        size="default"
        class="category-form"
      >
        <el-form-item prop="categoryName" label="分类名称">
          <el-input
            v-model="addModel.categoryName"
            placeholder="请输入分类名称"
            class="custom-input"
          ></el-input>
        </el-form-item>
        <el-form-item label="序号" prop="orderNum">
          <el-input
            type="number"
            v-model="addModel.orderNum"
            placeholder="请输入序号"
            class="custom-input"
          ></el-input>
        </el-form-item>
      </el-form>
    </template>
  </SysDialog>
</template>

<script setup lang="ts">
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import { CategoryModel } from "@/api/category/CategoryModel";
import { nextTick, reactive, ref } from "vue";
import { ElMessage, FormInstance } from "element-plus";
import {addApi,editApi} from '@/api/category/index'
import { EditType } from "@/type/BaseType";
//表单的ref属性
const addFormRef = ref<FormInstance>();
//弹框属性
const { dialog, onClose, onConfirm, onShow } = useDialog();
//显示弹框
const show = (type:string,row?:CategoryModel) => {
  dialog.height = 280
  dialog.width = 480
  onShow();
  //编辑的时候，数据回显
  if(row && type == EditType.EDIT){
    nextTick(()=>{
      Object.assign(addModel,row)
    })
  }
  //设置编辑的属性：0：新增  1：编辑
  addModel.type = type;
  //清空表单
  addFormRef.value?.resetFields()
};
//暴露出去，给外部组件使用
defineExpose({
  show,
});
//表单绑定的对象
const addModel = reactive<CategoryModel>({
  type: "",
  categoryId: "",
  categoryName: "",
  orderNum: "",
});
//表单验证规则
const rules = reactive({
  categoryName: [
    {
      required: true,
      message: "请填写分类名称",
      trigger: "blur",
    },
  ]
});
//注册事件
const emits = defineEmits(['onFresh'])
//表单提交
const commit = ()=>{
    addFormRef.value?.validate(async(valid)=>{
      if(valid){
        let res = null;
        if(addModel.type == EditType.ADD){
          res = await addApi(addModel)
        }else{
          res = await editApi(addModel)
        }
        if(res && res.code == 200){
          ElMessage.success(res.msg)
          //刷新表格
          emits('onFresh')
          onClose()
        }
      }
    })
}
</script>

<style scoped lang="scss">
.category-form {
  padding: 30px 40px;
  background: #ffffff;
  border-radius: 8px;

  :deep(.el-form-item) {
    margin-bottom: 25px;

    .el-form-item__label {
      font-weight: 500;
      color: #333;
      font-size: 14px;
      padding-right: 15px;
    }

    &:last-child {
      margin-bottom: 0;
    }
  }

  .custom-input {
    :deep(.el-input__wrapper) {
      background-color: #f8fafc;
      border-radius: 6px;
      box-shadow: none;
      border: 1px solid #e2e8f0;
      transition: all 0.3s ease;

      &:hover {
        border-color: #7e22ce;
        background-color: #fff;
      }

      &.is-focus {
        border-color: #7e22ce;
        box-shadow: 0 0 0 2px rgba(126, 34, 206, 0.1);
        background-color: #fff;
      }

      .el-input__inner {
        height: 38px;
        color: #1e293b;
        font-size: 14px;

        &::placeholder {
          color: #94a3b8;
        }
      }
    }
  }
}

// 修改 Dialog 样式
:deep(.el-dialog) {
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 8px 30px rgba(0, 0, 0, 0.12);
  backdrop-filter: blur(10px);

  .el-dialog__header {
    margin: 0;
    padding: 16px 24px;
    background: linear-gradient(135deg, #7e22ce, #3b82f6);
    border-bottom: 1px solid rgba(255, 255, 255, 0.1);

    .el-dialog__title {
      color: white;
      font-size: 16px;
      font-weight: 600;
      letter-spacing: 0.5px;
    }

    .el-dialog__headerbtn {
      top: 16px;
      right: 16px;
    }

    .el-dialog__close {
      color: rgba(255, 255, 255, 0.9);
      font-size: 18px;
      transition: all 0.3s ease;

      &:hover {
        color: #fff;
        transform: rotate(90deg);
      }
    }
  }

  .el-dialog__body {
    padding: 0;
  }

  .el-dialog__footer {
    padding: 16px 24px;
    background: #f8fafc;
    border-top: 1px solid #e2e8f0;

    .el-button {
      padding: 8px 24px;
      border-radius: 6px;
      font-weight: 500;
      transition: all 0.3s ease;

      // 取消按钮
      &.el-button--default {
        background: #f1f5f9;
        border-color: #e2e8f0;
        color: #64748b;

        &:hover {
          background: #e2e8f0;
          color: #334155;
        }
      }

      // 确认按钮
      &.el-button--primary {
        background: linear-gradient(135deg, #7e22ce, #3b82f6);
        border: none;

        &:hover {
          transform: translateY(-2px);
          box-shadow: 0 4px 12px rgba(126, 34, 206, 0.2);
        }
      }
    }
  }
}

// 响应式设计
@media (max-width: 768px) {
  .category-form {
    padding: 20px;
  }

  :deep(.el-dialog) {
    width: 90% !important;
    margin: 5vh auto !important;
  }
}
</style>
