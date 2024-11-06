<template>
  <el-dialog
    :title="dialog.title"
    v-model="dialog.visible"
    width="500px"
    class="custom-dialog"
  >
    <el-form :model="commentForm" :rules="rules" ref="commentFormRef" label-width="100px">
      <el-form-item label="商品名称" prop="goodsName">
        <el-input v-model="commentForm.goodsName"></el-input>
      </el-form-item>
      <el-form-item label="评论内容" prop="content">
        <el-input type="textarea" v-model="commentForm.content"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="onClose">取 消</el-button>
      <el-button type="primary" @click="onSubmit">确 定</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import type { FormInstance } from 'element-plus'
import { ElMessage } from 'element-plus'

// 表单ref
const commentFormRef = ref<FormInstance>()

// 对话框数据
const dialog = reactive({
  title: '',
  visible: false
})

// 表单数据
const commentForm = reactive({
  commentId: '',
  goodsName: '',
  content: ''
})

// ���单验证规则
const rules = {
  goodsName: [{ required: true, message: '请输入商品名称', trigger: 'blur' }],
  content: [{ required: true, message: '请输入评论内容', trigger: 'blur' }]
}

// 定义emit
const emit = defineEmits(['onFresh'])

// 打开新增
const show = (type: string, row?: any) => {
  dialog.visible = true
  dialog.title = type === 'add' ? '新增评论' : '编辑评论'
  if (type === 'edit' && row) {
    commentForm.commentId = row.commentId
    commentForm.goodsName = row.goodsName
    commentForm.content = row.content
  }
}

// 关闭弹框
const onClose = () => {
  dialog.visible = false
  commentForm.commentId = ''
  commentForm.goodsName = ''
  commentForm.content = ''
}

// 提交表单
const onSubmit = async () => {
  if (!commentFormRef.value) return
  await commentFormRef.value.validate(async (valid) => {
    if (valid) {
      // 这里调用后端接口
      ElMessage.success(commentForm.commentId ? '编辑成功' : '新增成功')
      onClose()
      emit('onFresh')
    }
  })
}

// 暴露方法
defineExpose({
  show
})
</script>

<style scoped lang="scss">
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