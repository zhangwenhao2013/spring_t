package com.conan.springtransaction;

import com.conan.springtransaction.service.AccountService;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig.xml")
public class SpringDemo1 {

    @Resource(name = "accountService")
    private AccountService accountService;


    @Resource(name = "dataSource")
    private ComboPooledDataSource dataSource;

    @Test
    public void testDemo1() {
        accountService.transfer("aaa", "bbb", 200d);
    }
}
