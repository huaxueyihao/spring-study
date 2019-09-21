package com.time.plan.controller;

import com.time.plan.bean.DayExecutionDto;
import com.time.plan.bean.echarts.PieChartDto;
import com.time.plan.common.JSONResponse;
import com.time.plan.common.PageParam;
import com.time.plan.common.PageResult;
import com.time.plan.model.SysPlanDayExecution;
import com.time.plan.service.SysPlanDayExecutionService;
import com.time.plan.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * controller
 */
@Slf4j
@Controller
@RequestMapping("/planDayExecution")
public class SysPlanDayExecutionController extends BaseController {

    @Autowired
    private SysPlanDayExecutionService sysPlanDayExecutionService;


    @GetMapping("/getAll")
    @ResponseBody
    public JSONResponse getAll() {
        List<DayExecutionDto> dayExecutionDtoList = sysPlanDayExecutionService.getAll();
        return success(dayExecutionDtoList);
    }

    @GetMapping("/getAllByStartTime/{startTime}/{endTime}")
    @ResponseBody
    public JSONResponse getAllByStartTime(@PathVariable(required = false) long startTime, @PathVariable(required = false) long endTime) {
        List<DayExecutionDto> dayExecutionDtoList = sysPlanDayExecutionService.getAllByStartTime(startTime, endTime);
        return success(dayExecutionDtoList);
    }

    @GetMapping({"/getAllGroupByType", "/getAllGroupByType/{dateTime}"})
    @ResponseBody
    public JSONResponse sumAllGroupByType(@PathVariable(required = false) Long dateTime) {
        Map<String, Object> maps = sysPlanDayExecutionService.sumAllGroupByPlanType(dateTime);
        return success(maps);
    }

    @GetMapping({"/getAllGroupByTypeAndStartTime","/getAllGroupByTypeAndStartTime/{dateTime}"})
    @ResponseBody
    public JSONResponse sumAllGroupByPlanTypeAndStartTime(@PathVariable(required = false) Long dateTime) {
        Map<String, Object> maps = sysPlanDayExecutionService.sumAllGroupByPlanTypeAndStartTime(dateTime);
        return success(maps);
    }


    @PostMapping("/pageList")
    @ResponseBody
    public JSONResponse pageList(@RequestBody PageParam<SysPlanDayExecution> pageParam) {
        PageResult<SysPlanDayExecution> pageResult = sysPlanDayExecutionService.pageList(pageParam);
        return success(pageResult);
    }

    @PostMapping("/add")
    @ResponseBody
    public JSONResponse addUser(@RequestBody SysPlanDayExecution sysPlanDayExecution) {
        // 校验
        ValidatorUtil.validator(sysPlanDayExecution);
        sysPlanDayExecutionService.add(sysPlanDayExecution);
        return success(null);
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public JSONResponse remove(@PathVariable Long id) {
        sysPlanDayExecutionService.remove(id);
        return success(null);
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    public JSONResponse batchRemove(@RequestBody Long[] ids) {
        sysPlanDayExecutionService.batchRemove(ids);
        return success(null);
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public JSONResponse detail(@PathVariable Long id) {
        SysPlanDayExecution sysPlanDayExecution = sysPlanDayExecutionService.detail(id);
        return success(sysPlanDayExecution);
    }

    @PostMapping("/update")
    @ResponseBody
    public JSONResponse updateUser(@RequestBody SysPlanDayExecution sysPlanDayExecution) {
        sysPlanDayExecutionService.update(sysPlanDayExecution);
        return success(null);
    }


}
