package com.conan.springt.beans;

import com.conan.springt.annotation.Auditable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "bizlogicimpl")
public class BizLogicImpl implements Bizlogic {

    @Auditable(value = "这是通过注解传入的参数")
    public String save(String str) {
        String s = str.toUpperCase();
        System.out.println("这里是 BizLogicImpl 的 save 方法  " + s);
        return s;
    }

    public void save() {
        System.out.println("这里是 BizLogicImpl 的 save 方法  无参方法" );
    }
}
