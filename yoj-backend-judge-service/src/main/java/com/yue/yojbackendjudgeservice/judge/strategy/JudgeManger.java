package com.yue.yojbackendjudgeservice.judge.strategy;

import com.yue.yojbackendmodel.codesandbox.JudgeInfo;
import com.yue.yojbackendmodel.model.entity.QuestionSubmit;

/**
 * @author Yuuue
 * creat by 2023-12-03
 * 策略管理
 */
public class JudgeManger {

    /**
     * 根据语言选择使用哪个策略
     * @param judgeContext
     * @return
     */
    public static JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategyImpl();
        if ("java".equals(language)){
            judgeStrategy = new JavaJudgeStrategyImpl();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
