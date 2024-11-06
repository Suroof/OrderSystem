import { getSelectListApi } from "@/api/goods";
import { ref } from "vue";
export default function useSelectCategory(){
    //下拉数据
    const selectData = ref([])
    //获取数据
    const getSelect = async()=>{
        let res = await getSelectListApi()
        if(res && res.code == 200){
            selectData.value = res.data
        }
    }
    return{
        selectData,
        getSelect
    }
}