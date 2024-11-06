"use strict";
var common_vendor = require("../../../../common/vendor.js");
const _sfc_main = {
  name: "u-loading",
  props: {
    mode: {
      type: String,
      default: "circle"
    },
    color: {
      type: String,
      default: "#c7c7c7"
    },
    size: {
      type: [String, Number],
      default: "34"
    },
    show: {
      type: Boolean,
      default: true
    }
  },
  computed: {
    cricleStyle() {
      let style = {};
      style.width = this.size + "rpx";
      style.height = this.size + "rpx";
      if (this.mode == "circle")
        style.borderColor = `#e4e4e4 #e4e4e4 #e4e4e4 ${this.color ? this.color : "#c7c7c7"}`;
      return style;
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: $props.show
  }, $props.show ? {
    b: common_vendor.n($props.mode == "circle" ? "u-loading-circle" : "u-loading-flower"),
    c: common_vendor.s($options.cricleStyle)
  } : {});
}
var Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-35d0dd20"], ["__file", "D:/Work/\u70B9\u9910\u7CFB\u7EDF/\u7CFB\u7EDF\u6700\u7EC8\u6E90\u7801/\u5C0F\u7A0B\u5E8F\uFF08\u79FB\u52A8\u7AEF\u3001\u624B\u673A\u7AEF\u3001\u7528\u6237\u7AEF\uFF09/1-106\uFF08\u5C0F\u7A0B\u5E8F\u6700\u7EC8\u7248\u672C\u6E90\u7801\uFF09/order-app/src/uni_modules/vk-uview-ui/components/u-loading/u-loading.vue"]]);
wx.createComponent(Component);
