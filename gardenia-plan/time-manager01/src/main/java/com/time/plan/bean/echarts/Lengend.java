package com.time.plan.bean.echarts;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class Lengend {

//    orient : 'vertical',
//    x : 'left',
//    data:['高圆圆','赵丽颖','江莱']

    private String orient;

    private String x;

    private List<String> data;

}
