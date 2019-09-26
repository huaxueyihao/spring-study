package com.time.plan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.time.plan.validator.annotation.ComparetorGroup;
import com.time.plan.validator.annotation.DateComparetor;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "sys_plan_day_execution")
@DateComparetor(fieldGroup = {@ComparetorGroup(beforeField = "startTime", afterField = "endTime", subValue = 60, message = "结束日期小于开始日期")}, message = "呵")
public class SysPlanDayExecution implements Serializable {

    private static final long serialVersionUID = -5424961325750528661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "type_desc")
    private String typeDesc;

    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Column(name = "plan_state")
    private String planState;

    @Column(name = "use_time")
    private Integer useTime;

    @Column(name = "remark")
    private String remark;

    @Column(name = "location")
    private String location;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;


}
