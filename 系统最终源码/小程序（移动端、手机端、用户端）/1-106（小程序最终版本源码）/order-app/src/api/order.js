import http from '../common/http.js'


export const splaceOrderApi = parm=>{
	return http.post('/wxapi/order/splaceOrder',parm)
}


//获取 订单列表 有哪些商品
export const getOrderListApi = parm=>{
	return http.get('/wxapi/order/getOrderList',parm)
}


//取消订单
export const cancelOrderApi = parm=>{
	return http.post('/wxapi/order/cancelOrder',parm)
}

//小程序评论列表   查询评论列表数据
export const commentListApi = parm=>{
	return http.get('/wxapi/comment/commentList',parm)
}




//收藏
export const addCollectApi = parm=>{
	return http.post('/wxapi/collect/addCollect',parm)
}
//根据  goodsid 来判断是否已经收藏 
export const hasCollectApi = parm=>{
	return http.get('/wxapi/collect/hasCollect',parm)
}
//取消 收藏
export const cancelCollectApi = parm=>{
	return http.post('/wxapi/collect/cancelCollect',parm)
}



//确定收货
export const confirmOrderApi = parm=>{
	return http.post('/wxapi/order/confirmOrder',parm)
}