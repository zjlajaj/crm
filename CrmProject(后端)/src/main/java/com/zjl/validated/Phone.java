package com.zjl.validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValid.class)
public @interface Phone {

    String message() default "手机号格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
