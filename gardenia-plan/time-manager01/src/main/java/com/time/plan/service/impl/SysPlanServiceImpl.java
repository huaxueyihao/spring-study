package com.time.plan.service.impl;

import com.time.plan.common.PageParam;
import com.time.plan.common.PageResult;
import com.time.plan.mapper.SysPlanMapper;
import com.time.plan.model.SysPlan;
import com.time.plan.service.SysPlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SysPlanServiceImpl implements SysPlanService {


    @Autowired
    private SysPlanMapper sysPlanMapper;


    @Override
    public void add(SysPlan sysPlan) {
        sysPlanMapper.insert(sysPlan);
    }

    @Override
    public SysPlan detail(Long id) {
        return sysPlanMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(SysPlan sysPlan) {
        SysPlan entity = sysPlanMapper.selectByPrimaryKey(sysPlan.getId());

        if (entity != null) {
            entity.setRemark(sysPlan.getRemark());
            entity.setMinTotalPlanAmount(sysPlan.getMinTotalPlanAmount());
            entity.setMaxTotalPlanAmount(sysPlan.getMaxTotalPlanAmount());
            entity.setFinishPlanAmount(sysPlan.getFinishPlanAmount());
            sysPlanMapper.updateByPrimaryKey(entity);
        }

    }

    @Override
    public void remove(Long id) {
        sysPlanMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchRemove(Long[] ids) {
        Arrays.stream(ids).forEach(id -> {
            sysPlanMapper.deleteByPrimaryKey(id);
        });
    }

    @Override
    public PageResult<SysPlan> pageList(PageParam<SysPlan> pageParam) {
        int limit = pageParam.getLimit();
        int page = pageParam.getPage();
        SysPlan condition = pageParam.getCondition();
        PageResult<SysPlan> pageResult = new PageResult<>();
        Example example = new Example(SysPlan.class);
        Example.Criteria criteria = example.createCriteria();
        Optional.ofNullable(condition).ifPresent(param -> {
            if(StringUtils.isNotBlank(param.getTitle())){
                criteria.andLike("title", "%" + param.getTitle() + "%");
            }
            if(StringUtils.isNotBlank(param.getType())){
                criteria.andLike("type", param.getType());
            }

        });
        int count = sysPlanMapper.selectCountByExample(example);
        if (count > 0) {
            List<SysPlan> sysUsers = sysPlanMapper.selectByExampleAndRowBounds(example, pageParam.getRowBounds());
            pageResult.setDataList(sysUsers);
            pageResult.setCount(count);
        }
        return pageResult;
    }

}
