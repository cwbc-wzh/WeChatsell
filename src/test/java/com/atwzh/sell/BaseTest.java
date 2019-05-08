package com.atwzh.sell;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author wangzihang
 * @createTime 2019/5/8
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseTest {

    private Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Before
    public void before() {
        logger.info("=======================开始测试=========================");
    }

    @After
    public void after() {
        logger.info("=======================结束测试=========================");
    }

}
