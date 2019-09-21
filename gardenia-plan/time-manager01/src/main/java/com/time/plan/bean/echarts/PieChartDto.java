package com.time.plan.bean.echarts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PieChartDto {

    private Title title;

    private ToolTip toolTip;

    private Lengend lengend;

    private PieSeries series;

}
