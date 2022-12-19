package com.zjl.validated;

import com.alibaba.druid.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValid implements ConstraintValidator<Email,String> {
    @Override
    public void initialize(Email constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!StringUtils.isEmpty(s)) {
            return isEmail(s);
        }else{
            return false;
        }
    }
    public static boolean isEmail(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String s2="^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";// 验证邮箱
        if(!StringUtils.isEmpty(str)){
            p = Pattern.compile(s2);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }
}
