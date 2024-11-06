import { RiderType } from "@/api/rider/RiderModel";
import { deleteApi } from '@/api/rider/index';
import useInstance from "@/hooks/useInstance";
import { EditType, FuncList } from "@/type/BaseType";
import { ElMessage } from "element-plus";
import { ref } from "vue";

export default function useRider(getList: FuncList) {
    const { global } = useInstance()
    // 新增弹框的ref属性
    const addRef = ref<{ show: (type: string, row?: RiderType) => void }>();

    // 新增
    const addBtn = () => {
        addRef.value?.show(EditType.ADD)
    }

    // 编辑
    const editBtn = (row: RiderType) => {
        addRef.value?.show(EditType.EDIT, row)
    }

    // 删除
    const deleteBtn = async (row: RiderType) => {
        const confirm = await global.$myconfirm('确定删除吗？')
        if(confirm) {
            let res = await deleteApi(row.riderId)
            if(res && res.code == 200) {
                ElMessage.success(res.msg)
                getList()
            }
        }
    }

    return {
        addRef,
        addBtn,
        editBtn,
        deleteBtn
    }
}
