package com.time.plan.bean.echarts;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class PieSeries {

    private String name;

    private String type;

    private String radius;

    private String[] center;

    private List<SeriesData> data;

//    name:'女神',
//    type:'pie',
//    radius : '55%',//饼图的半径大小
//    center: ['50%', '60%'],//饼图的位置
}
