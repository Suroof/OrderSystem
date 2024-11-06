import { defineStore } from "pinia";
//定义store  第一个参数是id collapseStore
export const collapseStore = defineStore('collapseStore',{
    state:()=>{
      // return返回的是一个对象
        return{
            collapse:false
        }
    },
    getters:{
        getCollapse(state){
            return state.collapse
        }
    },
    actions:{
        setCollapse(collapse:boolean){
            this.collapse = collapse
        }
    }
})