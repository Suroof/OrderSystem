import { defineStore } from "pinia"
//定义store
// defineStore 即：第一个参数是id，是唯一的，不能重复
// 一般取名为：  目录名称+Store 来区分，例如 testStore
export const testStore = defineStore('testStore', {
    state: () => {
        return {
            count: 0
        }
    },
    // 获取state里面的数据
    getters: {
        getCount(state) {
            return state.count
        }
    },
    // 设置state里面的 数据  ，count: number  count理解为变量名 number是变量的类型  
    actions: {
        setCount(count: number) {
            console.log(count)
            this.count = count;
        }
    }
})