package com.time.plan.service.date;

/**
 * |年
 * |--季
 * |--|--月
 * |--|--|--周
 * <p>
 * |年
 * |--月
 * |--|--周
 */
public interface DateTreeGenerator {

    /**
     *
     * @param year 生成某一年
     * @param type 类型
     */
    void generate(int year, int type);



}
