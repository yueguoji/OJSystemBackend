package com.yue.yojbackendmodel.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum QuestionSubmitLanguageEnum {

    JAVA("java","java"),

    CPLUPLUS("cpp","cpp"),

    GOLANG("go","go");

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
    public static QuestionSubmitLanguageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        //QuestionSubmitLanguageEnum.values() 返回的是枚举集合的数组
        for (QuestionSubmitLanguageEnum anEnum : QuestionSubmitLanguageEnum.values()) {
            //TODO 这个for循环怎么遍历的
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }




    QuestionSubmitLanguageEnum(String text, String value) {
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
