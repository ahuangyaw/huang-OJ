package asia.huangzhitao.huangOJ.controller;

import asia.huangzhitao.huangOJ.annotation.AuthCheck;
import asia.huangzhitao.huangOJ.common.BaseResponse;
import asia.huangzhitao.huangOJ.common.ErrorCode;
import asia.huangzhitao.huangOJ.common.ResultUtils;
import asia.huangzhitao.huangOJ.constant.UserConstant;
import asia.huangzhitao.huangOJ.exception.BusinessException;
import asia.huangzhitao.huangOJ.model.dto.topicsubmit.TopicSubmitAddRequest;
import asia.huangzhitao.huangOJ.model.dto.topicsubmit.TopicSubmitQueryRequest;
import asia.huangzhitao.huangOJ.model.entity.Topic;
import asia.huangzhitao.huangOJ.model.entity.TopicSubmit;
import asia.huangzhitao.huangOJ.model.entity.User;
import asia.huangzhitao.huangOJ.model.vo.TopicSubmitVO;
import asia.huangzhitao.huangOJ.service.TopicSubmitService;
import asia.huangzhitao.huangOJ.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author hwang
 * 
 */
@RestController
@RequestMapping("/topic_submit")
@Slf4j
@Api(tags = "题目提交接口")
public class TopicSubmitController {

    @Resource
    private TopicSubmitService topicSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param topicSubmitAddRequest
     * @param request
     * @return 提交记录id
     */
    @PostMapping("/")
    @ApiOperation("提交题目")
    public BaseResponse<Long> doTopicSubmit(@RequestBody TopicSubmitAddRequest topicSubmitAddRequest,
            HttpServletRequest request) {
        if (topicSubmitAddRequest == null || topicSubmitAddRequest.getTopicId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能提交
        final User loginUser = userService.getLoginUser(request);
        long topicSubmitId = topicSubmitService.doTopicSubmit(topicSubmitAddRequest, loginUser);
        return ResultUtils.success(topicSubmitId);
    }

    /**
     * 分页获取题目提交列表（除管理员外，其他用户只能看见非答案的公开信息）
     *
     * @param topicSubmitQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @ApiOperation(value = "分页获取题目提交列表", notes = "分页获取题目提交列表")
    public BaseResponse<Page<TopicSubmitVO>> listTopicSubmitByPage(@RequestBody TopicSubmitQueryRequest topicSubmitQueryRequest,
                                                                   HttpServletRequest request) {
        long current = topicSubmitQueryRequest.getCurrent();
        long size = topicSubmitQueryRequest.getPageSize();
        // 从数据库查到原始的分页信息
        Page<TopicSubmit> topicSubmitPage = topicSubmitService.page(new Page<>(current, size),
                topicSubmitService.getQueryWrapper(topicSubmitQueryRequest));
        final User loginUser = userService.getLoginUser(request);
        // 返回脱敏信息
        return ResultUtils.success(topicSubmitService.getTopicSubmitVOPage(topicSubmitPage,loginUser));
    }
}
