package com.zjl.validated;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValid.class)
public @interface Email {
    String message() default "邮箱格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
