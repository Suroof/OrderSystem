import { CommentListParm } from "@/api/comment/CommentModel";
import { deleteApi, getListApi } from '@/api/comment/index';
import useInstance from "@/hooks/useInstance";
import { ElMessage } from "element-plus";
import { nextTick, onMounted, reactive, ref } from "vue";
export default function useCommentTable(){
    const {global} = useInstance()
    //表格高度
    const tableHeight = ref(0)
    //接收表格数据
    const tableList = ref([])
    //列表查询的参数
    const listParm = reactive<CommentListParm>({
        goodsName:'',
        currentPage:1,
        pageSize:3,
        // pageSize:10,
        total:0
    })
    //列表
    const getList = async()=>{
        let res = await getListApi(listParm)
        if(res && res.code == 200){
            tableList.value = res.data.records;
            listParm.total = res.data.total;
        }
    }
    //删除
    const deleteBtn = async(commentId:string)=>{
        const confirm = await global.$myconfirm('确定删除吗？')
        if(confirm){
            let res = await deleteApi(commentId)
            if(res && res.code == 200){
                ElMessage.success(res.msg)
                getList()
            }
        }
    }
    //搜索
    const searchBtn = ()=>{
        getList()
    }
    //重置
    const resetBtn = ()=>{
        listParm.currentPage = 1;
        getList()
    }
    //页容量改变时触发
    const sizeChange = (size:number)=>{
        listParm.pageSize = size;
        getList()
    }
    //页数改变触发
    const currentChange = (page:number)=>{
        listParm.currentPage = page;
        getList()
    }
    onMounted(()=>{
        getList()
        nextTick(()=>{
            tableHeight.value = window.innerHeight - 180
        })
    })
    return{
        listParm,
        getList,
        searchBtn,
        resetBtn,
        tableList,
        sizeChange,
        currentChange,
        tableHeight,
        deleteBtn
    }
}