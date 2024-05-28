<template>
  <div id="example">
    <CodeEditor :value="codeValue" :handle-change="onCodeChange" />
    <MdEditor :value="mdValue" :handle-change="onMdChange" />
  </div>
</template>

<script setup lang="ts">
import MdEditor from "@/components/MdEditor.vue";
import { ref } from "vue";
import CodeEditor from "@/components/CodeEditor.vue";

//用户输入的值
const codeValue = ref();
const mdValue = ref();

const onCodeChange = (val: string) => {
  codeValue.value = val;
};
const onMdChange = (val: string) => {
  mdValue.value = val;
};
</script>
