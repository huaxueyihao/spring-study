package com.time.plan.mapper;


import com.time.plan.bean.SumUseTimeDto;
import com.time.plan.common.MyMapperSupport;
import com.time.plan.model.SysPlanDayExecution;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SysPlanDayExecutionMapper extends MyMapperSupport<SysPlanDayExecution> {

    List<SysPlanDayExecution> selectByStartTimeBetween(@Param("startTime") LocalDateTime startTimeDate, @Param("endTime") LocalDateTime endTimeDate);


    List<SumUseTimeDto> sumUseTimeGroupByType();

    List<SumUseTimeDto> sumUseTimeGroupByTypeAndStartTime();

    List<String> selectTypeNameByStartTimeOrderByUseTimeDesc();

    List<SumUseTimeDto> sumUseTimeGroupByTypeAndStartTimeBetween(@Param("start") LocalDateTime firstDayOfWeek, @Param("end") LocalDateTime sevenDayOfWeek);

    List<SumUseTimeDto> sumUseTimeGroupByTypeStartTimeAndStartTimeBetween(@Param("start") LocalDateTime firstDayOfWeek, @Param("end") LocalDateTime sevenDayOfWeek);
}
