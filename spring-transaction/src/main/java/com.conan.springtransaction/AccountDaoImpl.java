package com.conan.springtransaction;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {


    public void outMoney(String name, Double money) {

        String sql = "update account set money = money - ? where name = ? ";
        this.getJdbcTemplate().update(sql, money, name);

    }

    public void inMoney(String name, Double money) {
        String sql = "update account set money = money + ? where name = ? ";
        this.getJdbcTemplate().update(sql, money, name);
    }
}
