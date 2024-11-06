import { RiderListParm } from "@/api/rider/RiderModel";
import { getListApi } from '@/api/rider/index';
import { nextTick, onMounted, reactive, ref } from "vue";

export default function useRiderTable() {
    // 表格高度
    const tableHeight = ref(0)
    // 接收表格数据
    const tableList = ref([])
    // 列表查询的参数
    const listParm = reactive<RiderListParm>({
        currentPage: 1,
        pageSize: 10,
        name: '',
        total: 0
    })

    // 列表
    const getList = async() => {
        try {
            let res = await getListApi(listParm)
            if(res && res.code == 200) {
                tableList.value = res.data.records;
                listParm.total = res.data.total;
            }
        } catch (error) {
            console.error('获取列表数据失败:', error);
        }
    }

    // 搜索
    const searchBtn = () => {
        listParm.currentPage = 1; // 重置到第一页
        getList()
    }

    // 重置
    const resetBtn = () => {
        listParm.currentPage = 1;
        listParm.name = ''
        getList()
    }

    // 页容量改变时触发
    const sizeChange = (size: number) => {
        listParm.pageSize = size;
        getList()
    }

    // 页数改变触发
    const currentChange = (page: number) => {
        listParm.currentPage = page;
        getList()
    }

    onMounted(() => {
        getList()
        nextTick(() => {
            tableHeight.value = window.innerHeight - 220
        })
    })

    return {
        listParm,
        getList,
        searchBtn,
        resetBtn,
        tableList,
        sizeChange,
        currentChange,
        tableHeight
    }
}
