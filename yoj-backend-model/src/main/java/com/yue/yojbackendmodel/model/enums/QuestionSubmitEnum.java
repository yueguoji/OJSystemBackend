package com.yue.yojbackendmodel.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yuuue
 * creat by 2023-11-28
 * 题目提交状态枚举
 */
public enum QuestionSubmitEnum {

    WAITTING("等待中",0),

    RUNNING("判题中",1),

    SUCCEED("成功",2),

    FAILED("失败",3);

    private final String text;

    private final Integer value;

    /**
     * 获取值列表
     * 提取每个枚举值的value返回值列表
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     * @param value
     * @return
     */
    public static QuestionSubmitEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (QuestionSubmitEnum anEnum : QuestionSubmitEnum.values()) {
            //TODO 这个for循环怎么遍历的
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }




    QuestionSubmitEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public Integer getValue() {
        return value;
    }
}
