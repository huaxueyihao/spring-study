package com.time.plan.controller;

import com.time.plan.common.JSONResponse;
import com.time.plan.common.PageParam;
import com.time.plan.common.PageResult;
import com.time.plan.model.SysFile;
import com.time.plan.service.SysFileService;
import com.time.plan.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传的controller
 */
@Slf4j
@Controller
@RequestMapping("/file")
public class SysFileController extends BaseController {

    @Autowired
    private SysFileService sysFileService;


    @RequestMapping("/upload")
    @ResponseBody
    public JSONResponse upload(@RequestParam("file") List<MultipartFile> fileList) {
        return success(null);
    }


    @PostMapping("/pageList")
    @ResponseBody
    public JSONResponse pageList(@RequestBody PageParam<SysFile> pageParam) {
        PageResult<SysFile> pageResult = sysFileService.pageList(pageParam);
        return success(pageResult);
    }

    @PostMapping("/add")
    @ResponseBody
    public JSONResponse addUser(@RequestBody SysFile sysFile) {
        // 校验
        ValidatorUtil.validator(sysFile);
        sysFileService.addUser(sysFile);
        return success(null);
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public JSONResponse remove(@PathVariable Long id) {
        sysFileService.remove(id);
        return success(null);
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    public JSONResponse batchRemove(@RequestBody Long[] ids) {
        sysFileService.batchRemove(ids);
        return success(null);
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public JSONResponse detail(@PathVariable Long id) {
        SysFile sysFile = sysFileService.detail(id);
        return success(sysFile);
    }

    @PostMapping("/update")
    @ResponseBody
    public JSONResponse updateUser(@RequestBody SysFile sysFile) {
        sysFileService.update(sysFile);
        return success(null);
    }


}
