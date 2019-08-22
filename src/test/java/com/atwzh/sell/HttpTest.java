package com.atwzh.sell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangzihang
 * @createTime 2019/8/12
 * @description
 */
//@SpringBootTest
@RunWith(SpringRunner.class)
public class HttpTest {

    @Test
    public void test() {

        int start = 590415031 + 500000;

        for(int w = 0; w < 10; w++) {

            String starts = "<Request><items>\n";
            String ends = "</items></Request>";
            String a = "<item><nodeId>37100017</nodeId><bookId>";
            String b = "</bookId><action>0</action></item>\n";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                start++;
                sb.append(a + start + b);
            }

            String url = "http://10.211.93.216:9088/hybc/notify/bookOnOffShelfChange";

            String xml = starts + sb.toString() + ends;

            try {
                String s = com.missfresh.antispam.sdk.utils.HttpClientUtil.httpPostXMLDataRequest(url, xml);
                System.out.println(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
