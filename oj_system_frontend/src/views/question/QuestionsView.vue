<template>
  <div id="questionsView">
    <h2>题目列表</h2>
    <a-space direction="vertical" size="large">
      <a-form :model="searchParams" layout="inline">
        <a-form-item field="title" label="标题">
          <a-input
            :v-model="searchParams.title"
            placeholder="请输入标题"
            style="min-width: 240px"
          />
        </a-form-item>
        <a-form-item field="tags" label="标签">
          <a-input-tag
            :v-model="searchParams.tags"
            placeholder="请输入标签"
            style="min-width: 280px"
          />
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="doSubmit">搜索</a-button>
        </a-form-item>
      </a-form>
    </a-space>
    <a-divider size="0" />
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
      <template #tags="{ record }">
        <a-tag v-for="(tag, index) of record.tags" :key="index" color="blue">
          {{ tag }}
        </a-tag>
      </template>
      <template #acceptedRate="{ record }">
        {{
          `${record.submitNum ? record.acceptedNum / record.submitNum : "0"} %
          (${record.acceptedNum}/${record.submitNum})`
        }}
      </template>
      <template #createTime="{ record }">
        {{ dayjs(`${record.createTime}`).format("YYYY/MM/DD") }}
      </template>
      <template #optional="{ record }">
        <a-space>
          <a-button type="primary" @click="toDoQuestion(record)">
            去做题
          </a-button>
        </a-space>
      </template>
    </a-table>
  </div>
</template>
<script setup lang="ts">
import { onMounted, ref, watchEffect } from "vue";
import {
  Question,
  QuestionControllerService,
  QuestionQueryRequest,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { questionsColumns } from "@/common/columns";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";

const router = useRouter();
const tableRef = ref();

/**
 * @description: 表头数据
 */
const columns = questionsColumns;

/**
 * @description: 题目列表
 */
const dataList = ref([]);
/**
 * @description: 题目总数
 */
const total = ref(0);
/**
 * @description: 搜索参数
 */
const searchParams = ref<QuestionQueryRequest>({
  title: "",
  tags: [],
  pageSize: 10,
  current: 1,
});

/**
 * @description: 获取题目列表
 */
const loadData = async () => {
  const res = await QuestionControllerService.listQuestionVoByPageUsingPost(
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

/**
 * 跳转到做题题目页面
 * @param question
 */
const toDoQuestion = (question: Question) => {
  router.push({
    path: `/view/question/${question.id}`,
  });
};

/**
 * 提交搜索表单
 */
const doSubmit = () => {
  // 这里需要重置搜索页号
  searchParams.value = {
    ...searchParams.value,
    current: 1,
  };
};
</script>
<style scoped>
#questionsView {
  max-width: 1280px;
  margin: 0 auto;
}
</style>
