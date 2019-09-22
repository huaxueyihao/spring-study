package com.time.plan.service;

import com.time.plan.common.PageParam;
import com.time.plan.common.PageResult;
import com.time.plan.model.SysPlan;

public interface SysPlanService {


    /**
     * 增加
     * @param sysPlan
     */
    void add(SysPlan sysPlan);

    /**
     * 详情
     * @param id
     * @return
     */
    SysPlan detail(Long id);

    /**
     * 修改
     * @param sysPlan
     */
    void update(SysPlan sysPlan);

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
    PageResult<SysPlan> pageList(PageParam<SysPlan> pageParam);

}
