package com.yue.yojbackendclientservice.service;


import com.yue.yojbackendmodel.model.entity.Question;
import com.yue.yojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author YUE
*/
@FeignClient(name = "yoj-backend-question-service",path = "/api/question/inner")
public interface QuestionFeignClient {

    /**
     * 根据题目Id获取题目
     * @param questionId
     * @return
     */
    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") Long questionId);

    /**
     * 根据题目Id获取题目提交
     * @param questionSubmitId
     * @return
     */
    @GetMapping("/questionSubmit/get/id")
    QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId")Long questionSubmitId);


    /**
     * 根据题目Id获取题目提交

     */
    @PostMapping("/questionSubmit/update")
    boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit);





}
