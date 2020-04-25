package com.luis;


import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class JunitTest {

    //运行时间测试
    @Test(timeout = 2 * 1000L)
    public void testTime() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //抛出指定异常测试
    @Test(expected = ArithmeticException.class)
    public void testException() {
        System.out.println(1 / 0);
    }
}
