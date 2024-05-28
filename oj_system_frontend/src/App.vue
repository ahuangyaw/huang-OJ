<template>
  <div id="app">
    <template v-if="route.path.startsWith('/user')">
      <router-view />
    </template>
    <template v-else>
      <BasicLayout />
    </template>
  </div>
</template>

<script setup lang="ts">
import BasicLayout from "@/components/layout/BasicLayout.vue";
import { onMounted } from "vue";
import { useRoute } from "vue-router";

/**
 * 全局初始化函数，有全局单次调用的代码，都可以写到这里
 */
const doInit = () => {
  console.log(
    " .....................阿弥陀佛.....................\n" +
      "                       _oo0oo_                      \n" +
      "                      o8888888o                     \n" +
      '                      88" . "88                     \n' +
      "                      (| -_- |)                     \n" +
      "                      0\\  =  /0                     \n" +
      "                   ___/‘---’\\___                   \n" +
      "                  .' \\|       |/ '.                 \n" +
      "                 / \\\\|||  :  |||// \\                \n" +
      "                / _||||| -卍-|||||_ \\               \n" +
      "               |   | \\\\\\  -  /// |   |              \n" +
      "               | \\_|  ''\\---/''  |_/ |              \n" +
      "               \\  .-\\__  '-'  ___/-. /              \n" +
      "             ___'. .'  /--.--\\  '. .'___            \n" +
      '         ."" ‘<  ‘.___\\_<|>_/___.’>’ "".          \n' +
      "       | | :  ‘- \\‘.;‘\\ _ /’;.’/ - ’ : | |        \n" +
      "         \\  \\ ‘_.   \\_ __\\ /__ _/   .-’ /  /        \n" +
      "    =====‘-.____‘.___ \\_____/___.-’___.-’=====     \n" +
      "                       ‘=---=’                      \n" +
      "         佛曰:\n" +
      "             写字楼里写字间，写字间里程序员；\n" +
      "             程序人员写程序，又拿程序换酒钱。\n" +
      "             酒醒只在网上坐，酒醉还来网下眠；\n" +
      "             酒醉酒醒日复日，网上网下年复年。\n" +
      "             但愿老死电脑间，不愿鞠躬老板前；\n" +
      "             奔驰宝马贵者趣，公交自行程序员。\n" +
      "             别人笑我忒疯癫，我笑自己命太贱；\n" +
      "             不见满街漂亮妹，哪个归得程序员？\n" +
      "..................佛祖保佑，永无BUG.................."
  );
};
const route = useRoute();
onMounted(() => {
  doInit();
});
</script>
<style>
#app {
  margin: 0;
}
</style>
