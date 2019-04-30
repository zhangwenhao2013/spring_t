package com.conan.springt;

import com.conan.springt.beans.Bizlogic;
import com.conan.springt.introductioninterface.UsageTracked;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAopFactory {

    private AnnotationConfigApplicationContext context;

    @Before
    public void before() {

        context = AOPFactory.getApplicationContext();

    }

    @After
    public void after() {
        context.close();
    }

    @Test
    public void testBefore() {
        Bizlogic bizlogicimpl = (Bizlogic) context.getBean("bizlogicimpl");
        bizlogicimpl.save("abcdefg");
    }

    @Test
    public void testExecuteBizlogicSaveAfterReturning() {

        Bizlogic bizlogicimpl = (Bizlogic) context.getBean("bizlogicimpl");
        bizlogicimpl.save("abcdefg");

    }

    @Test
    public void testExecuteBizlogicSaveAfterThrowing() {
        Bizlogic bizlogicimpl = (Bizlogic) context.getBean("bizlogicimpl");
        bizlogicimpl.save(null);

    }

    @Test
    public void testExecuteBizlogicSaveAround() {

        Bizlogic bizlogicimpl = (Bizlogic) context.getBean("bizlogicimpl");
        bizlogicimpl.save("abcdefg");

    }


    /**
     * Introduction 实现增强某个类的功能
     */
    @Test
    public void testExecuteBizlogicIntroduction() {
//        Bizlogic bizlogicimpl = (Bizlogic) context.getBean("bizlogicimpl");
//        bizlogicimpl.save("abcdefg");
//        UsageTracked  usageTracked = (UsageTracked) bizlogicimpl;
//        usageTracked.usageTracked("abcdefg");

        UsageTracked usageTracked = (UsageTracked) context.getBean("bizlogicimpl");
        usageTracked.usageTracked("abcdefg");

    }

}
