package com.time.plan.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanTypeSelectDto {

    private String name;

    private String value;


}
