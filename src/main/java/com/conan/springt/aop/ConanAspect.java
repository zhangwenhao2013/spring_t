package com.conan.springt.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect()
public class ConanAspect {

    @Before(value = "execution(* com.conan.springt.beans.BizLogicImpl.save())")
    public void executeSaveBefore() {
        System.out.println("ConanAspect  before");
    }

    @Before(value = "execution(* com.conan.springt.beans.BizLogicImpl.save(*))")
    public void executeSaveWithParamsBefore() {
        System.out.println("ConanAspect  before with params");
    }

    @After(value = "execution(* com.conan.springt.beans.BizLogicImpl.save())")
    public void executeSaveAfter() {
        System.out.println("ConanAspect  After");
    }

    @After(value = "execution(* com.conan.springt.beans.BizLogicImpl.save(*))")
    public void executeSaveWithParamsAfter() {
        System.out.println("ConanAspect  After with params");
    }

}
