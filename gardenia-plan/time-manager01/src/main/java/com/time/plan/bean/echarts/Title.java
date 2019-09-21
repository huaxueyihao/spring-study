package com.time.plan.bean.echarts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Title {

    // 正标题
    private String text;

    // 副标题
    private String subText;

    // 位置 center
    private String x;


}
