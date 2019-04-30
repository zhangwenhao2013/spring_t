package com.conan.springt.beans2;

import org.springframework.stereotype.Component;

@Component(value = "humen")
public class Humen {

    public void say() {
        System.out.println("Humen say  Hello");
    }
}
