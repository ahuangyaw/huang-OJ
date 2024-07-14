package asia.huangzhitao.huangOJ;

import asia.huangzhitao.huangOJ.common.BaseResponse;
import asia.huangzhitao.huangOJ.config.WxOpenConfig;
import javax.annotation.Resource;
import asia.huangzhitao.huangOJ.model.dto.question.*;
import asia.huangzhitao.huangOJ.controller.QuestionController;
import asia.huangzhitao.huangOJ.model.dto.question.QuestionAddRequest;
import asia.huangzhitao.huangOJ.model.entity.Question;
import asia.huangzhitao.huangOJ.model.vo.LoginUserVO;
import asia.huangzhitao.huangOJ.service.QuestionService;
import asia.huangzhitao.huangOJ.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 主类测试
 *
 * @author hwang
 * 
 */
@SpringBootTest
class MainApplicationTests {

    @Resource
    private WxOpenConfig wxOpenConfig;

    @Resource
    private QuestionController questionService;

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
    }

}
