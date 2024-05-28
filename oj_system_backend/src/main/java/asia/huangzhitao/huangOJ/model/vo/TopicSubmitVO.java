package asia.huangzhitao.huangOJ.model.vo;

import asia.huangzhitao.huangOJ.common.PageRequest;
import asia.huangzhitao.huangOJ.model.dto.topic.JudgeConfig;
import asia.huangzhitao.huangOJ.model.dto.topicsubmit.JudgeInfo;
import asia.huangzhitao.huangOJ.model.entity.Topic;
import asia.huangzhitao.huangOJ.model.entity.TopicSubmit;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目提交脱敏信息
 * @TableName topic
 */
@TableName(value ="topicSubmitVO")
@Data

public class TopicSubmitVO  implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）
     */
    private Integer status;

    /**
     * 题目 id
     */
    private Long topicId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 提交者信息
     */
    private UserVO userVO;

    /**
     * 提交题目信息
     */
    private TopicVO topicVO;

    /**
     * 包装类转对象
     *
     * @param topicSubmitVO
     * @return
     */
    public static TopicSubmit voToObj(TopicSubmitVO topicSubmitVO) {
        if (topicSubmitVO == null) {
            return null;
        }
        TopicSubmit topicSubmit = new TopicSubmit();
        BeanUtils.copyProperties(topicSubmitVO, topicSubmit);
        JudgeInfo judgeInfoObj = topicSubmitVO.getJudgeInfo();
        if (judgeInfoObj != null){
            topicSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfoObj));
        }
        return topicSubmit;
    }

    /**
     * 对象转包装类
     *
     * @param topicSubmit
     * @return
     */
    public static TopicSubmitVO objToVo(TopicSubmit topicSubmit) {
        if (topicSubmit == null) {
            return null;
        }
        TopicSubmitVO topicSubmitVO = new TopicSubmitVO();
        BeanUtils.copyProperties(topicSubmit, topicSubmitVO);
        String judgeInfoOStr = topicSubmit.getJudgeInfo();
        topicSubmitVO.setJudgeInfo(JSONUtil.toBean(judgeInfoOStr, JudgeInfo.class));
        return topicSubmitVO;
    }

    private static final long serialVersionUID = 1L;
}