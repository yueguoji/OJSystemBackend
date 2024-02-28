package com.yue.yojbackendjudgeservice.judge.codesandbox;

import com.yue.yojbackendjudgeservice.judge.codesandbox.impl.ExampleCodeSandbox;
import com.yue.yojbackendjudgeservice.judge.codesandbox.impl.RemoteCodeSandbox;
import com.yue.yojbackendjudgeservice.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * @author Yuuue
 * creat by 2023-12-03
 * 工厂模式（根据传入的字符串生成对应的对象）
 */
public class CodeSanboxFactory {

    /**
     * 创建代码沙箱实例
     * 如果确定代码沙箱不会出现线程安全问题，可复用，那么可以使用单例工厂模式
     * @param type 沙箱类型
     * @return
     */
    public static CodeSandBox newInstance(String type){
        switch (type){
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
