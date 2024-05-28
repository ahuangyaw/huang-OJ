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
  background: linear-gradient(to right, #fff, #eee);
  margin-bottom: 16px;
  padding: 16px;
}

#BasicLayout .footer {
  background: #efefefef;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 16px;
  text-align: center;
}
</style>
