<template>
  <div class="admin" v-if="userStore.state.loginUser.userName === '未登录'">
    <a-button>去登陆</a-button>
  </div>
</template>

<script setup lang="ts">
import user from "@/store/user";

const userStore = user();
</script>
