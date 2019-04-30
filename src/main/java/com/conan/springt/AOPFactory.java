package com.conan.springt;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = "classpath:spring-xml-config.xml")
public class AOPFactory {

    public static AnnotationConfigApplicationContext getApplicationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AOPFactory.class);
        return context;
    }

}
