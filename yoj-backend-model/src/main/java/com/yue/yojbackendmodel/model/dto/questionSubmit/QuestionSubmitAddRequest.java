package com.yue.yojbackendmodel.model.dto.questionSubmit;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Yuuue
 * creat by 2023-11-26
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {


    /**
     * 语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;


    /**
     * 题目 id
     */
    private Long questionId;



    private static final long serialVersionUID = 1L;
}
