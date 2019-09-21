package com.time.plan.service;

import com.time.plan.bean.PlanTypeSelectDto;
import com.time.plan.common.PageParam;
import com.time.plan.common.PageResult;
import com.time.plan.model.SysPlanType;

import java.util.List;

public interface SysPlanTypeService {


    /**
     * 增加
     * @param sysPlanType
     */
    void add(SysPlanType sysPlanType);

    /**
     * 详情
     * @param id
     * @return
     */
    SysPlanType detail(Long id);

    /**
     * 修改
     * @param sysPlanType
     */
    void update(SysPlanType sysPlanType);

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
    PageResult<SysPlanType> pageList(PageParam<SysPlanType> pageParam);

    List<PlanTypeSelectDto> getAll();
}
