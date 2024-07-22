package asia.huangzhitao.huangojbackendserviceclient.dto.question;

import lombok.Data;

/**
 * @author : hwang
 * description : 题目配置
 */

@Data
public class JudgeConfig {

    /**
     * 时间限制
     * 单位：ms
     */
    private Long timeLimit;

    /**
     * 内存限制
     * 单位：kB
     */
    private Long memoryLimit;

    /**
     * 堆栈限制
     * 单位：kB
     */
    private Long stackLimit;

}
