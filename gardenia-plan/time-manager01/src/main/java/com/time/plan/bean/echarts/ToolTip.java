package com.time.plan.bean.echarts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToolTip {

//    tooltip : {
//        trigger: 'item',
//        formatter: "{a} <br/>{b} : {c} 岁"
//    },

    private String trigger;

    private String formatter;

}
