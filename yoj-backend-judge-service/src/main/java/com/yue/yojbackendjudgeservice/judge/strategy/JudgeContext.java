package com.yue.yojbackendjudgeservice.judge.strategy;

import com.yue.yojbackendmodel.codesandbox.JudgeInfo;
import com.yue.yojbackendmodel.model.dto.question.JudgeCase;
import com.yue.yojbackendmodel.model.entity.Question;
import com.yue.yojbackendmodel.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @author Yuuue
 * creat by 2023-12-03
 */
@Data
public class JudgeContext {

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private JudgeInfo judgeInfo;


    private Question question;

    private QuestionSubmit questionSubmit;


}
