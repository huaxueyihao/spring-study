package com.time.plan.validator;

import com.time.plan.exception.BusinessException;
import com.time.plan.validator.annotation.ComparetorGroup;
import com.time.plan.validator.annotation.DateComparetor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.util.Date;


public class DateComparetorConstraintValidator implements ConstraintValidator<DateComparetor, Object> {


    private ComparetorGroup[] comparetorGroups;


    @Override
    public void initialize(DateComparetor constraintAnnotation) {
        this.comparetorGroups = constraintAnnotation.fieldGroup();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        System.out.println(obj);
        if (obj == null) {
            return true;
        }
        Class<?> clazz = obj.getClass();
        for (ComparetorGroup group : comparetorGroups) {
            String beforeField = group.beforeField();
            String afterField = group.afterField();
            long subValue = group.subValue();

            try {
                Field bField = clazz.getDeclaredField(beforeField);
                Field aField = clazz.getDeclaredField(afterField);
                long atime = getFieldValue(aField, obj);
                long btime = getFieldValue(bField, obj);
                long sub = atime - btime;
                if (sub < subValue) {
                    context.buildConstraintViolationWithTemplate(group.message()).addBeanNode().addConstraintViolation();
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private long getFieldValue(Field field, Object obj) throws IllegalAccessException {
        Class<?> type = field.getType();
        field.setAccessible(true);
        Object value = field.get(obj);

        if (type == LocalDateTime.class) {
            LocalDateTime localDateTime = (LocalDateTime) value;
            return localDateTime.toEpochSecond(ZoneOffset.of("+8"));
        }

        if (type == LocalDate.class) {
            LocalDate localDate = (LocalDate) value;
            LocalDateTime localDateTime = localDate.atTime(0, 0, 0);
            return localDateTime.toEpochSecond(ZoneOffset.of("+8"));
        }
        if (type == Date.class) {
            Date date = (Date) value;
            return date.getTime();
        }
        return 0;
    }
}
