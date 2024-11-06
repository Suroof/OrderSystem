import { deleteUserApi } from '@/api/user/index';
import useInstance from "@/hooks/useInstance";
import { EditType } from "@/type/BaseType";
import { ElMessage } from "element-plus";
import { ref } from "vue";
import { UserModel } from "./UserModel";


//增删改的业务操作
// export default function useUser(){
export default function useUser(getList:FuncList){
    
    // //新增、编辑弹框的ref属性
    // const addRef = ref<{show:()=>void}>();
    // //新增
    // const addBtn = ()=>{
    //     console.log(addRef)
    //     addRef.value?.show()
    // }
    // //编辑
    // const editBtn = ()=>{
        
    // }
    // //删除
    // const deleteBtn = ()=>{
        
    // }

    const {global} = useInstance()
    //新增、编辑弹框的ref属性
    const addRef = ref<{show:(type:string,row?:UserModel)=>void}>();
    //新增
    const addBtn = ()=>{
        console.log(addRef)
        addRef.value?.show(EditType.ADD)
    }
    //编辑
    const editBtn = (row:UserModel)=>{
        addRef.value?.show(EditType.EDIT,row)
    }
    //删除
    const deleteBtn = async(row:UserModel)=>{
        let confirm = await global.$myconfirm('确定删除该数据吗?')
        if(confirm){
            let res = await deleteUserApi(row.userId)
            if(res && res.code == 200){
                ElMessage.success(res.msg)
                //刷新列表
                getList()
            }
        }
    }

    return{
        addBtn,
        editBtn,
        deleteBtn,
        addRef
    }
}