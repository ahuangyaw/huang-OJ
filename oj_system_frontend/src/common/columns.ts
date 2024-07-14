export const manageColumns = [
  {
    title: "id",
    dataIndex: "id",
  },
  {
    title: "标题",
    dataIndex: "title",
  },
  {
    title: "内容",
    dataIndex: "content",
  },
  {
    title: "标签",
    dataIndex: "tags",
  },
  {
    title: "答案",
    dataIndex: "answer",
  },
  {
    title: "提交数",
    dataIndex: "total",
  },
  {
    title: "通过数",
    dataIndex: "acceptedNum",
  },
  {
    title: "判题配置",
    dataIndex: "judgeCase",
  },
  {
    title: "判题配置",
    dataIndex: "judgeConfig",
  },
  {
    title: "用户id",
    dataIndex: "userId",
  },
  {
    title: "创建时间",
    dataIndex: "updateTime",
  },
  {
    title: "操作",
    slotName: "optional",
  },
];

export const addQuestion = {
  title: "",
  tags: [],
  answer: "",
  content: "",
  judgeConfig: [
    {
      memoryLimit: 1000,
      stackLimit: 1000,
      timeLimit: 1000,
    },
  ],
  judgeCase: [
    {
      input: "",
      output: "",
    },
  ],
};
