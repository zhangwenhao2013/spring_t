package com.conan.springtransaction.dao;

public interface AccountDao {

    public void outMoney(String name, Double money);

    public void inMoney(String name, Double money);
}
