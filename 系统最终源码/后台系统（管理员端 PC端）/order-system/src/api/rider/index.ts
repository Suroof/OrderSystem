import http from "@/http";
import { RiderListParm, RiderType } from "./RiderModel";

// 新增
export const addApi = (parm: RiderType) => {
    return http.post("/api/rider", parm)
}

// 列表 分页查询
export const getListApi = (parm: RiderListParm) => {
    return http.get("/api/rider/list", parm)
}

// 编辑
export const editApi = (parm: RiderType) => {
    return http.put("/api/rider", parm)
}

// 删除
export const deleteApi = (riderId: string) => {
    return http.delete(`/api/rider/${riderId}`)
}