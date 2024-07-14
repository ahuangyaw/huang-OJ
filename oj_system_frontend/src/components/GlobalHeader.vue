<template>
  <a-row id="global-header" align="center" :wrap="false">
    <a-col flex="auto">
      <a-menu
        mode="horizontal"
        :selected-keys="selectedKeys"
        :default-selected-keys="['1']"
        @menu-item-click="doMenuClick"
        class="menu"
      >
        <a-menu-item
          key="/"
          :style="{ padding: 0, marginRight: '38px' }"
          class="menu-item"
        >
          <div class="title-bar">
            <img class="logo" src="../assets/fu.png" alt="logo" />
            <div class="title">huangOJ</div>
          </div>
        </a-menu-item>
        <a-menu-item
          v-for="item in visibleRoutes"
          :key="item.path"
          class="menu-item"
        >
          {{ item.name }}
        </a-menu-item>
      </a-menu>
    </a-col>
    <a-col flex="100px">
      <div style="text-align: center">
        {{ store.state.user?.loginUser?.userName ?? "未登录" }}
      </div>
    </a-col>
  </a-row>
</template>

<script setup lang="ts">
import { routes } from "@/router/routes";
import { useRouter } from "vue-router";
import { computed, ref } from "vue";
import { useStore } from "vuex";
import checkAccess from "@/access/checkAccess";
import ACCESS_ENUM from "@/access/ACCESS_ENUM";

const router = useRouter();
const store = useStore();

//在菜单展示的路由数组
const visibleRoutes = computed(() => {
  return routes.filter((item, index) => {
    if (item.meta?.hideInMenu) {
      return false;
    }
    // 根据权限过滤菜单
    if (
      !checkAccess(store.state.user.loginUser, item?.meta?.access as string)
    ) {
      return false;
    }
    return true;
  });
});

// 当前选中的菜单高亮
const selectedKeys = ref(["/"]);
// 监听路由变化
router.afterEach((to) => {
  selectedKeys.value = [to.path];
});

// setTimeout(() => {
//   store.dispatch("user/getLoginUser", {
//     username: "小黄",
//     userRole: ACCESS_ENUM.ADMIN,
//   });
// }, 3000);

// 菜单点击事件
const doMenuClick = (e: string) => {
  router.push({
    path: e,
  });
};
console.log();
</script>

<style scoped>
#global-header {
  box-sizing: border-box;
  width: 100%;
  padding: 10px;
  display: flex;
  align-items: center;
  background-image: linear-gradient(to top, #df89b5 0%, #bfd9fe 100%);
}

.title-bar {
  display: flex;
  align-items: center;
  background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);
}

.title {
  font-size: 18px;
  font-weight: bold;
}

.title-bar .logo {
  height: 100px;
}

#global-header .menu-item {
  font-size: 18px;
  background-image: linear-gradient(to top, #d9afd9 0%, #97d9e1 100%);
}
.menu {
  background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);
}
</style>
