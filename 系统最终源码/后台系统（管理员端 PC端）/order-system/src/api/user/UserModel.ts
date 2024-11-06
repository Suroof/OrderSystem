//定义列表查询的数据类型(代表列表模块的数据类型)
//type:通常用于定义数据的类型
export type ListUserParm = {
  currentPage:number;
  pageSize:number;
  name:string;
  phone:string;
}


//表单的数据类型  用户管理 新增用户实体
export type UserModel = {
  type:string; //区分是新增还是编辑
  userId:string;
  username:string;
  password:string;
  phone:string;
  email:string;
  sex:string;
  name:string;
}