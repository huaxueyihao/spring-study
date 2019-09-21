package com.time.plan.bean.echarts;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class SeriesData {

    private String name;

    private BigDecimal value;

    private ItemStyle itemStyle;

}
