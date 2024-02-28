package com.yue.yojbackendjudgeservice.judge.controller.inner;

import com.yue.yojbackendclientservice.service.JudegeFeignClient;
import com.yue.yojbackendjudgeservice.judge.JudegeService;
import com.yue.yojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Yuuue
 * creat by 2023-12-28
 */
@RestController
@RequestMapping("/inner")
public class JudgeInnerController implements JudegeFeignClient {

    @Resource
    private JudegeService judegeService;

    /**
     * 判题
     *
     * @param questionId
     * @return
     */
    @Override
    @PostMapping("/do")
    public QuestionSubmit dojudge(@RequestParam("questionId")long questionId) {
        return judegeService.dojudge(questionId);
    }
}
