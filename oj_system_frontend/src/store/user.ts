import { StoreOptions } from "vuex";
import ACCESS_ENUM from "@/access/ACCESS_ENUM";
import { UserControllerService } from "../../generated";

export default {
  namespaced: true,
  // 存储的状态信息
  state: () => ({
    loginUser: {
      userName: "未登录",
    },
  }),
  // 执行异步操作，并且触发 mutation 的更改（actions 调用 mutation）
  actions: {
    async getLoginUser({ commit, state }) {
      // 从远程获取请求登录信息
      const res = await UserControllerService.getLoginUserUsingGet();
      if (res.code === 0) {
        commit("updateUser", res.data);
        console.log(res.data);
      } else {
        commit("updateUser", {
          ...state.loginUser,
          userRole: ACCESS_ENUM.NOT_LOGIN,
        });
      }
    },
  },
  // 定义了对变量进行增删改（更新）的方法
  mutations: {
    updateUser(state, payload) {
      state.loginUser = payload;
    },
  },
} as StoreOptions<any>;
