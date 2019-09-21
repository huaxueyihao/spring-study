package com.time.plan.service;

import com.time.plan.bean.DayExecutionDto;
import com.time.plan.bean.echarts.PieChartDto;
import com.time.plan.common.PageParam;
import com.time.plan.common.PageResult;
import com.time.plan.model.SysPlanDayExecution;

import java.util.List;
import java.util.Map;

public interface SysPlanDayExecutionService {


    /**
     * 增加
     * @param sysPlanDayExecution
     */
    void add(SysPlanDayExecution sysPlanDayExecution);

    /**
     * 详情
     * @param id
     * @return
     */
    SysPlanDayExecution detail(Long id);

    /**
     * 修改
     * @param sysPlanDayExecution
     */
    void update(SysPlanDayExecution sysPlanDayExecution);

    /**
     * 删除
     * @param id
     */
    void remove(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void batchRemove(Long[] ids);

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    PageResult<SysPlanDayExecution> pageList(PageParam<SysPlanDayExecution> pageParam);

    /**
     * 获得全部的数据
     * @return
     */
    List<DayExecutionDto> getAll();

    /**
     * 根据日期查询数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<DayExecutionDto> getAllByStartTime(long startTime, long endTime);

    PieChartDto sumAllGroupByType();

    Map<String, Object> sumAllGroupByPlanType(Long dateTime);

    Map<String, Object> sumAllGroupByPlanTypeAndStartTime(Long dateTime);
}
