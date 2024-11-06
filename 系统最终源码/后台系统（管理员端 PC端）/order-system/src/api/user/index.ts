import http from "@/http";
import { UserModel,ListUserParm } from "./UserModel";


//用户管理   新增  因为后端 用户控制器里面只有一个 post请求，默认就是新增的post来接收前端发送到后台的数据
export const addUserApi = (parm:UserModel)=>{
    return http.post("/api/user",parm)
}

//用户管理  列表  数据类型 ListUserParm
export const getListApi = (parm:ListUserParm)=>{
    return http.get("/api/user/list",parm)
}

//编辑
export const editUserApi = (parm:UserModel)=>{
    return http.put("/api/user",parm)
}
//删除
export const deleteUserApi = (userId:string)=>{
    return http.delete(`/api/user/${userId}`)
}