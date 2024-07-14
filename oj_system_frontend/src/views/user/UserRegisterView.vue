<template>
  <div class="userRegister">
    <a-form :model="form" :style="{ width: '600px' }" @submit="handleSubmit">
      <a-form-item field="userAccount" label="账号">
        <a-input v-model="form.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item field="userPassword" label="密码" tooltip="密码不小于8位">
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入密码"
        />
      </a-form-item>
      <a-form-item
        field="checkPassword"
        label="确认密码"
        tooltip="确认密码不小于8位"
      >
        <a-input-password
          v-model="form.checkPassword"
          placeholder="请输入确认密码"
        />
      </a-form-item>
      <a-form-item>
        <a-button html-type="submit">注册</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script lang="ts" setup>
import { reactive } from "vue";
import { UserControllerService, UserLoginRequest } from "../../../generated";
import message from "@arco-design/web-vue/es/message/index";
import { useRouter } from "vue-router";

const router = useRouter();
/**
 * 登陆参数
 */
const form = reactive({
  userAccount: "",
  userPassword: "",
  checkPassword: "",
} as UserLoginRequest);
/**
 * 登录按钮
 * @returns
 */
const handleSubmit = async () => {
  const res = await UserControllerService.userRegisterUsingPost(form);
  if (res.code === 0) {
    message.success("注册成功");
    await router.push({
      path: "/user/login",
      replace: true,
    });
  } else {
    message.error("注册失败" + res.message);
  }
};
</script>
<style scoped>
.userRegister {
  text-align: center;
}
</style>
