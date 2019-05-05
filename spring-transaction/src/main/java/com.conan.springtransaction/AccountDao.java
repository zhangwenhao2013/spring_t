package com.conan.springtransaction;

public interface AccountDao {

    public void outMoney(String name, Double money);

    public void inMoney(String name, Double money);
}
