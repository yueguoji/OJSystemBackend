package com.yue.yojbackendjudgeservice.judge;

import cn.hutool.json.JSONUtil;
import com.yue.yojbackendclientservice.service.QuestionFeignClient;
import com.yue.yojbackendcommon.common.ErrorCode;
import com.yue.yojbackendcommon.exception.BusinessException;
import com.yue.yojbackendjudgeservice.judge.codesandbox.CodeSanboxFactory;
import com.yue.yojbackendjudgeservice.judge.codesandbox.CodeSandBox;
import com.yue.yojbackendjudgeservice.judge.codesandbox.CodeSandboxProxy;
import com.yue.yojbackendjudgeservice.judge.strategy.JudgeContext;
import com.yue.yojbackendjudgeservice.judge.strategy.JudgeManger;
import com.yue.yojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.yue.yojbackendmodel.codesandbox.ExecuteCodeResponse;
import com.yue.yojbackendmodel.codesandbox.JudgeInfo;
import com.yue.yojbackendmodel.model.dto.question.JudgeCase;
import com.yue.yojbackendmodel.model.entity.Question;
import com.yue.yojbackendmodel.model.entity.QuestionSubmit;
import com.yue.yojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import io.github.classgraph.json.Id;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yuuue
 * creat by 2023-12-03
 */
@Service
public class JudegeServiceImpl implements JudegeService {


    @Resource
    private QuestionFeignClient questionFeignClient;

    @Value("${codeSandbox.type}")
    private String type;
    /**
     * 判题
     *
     * @param questionSubmitId
     * @return
     */
    @Override
    public QuestionSubmit dojudge(long questionSubmitId) {
        //1）传入题目的提交 id，获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (questionSubmit ==null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionFeignClient.getQuestionById(questionId);
        if (question ==null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        }
        String judgeInfo = questionSubmit.getJudgeInfo();
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        // 2）如果题目提交状态不为等待中，就不用重复执行了
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"题目状态错误");
        }
        // 3）更改判题（题目提交）的状态为 “判题中”，防止重复执行，也能让用户即时看到状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.RUNING.getValue());
        boolean status = questionFeignClient.updateQuestionSubmitById(questionSubmit);
        if (!status){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"更新状态错误");
        }
        // 4）调用沙箱，获取到执行结果
        CodeSandBox codeSandBox = CodeSanboxFactory.newInstance(type);
        codeSandBox =  new CodeSandboxProxy(codeSandBox);
        String judgeCase = question.getJudgeCase();
//        String judgeConfig = question.getJudgeConfig();
//        List<JudgeConfig> judgeConfigs = JSONUtil.toList(judgeConfig, JudgeConfig.class);
        //获取输入用例
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCase, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .inputList(inputList)
                .language(language)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        System.out.println(executeCodeResponse);
        List<String> outputList = executeCodeResponse.getOutputList();
        JudgeInfo questionJudgeInfo = executeCodeResponse.getJudgeInfo();
        // 5）根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeInfo(questionJudgeInfo);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);

        JudgeInfo doJudge = JudgeManger.doJudge(judgeContext);
        //更新数据库
        questionSubmit.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(doJudge));
        boolean  b= questionFeignClient.updateQuestionSubmitById(questionSubmit);
        if (!b){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"更新状态错误");
        }

        return questionSubmit;
    }
}
