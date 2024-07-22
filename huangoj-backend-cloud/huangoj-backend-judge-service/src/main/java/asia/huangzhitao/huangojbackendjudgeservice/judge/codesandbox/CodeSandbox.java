package asia.huangzhitao.huangojbackendjudgeservice.judge.codesandbox;


import asia.huangzhitao.huangojbackendserviceclient.codesandbox.ExecuteCodeRequest;
import asia.huangzhitao.huangojbackendserviceclient.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
