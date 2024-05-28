package asia.huangzhitao.huangOJ.service.impl;

import asia.huangzhitao.huangOJ.common.ErrorCode;
import asia.huangzhitao.huangOJ.constant.CommonConstant;
import asia.huangzhitao.huangOJ.exception.BusinessException;
import asia.huangzhitao.huangOJ.mapper.TopicSubmitMapper;
import asia.huangzhitao.huangOJ.model.dto.topic.TopicQueryRequest;
import asia.huangzhitao.huangOJ.model.dto.topicsubmit.TopicSubmitAddRequest;
import asia.huangzhitao.huangOJ.model.dto.topicsubmit.TopicSubmitQueryRequest;
import asia.huangzhitao.huangOJ.model.entity.Topic;
import asia.huangzhitao.huangOJ.model.entity.TopicSubmit;
import asia.huangzhitao.huangOJ.model.entity.User;
import asia.huangzhitao.huangOJ.model.enums.TopicSubmitEnum;
import asia.huangzhitao.huangOJ.model.enums.TopicSubmitLanguageEnum;
import asia.huangzhitao.huangOJ.model.vo.TopicSubmitVO;
import asia.huangzhitao.huangOJ.model.vo.TopicVO;
import asia.huangzhitao.huangOJ.model.vo.UserVO;
import asia.huangzhitao.huangOJ.service.TopicService;
import asia.huangzhitao.huangOJ.service.TopicSubmitService;
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
import java.util.stream.Stream;

/**
* @author hwang
* @description 针对表【topic_submit(提交题目)】的数据库操作Service实现
* @createDate 2024-03-27 17:54:46
*/
@Service
public class TopicSubmitServiceImpl extends ServiceImpl<TopicSubmitMapper, TopicSubmit>
    implements TopicSubmitService{
    @Resource
    private TopicService topicService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param topicSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doTopicSubmit(TopicSubmitAddRequest topicSubmitAddRequest, User loginUser) {
        // 判断编程语言是否合法
        String language = topicSubmitAddRequest.getLanguage();
        TopicSubmitLanguageEnum
            languageEnum = TopicSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言不合法");
        }
        long topicId = topicSubmitAddRequest.getTopicId();
        // 判断实体是否存在，根据类别获取实体
        Topic topic = topicService.getById(topicId);
        if (topic == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已提交题目
        long userId = loginUser.getId();
        // 每个用户串行提交题目
        TopicSubmit topicSubmit = new TopicSubmit();
        topicSubmit.setUserId(userId);
        topicSubmit.setTopicId(topicId);
        topicSubmit.setCode(topicSubmitAddRequest.getCode());
        topicSubmit.setLanguage(language);
        // 设置初始状态
        topicSubmit.setStatus(TopicSubmitEnum.WAITING.getValue());
        topicSubmit.setJudgeInfo("{}");
        boolean save = this.save(topicSubmit);
        if (!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"提交题目失败");
        }
        return topicSubmit.getId();

    }

    /**
     * 获取查询包装类（用户根据那些字段，根据前端传来的请求对象，获得mybatis框架支持的查询QueryWrapper类）
     *
     * @param topicSubmitQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<TopicSubmit> getQueryWrapper(TopicSubmitQueryRequest topicSubmitQueryRequest) {
        QueryWrapper<TopicSubmit> queryWrapper = new QueryWrapper<>();
        if (topicSubmitQueryRequest == null) {
            return queryWrapper;
        }
        String language = topicSubmitQueryRequest.getLanguage();
        Integer status = topicSubmitQueryRequest.getStatus();
        Long topicId = topicSubmitQueryRequest.getTopicId();
        Long userId = topicSubmitQueryRequest.getUserId();
        String sortField = topicSubmitQueryRequest.getSortField();
        String sortOrder = topicSubmitQueryRequest.getSortOrder();

        // 设置查询条件
        queryWrapper.eq(StringUtils.isNotBlank(language), "language", language);
        queryWrapper.eq(ObjectUtils.isNotEmpty(topicId), "topicId", topicId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(TopicSubmitEnum.getEnumByValue(status) != null, "status", status);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    /**
     *  获取单条信息
     * @param topicSubmit
     * @param loginUser
     * @return
     */
    @Override
    public TopicSubmitVO getTopicSubmitVO(TopicSubmit topicSubmit, User loginUser) {
        TopicSubmitVO topicSubmitVO = TopicSubmitVO.objToVo(topicSubmit);
        long userid = loginUser.getId();
        if (userid != topicSubmit.getUserId() && userService.isAdmin(loginUser)){
            topicSubmitVO.setCode(null);
        }
        return topicSubmitVO;
    }

    /**
     * 获取分页信息
     * @param topicSubmitPage
     * @param loginUser
     * @return
     */
    @Override
    public Page<TopicSubmitVO> getTopicSubmitVOPage(Page<TopicSubmit> topicSubmitPage, User loginUser) {
        List<TopicSubmit> topicSubmitList = topicSubmitPage.getRecords();
        Page<TopicSubmitVO> topicSubmitVOPage = new Page<>(topicSubmitPage.getCurrent(), topicSubmitPage.getSize(), topicSubmitPage.getTotal());
        if (CollUtil.isEmpty(topicSubmitList)) {
            return topicSubmitVOPage;
        }
        // 1. 关联查询用户信息
//        Set<Long> userIdSet = topicList.stream().map(TopicSubmit::getUserId).collect(Collectors.toSet());
//        Map<Long, List<User>> userIdUserListMap = userService.listByIds(userIdSet).stream()
//                .collect(Collectors.groupingBy(User::getId));
//
//        // 填充信息
//        List<TopicSubmitVO> topicVOList = topicList.stream().map(topicSubmit -> {
//            TopicSubmitVO topicSubmitVO = TopicSubmitVO.objToVo(topicSubmit);
//            Long userId = topicSubmitVO.getUserId();
//            User user = null;
//            if (userIdUserListMap.containsKey(userId)) {
//                user = userIdUserListMap.get(userId).get(0);
//            }
//            topicSubmitVO.setUserVO(userService.getUserVO(user));
//            return topicSubmitVO;
//        }).collect(Collectors.toList());
        List<TopicSubmitVO> topicSubmitVOList = topicSubmitList.stream()
                .map(topicSubmit -> getTopicSubmitVO(topicSubmit, loginUser))
                .collect(Collectors.toList());
        topicSubmitVOPage.setRecords(topicSubmitVOList);
        return topicSubmitVOPage;
    }


}




