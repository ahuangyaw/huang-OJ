<template>
  <div id="BasicLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <GlobalHeader></GlobalHeader>
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

<script setup lang="ts">
import GlobalHeader from "@/components/GlobalHeader.vue";
</script>

<style scoped>
#BasicLayout :deep(.arco-layout-content) {
  background-color: rgb(var(--arcoblue-6));
}

#BasicLayout {
  display: block;
  box-shadow: #eee 1px 1px 5px;
}

#BasicLayout .content {
  background: url("https://gw.alipayobjects.com/zos/rmsportal/FfdJeJRQWjEeGTpqgBKj.png")
    0 0 / 100% 100%;
  margin-bottom: 16px;
  padding: 16px;
}

#BasicLayout .footer {
  background-color: #dcd9d4;
  background-image: linear-gradient(
      to bottom,
      rgba(255, 255, 255, 0.5) 0%,
      rgba(0, 0, 0, 0.5) 100%
    ),
    radial-gradient(
      at 50% 0%,
      rgba(255, 255, 255, 0.1) 0%,
      rgba(0, 0, 0, 0.5) 50%
    );
  background-blend-mode: soft-light, screen;
  position: relative;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 16px;
  text-align: center;
}
</style>
