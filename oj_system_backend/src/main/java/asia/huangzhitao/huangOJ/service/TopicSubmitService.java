package asia.huangzhitao.huangOJ.service;

import asia.huangzhitao.huangOJ.model.dto.topic.TopicQueryRequest;
import asia.huangzhitao.huangOJ.model.dto.topicsubmit.TopicSubmitAddRequest;
import asia.huangzhitao.huangOJ.model.dto.topicsubmit.TopicSubmitQueryRequest;
import asia.huangzhitao.huangOJ.model.entity.Topic;
import asia.huangzhitao.huangOJ.model.entity.TopicSubmit;
import asia.huangzhitao.huangOJ.model.entity.User;
import asia.huangzhitao.huangOJ.model.vo.TopicSubmitVO;
import asia.huangzhitao.huangOJ.model.vo.TopicVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author hwang
* @description 针对表【topic_submit(题目提交)】的数据库操作Service
* @createDate 2024-03-27 17:54:47
*/
public interface TopicSubmitService extends IService<TopicSubmit> {
    /**
     * 点赞
     *
     * @param topicSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doTopicSubmit(TopicSubmitAddRequest topicSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param topicSubmitQueryRequest
     * @return
     */
    QueryWrapper<TopicSubmit> getQueryWrapper(TopicSubmitQueryRequest topicSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param topicSubmit
     * @param loginUser
     * @return
     */
    TopicSubmitVO getTopicSubmitVO(TopicSubmit topicSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param topicSubmitPage
     * @param loginUser
     * @return
     */
    Page<TopicSubmitVO> getTopicSubmitVOPage(Page<TopicSubmit> topicSubmitPage, User loginUser);


}
