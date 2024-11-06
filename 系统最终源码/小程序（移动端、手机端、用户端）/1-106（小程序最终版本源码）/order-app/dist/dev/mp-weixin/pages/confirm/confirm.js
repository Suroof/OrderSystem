"use strict";
var common_vendor = require("../../common/vendor.js");
var store_order = require("../../store/order.js");
var store_car = require("../../store/car.js");
var store_address = require("../../store/address.js");
var api_user = require("../../api/user.js");
var api_order = require("../../api/order.js");
require("../../common/http.js");
require("../../store/user.js");
if (!Array) {
  const _easycom_u_icon2 = common_vendor.resolveComponent("u-icon");
  _easycom_u_icon2();
}
const _easycom_u_icon = () => "../../uni_modules/vk-uview-ui/components/u-icon/u-icon.js";
if (!Math) {
  _easycom_u_icon();
}
const _sfc_main = {
  __name: "confirm",
  setup(__props) {
    const store = store_order.orderStore();
    const carstore = store_car.carStore();
    const astore = store_address.addressStore();
    const goods = common_vendor.computed$1(() => {
      return store.orderList;
    });
    const totalNum = common_vendor.computed$1(() => {
      let totalNum2 = 0;
      store.orderList.map((item) => {
        item.flag ? totalNum2 += item.num : totalNum2 += 0;
      });
      return totalNum2;
    });
    const totalPrice = common_vendor.computed$1(() => {
      let totalPrice2 = 0;
      store.orderList.map((item) => {
        item.flag ? totalPrice2 += item.num * item.price : totalPrice2 += 0;
      });
      return totalPrice2;
    });
    const toAddress = (item) => {
      common_vendor.index.navigateTo({
        url: "../addresslist/addresslist"
      });
    };
    const getAddress = async () => {
      let res = await api_user.getAddressApi({
        openid: common_vendor.index.getStorageSync("openid")
      });
      console.log(res);
      if (res && res.code == 200) {
        astore.checkedId = res.data.addressId;
        astore.userName = res.data.userName;
        astore.phone = res.data.phone;
        astore.area = res.data.area;
        astore.address = res.data.address;
      }
    };
    const commitBtn = async () => {
      let commitParm = common_vendor.reactive({
        openid: common_vendor.index.getStorageSync("openid"),
        userName: astore.userName,
        phone: astore.phone,
        address: astore.area + "," + astore.address,
        price: totalPrice.value,
        details: store.orderList
      });
      const res = await api_order.splaceOrderApi(commitParm);
      console.log(res);
      if (res && res.code == 200) {
        store.orderList = [];
        carstore.carList = [];
        common_vendor.index.navigateBack();
        common_vendor.index.navigateTo({
          url: "../order/order"
        });
      }
    };
    common_vendor.onLoad(() => {
      getAddress();
    });
    return (_ctx, _cache) => {
      return common_vendor.e({
        a: common_vendor.t(common_vendor.unref(astore).userName),
        b: common_vendor.t(common_vendor.unref(astore).phone),
        c: common_vendor.t(common_vendor.unref(astore).area),
        d: common_vendor.t(common_vendor.unref(astore).address),
        e: common_vendor.p({
          name: "arrow-right",
          color: "#c8c9cc"
        }),
        f: common_vendor.o(toAddress),
        g: common_vendor.unref(goods).length > 0
      }, common_vendor.unref(goods).length > 0 ? {
        h: common_vendor.f(common_vendor.unref(goods), (item, index, i0) => {
          return {
            a: item.goodsImage,
            b: common_vendor.t(item.goodsName),
            c: common_vendor.t(item.specsName),
            d: common_vendor.t(item.price),
            e: common_vendor.t(item.goodsUnit),
            f: common_vendor.t(item.num),
            g: index
          };
        })
      } : {}, {
        i: common_vendor.t(common_vendor.unref(totalPrice)),
        j: common_vendor.t(common_vendor.unref(totalNum)),
        k: common_vendor.o(commitBtn)
      });
    };
  }
};
var MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Work/\u70B9\u9910\u7CFB\u7EDF/\u7CFB\u7EDF\u6700\u7EC8\u6E90\u7801/\u5C0F\u7A0B\u5E8F\uFF08\u79FB\u52A8\u7AEF\u3001\u624B\u673A\u7AEF\u3001\u7528\u6237\u7AEF\uFF09/1-106\uFF08\u5C0F\u7A0B\u5E8F\u6700\u7EC8\u7248\u672C\u6E90\u7801\uFF09/order-app/src/pages/confirm/confirm.vue"]]);
wx.createPage(MiniProgramPage);
