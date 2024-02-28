package com.yue.yojbackendjudgeservice.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yue.yojbackendcommon.common.ErrorCode;
import com.yue.yojbackendjudgeservice.judge.codesandbox.CodeSandBox;
import com.yue.yojbackendmodel.codesandbox.ExecuteCodeRequest;
import com.yue.yojbackendmodel.codesandbox.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Yuuue
 * creat by 2023-12-03
 * 远程代码沙箱
 */
public class RemoteCodeSandbox implements CodeSandBox {

    //定义鉴权
    private final String REQUEST_AUTH = "auth";

    private final String REQUEST_SERCT = "serct";

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "localhost:8090/codesandbox";
        String s = JSONUtil.toJsonStr(executeCodeRequest);
        String body = HttpUtil.createPost(url)
                .header(REQUEST_AUTH,REQUEST_SERCT)
                .body(s)
                .execute()
                .body();
        if (StringUtils.isEmpty(body)){
            throw new RuntimeException(ErrorCode.API_REQUEST_ERROR.getMessage());
        }

        return JSONUtil.toBean(body,ExecuteCodeResponse.class);
    }
}
