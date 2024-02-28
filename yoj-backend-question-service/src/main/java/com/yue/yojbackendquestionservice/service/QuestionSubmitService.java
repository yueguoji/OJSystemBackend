package com.yue.yojbackendquestionservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yue.yojbackendmodel.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.yue.yojbackendmodel.model.dto.questionSubmit.QuestionSubmitQueryRequest;
import com.yue.yojbackendmodel.model.entity.QuestionSubmit;
import com.yue.yojbackendmodel.model.entity.User;
import com.yue.yojbackendmodel.model.vo.QuestionSubmitVo;

/**
* @author YUE
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2023-11-20 20:41:35
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 点赞
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取题目查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVo getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVo> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);



}
