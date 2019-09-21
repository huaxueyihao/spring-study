package com.time.plan.service;

import com.time.plan.common.PageParam;
import com.time.plan.common.PageResult;
import com.time.plan.model.SysFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SysFileService {


    /**
     * 增加
     * @param sysFile
     */
    void addUser(SysFile sysFile);

    /**
     * 详情
     * @param id
     * @return
     */
    SysFile detail(Long id);

    /**
     * 修改
     * @param sysFile
     */
    void update(SysFile sysFile);

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
    PageResult<SysFile> pageList(PageParam<SysFile> pageParam);

}
