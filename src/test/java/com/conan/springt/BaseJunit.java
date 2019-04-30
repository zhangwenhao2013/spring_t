package com.conan.springt;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

public class BaseJunit {

    private String xmlParth;
    protected ClassPathXmlApplicationContext context;

    public BaseJunit(String xmlParth) {

        this.xmlParth = xmlParth;
    }

    @Before
    public void before() {
        System.out.println("===================before==================");
        if (StringUtils.isEmpty(xmlParth)) {
            xmlParth = "classpath*:spring-*.xml";
        }

        context = new ClassPathXmlApplicationContext(xmlParth);
    }

    public void after() {
        context.close();
        System.out.println("===================after===================");
    }
}
