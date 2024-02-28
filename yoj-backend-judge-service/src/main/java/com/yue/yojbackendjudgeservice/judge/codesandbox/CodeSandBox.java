package com.yue.yojbackendjudgeservice.judge.codesandbox;

import com.yue.yojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.yue.yojbackendmodel.codesandbox.ExecuteCodeResponse;

public interface CodeSandBox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
