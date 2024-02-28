package com.yue.yojbackendjudgeservice.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.yue.yojbackendmodel.codesandbox.JudgeInfo;
import com.yue.yojbackendmodel.model.dto.question.JudgeCase;
import com.yue.yojbackendmodel.model.dto.question.JudgeConfig;
import com.yue.yojbackendmodel.model.entity.Question;
import com.yue.yojbackendmodel.model.enums.JudgeInfoMessageEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuuue
 * creat by 2023-12-03
 */
@Service
public class DefaultJudgeStrategyImpl implements JudgeStrategy {


    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        Question question = judgeContext.getQuestion();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();


        JudgeInfo judgeInfoRespose = new JudgeInfo();
        judgeInfoRespose.setMemory(memory);
        judgeInfoRespose.setTime(time);
        //5.1先判断执行的结果的数量是否和预期的相等

        int outputSize = outputList.size();
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        if (outputSize !=inputList.size()){
            judgeInfoMessageEnum= JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoRespose.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoRespose;
        }
        //5.2在判断每一项输出结果和预期输出结果是否相等
        for (int i = 0; i <outputSize; i++) {
            JudgeCase judgeCase1 = judgeCaseList.get(i);
            if (!judgeCase1.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoRespose.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoRespose;
            }
        }



        String judgeConfig = question.getJudgeConfig();
        JudgeConfig questionjudgeConfig = JSONUtil.toBean(judgeConfig, JudgeConfig.class);
        Long memoryLimit = questionjudgeConfig.getMemoryLimit();
        Long timeLimit = questionjudgeConfig.getTimeLimit();
        if (memory>memoryLimit){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoRespose.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoRespose;
        }
        if (time>timeLimit){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoRespose.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoRespose;
        }
        judgeInfoRespose.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoRespose;
    }
}
