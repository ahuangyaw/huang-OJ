package asia.huangzhitao.huangojbackendquestionservice.controller.inner;

import asia.huangzhitao.huangojbackendquestionservice.service.QuestionService;
import asia.huangzhitao.huangojbackendquestionservice.service.QuestionSubmitService;
import asia.huangzhitao.huangojbackendserviceclient.entity.Question;
import asia.huangzhitao.huangojbackendserviceclient.entity.QuestionSubmit;
import asia.huangzhitao.huangojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author tao
 * @description UserInnerController
 */
@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    @GetMapping("/get/id")
    public Question getQuestionById(@RequestParam("questionId") long questionId){
        return questionService.getById(questionId);
    }

    @Override
    @GetMapping("/question_submit/get/id")
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId){
        return questionSubmitService.getById(questionSubmitId);
    }

    @Override
    @PostMapping("/question_submit/update")
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit){
        return questionSubmitService.updateById(questionSubmit);
    }
}
