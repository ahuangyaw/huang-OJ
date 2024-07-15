package asia.huangzhitao.huangOJ.judge;

import asia.huangzhitao.huangOJ.judge.strategy.DefaultJudgeStrategy;
import asia.huangzhitao.huangOJ.judge.strategy.JavaLanguageJudgeStrategy;
import asia.huangzhitao.huangOJ.judge.strategy.JudgeContext;
import asia.huangzhitao.huangOJ.judge.strategy.JudgeStrategy;
import asia.huangzhitao.huangOJ.model.dto.questionsubmit.JudgeInfo;
import asia.huangzhitao.huangOJ.model.entity.QuestionSubmit;
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
