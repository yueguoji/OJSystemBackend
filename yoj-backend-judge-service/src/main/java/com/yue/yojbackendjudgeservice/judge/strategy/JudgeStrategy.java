package com.yue.yojbackendjudgeservice.judge.strategy;


import com.yue.yojbackendmodel.codesandbox.JudgeInfo;

public interface JudgeStrategy {

    JudgeInfo doJudge(JudgeContext judgeContext);
}
