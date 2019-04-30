package com.conan.springt.introductioninterface;

public class UsageTrackedImpl implements UsageTracked {
    public String usageTracked(String str) {
        String s = "UsageTrackedImpl usageTracked " + str;
        System.out.println(s);
        return s;
    }
}
