package com.time.plan.service.impl;

import com.time.plan.bean.DayExecutionDto;
import com.time.plan.bean.SumUseTimeDto;
import com.time.plan.bean.echarts.*;
import com.time.plan.common.PageParam;
import com.time.plan.common.PageResult;
import com.time.plan.exception.BusinessException;
import com.time.plan.mapper.SysPlanDayExecutionMapper;
import com.time.plan.mapper.SysPlanMapper;
import com.time.plan.mapper.SysPlanTypeMapper;
import com.time.plan.model.SysPlan;
import com.time.plan.model.SysPlanDayExecution;
import com.time.plan.model.SysPlanType;
import com.time.plan.service.SysPlanDayExecutionService;
import com.time.plan.util.GardeniaDateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SysPlanDayExecutionServiceImpl implements SysPlanDayExecutionService {

    @Autowired
    private SysPlanDayExecutionMapper sysPlanDayExecutionMapper;

    @Autowired
    private SysPlanTypeMapper sysPlanTypeMapper;

    @Autowired
    private SysPlanMapper sysPlanMapper;

    @Override
    public void add(SysPlanDayExecution sysPlanDayExecution) {

        LocalDateTime endTime = sysPlanDayExecution.getEndTime();
        LocalDateTime startTime = sysPlanDayExecution.getStartTime();
        long useTime = Duration.between(startTime, endTime).toMinutes();
        sysPlanDayExecution.setUseTime((int) useTime);

        String typeName = sysPlanDayExecution.getTypeName();
        Optional.ofNullable(typeName).ifPresent(name -> {
            String[] split = name.split("-");
            sysPlanDayExecution.setTypeName(split[0]);
            sysPlanDayExecution.setTypeDesc(split[1]);
        });
        sysPlanDayExecution.setUserId(1L);
        // 计算耗时

        sysPlanDayExecutionMapper.insert(sysPlanDayExecution);
    }

    @Override
    public SysPlanDayExecution detail(Long id) {
        SysPlanDayExecution excution = sysPlanDayExecutionMapper.selectByPrimaryKey(id);
        Long planId = excution.getPlanId();
        if (planId != null && planId > 0) {
            SysPlan sysPlan = sysPlanMapper.selectByPrimaryKey(planId);
            Date startTime = sysPlan.getStartTime();
            String start = GardeniaDateUtil.date2Str(startTime, "yyyy-MM-dd");
            String end = GardeniaDateUtil.date2Str(sysPlan.getEndTime(), "yyyy-MM-dd");
            excution.setPlanTitle(sysPlan.getTitle() + "(" + start + "至" + end + ")");
        }
        return excution;
    }

    @Override
    public void update(SysPlanDayExecution plan) {
//        LocalDateTime endTime = sysPlanDayExecution.getEndTime();
//        LocalDateTime startTime = sysPlanDayExecution.getStartTime();
//        long useTime = Duration.between(startTime, endTime).toMinutes();
//        if (useTime <= 0) {
//            throw new BusinessException(100L, "结束时间要在开始时间之后");
//        }
//        sysPlanDayExecution.setUseTime((int) useTime);
        SysPlanDayExecution excution = sysPlanDayExecutionMapper.selectByPrimaryKey(plan.getId());
        if (excution != null) {
            excution.setStartTime(plan.getStartTime());
            excution.setEndTime(plan.getEndTime());
            excution.setTitle(plan.getTitle());
            excution.setLocation(plan.getLocation());
            excution.setPlanId(plan.getPlanId());
            excution.setRemark(plan.getRemark());
            LocalDateTime endTime = plan.getEndTime();
            LocalDateTime startTime = plan.getStartTime();
            long useTime = Duration.between(startTime, endTime).toMinutes();
            excution.setUseTime((int) useTime);
            sysPlanDayExecutionMapper.updateByPrimaryKey(excution);
        }
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void batchRemove(Long[] ids) {

    }

    @Override
    public PageResult<SysPlanDayExecution> pageList(PageParam<SysPlanDayExecution> pageParam) {
        return null;
    }

    @Override
    public List<DayExecutionDto> getAll() {
        List<SysPlanDayExecution> sysPlanDayExecutions = sysPlanDayExecutionMapper.selectAll();
        return getDayExecutionDtos(sysPlanDayExecutions);
    }

    private List<DayExecutionDto> getDayExecutionDtos(List<SysPlanDayExecution> sysPlanDayExecutions) {
        return sysPlanDayExecutions.stream().map(plan -> {
            Example example = new Example(SysPlanType.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("typeName", plan.getTypeName());
            SysPlanType planType = sysPlanTypeMapper.selectOneByExample(example);
            return DayExecutionDto.builder().
                    start(plan.getStartTime()).end(plan.getEndTime()).backgroundColor(planType.getBgColor()).
                    groupId(1L).title(plan.getTitle()).textColor("#fff").id(plan.getId()).build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<DayExecutionDto> getAllByStartTime(long startTime, long endTime) {

        LocalDateTime startTimeDate = LocalDateTime.ofEpochSecond(startTime / 1000, 0, ZoneOffset.ofHours(8));
        LocalDateTime endTimeDate = LocalDateTime.ofEpochSecond(endTime / 1000, 0, ZoneOffset.ofHours(8));
        List<SysPlanDayExecution> sysPlanDayExecutions = sysPlanDayExecutionMapper.selectByStartTimeBetween(startTimeDate, endTimeDate);

        return getDayExecutionDtos(sysPlanDayExecutions);
    }

    @Override
    public PieChartDto sumAllGroupByType() {

        List<SumUseTimeDto> sumUseTimeDtos = sysPlanDayExecutionMapper.sumUseTimeGroupByType();

        PieChartDto pieChartDto = new PieChartDto();
        if (sumUseTimeDtos != null && sumUseTimeDtos.size() > 0) {
            List<String> lengendData = new ArrayList<>(sumUseTimeDtos.size());
            List<SeriesData> seriesDataList = new ArrayList<>(sumUseTimeDtos.size());
            sumUseTimeDtos.forEach(sumUseTimeDto -> {
                convertDto(lengendData, seriesDataList, sumUseTimeDto);
            });

            Title title = Title.builder().text("行为周记录").subText("").x("center").build();
            Lengend lengend = Lengend.builder().data(lengendData).orient("vertical").x("left").build();
            PieSeries series = PieSeries.builder().data(seriesDataList).name("pie").center(new String[]{"50%", "60%"}).radius("50%").type("pie").build();
            ToolTip toolTip = ToolTip.builder().trigger("item").formatter("{a} <br/>{b} : {c} 岁").build();
            pieChartDto = PieChartDto.builder().title(title).lengend(lengend).series(series).toolTip(toolTip).build();
        }
        return pieChartDto;
    }

    @Override
    public Map<String, Object> sumAllGroupByPlanType(Long dateTime) {

        LocalDateTime localDateTime = dateTime == null ? LocalDateTime.now() : LocalDateTime.ofEpochSecond(dateTime / 1000, 0, ZoneOffset.ofHours(8));
        int value = localDateTime.getDayOfWeek().getValue();
        // 星期一
        LocalDateTime firstDayOfWeek = localDateTime.plusDays(1 - value);
        firstDayOfWeek = LocalDateTime.of(firstDayOfWeek.getYear(),firstDayOfWeek.getMonthValue(),firstDayOfWeek.getDayOfMonth(),0,0,0);
        // 星期七
        LocalDateTime sevenDayOfWeek = firstDayOfWeek.plusDays(6);
        sevenDayOfWeek = LocalDateTime.of(sevenDayOfWeek.getYear(),sevenDayOfWeek.getMonthValue(),sevenDayOfWeek.getDayOfMonth(),23,59,59);

        List<SumUseTimeDto> sumUseTimeDtos = sysPlanDayExecutionMapper.sumUseTimeGroupByTypeAndStartTimeBetween(firstDayOfWeek, sevenDayOfWeek);
        Map<String, Object> map = new HashMap<>();
        if (sumUseTimeDtos != null && sumUseTimeDtos.size() > 0) {
            List<String> lengendData = new ArrayList<>(sumUseTimeDtos.size());
            List<SeriesData> seriesDataList = new ArrayList<>(sumUseTimeDtos.size());
            for (SumUseTimeDto sumUseTimeDto : sumUseTimeDtos) {
                convertDto(lengendData, seriesDataList, sumUseTimeDto);
            }
            map.put("lengendData", lengendData);
            map.put("seriesDataList", seriesDataList);
        }
        return map;
    }

    @Override
    public Map<String, Object> sumAllGroupByPlanTypeAndStartTime(Long dateTime) {

        LocalDateTime localDateTime = dateTime == null ? LocalDateTime.now() : LocalDateTime.ofEpochSecond(dateTime / 1000, 0, ZoneOffset.ofHours(8));
        int value = localDateTime.getDayOfWeek().getValue();
        // 星期一
        LocalDateTime firstDayOfWeek = localDateTime.plusDays(1 - value);
        firstDayOfWeek = LocalDateTime.of(firstDayOfWeek.getYear(),firstDayOfWeek.getMonthValue(),firstDayOfWeek.getDayOfMonth(),0,0,0);
        // 星期七
        LocalDateTime sevenDayOfWeek = firstDayOfWeek.plusDays(6);
        sevenDayOfWeek = LocalDateTime.of(sevenDayOfWeek.getYear(),sevenDayOfWeek.getMonthValue(),sevenDayOfWeek.getDayOfMonth(),23,59,59);


        Map<String, Object> map = new HashMap<>();
        // 名称 按耗时降序排序
        List<SumUseTimeDto> typeNameList = sysPlanDayExecutionMapper.sumUseTimeGroupByTypeAndStartTimeBetween(firstDayOfWeek, sevenDayOfWeek);
        if (typeNameList != null && typeNameList.size() > 0) {

            // TODO 这里的sql是mysql ，h2不支持
            List<SumUseTimeDto> sumUseTimeDtos = sysPlanDayExecutionMapper.sumUseTimeGroupByTypeStartTimeAndStartTimeBetween(firstDayOfWeek, sevenDayOfWeek);
            // 标题处的列表轴数据
            List<String> typeDescList = typeNameList.stream().map(dto -> dto.getTypeDesc()).collect(Collectors.toList());
            // y轴数据
            List<LineSeries> lineSeriesList = new ArrayList<>();

//            List<String> dates = sumUseTimeDtos.stream().map(dto -> dto.getStartTimeStr()).distinct().collect(Collectors.toList());

            List<String> dates = new ArrayList<>();
            LocalDate firstLocalDate = LocalDate.of(firstDayOfWeek.getYear(), firstDayOfWeek.getMonthValue(), firstDayOfWeek.getDayOfMonth());
            dates.add(firstLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            for (int i = 1; i < 7; i++) {
                LocalDateTime temp = firstDayOfWeek.plusDays(i);
                LocalDate tempTime = LocalDate.of(temp.getYear(), temp.getMonthValue(), temp.getDayOfMonth());
                String format = tempTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                dates.add(format);
            }

            // 结果集中没有某一天的数据，要手动new一个


            List<SumUseTimeDto> tempList = new ArrayList<>();
            dates.forEach(dateStr -> {
                typeNameList.forEach(planType -> {
                    String typeDesc = planType.getTypeDesc();
                    String typeName = planType.getTypeName();

                    SumUseTimeDto dto = SumUseTimeDto.builder().typeDesc(typeDesc).typeName(typeName).startTimeStr(dateStr).sumUserTime(BigDecimal.ZERO).build();
                    tempList.add(dto);
                });
            });

            List<SumUseTimeDto> useTimeDtos = tempList.stream().map(dto -> {
                SumUseTimeDto sumUseTimeDto = getExistDto(dto, sumUseTimeDtos);
                return sumUseTimeDto == null ? dto : sumUseTimeDto;
            }).collect(Collectors.toList());


            typeNameList.forEach(uDto -> {
                List<BigDecimal> typeNameUseTimeSumList = useTimeDtos.stream().filter(dto -> {
                    return dto.getTypeName().equals(uDto.getTypeName());
                }).map(dto ->
                        dto.getSumUserTime().divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP)
                ).collect(Collectors.toList());

//                Map<String, Object> itemStyle = new HashMap<>();
//                Map<String, Object> normal = new HashMap<>();
//                Map<String, Object> lineStyle = new HashMap<>();
//                SysPlanType planType = sysPlanTypeMapper.selectOneByTypeName(uDto.getTypeName());

//                lineStyle.put("color", planType.getBgColor());
//                normal.put("lineStyle", lineStyle);
//                itemStyle.put("normal", normal);
                LineSeries lineSeries = LineSeries.builder().name(uDto.getTypeDesc()).data(typeNameUseTimeSumList).stack("总量").type("line").build();
                lineSeriesList.add(lineSeries);
            });

            List<String> dateList = dates.stream().map(date -> {
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDateTime tempTime = localDate.atStartOfDay();
                return GardeniaDateUtil.dateWeek(tempTime);
            }).collect(Collectors.toList());


            map.put("legendData", typeDescList);
            map.put("series", lineSeriesList);
            map.put("xAxisData", dateList);
        }
        return map;
    }

    private SumUseTimeDto getExistDto(SumUseTimeDto dto, List<SumUseTimeDto> sumUseTimeDtos) {
        for (SumUseTimeDto sumUseTimeDto : sumUseTimeDtos) {
            if (sumUseTimeDto.getTypeName().equals(dto.getTypeName()) && sumUseTimeDto.getStartTimeStr().equals(dto.getStartTimeStr())) {
                return sumUseTimeDto;
            }
        }
        return null;
    }

    private SumUseTimeDto createSumUseTimeDto(List<String> dates, List<String> typeDescList, String startTimeStr, String typeName) {
        if (dates.contains(startTimeStr) && typeDescList.contains(typeName)) {
            return null;
        } else {
            return SumUseTimeDto.builder().startTimeStr(startTimeStr).typeName(typeName).typeDesc("").sumUserTime(BigDecimal.ZERO).build();
        }
    }

    private void convertDto(List<String> lengendData, List<SeriesData> seriesDataList, SumUseTimeDto sumUseTimeDto) {
        String typeName = sumUseTimeDto.getTypeName();
        String typeDesc = sumUseTimeDto.getTypeDesc();
        BigDecimal sumUserTime = sumUseTimeDto.getSumUserTime();
        SysPlanType planType = sysPlanTypeMapper.selectOneByTypeName(typeName);
        String bgColor = planType.getBgColor();
        ItemStyle itemStyle = ItemStyle.builder().color(bgColor).build();

        SeriesData seriesData = SeriesData.builder().name(typeDesc).value(sumUserTime.divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP)).itemStyle(itemStyle).build();
        seriesDataList.add(seriesData);
        lengendData.add(typeDesc);
    }


}
