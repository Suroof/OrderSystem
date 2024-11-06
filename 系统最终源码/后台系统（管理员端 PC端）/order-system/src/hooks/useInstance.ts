import { ComponentInternalInstance, getCurrentInstance } from 'vue'
export default function useInstance(){
    const {appContext,proxy} = getCurrentInstance() as ComponentInternalInstance
    //获取全局属性
    const  global = appContext.config.globalProperties
    return{
        global,  //全局  可以 获取到挂载的 $myconfirm实例
        proxy
    }
}