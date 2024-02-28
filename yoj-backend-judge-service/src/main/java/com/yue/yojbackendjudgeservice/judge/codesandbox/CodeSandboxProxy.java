package com.yue.yojbackendjudgeservice.judge.codesandbox;

import com.yue.yojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.yue.yojbackendmodel.codesandbox.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Yuuue
 * creat by 2023-12-03
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandBox{

    private final CodeSandBox codeSandBox;

    public CodeSandboxProxy(CodeSandBox codeSandBox) {
        this.codeSandBox = codeSandBox;
    }

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("调用前");
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        log.info("调用后");
        return executeCodeResponse;
    }
}
