package com.time.plan.bean.echarts;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LineSeries {

//    {
//        name: '联盟广告',
//                type: 'line',
//            stack: '总量',
//            data: [220, 182, 191, 234, 290, 330, 310]
//    },

    private String name;

    private String type;

    private String stack;

    private List<BigDecimal> data;

//    private Map<String,Object> itemStyle;


}
