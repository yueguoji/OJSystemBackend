package com.yue.yojbackendmodel.model.dto.questionSubmit;

import lombok.Data;

/**
 * @author Yuuue
 * creat by 2023-11-20
 */
@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗内存
     */
    private Long memory;

    /**
     * 消耗时间
     */
    private Long time;

}
