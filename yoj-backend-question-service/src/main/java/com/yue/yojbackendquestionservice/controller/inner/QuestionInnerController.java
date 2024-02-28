package com.yue.yojbackendquestionservice.controller.inner;

import com.yue.yojbackendclientservice.service.QuestionFeignClient;
import com.yue.yojbackendmodel.model.entity.Question;
import com.yue.yojbackendmodel.model.entity.QuestionSubmit;
import com.yue.yojbackendquestionservice.service.QuestionService;
import com.yue.yojbackendquestionservice.service.QuestionSubmitService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Yuuue
 * creat by 2023-12-28
 */
@RequestMapping("/inner")
@RestController
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;
    /**
     * 根据题目Id获取题目
     * @param questionId
     * @return
     */
    @Override
    @GetMapping("/get/id")
    public Question getQuestionById(@RequestParam("questionId")Long questionId) {
        return questionService.getById(questionId);
    }

    /**
     * 根据题目Id获取题目提交
     * @param questionSubmitId
     * @return
     */
    @Override
    @GetMapping("/questionSubmit/get/id")
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId")Long questionSubmitId) {
        return questionSubmitService.getById(questionSubmitId);
    }

    /**
     * 根据题目Id获取题目提交
     *
     * @param questionSubmit
     */
    @Override
    @PostMapping("/questionSubmit/update")
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit) {
        return questionSubmitService.updateById(questionSubmit);
    }
}
