package com.conan.springtransaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springconfig_program.xml")
public class TestTransactionManageProgram {

    @Resource(name = "accountService")
    private AccountService accountService;

    @Test
    public void testTransactionManagerProgram() {

        accountService.transfer("aaa", "bbb", 200d);

    }
}
