package com.conan.springt.beans;

import org.springframework.stereotype.Component;

@Component(value = "bizlogicimpl")
public class BizLogicImpl implements Bizlogic {
    public String save(String str) {
        String s = str.toUpperCase();
        System.out.println("这里是 BizLogicImpl 的 save 方法  " + s);
        return s;
    }
}
