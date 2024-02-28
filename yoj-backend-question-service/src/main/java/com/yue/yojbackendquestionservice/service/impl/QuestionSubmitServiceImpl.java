package com.yue.yojbackendquestionservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yue.yojbackendclientservice.service.JudegeFeignClient;
import com.yue.yojbackendclientservice.service.UserFeignClient;
import com.yue.yojbackendcommon.common.ErrorCode;
import com.yue.yojbackendcommon.constant.CommonConstant;
import com.yue.yojbackendcommon.exception.BusinessException;
import com.yue.yojbackendcommon.utils.SqlUtils;
import com.yue.yojbackendmodel.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.yue.yojbackendmodel.model.dto.questionSubmit.QuestionSubmitQueryRequest;
import com.yue.yojbackendmodel.model.entity.Question;
import com.yue.yojbackendmodel.model.entity.QuestionSubmit;
import com.yue.yojbackendmodel.model.entity.User;
import com.yue.yojbackendmodel.model.enums.QuestionSubmitEnum;
import com.yue.yojbackendmodel.model.enums.QuestionSubmitLanguageEnum;
import com.yue.yojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import com.yue.yojbackendmodel.model.vo.QuestionSubmitVo;
import com.yue.yojbackendquestionservice.mapper.QuestionSubmitMapper;
import com.yue.yojbackendquestionservice.rabbitmq.MyMessageProducer;
import com.yue.yojbackendquestionservice.service.QuestionService;
import com.yue.yojbackendquestionservice.service.QuestionSubmitService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
* @author YUE
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    @Resource
    private UserFeignClient userFeignClient;

    @Resource
    private MyMessageProducer myMessageProducer;

    @Resource
    @Lazy
    private JudegeFeignClient judegeFeignClient;


    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"编程语言错误");
        }
        Long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        }
        // 插入数据
        long userId = loginUser.getId();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setStatus(QuestionSubmitEnum.WAITTING.getValue());
        questionSubmit.setLanguage(questionSubmitAddRequest.getLanguage());
        questionSubmit.setJudgeInfo("{}");
        questionSubmit.setUserId(userId);
        boolean save = this.save(questionSubmit);
        if (!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"插入失败");
        }
        Long questionSubmitId = questionSubmit.getId();

        //发送消息
        myMessageProducer.sendMessage("code_exchange","my_routingKey",String.valueOf(questionSubmitId));
        //提交题目 异步提交
//        CompletableFuture.runAsync(() ->
//                judegeFeignClient.dojudge(questionSubmitId) );
        return questionSubmitId;
    }

    /**
     * 获取查询包装类
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if (questionSubmitQueryRequest == null) {
            return queryWrapper;
        }
        String language = questionSubmitQueryRequest.getLanguage();
        String code = questionSubmitQueryRequest.getCode();
        Integer status = questionSubmitQueryRequest.getStatus();
        Long questionId = questionSubmitQueryRequest.getQuestionId();
        Long userId = questionSubmitQueryRequest.getUserId();
        String sortField = questionSubmitQueryRequest.getSortField();
        String sortOrder = questionSubmitQueryRequest.getSortOrder();

        queryWrapper.eq(StringUtils.isNotBlank(language), "language", language);
        queryWrapper.eq(StringUtils.isNotBlank(code), "code", code);
        queryWrapper.eq(QuestionSubmitStatusEnum.getEnumByValue(status) !=null, "status", status);
        queryWrapper.eq(questionId !=null, "questionId", questionId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    /**
     * 获取单个信息的包装类
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    @Override
    public QuestionSubmitVo getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser) {
        QuestionSubmitVo questionSubmitVO = QuestionSubmitVo.objToVo(questionSubmit);
        // 1. 关联查询用户信息

        //仅本人和管理员可以看见自己提交的代码
        Long id = loginUser.getId();
        if (!Objects.equals(id, questionSubmit.getUserId()) || !userFeignClient.isAdmin(loginUser)){
            questionSubmitVO.setCode(null);
        }
        return questionSubmitVO;
    }

    /**
     * 获取分页信息的包装类
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    @Override
    public Page<QuestionSubmitVo> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser) {
        List<QuestionSubmit> questionList = questionSubmitPage.getRecords();
        Page<QuestionSubmitVo> questionSubmitVoPage = new Page<>(questionSubmitPage.getCurrent(), questionSubmitPage.getSize(), questionSubmitPage.getTotal());
        if (CollectionUtils.isEmpty(questionList)) {
            return questionSubmitVoPage;
        }
        List<QuestionSubmitVo> questionVOList = questionList.stream().map(questionSubmit -> this.getQuestionSubmitVO(questionSubmit, loginUser)).collect(Collectors.toList());

        questionSubmitVoPage.setRecords(questionVOList);
        return questionSubmitVoPage;
    }

}




