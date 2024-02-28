package com.yue.yojbackendmodel.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum JudgeInfoMessageEnum {

    ACCEPTED("成功","Accepted"),

    WRONG_ANSWER("答案错误","wrong_answer"),

    COMPILE_ERROR("编译错误","compileError"),

    MEMORY_LIMIT_EXCEEDED("内存溢出","memoryLimitExceeded"),

    TIME_LIMIT_EXCEEDED("超时","TimeLimitExceeded"),

    PRESENTATION_ERROR("展示错误","PresentationError"),

    DANGEROUS_OPERATION("危险操作","DangerousOperation"),

    OUTPUT_LIMIT_EXCEEDED("输出溢出","OutputLimitExceeded"),

    RUNTIME_ERROR("运行错误（用户程序的问题）","RuntimeError"),

    SYSTEM_ERROR("系统错误（做系统人的问题）","SystemError");


    private final String text;

    private final String value;

    /**
     * 获取值列表
     * 提取每个枚举值的value返回值列表
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     * @param value
     * @return
     */
    public static JudgeInfoMessageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        //QuestionSubmitLanguageEnum.values() 返回的是枚举集合的数组
        for (JudgeInfoMessageEnum anEnum : JudgeInfoMessageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }




    JudgeInfoMessageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
