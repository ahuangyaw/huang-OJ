package asia.huangzhitao.huangojbackendquestionservice.service;


import asia.huangzhitao.huangojbackendserviceclient.dto.questionsubmit.QuestionSubmitAddRequest;
import asia.huangzhitao.huangojbackendserviceclient.dto.questionsubmit.QuestionSubmitQueryRequest;
import asia.huangzhitao.huangojbackendserviceclient.entity.QuestionSubmit;
import asia.huangzhitao.huangojbackendserviceclient.entity.User;
import asia.huangzhitao.huangojbackendserviceclient.vo.QuestionSubmitVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hwang
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-03-27 17:54:47
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 点赞
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);


}
