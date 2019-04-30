package com.conan.springt.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect()
public class ConanAspect {

    @Pointcut("execution(* com.conan.springt.beans.Bizlogic.save(*))")
    public void excuteSaveWithParamsPointcut() {

    }

    @Before(value = "execution(* com.conan.springt.beans.Bizlogic.save())")
    public void executeSaveBefore() {
        System.out.println("ConanAspect  before");
    }

    @Before(value = "excuteSaveWithParamsPointcut()")
    public void executeSaveWithParamsBefore() {
        System.out.println("ConanAspect  before with params");
    }

    @After(value = "execution(* com.conan.springt.beans.Bizlogic.save())")
    public void executeSaveAfter() {
        System.out.println("ConanAspect  After  我执行在return 之前 ,不管是否 正常/异常");
    }

    @After(value = "execution(* com.conan.springt.beans.Bizlogic.save(*))")
    public void executeSaveWithParamsAfter() {
        System.out.println("ConanAspect  After with params  我执行在return 之前 ,不管是否 正常/异常");
    }

    @AfterReturning(value = "execution(* com.conan.springt.beans.Bizlogic.save(*))")
    public void executeSaveWithParamsAfterReturning() {

        System.out.println("ConanAspect  executeSaveWithParamsAfterReturning  我执行在return 之后 ");

    }

    @AfterThrowing(value = "execution(* com.conan.springt.beans.Bizlogic.save(*))")
    public void executeSaveWithParamsAfterThrowing() {

        System.out.println("ConanAspect  executeSaveWithParamsAfterThrowing ");

    }

    @Around("execution(* com.conan.springt.beans.Bizlogic.save(*))")
    public Object executeSaveWithParamsAround(ProceedingJoinPoint proceedingJoinPoint) {

        Object proceed = null;
        try {

            System.out.println("ConanAspect  executeSaveWithParamsAround  在目标方法执行之前 ");
            proceed = proceedingJoinPoint.proceed();
            System.out.println("ConanAspect  executeSaveWithParamsAround  在目标方法执行之后");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return proceed;

    }


}
