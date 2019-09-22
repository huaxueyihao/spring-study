package com.time.plan.mapper;


import com.time.plan.common.MyMapperSupport;
import com.time.plan.model.SysPlanType;
import org.apache.ibatis.annotations.Param;

public interface SysPlanTypeMapper extends MyMapperSupport<SysPlanType> {

    int countByTypeName(@Param("typeName") String typeName);


    SysPlanType selectOneByTypeName(@Param("typeName")String typeName);
}
