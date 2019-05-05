package com.conan.springtransaction.service;

public interface AccountService {

    public void transfer(String out, String in, Double money);
}
