package com.conan.springtransaction;

import com.conan.springtransaction.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * spring 声明式事务管理 方式1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig_statement1.xml")
public class TestTransactionManagerStatement1 {

    @Resource(name = "transactionProxyFactoryBean")
    private AccountService accountService;

    @Test
    public void testTransactionManagerStatement1() {

        accountService.transfer("aaa", "bbb", 200d);

    }
}
