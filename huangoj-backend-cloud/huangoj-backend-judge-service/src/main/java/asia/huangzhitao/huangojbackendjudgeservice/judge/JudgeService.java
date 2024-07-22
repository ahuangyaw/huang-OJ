package asia.huangzhitao.huangojbackendjudgeservice.judge;


import asia.huangzhitao.huangojbackendserviceclient.entity.QuestionSubmit;

/**
 * 判题服务
 */
public interface JudgeService {

    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
