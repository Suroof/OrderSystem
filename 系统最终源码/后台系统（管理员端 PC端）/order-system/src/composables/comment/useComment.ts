import { ref } from 'vue'
import type { AddCommentInstance } from '@/types/comment'

export default function useComment(getList: () => void) {
  // 新增编辑ref
  const addRef = ref<AddCommentInstance>()

  // 新增
  const addBtn = () => {
    addRef.value?.show('add')
  }

  // 编辑
  const editBtn = (row: any) => {
    addRef.value?.show('edit', row)
  }

  // 删除
  const deleteBtn = async (row: any) => {
    // 这里调用删除接口
    await getList()
  }

  return {
    addRef,
    addBtn,
    editBtn,
    deleteBtn
  }
}