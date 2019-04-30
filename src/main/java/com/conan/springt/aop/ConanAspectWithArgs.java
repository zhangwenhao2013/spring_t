package com.conan.springt.aop;

import com.conan.springt.annotation.Auditable;
import com.conan.springt.introductioninterface.UsageTracked;
import com.conan.springt.introductioninterface.UsageTrackedImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ConanAspectWithArgs {

    @Pointcut(value = "execution(* com.conan.springt.beans.Bizlogic.save(java.lang.String)) && args(ss) ")
    public void executePointcut(String ss) {

    }

    /**
     * @param sss 获取方法传入参数,可以指定运行时环境 传入的具体参数
     *            <p>
     *            argNames = "sss" 指定注解方法的参数名 如果有多个 就是 && args(a) && args(b)
     */
    @Before(value = "execution(* com.conan.springt.beans.Bizlogic.save(java.lang.String)) && args(sss))"
            , argNames = "sss") // 可以拿到传入参数的值, argNames = "sss" 指定注解方法的参数名, 参数类型必须和pointcut 中定义的一致;
    public void executeSaveBefore(String sss) {
        System.out.println(" executeSaveBefore1  " + sss);
    }

    @Before(value = "executePointcut(sss)"
            , argNames = "sss") // 可以拿到传入参数的值, argNames = "sss" 指定注解方法的参数名, 参数类型必须和pointcut 中定义的一致;
    public void executeSaveBefore2(String sss) {
        System.out.println(" executeSaveBefore2  " + sss);
    }


    /**
     * @param auditable 通过注解 注解方法,可以手动控制传入advice的参数逻辑
     */
    @Before(value = "execution(* com.conan.springt.beans.Bizlogic.save(java.lang.String)) && @annotation(auditable))"
            , argNames = "auditable") // 可以拿到传入参数的值, argNames = "sss" 指定注解方法的参数名, 参数类型必须和pointcut 中定义的一致;
    public void executeSaveBefore3(Auditable auditable) {
        String value = auditable.value();
        System.out.println(" executeSaveBefore3  " + value);
    }

    @AfterReturning(value = "execution(* com.conan.springt.beans.Bizlogic.save(java.lang.String)))"
            , returning = "returnValue") //可以拿到返回值
    public void executeSaveAfterReturning(Object returnValue) {

        String str = (String) returnValue;

        System.out.println(" executeSaveAfterReturning " + str);
    }

    @AfterThrowing(value = "execution(* com.conan.springt.beans.Bizlogic.save(java.lang.String)))"
            , throwing = "ex") //可以拿到抛出的异常
    public void executeSaveAfterThrowing(Exception ex) {
        System.out.println(" executeSaveAfterThrowing " + ex.getStackTrace());
    }

    @Around(value = "execution(* com.conan.springt.beans.Bizlogic.save(java.lang.String))) && args(objects)")
    public Object executeSaveAround(ProceedingJoinPoint proceedingJoinPoint) {

        Object proceed = null;
        try {
            System.out.println(" executeSaveAround  前面");
            proceed = proceedingJoinPoint.proceed();
            System.out.println(" executeSaveAround  后面");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;

    }


    @DeclareParents(value = "com.conan.springt.beans.BizLogicImpl+" // xxxx + 表示对 xxxx 这个类的增强;
            , defaultImpl = UsageTrackedImpl.class)                 // 增强的默认实现
    public UsageTracked mixin;


}
