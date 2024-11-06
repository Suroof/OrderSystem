"use strict";
var common_vendor = require("../../../../common/vendor.js");
const _sfc_main = {
  name: "u-line",
  props: {
    color: {
      type: String,
      default: "#e4e7ed"
    },
    length: {
      type: String,
      default: "100%"
    },
    direction: {
      type: String,
      default: "row"
    },
    hairLine: {
      type: Boolean,
      default: true
    },
    margin: {
      type: String,
      default: "0"
    },
    borderStyle: {
      type: String,
      default: "solid"
    }
  },
  computed: {
    lineStyle() {
      let style = {};
      style.margin = this.margin;
      if (this.direction == "row") {
        style.borderBottomWidth = "1px";
        style.borderBottomStyle = this.borderStyle;
        style.width = this.$u.addUnit(this.length);
        if (this.hairLine)
          style.transform = "scaleY(0.5)";
      } else {
        style.borderLeftWidth = "1px";
        style.borderLeftStyle = this.borderStyle;
        style.height = this.$u.addUnit(this.length);
        if (this.hairLine)
          style.transform = "scaleX(0.5)";
      }
      style.borderColor = this.color;
      return style;
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.s($options.lineStyle)
  };
}
var Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-03634556"], ["__file", "D:/Work/\u70B9\u9910\u7CFB\u7EDF/\u7CFB\u7EDF\u6700\u7EC8\u6E90\u7801/\u5C0F\u7A0B\u5E8F\uFF08\u79FB\u52A8\u7AEF\u3001\u624B\u673A\u7AEF\u3001\u7528\u6237\u7AEF\uFF09/1-106\uFF08\u5C0F\u7A0B\u5E8F\u6700\u7EC8\u7248\u672C\u6E90\u7801\uFF09/order-app/src/uni_modules/vk-uview-ui/components/u-line/u-line.vue"]]);
wx.createComponent(Component);
