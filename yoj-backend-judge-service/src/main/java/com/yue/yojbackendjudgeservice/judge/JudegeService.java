package com.yue.yojbackendjudgeservice.judge;

import com.yue.yojbackendmodel.model.entity.QuestionSubmit;

public interface JudegeService {

    /**
     * 判题
     * @param questionId
     * @return
     */
    QuestionSubmit dojudge(long questionId);
}
