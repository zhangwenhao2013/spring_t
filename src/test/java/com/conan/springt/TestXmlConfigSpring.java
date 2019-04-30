package com.conan.springt;


import com.conan.springt.beans.BizLogicImpl;
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

        BizLogicImpl bizlogicimpl = (BizLogicImpl) context.getBean("bizlogicimpl");
        bizlogicimpl.save("abcdefg");

    }


    @Test
    public void testHemen() {
        Humen humen = (Humen) context.getBean("humen");
        humen.say();

    }
}
