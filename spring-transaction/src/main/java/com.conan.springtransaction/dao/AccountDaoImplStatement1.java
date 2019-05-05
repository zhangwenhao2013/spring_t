package com.conan.springtransaction.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDaoImplStatement1 extends JdbcDaoSupport implements AccountDao {


    public void outMoney(String name, Double money) {

        String sql = "update account set money = money - ? where name = ? ";
        this.getJdbcTemplate().update(sql, money, name);

    }

    public void inMoney(String name, Double money) {
        String sql = "update account set money = money + ? where name = ? ";
        this.getJdbcTemplate().update(sql, money, name);
    }
}
