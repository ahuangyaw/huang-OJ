package asia.huangzhitao.huangOJ;

import asia.huangzhitao.huangOJ.common.BaseResponse;
import asia.huangzhitao.huangOJ.config.WxOpenConfig;

import javax.annotation.Resource;

import asia.huangzhitao.huangOJ.judge.codesandbox.CodeSandbox;
import asia.huangzhitao.huangOJ.judge.codesandbox.CodeSandboxFactory;
import asia.huangzhitao.huangOJ.judge.codesandbox.CodeSandboxProxy;
import asia.huangzhitao.huangOJ.judge.codesandbox.impl.ExampleCodeSandbox;
import asia.huangzhitao.huangOJ.judge.codesandbox.model.ExecuteCodeRequest;
import asia.huangzhitao.huangOJ.judge.codesandbox.model.ExecuteCodeResponse;
import asia.huangzhitao.huangOJ.model.dto.question.*;
import asia.huangzhitao.huangOJ.controller.QuestionController;
import asia.huangzhitao.huangOJ.model.dto.question.QuestionAddRequest;
import asia.huangzhitao.huangOJ.model.entity.Question;
import asia.huangzhitao.huangOJ.model.entity.QuestionSubmit;
import asia.huangzhitao.huangOJ.model.enums.QuestionSubmitLanguageEnum;
import asia.huangzhitao.huangOJ.model.vo.LoginUserVO;
import asia.huangzhitao.huangOJ.service.QuestionService;
import asia.huangzhitao.huangOJ.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 主类测试
 *
 * @author hwang
 */
@SpringBootTest
class MainApplicationTests {

    @Resource
    private WxOpenConfig wxOpenConfig;

    @Resource
    private QuestionController questionService;

    @Resource
    private UserService userService;
    @Autowired
    private MainApplication mainApplication;

    @Value("${codesandbox.type}")
    private String type;

    @Test
    void contextLoads() {

    }

    @Test
    void executeCode() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        String code = "public class HelloWorld ";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("123", "456");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }

    @Test
    void executeCodeByProxy() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = "public class HelloWorld ";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("123", "456");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }

}
