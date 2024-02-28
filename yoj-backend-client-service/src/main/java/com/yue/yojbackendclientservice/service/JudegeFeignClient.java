package com.yue.yojbackendclientservice.service;

import com.yue.yojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "yoj-backend-judge-service", path = "/api/judge/inner",contextId = "judge111")
public interface JudegeFeignClient {

    /**
     * 判题
     *
     * @param questionId
     * @return
     */
    @PostMapping("/do")
    QuestionSubmit dojudge(@RequestParam("questionId") long questionId);
}
