package com.time.plan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysDateTree {
    // 数据库自增主键
    private Long id;

    /**
     * 逻辑主键 就是日期比如
     * 年2019 00 00 00
     * 年季
     * 年季月 201901
     * 年季月周20190101
     */
    private String primaryKey;

    /**
     * 父层级的主键
     */
    private String parentKey;


    /**
     * 显示的名称
     */
    private String name;

    /**
     * 开始时间
     */
    private LocalDate startTime;

    /**
     * 结束时间
     */
    private LocalDate endTime;


}
