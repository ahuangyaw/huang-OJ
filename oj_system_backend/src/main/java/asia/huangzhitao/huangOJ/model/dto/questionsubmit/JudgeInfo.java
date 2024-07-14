package asia.huangzhitao.huangOJ.model.dto.questionsubmit;

import lombok.Data;

/**
 * @author : hwang
 * description : 判题信息
 */

@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗内存
     * 单位：kB
     */
    private Long memory;

    /**
     * 消耗时间
     * 单位：kB
     */
    private Long time;

}
