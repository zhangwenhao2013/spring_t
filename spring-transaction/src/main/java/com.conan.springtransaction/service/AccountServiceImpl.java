package com.conan.springtransaction.service;

import com.conan.springtransaction.dao.AccountDao;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void transfer(String out, String in, Double money) {
        accountDao.inMoney(in, money);
//        int i = 1 / 0;
        accountDao.outMoney(out, money);
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
