package com.time.plan.validator;

import com.time.plan.model.SysPlanDayExecution;
import com.time.plan.util.ValidatorUtil;
import org.junit.Test;

import java.time.LocalDateTime;

public class DateComparetorConstraintValidatorTest {

    @Test
    public void test(){
        SysPlanDayExecution plan = new SysPlanDayExecution();

        LocalDateTime start = LocalDateTime.of(2019, 9, 2, 0, 0, 0);
        plan.setStartTime(start);
        LocalDateTime end = LocalDateTime.of(2019, 9, 1, 0, 0, 0);
        plan.setEndTime(end);

        ValidatorUtil.validator(plan);
    }

    @Test
    public void testDate(){


    }


}
