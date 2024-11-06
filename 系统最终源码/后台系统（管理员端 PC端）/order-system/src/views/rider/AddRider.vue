<template>
  <SysDialog
    :title="dialog.title"
    :width="dialog.width"
    :height="dialog.height"
    :visible="dialog.visible"
    @onClose="onClose"
    @onConfirm="commit"
  >
    <template v-slot:content>
      <el-form
        :model="addModel"
        ref="addFormRef"
        :rules="rules"
        label-width="80px"
        :inline="false"
        size="default"
      >
        <el-form-item prop="name" label="姓名：">
          <el-input v-model="addModel.name"></el-input>
        </el-form-item>
        <el-form-item prop="age" label="年龄：">
          <el-input-number v-model="addModel.age" :min="18" :max="60"></el-input-number>
        </el-form-item>
        <el-form-item prop="vehicle" label="车辆：">
          <el-select
            style="width: 100%"
            v-model="addModel.vehicle"
            placeholder="请选择车辆类型"
          >
            <el-option label="电动车" value="电动车" />
            <el-option label="摩托车" value="摩托车" />
            <el-option label="汽车" value="汽车" />
          </el-select>
        </el-form-item>
        <el-form-item prop="contribution" label="贡献值：">
          <el-input-number v-model="addModel.contribution" :min="0"></el-input-number>
        </el-form-item>
      </el-form>
    </template>
  </SysDialog>
</template>

<script setup lang="ts">
import { EditType } from "@/type/BaseType";
import { RiderType } from "@/api/rider/RiderModel";
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import { ElMessage, FormInstance } from "element-plus";
import { reactive, ref, nextTick } from "vue";
import { addApi, editApi } from "@/api/rider/index";

// 表单ref属性
const addFormRef = ref<FormInstance>();

// 弹框属性
const { dialog, onClose, onConfirm, onShow } = useDialog();

// 弹框显示
const show = (type: string, row?: RiderType) => {
  dialog.height = 350;
  dialog.width = 600;
  onShow();

  if (type == EditType.EDIT && row) {
    nextTick(() => {
      Object.assign(addModel, row);
    });
  }
  // 清空表单
  addFormRef.value?.resetFields();
  addModel.type = type;
};

// 暴露出去
defineExpose({
  show,
});

// 表单绑定的对象
const addModel = reactive<RiderType>({
  type: "",
  riderId: "",
  name: "",
  age: 18,
  vehicle: "",
  contribution: 0,
  email: "",
  phone: ""
});

// 表单验证规则
const rules = reactive({
  name: [
    {
      required: true,
      trigger: "blur",
      message: "请填写姓名",
    },
  ],
  age: [
    {
      required: true,
      trigger: "blur",
      message: "请填写年龄",
    },
  ],
  vehicle: [
    {
      required: true,
      trigger: "blur",
      message: "请选择车辆类型",
    },
  ],
  contribution: [
    {
      required: true,
      trigger: "blur",
      message: "请填写贡献值",
    },
  ],
});

// 注册事件
const emits = defineEmits(['onFresh']);

// 表单提交
const commit = () => {
  addFormRef.value?.validate(async (valid) => {
    if (valid) {
      let res = null;
      if (addModel.type == EditType.ADD) {
        res = await addApi(addModel);
      } else {
        res = await editApi(addModel);
      }
      if (res && res.code == 200) {
        ElMessage.success(res.msg);
        emits('onFresh');
        onClose();
      }
    }
  });
};
</script>

<style scoped></style>
