package com.yue.yojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * @author Yuuue
 * creat by 2023-11-20
 */
@Data
public class JudgeConfig {

    private static final long serialVersionUID = 1L;
    /**
     * 时间限制（ms）
     */
    private Long timeLimit;

    /**
     * 内存限制（kb）
     */
    private Long memoryLimit;

    /**
     * 堆栈限制
     */
    private Long stackLimit;

}
