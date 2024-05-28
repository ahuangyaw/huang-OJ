package asia.huangzhitao.huangOJ.controller;

import asia.huangzhitao.huangOJ.annotation.AuthCheck;
import asia.huangzhitao.huangOJ.common.BaseResponse;
import asia.huangzhitao.huangOJ.common.DeleteRequest;
import asia.huangzhitao.huangOJ.common.ErrorCode;
import asia.huangzhitao.huangOJ.common.ResultUtils;
import asia.huangzhitao.huangOJ.constant.UserConstant;
import asia.huangzhitao.huangOJ.exception.BusinessException;
import asia.huangzhitao.huangOJ.exception.ThrowUtils;
import asia.huangzhitao.huangOJ.model.dto.topic.*;
import asia.huangzhitao.huangOJ.model.dto.topicsubmit.TopicSubmitQueryRequest;
import asia.huangzhitao.huangOJ.model.entity.Topic;
import asia.huangzhitao.huangOJ.model.entity.User;
import asia.huangzhitao.huangOJ.model.vo.TopicVO;
import asia.huangzhitao.huangOJ.service.TopicService;
import asia.huangzhitao.huangOJ.service.UserService;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 题目接口
 *
 * @author hwang
 * 
 */
@RestController
@RequestMapping("/topic")
@Slf4j
public class TopicController {

    @Resource
    private TopicService topicService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param topicAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addTopic(@RequestBody TopicAddRequest topicAddRequest, HttpServletRequest request) {
        if (topicAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Topic topic = new Topic();
        BeanUtils.copyProperties(topicAddRequest, topic);
        List<String> tags = topicAddRequest.getTags();
        if (tags != null) {
            topic.setTags(JSONUtil.toJsonStr(tags));
        }
        List<JudgeCase> judgeCase = topicAddRequest.getJudgeCase();
        if (judgeCase != null){
            topic.setJudgeCase(JSONUtil.toJsonStr(judgeCase));
        }
        JudgeConfig judgeConfig = topicAddRequest.getJudgeConfig();
        if (judgeConfig != null){
            topic.setJudgeCase(JSONUtil.toJsonStr(judgeConfig));
        }
        topicService.validTopic(topic, true);
        User loginUser = userService.getLoginUser(request);
        topic.setUserId(loginUser.getId());
        topic.setFavourNum(0);
        topic.setThumbNum(0);
        boolean result = topicService.save(topic);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newTopicId = topic.getId();
        return ResultUtils.success(newTopicId);
    }

    /**
     * 删除题目
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTopic(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Topic oldTopic = topicService.getById(id);
        ThrowUtils.throwIf(oldTopic == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldTopic.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = topicService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新题目（仅管理员）
     *
     * @param topicUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateTopic(@RequestBody TopicUpdateRequest topicUpdateRequest) {
        if (topicUpdateRequest == null || topicUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Topic topic = new Topic();
        BeanUtils.copyProperties(topicUpdateRequest, topic);
        List<String> tags = topicUpdateRequest.getTags();
        if (tags != null) {
            topic.setTags(JSONUtil.toJsonStr(tags));
        }
        List<JudgeCase> judgeCase = topicUpdateRequest.getJudgeCase();
        if (judgeCase != null){
            topic.setJudgeCase(JSONUtil.toJsonStr(judgeCase));
        }
        JudgeConfig judgeConfig = topicUpdateRequest.getJudgeConfig();
        if (judgeConfig != null){
            topic.setJudgeCase(JSONUtil.toJsonStr(judgeConfig));
        }
        // 参数校验
        topicService.validTopic(topic, false);
        long id = topicUpdateRequest.getId();
        // 判断是否存在
        Topic oldTopic = topicService.getById(id);
        ThrowUtils.throwIf(oldTopic == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = topicService.updateById(topic);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取题目
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<TopicVO> getTopicVOById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Topic topic = topicService.getById(id);
        if (topic == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(topicService.getTopicVO(topic, request));
    }

    /**
     * 分页获取列表（仅管理员）
     *
     * @param topicQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Topic>> listTopicByPage(@RequestBody TopicQueryRequest topicQueryRequest) {
        long current = topicQueryRequest.getCurrent();
        long size = topicQueryRequest.getPageSize();
        Page<Topic> topicPage = topicService.page(new Page<>(current, size),
                topicService.getQueryWrapper(topicQueryRequest));
        return ResultUtils.success(topicPage);
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param topicQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<TopicVO>> listTopicVOByPage(@RequestBody TopicQueryRequest topicQueryRequest,
            HttpServletRequest request) {
        long current = topicQueryRequest.getCurrent();
        long size = topicQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Topic> topicPage = topicService.page(new Page<>(current, size),
                topicService.getQueryWrapper(topicQueryRequest));
        return ResultUtils.success(topicService.getTopicVOPage(topicPage, request));
    }

    /**
     * 分页获取当前用户创建的资源列表
     *
     * @param topicQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/my/list/page/vo")
    public BaseResponse<Page<TopicVO>> listMyTopicVOByPage(@RequestBody TopicQueryRequest topicQueryRequest,
            HttpServletRequest request) {
        if (topicQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        topicQueryRequest.setUserId(loginUser.getId());
        long current = topicQueryRequest.getCurrent();
        long size = topicQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Topic> topicPage = topicService.page(new Page<>(current, size),
                topicService.getQueryWrapper(topicQueryRequest));
        return ResultUtils.success(topicService.getTopicVOPage(topicPage, request));
    }

    /**
     * 编辑（用户）
     *
     * @param topicEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editTopic(@RequestBody TopicEditRequest topicEditRequest, HttpServletRequest request) {
        if (topicEditRequest == null || topicEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Topic topic = new Topic();
        BeanUtils.copyProperties(topicEditRequest, topic);
        List<String> tags = topicEditRequest.getTags();
        if (tags != null) {
            topic.setTags(JSONUtil.toJsonStr(tags));
        }
        List<JudgeCase> judgeCase = topicEditRequest.getJudgeCase();
        if (judgeCase != null){
            topic.setJudgeCase(JSONUtil.toJsonStr(judgeCase));
        }
        JudgeConfig judgeConfig = topicEditRequest.getJudgeConfig();
        if (judgeConfig != null){
            topic.setJudgeCase(JSONUtil.toJsonStr(judgeConfig));
        }
        // 参数校验
        topicService.validTopic(topic, false);
        User loginUser = userService.getLoginUser(request);
        long id = topicEditRequest.getId();
        // 判断是否存在
        Topic oldTopic = topicService.getById(id);
        ThrowUtils.throwIf(oldTopic == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可编辑
        if (!oldTopic.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = topicService.updateById(topic);
        return ResultUtils.success(result);
    }




}
