package asia.huangzhitao.huangojbackendjudgeservice.judge.codesandbox.impl;


import asia.huangzhitao.huangojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import asia.huangzhitao.huangojbackendserviceclient.codesandbox.ExecuteCodeRequest;
import asia.huangzhitao.huangojbackendserviceclient.codesandbox.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（调用网上现成的代码沙箱）
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
