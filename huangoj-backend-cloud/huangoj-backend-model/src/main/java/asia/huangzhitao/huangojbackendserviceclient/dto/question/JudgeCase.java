package asia.huangzhitao.huangojbackendserviceclient.dto.question;

import lombok.Data;

/**
 * @author : hwang
 * description : JudgeCase
 */

@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;

}
