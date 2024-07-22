package asia.huangzhitao.huangojbackendjudgeservice.judge;


import asia.huangzhitao.huangojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import asia.huangzhitao.huangojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import asia.huangzhitao.huangojbackendjudgeservice.judge.strategy.JudgeContext;
import asia.huangzhitao.huangojbackendjudgeservice.judge.strategy.JudgeStrategy;
import asia.huangzhitao.huangojbackendserviceclient.codesandbox.JudgeInfo;
import asia.huangzhitao.huangojbackendserviceclient.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
