<template>
  <div id="UserLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <a-space>
          <img src="@/assets/fu.png" class="logo" alt="logo" />
          <div>huangOJ</div>
        </a-space>
      </a-layout-header>
      <a-layout>
        <a-layout-content class="content">
          <router-view></router-view>
        </a-layout-content>
      </a-layout>
      <a-layout-footer class="footer">Footer</a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
#UserLayout :deep(.arco-layout-content) {
  background-color: rgb(var(--arcoblue-6));
}

#UserLayout {
  display: block;
  text-align: center;
  background: url("@/assets/fuxuan.jpg") no-repeat;
}

#UserLayout .content {
  background: none;
  margin-bottom: 16px;
  padding: 16px;
}

#UserLayout .footer {
  background: #efefefef;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 16px;
  text-align: center;
}

#UserLayout .logo {
  height: 100px;
  margin: 16px;
}
</style>
