// stores/counter.js   引入pinia
import { defineStore } from 'pinia'
// 通过definestore定义一个store,
// definestore第一个参数是唯一的
export const carStore = defineStore('carStore', {
	state: () => {
		return { 
			count: 0 ,
            //存储购物车数据，存放到state里面
			carList:[]
		}
	},
	// 也可以这样定义
	// state: () => ({ count: 0 })
	actions: {
		increment() {
            //数量+1
			this.count++
		},
        //添加购物车
		addCar(goods){
			//判断是否加入到购物车，查找是否存在，返回位置
			const index = this.carList.findIndex(item => goods.goodsId == item.goodsId)
			//存在，删除原来的 数据
			if(index > -1){
				this.carList.splice(index,1)
			}
            //加到购物车
			this.carList.push(goods)
		}
	},
})
