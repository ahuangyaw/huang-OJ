<template>
  <div class="userLogin">
    <h2>用户登录</h2>
    <a-form
      label-align="left"
      :model="form"
      @submit="handleSubmit"
      auto-label-width
      style="min-width: 400px; max-width: 600px; margin: 0 auto"
    >
      <a-form-item field="userAccount" label="账号">
        <a-input v-model="form.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item field="userPassword" label="密码" tooltip="密码不小于8位">
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入密码"
        />
      </a-form-item>
      <a-form-item style="display: flex; justify-content: center">
        <a-button html-type="submit" style="width: 120px">登录</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script lang="ts" setup>
import { reactive } from "vue";
import { UserControllerService, UserLoginRequest } from "../../../generated";
import message from "@arco-design/web-vue/es/message/index";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const store = useStore();
/**
 * 登陆参数
 */
const form = reactive({
  userAccount: "",
  userPassword: "",
} as UserLoginRequest);
/**
 * 登录按钮
 * @returns
 */
const handleSubmit = async () => {
  const res = await UserControllerService.userLoginUsingPost(form);
  if (res.code === 0) {
    message.success("登录成功");
    await store.dispatch("user/getLoginUser");
    await router.push({
      path: "/",
      replace: true,
    });
  } else {
    message.error("登陆失败" + res.message);
  }
};
</script>
<style scoped>
.userLogin {
  text-align: center;
}
</style>
