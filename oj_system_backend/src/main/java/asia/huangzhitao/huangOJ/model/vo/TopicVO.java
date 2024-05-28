package asia.huangzhitao.huangOJ.model.vo;

import asia.huangzhitao.huangOJ.model.dto.topic.JudgeConfig;
import asia.huangzhitao.huangOJ.model.entity.Topic;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目脱敏信息
 * @TableName topic
 */
@TableName(value ="topic")
@Data
public class TopicVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 判题配置
     */
    private JudgeConfig judgeConfig;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

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
     *  创建题目人的信息
     */
    private UserVO userVO;

    /**
     * 包装类转对象
     *
     * @param topicVO
     * @return
     */
    public static Topic voToObj(TopicVO topicVO) {
        if (topicVO == null) {
            return null;
        }
        Topic topic = new Topic();
        BeanUtils.copyProperties(topicVO, topic);
        List<String> tagList = topicVO.getTags();
        if (tagList != null){
            topic.setTags(JSONUtil.toJsonStr(tagList));
        }
        JudgeConfig judgeConfig = topicVO.getJudgeConfig();
        if (judgeConfig != null){
            topic.setJudgeConfig(JSONUtil.toJsonStr(judgeConfig));
        }
        return topic;
    }

    /**
     * 对象转包装类
     *
     * @param topic
     * @return
     */
    public static TopicVO objToVo(Topic topic) {
        if (topic == null) {
            return null;
        }
        TopicVO topicVO = new TopicVO();
        BeanUtils.copyProperties(topic, topicVO);
        List<String> tagList = JSONUtil.toList(topic.getTags(), String.class);
        topicVO.setTags(tagList);
        String judgeConfigStr = topic.getJudgeConfig();
        topicVO.setJudgeConfig(JSONUtil.toBean(judgeConfigStr, JudgeConfig.class));
        return topicVO;
    }

    private static final long serialVersionUID = 1L;
}