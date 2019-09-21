package com.time.plan.mapper;


import com.time.plan.common.MyMapperSuppurt;
import com.time.plan.model.SysPlanDayExecution;
import com.time.plan.model.SysPlanType;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SysPlanTypeMapper extends MyMapperSuppurt<SysPlanType> {

    int countByTypeName(@Param("typeName") String typeName);


    SysPlanType selectOneByTypeName(@Param("typeName")String typeName);
}
