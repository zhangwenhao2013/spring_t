package com.conan.springtransaction.service;

import com.conan.springtransaction.dao.AccountDaoImplStatement1;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
public class AccountServiceImplStatement3 implements AccountService {

    private AccountDaoImplStatement1 accountDao;

    public void transfer(String out, String in, Double money) {
        accountDao.inMoney(in, money);
        int i = 1 / 0;
        accountDao.outMoney(out, money);
    }

    public void setAccountDao(AccountDaoImplStatement1 accountDao) {
        this.accountDao = accountDao;
    }
}
