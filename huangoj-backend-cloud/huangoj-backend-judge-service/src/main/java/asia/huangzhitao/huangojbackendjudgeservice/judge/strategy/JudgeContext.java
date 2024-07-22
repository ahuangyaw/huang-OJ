package asia.huangzhitao.huangojbackendjudgeservice.judge.strategy;


import asia.huangzhitao.huangojbackendserviceclient.codesandbox.JudgeInfo;
import asia.huangzhitao.huangojbackendserviceclient.dto.question.JudgeCase;
import asia.huangzhitao.huangojbackendserviceclient.entity.Question;
import asia.huangzhitao.huangojbackendserviceclient.entity.QuestionSubmit;
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
