"use strict";
var common_vendor = require("../../../../common/vendor.js");
const _sfc_main = {
  name: "u-mask",
  emits: ["click"],
  props: {
    show: {
      type: Boolean,
      default: false
    },
    zIndex: {
      type: [Number, String],
      default: ""
    },
    customStyle: {
      type: Object,
      default() {
        return {};
      }
    },
    zoom: {
      type: Boolean,
      default: true
    },
    duration: {
      type: [Number, String],
      default: 300
    },
    maskClickAble: {
      type: Boolean,
      default: true
    },
    blur: {
      type: [Number, String],
      default: 0
    }
  },
  data() {
    return {
      zoomStyle: {
        transform: ""
      },
      scale: "scale(1.2, 1.2)"
    };
  },
  watch: {
    show(n) {
      if (n && this.zoom) {
        this.zoomStyle.transform = "scale(1, 1)";
      } else if (!n && this.zoom) {
        this.zoomStyle.transform = this.scale;
      }
    }
  },
  computed: {
    maskStyle() {
      let style = {};
      style.backgroundColor = "rgba(0, 0, 0, 0.6)";
      if (this.show)
        style.zIndex = this.zIndex ? this.zIndex : this.$u.zIndex.mask;
      else
        style.zIndex = -1;
      style.transition = `all ${this.duration / 1e3}s ease-in-out`;
      if (Object.keys(this.customStyle).length)
        style = {
          ...style,
          ...this.customStyle
        };
      return style;
    },
    filterStyle() {
      let { blur } = this;
      let style = {};
      if (blur) {
        style.backdropFilter = `blur(${blur}rpx)`;
      }
      return style;
    }
  },
  methods: {
    click() {
      if (!this.maskClickAble)
        return;
      this.$emit("click");
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.s($options.maskStyle),
    b: common_vendor.s($data.zoomStyle),
    c: common_vendor.s($options.filterStyle),
    d: common_vendor.o((...args) => $options.click && $options.click(...args)),
    e: common_vendor.o(() => {
    }),
    f: $props.zoom ? 1 : "",
    g: $props.show ? 1 : ""
  };
}
var Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-22d50354"], ["__file", "D:/Work/\u70B9\u9910\u7CFB\u7EDF/\u7CFB\u7EDF\u6700\u7EC8\u6E90\u7801/\u5C0F\u7A0B\u5E8F\uFF08\u79FB\u52A8\u7AEF\u3001\u624B\u673A\u7AEF\u3001\u7528\u6237\u7AEF\uFF09/1-106\uFF08\u5C0F\u7A0B\u5E8F\u6700\u7EC8\u7248\u672C\u6E90\u7801\uFF09/order-app/src/uni_modules/vk-uview-ui/components/u-mask/u-mask.vue"]]);
wx.createComponent(Component);
