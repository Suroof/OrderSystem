"use strict";
var common_vendor = require("../../../../common/vendor.js");
const _sfc_main = {
  name: "u-divider",
  props: {
    halfWidth: {
      type: [Number, String],
      default: 150
    },
    borderColor: {
      type: String,
      default: "#dcdfe6"
    },
    type: {
      type: String,
      default: "primary"
    },
    color: {
      type: String,
      default: "#909399"
    },
    fontSize: {
      type: [Number, String],
      default: 26
    },
    bgColor: {
      type: String,
      default: "#ffffff"
    },
    height: {
      type: [Number, String],
      default: "auto"
    },
    marginTop: {
      type: [String, Number],
      default: 0
    },
    marginBottom: {
      type: [String, Number],
      default: 0
    },
    useSlot: {
      type: Boolean,
      default: true
    }
  },
  computed: {
    lineStyle() {
      let style = {};
      if (String(this.halfWidth).indexOf("%") != -1)
        style.width = this.halfWidth;
      else
        style.width = this.halfWidth + "rpx";
      if (this.borderColor)
        style.borderColor = this.borderColor;
      return style;
    }
  },
  methods: {
    click() {
      this.$emit("click");
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: common_vendor.n($props.type ? "u-divider-line--bordercolor--" + $props.type : ""),
    b: common_vendor.s($options.lineStyle),
    c: $props.useSlot
  }, $props.useSlot ? {
    d: $props.color,
    e: $props.fontSize + "rpx"
  } : {}, {
    f: common_vendor.n($props.type ? "u-divider-line--bordercolor--" + $props.type : ""),
    g: common_vendor.s($options.lineStyle),
    h: $props.height == "auto" ? "auto" : $props.height + "rpx",
    i: $props.bgColor,
    j: $props.marginBottom + "rpx",
    k: $props.marginTop + "rpx",
    l: common_vendor.o((...args) => $options.click && $options.click(...args))
  });
}
var Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-2cf2039a"], ["__file", "D:/Work/\u70B9\u9910\u7CFB\u7EDF/\u7CFB\u7EDF\u6700\u7EC8\u6E90\u7801/\u5C0F\u7A0B\u5E8F\uFF08\u79FB\u52A8\u7AEF\u3001\u624B\u673A\u7AEF\u3001\u7528\u6237\u7AEF\uFF09/1-106\uFF08\u5C0F\u7A0B\u5E8F\u6700\u7EC8\u7248\u672C\u6E90\u7801\uFF09/order-app/src/uni_modules/vk-uview-ui/components/u-divider/u-divider.vue"]]);
wx.createComponent(Component);
