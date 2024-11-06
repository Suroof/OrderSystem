export type RiderListParm = {
    currentPage:number;
    pageSize:number;
    name:string;
    total:number; //分页的总条数
  }
  export type RiderType = {
    type:string;
    riderId:string;
    name:string;
    age:number;
    email:string;
    phone:string;
    vehicle:string;
    contribution:number;
  }