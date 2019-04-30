package com.conan.springt;


import com.conan.springt.beans.Bizlogic;
import com.conan.springt.introductioninterface.UsageTracked;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestXmlconfigSpringWithArgs extends BaseJunit {
    public TestXmlconfigSpringWithArgs() {
        super("classpath:spring-xml-config.xml");
    }


    @Test
    public void testExecuteBizlogicSaveBefore() {

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
