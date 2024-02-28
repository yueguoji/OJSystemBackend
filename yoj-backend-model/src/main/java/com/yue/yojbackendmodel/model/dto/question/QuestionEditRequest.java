package com.yue.yojbackendmodel.model.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 编辑请求
 * 跟普通用户使用的，只能指定部分字段
 */
@Data
public class QuestionEditRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 判题用例（json 对象）
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置（json 对象）
     */
    private List<JudgeConfig> judgeConfig;

    private static final long serialVersionUID = 1L;

}
