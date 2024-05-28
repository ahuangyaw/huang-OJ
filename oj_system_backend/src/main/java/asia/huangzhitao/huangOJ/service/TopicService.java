package asia.huangzhitao.huangOJ.service;

import asia.huangzhitao.huangOJ.model.dto.topic.TopicQueryRequest;
import asia.huangzhitao.huangOJ.model.entity.Topic;
import asia.huangzhitao.huangOJ.model.vo.TopicVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import asia.huangzhitao.huangOJ.model.entity.Topic;

import javax.servlet.http.HttpServletRequest;

/**
* @author hwang
* description 针对表【topic(题目)】的数据库操作Service
*/
public interface TopicService extends IService<Topic> {

    /**
     * 校验
     *
     * @param topic
     * @param add
     */
    void validTopic(Topic topic, boolean add);

    /**
     * 获取查询条件
     *
     * @param topicQueryRequest
     * @return
     */
    QueryWrapper<Topic> getQueryWrapper(TopicQueryRequest topicQueryRequest);

    /**
     * 获取题目封装
     *
     * @param topic
     * @param request
     * @return
     */
    TopicVO getTopicVO(Topic topic, HttpServletRequest request);

    /**
     * 分页获取题目封装
     *
     * @param topicPage
     * @param request
     * @return
     */
    Page<TopicVO> getTopicVOPage(Page<Topic> topicPage, HttpServletRequest request);

}
