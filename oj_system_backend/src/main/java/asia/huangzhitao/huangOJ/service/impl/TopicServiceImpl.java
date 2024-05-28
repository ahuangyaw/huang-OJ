package asia.huangzhitao.huangOJ.service.impl;

import asia.huangzhitao.huangOJ.common.ErrorCode;
import asia.huangzhitao.huangOJ.constant.CommonConstant;
import asia.huangzhitao.huangOJ.exception.BusinessException;
import asia.huangzhitao.huangOJ.exception.ThrowUtils;
import asia.huangzhitao.huangOJ.mapper.TopicMapper;
import asia.huangzhitao.huangOJ.model.dto.topic.TopicQueryRequest;
import asia.huangzhitao.huangOJ.model.entity.Topic;
import asia.huangzhitao.huangOJ.model.entity.User;
import asia.huangzhitao.huangOJ.model.vo.TopicVO;
import asia.huangzhitao.huangOJ.model.vo.UserVO;
import asia.huangzhitao.huangOJ.service.TopicService;
import asia.huangzhitao.huangOJ.service.UserService;
import asia.huangzhitao.huangOJ.utils.SqlUtils;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author hwang
* @description 针对表【topic(题目)】的数据库操作Service实现
* @createDate 2024-03-27 17:51:12
*/
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic>
    implements TopicService{

    @Resource
    private UserService userService;

    /**
     * 校验题目是否合法
     * @param topic
     * @param add
     */
    @Override
    public void validTopic(Topic topic, boolean add) {
        if (topic == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String title = topic.getTitle();
        String content = topic.getContent();
        String tags = topic.getTags();
        String answer = topic.getAnswer();
        String judgeCase = topic.getJudgeCase();
        String judgeConfig = topic.getJudgeConfig();

        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(title, content, tags), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(title) && title.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长");
        }
        if (StringUtils.isNotBlank(content) && content.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
        if (StringUtils.isNotBlank(answer) && answer.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "答案过长");
        }
        if (StringUtils.isNotBlank(judgeCase) && judgeCase.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "判题用例过长");
        }
        if (StringUtils.isNotBlank(judgeConfig) && judgeConfig.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "判题配置过长");
        }

    }

    /**
     * 获取查询包装类（用户根据那些字段，根据前端传来的请求对象，获得mybatis框架支持的查询QueryWrapper类）
     *
     * @param topicQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<Topic> getQueryWrapper(TopicQueryRequest topicQueryRequest) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        if (topicQueryRequest == null) {
            return queryWrapper;
        }
        Long id = topicQueryRequest.getId();
        String title = topicQueryRequest.getTitle();
        String content = topicQueryRequest.getContent();
        List<String> tags = topicQueryRequest.getTags();
        String answer = topicQueryRequest.getAnswer();
        Long userId = topicQueryRequest.getUserId();
        String sortField = topicQueryRequest.getSortField();
        String sortOrder = topicQueryRequest.getSortOrder();
        
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
        queryWrapper.like(StringUtils.isNotBlank(content), "content", content);
        queryWrapper.like(StringUtils.isNotBlank(answer), "answer", answer);
        if (CollUtil.isNotEmpty(tags)) {
            for (String tag : tags) {
                queryWrapper.like("tags", "\"" + tag + "\"");
            }
        }
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public TopicVO getTopicVO(Topic topic, HttpServletRequest request) {
        TopicVO topicVO = TopicVO.objToVo(topic);
        long topicId = topic.getId();
        // 1. 关联查询用户信息
        Long userId = topic.getUserId();
        User user = null;
        if (userId != null && userId > 0) {
            user = userService.getById(userId);
        }
        UserVO userVO = userService.getUserVO(user);
        topicVO.setUserVO(userVO);
        return topicVO;
    }

    @Override
    public Page<TopicVO> getTopicVOPage(Page<Topic> topicPage, HttpServletRequest request) {
        List<Topic> topicList = topicPage.getRecords();
        Page<TopicVO> topicVOPage = new Page<>(topicPage.getCurrent(), topicPage.getSize(), topicPage.getTotal());
        if (CollUtil.isEmpty(topicList)) {
            return topicVOPage;
        }
        // 1. 关联查询用户信息
        Set<Long> userIdSet = topicList.stream().map(Topic::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap = userService.listByIds(userIdSet).stream()
                .collect(Collectors.groupingBy(User::getId));

        // 填充信息
        List<TopicVO> topicVOList = topicList.stream().map(topic -> {
            TopicVO topicVO = TopicVO.objToVo(topic);
            Long userId = topic.getUserId();
            User user = null;
            if (userIdUserListMap.containsKey(userId)) {
                user = userIdUserListMap.get(userId).get(0);
            }
            topicVO.setUserVO(userService.getUserVO(user));
            return topicVO;
        }).collect(Collectors.toList());
        topicVOPage.setRecords(topicVOList);
        return topicVOPage;
    }

}




