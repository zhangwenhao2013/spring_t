package com.conan.springtransaction.service;

import com.conan.springtransaction.dao.AccountDao;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class AccountServiceImplPragram implements AccountService {

    private AccountDao accountDao;
    private TransactionTemplate transactionTemplate;

    public void transfer(final String out, final String in, final Double money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                accountDao.inMoney(in, money);
//                int i = 1 / 0;
                accountDao.outMoney(out, money);
            }
        });

    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
