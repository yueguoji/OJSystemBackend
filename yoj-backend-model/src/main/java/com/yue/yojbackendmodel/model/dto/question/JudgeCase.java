package com.yue.yojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * @author Yuuue
 * creat by 2023-11-20
 */
@Data
public class JudgeCase {

    private static final long serialVersionUID = 1L;

    /**
     * 输入用例
     */
    private String input;

    /**
     *输出用例
     */
    private String output;
}
