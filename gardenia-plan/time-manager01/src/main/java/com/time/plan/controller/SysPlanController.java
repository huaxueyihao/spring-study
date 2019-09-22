package com.time.plan.controller;

import com.time.plan.bean.PlanTypeSelectDto;
import com.time.plan.common.JSONResponse;
import com.time.plan.common.PageParam;
import com.time.plan.common.PageResult;
import com.time.plan.model.SysPlan;
import com.time.plan.service.SysPlanService;
import com.time.plan.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * controller
 */
@Slf4j
@Controller
@RequestMapping("/plan")
public class SysPlanController extends BaseController {

    @Autowired
    private SysPlanService sysPlanService;


    @PostMapping("/pageList")
    @ResponseBody
    public JSONResponse pageList(@RequestBody PageParam<SysPlan> pageParam) {
        PageResult<SysPlan> pageResult = sysPlanService.pageList(pageParam);
        return success(pageResult);
    }

    @PostMapping("/add")
    @ResponseBody
    public JSONResponse addUser(@RequestBody SysPlan sysPlan) {
        // 校验
        ValidatorUtil.validator(sysPlan);
        sysPlanService.add(sysPlan);
        return success(null);
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public JSONResponse remove(@PathVariable Long id) {
        sysPlanService.remove(id);
        return success(null);
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    public JSONResponse batchRemove(@RequestBody Long[] ids) {
        sysPlanService.batchRemove(ids);
        return success(null);
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public JSONResponse detail(@PathVariable Long id) {
        SysPlan sysPlan = sysPlanService.detail(id);
        return success(sysPlan);
    }

    @PostMapping("/update")
    @ResponseBody
    public JSONResponse updateUser(@RequestBody SysPlan sysPlan) {
        sysPlanService.update(sysPlan);
        return success(null);
    }


}
