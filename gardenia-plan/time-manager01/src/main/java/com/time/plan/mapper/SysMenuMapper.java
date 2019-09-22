package com.time.plan.mapper;

import com.time.plan.common.MyMapperSupport;
import com.time.plan.model.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends MyMapperSupport<SysMenu> {

    List<SysMenu> selectByParentId(@Param("parentId") Long parentId);

    void updateLeafNodeById(@Param("leafNode") Integer leafNode, @Param("id") Long id);

}
