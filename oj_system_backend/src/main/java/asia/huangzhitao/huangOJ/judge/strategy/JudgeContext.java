package asia.huangzhitao.huangOJ.judge.strategy;

import asia.huangzhitao.huangOJ.model.dto.question.JudgeCase;
import asia.huangzhitao.huangOJ.model.dto.questionsubmit.JudgeInfo;
import asia.huangzhitao.huangOJ.model.entity.Question;
import asia.huangzhitao.huangOJ.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略中传递的参数）
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
