package com.atwzh.sell.log;

import com.atwzh.sell.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangzihang
 * @createTime 2019/5/8
 * @description
 */
public class Testlogger extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(Testlogger.class);

    @Test
    public void testLogger() {
        logger.info("info...");
        logger.debug("debug...");
        logger.error("error...");
    }

}
