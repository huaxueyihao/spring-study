package com.time.plan.bean;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SumUseTimeDto {

    private String typeName;

    private String typeDesc;

    private String startTimeStr;

    private BigDecimal sumUserTime;

}
