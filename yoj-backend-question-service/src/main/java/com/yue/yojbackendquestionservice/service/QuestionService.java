package com.yue.yojbackendquestionservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yue.yojbackendmodel.model.dto.question.QuestionQueryRequest;
import com.yue.yojbackendmodel.model.entity.Question;
import com.yue.yojbackendmodel.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author YUE
* @description 针对表【question(帖子)】的数据库操作Service
* @createDate 2023-11-20 20:38:41
*/
public interface QuestionService extends IService<Question> {

    /**
     * 校验
     *
     * @param post
     * @param add
     */
    void validQuestion(Question post, boolean add);

    /**
     * 获取查询条件
     *
     * @param postQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest postQueryRequest);


    /**
     * 获取帖子封装
     *
     * @param post
     * @param request
     * @return
     */
    QuestionVO getQuestionVO(Question post, HttpServletRequest request);

    /**
     * 分页获取帖子封装
     *
     * @param postPage
     * @param request
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> postPage, HttpServletRequest request);

}
