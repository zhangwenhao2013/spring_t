package com.conan.springtransaction;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void transfer(String out, String in, Double money) {
        accountDao.inMoney(in, money);
        accountDao.outMoney(out, money);
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
