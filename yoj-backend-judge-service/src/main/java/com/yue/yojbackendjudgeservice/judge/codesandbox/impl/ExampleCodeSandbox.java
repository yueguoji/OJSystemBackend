package com.yue.yojbackendjudgeservice.judge.codesandbox.impl;


import com.yue.yojbackendjudgeservice.judge.codesandbox.CodeSandBox;
import com.yue.yojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.yue.yojbackendmodel.codesandbox.ExecuteCodeResponse;

import java.util.List;

/**
 * @author Yuuue
 * creat by 2023-12-03
 * 示例代码沙箱(跑通流程)
 */
public class ExampleCodeSandbox implements CodeSandBox {
    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();

        System.out.println("示例代码沙箱");
        return null;
    }
}
