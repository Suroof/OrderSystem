"use strict";
var common_vendor = require("../../../../common/vendor.js");
const _sfc_main = {
  name: "u-cell-group",
  props: {
    title: {
      type: String,
      default: ""
    },
    border: {
      type: Boolean,
      default: true
    },
    titleStyle: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  data() {
    return {
      index: 0
    };
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: $props.title
  }, $props.title ? {
    b: common_vendor.t($props.title),
    c: common_vendor.s($props.titleStyle)
  } : {}, {
    d: $props.border ? 1 : ""
  });
}
var Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-53f69c96"], ["__file", "D:/Work/\u70B9\u9910\u7CFB\u7EDF/\u7CFB\u7EDF\u6700\u7EC8\u6E90\u7801/\u5C0F\u7A0B\u5E8F\uFF08\u79FB\u52A8\u7AEF\u3001\u624B\u673A\u7AEF\u3001\u7528\u6237\u7AEF\uFF09/1-106\uFF08\u5C0F\u7A0B\u5E8F\u6700\u7EC8\u7248\u672C\u6E90\u7801\uFF09/order-app/src/uni_modules/vk-uview-ui/components/u-cell-group/u-cell-group.vue"]]);
wx.createComponent(Component);
