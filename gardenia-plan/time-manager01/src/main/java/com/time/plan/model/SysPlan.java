package com.time.plan.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_plan")
public class SysPlan {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 计划类型：年/季/月/周
     */
    @Column(name = "`type`")
    private String type;

    /**
     * 计划主题
     */
    @Column(name = "title")
    private String title;

    /**
     * 开始时间：年月日时分秒
     */
    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间：年月日时分秒
     */
    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 状态，1：计划，2：执行，3：暂停，4，延后：5：完成，
     */
    @Column(name = "plan_state")
    private String planState;

    /**
     * 耗时
     */
    @Column(name = "use_time")
    private Long useTime;

    /**
     * 计划量:比如，书籍多少页，视频多少集，可进行量化的量或者某个计划分了1，2，3，4个阶段，共4阶段，完成到几阶段
     */
    @Column(name = "total_plan_amount")
    private Integer totalPlanAmount;

    /**
     * 已完成计划量:自己估算
     */
    @Column(name = "finish_plan_amount")
    private Integer finishPlanAmount;

    /**
     * 说明
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取计划类型：年/季/月/周
     *
     * @return type - 计划类型：年/季/月/周
     */
    public String getType() {
        return type;
    }

    /**
     * 设置计划类型：年/季/月/周
     *
     * @param type 计划类型：年/季/月/周
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取计划主题
     *
     * @return title - 计划主题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置计划主题
     *
     * @param title 计划主题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取开始时间：年月日时分秒
     *
     * @return start_time - 开始时间：年月日时分秒
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间：年月日时分秒
     *
     * @param startTime 开始时间：年月日时分秒
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间：年月日时分秒
     *
     * @return end_time - 结束时间：年月日时分秒
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间：年月日时分秒
     *
     * @param endTime 结束时间：年月日时分秒
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取状态，1：计划，2：执行，3：暂停，4，延后：5：完成，
     *
     * @return plan_state - 状态，1：计划，2：执行，3：暂停，4，延后：5：完成，
     */
    public String getPlanState() {
        return planState;
    }

    /**
     * 设置状态，1：计划，2：执行，3：暂停，4，延后：5：完成，
     *
     * @param planState 状态，1：计划，2：执行，3：暂停，4，延后：5：完成，
     */
    public void setPlanState(String planState) {
        this.planState = planState;
    }

    /**
     * 获取耗时
     *
     * @return use_time - 耗时
     */
    public Long getUseTime() {
        return useTime;
    }

    /**
     * 设置耗时
     *
     * @param useTime 耗时
     */
    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    /**
     * 获取计划量:比如，书籍多少页，视频多少集，可进行量化的量或者某个计划分了1，2，3，4个阶段，共4阶段，完成到几阶段
     *
     * @return total_plan_amount - 计划量:比如，书籍多少页，视频多少集，可进行量化的量或者某个计划分了1，2，3，4个阶段，共4阶段，完成到几阶段
     */
    public Integer getTotalPlanAmount() {
        return totalPlanAmount;
    }

    /**
     * 设置计划量:比如，书籍多少页，视频多少集，可进行量化的量或者某个计划分了1，2，3，4个阶段，共4阶段，完成到几阶段
     *
     * @param totalPlanAmount 计划量:比如，书籍多少页，视频多少集，可进行量化的量或者某个计划分了1，2，3，4个阶段，共4阶段，完成到几阶段
     */
    public void setTotalPlanAmount(Integer totalPlanAmount) {
        this.totalPlanAmount = totalPlanAmount;
    }

    /**
     * 获取已完成计划量:自己估算
     *
     * @return finish_plan_amount - 已完成计划量:自己估算
     */
    public Integer getFinishPlanAmount() {
        return finishPlanAmount;
    }

    /**
     * 设置已完成计划量:自己估算
     *
     * @param finishPlanAmount 已完成计划量:自己估算
     */
    public void setFinishPlanAmount(Integer finishPlanAmount) {
        this.finishPlanAmount = finishPlanAmount;
    }

    /**
     * 获取说明
     *
     * @return remark - 说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置说明
     *
     * @param remark 说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
