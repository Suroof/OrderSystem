// 信息确定框的封装
// 引入ElMessageBox 信息弹出框组件
import { ElMessageBox } from 'element-plus'
export default function myConfirm(text: string) {
  // resolve, reject两个回调
    return new Promise((resolve, reject) => {
      // 复制官网内容
        ElMessageBox.confirm(
            text,
            '系统提示',
            {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            }
        ).then(() => {
            resolve(true)
        }).catch(() => {
            reject(false)
        })
    }).catch(()=>{
        return false
    })
}
// 全屏
export function fullScreen() {
    const el: any = document.documentElement
    const rfs =
      el.requestFullScreen ||
      el.webkitRequestFullScreen ||
      el.mozRequestFullScreen ||
      el.msRequestFullScreen

    if (rfs) {
      rfs.call(el)
    }
  }

  //退出全屏
  export function exitScreen() {
    const el: any = document
    const cfs =
      el.cancelFullScreen || el.webkitCancelFullScreen || el.mozCancelFullScreen || el.exitFullScreen

    if (cfs) {
      cfs.call(el)
    }
  }