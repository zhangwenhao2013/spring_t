package com.conan.springt;


import com.conan.springt.beans.Bizlogic;
import com.conan.springt.beans2.Humen;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestXmlConfigSpring extends BaseJunit {
    public TestXmlConfigSpring() {
        super("classpath:spring-xml-config.xml");
    }

    @Test
    public void testBizlogicImpl() {

        Bizlogic bizlogicimpl = (Bizlogic) context.getBean("bizlogicimpl");
        bizlogicimpl.save("abcdefg");

    }


    @Test
    public void testHemen() {
        Humen humen = (Humen) context.getBean("humen");
        humen.say();

    }

    @Test
    public void testBizlogicImplExecuteBefore() {
        /**
         * 如果不配置proxy-target-class="true"的话 会使用jdk的动态代理方式;
         *
         * 如果设置了的话  ,会使用CGLIB代理方式; 可以直接获取接口的实现类;
         *
         * <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy>
         *
         */
        Bizlogic bizlogicimpl = (Bizlogic) context.getBean("bizlogicimpl");
        bizlogicimpl.save();
    }

    @Test
    public void testBizlogicSaveWithParamsAfterReturning() {
        Bizlogic bizlogicimpl = (Bizlogic) context.getBean("bizlogicimpl");
        bizlogicimpl.save("xyzasdf");
    }


    @Test
    public void testBizlogicSaveWithParamsAfterThrowing() {
        Bizlogic bizlogicimpl = (Bizlogic) context.getBean("bizlogicimpl");
        bizlogicimpl.save(null);
    }


    @Test
    public void testBizlogicSaveWithParamsAround() {
        Bizlogic bizlogicimpl = (Bizlogic) context.getBean("bizlogicimpl");
        bizlogicimpl.save("qwertyui");
    }


}
