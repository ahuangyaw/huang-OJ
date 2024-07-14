<template>
  <div id="manageQuestionView">
    <h2>管理题目</h2>
    <a-table
      :ref="tableRef"
      :columns="columns"
      :data="dataList"
      :pagination="{
        showTotal: true,
        total,
        pageSize: searchParams.pageSize,
        current: searchParams.current,
      }"
      @page-change="onPageChange"
    >
      <template #optional="{ record }">
        <a-space>
          <a-button type="primary" @click="doUpdate(record)"> 修改</a-button>
          <a-button status="danger" @click="doDelete(record)">删除</a-button>
        </a-space>
      </template>
    </a-table>
  </div>
</template>
<script setup lang="ts">
import { onMounted, ref, watchEffect } from "vue";
import { Question, QuestionControllerService } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { manageColumns } from "@/common/columns";

const router = useRouter();
const tableRef = ref();

/**
 * @description: 表头数据
 */
const columns = manageColumns;

/**
 * @description: 题目列表
 */
const dataList = ref([]);
/**
 * @description: 题目总数
 */
const total = ref(0);
/**
 * @description: 获取题目列表
 */
const searchParams = ref({
  pageSize: 10,
  current: 1,
});
const loadData = async () => {
  const res = await QuestionControllerService.listQuestionByPageUsingPost(
    searchParams.value
  );
  if (res.code === 0) {
    dataList.value = res.data.records;
    total.value = res.data.total;
  } else {
    message.error("加载失败，" + res.message);
  }
};

onMounted(() => {
  loadData();
});

/**
 * 监听 searchParams 变量，改变时触发页面的重新加载
 */
watchEffect(() => {
  loadData();
});

const onPageChange = (page: number) => {
  searchParams.value = {
    ...searchParams.value,
    current: page,
  };
};

const doDelete = async (record: Question) => {
  const res = await QuestionControllerService.deleteQuestionUsingPost({
    id: record.id,
  });
  if (res.code === 0) {
    message.success("删除成功");
    loadData();
  } else {
    message.error("删除失败，" + res.message);
  }
};

const doUpdate = (record: Question) => {
  router.push({
    path: "/update/question",
    query: {
      id: record.id,
    },
  });
};
</script>
<style scoped>
#manageQuestionView {
}
</style>
