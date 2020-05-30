package com.luis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

//①设置运行器为参数化运行器
@RunWith(Parameterized.class)
public class ParameterizedJunitDemoTest {
    private boolean expect;
    private int input;

    //②input,expect的順序很重要。
    public ParameterizedJunitDemoTest(int input, boolean expect) {
        this.expect = expect;
        this.input = input;
    }

    //③产生测试数据，返回类型必须为Collection
    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{{1, true}, {2, false}, {4, false}});
    }

    //④测试方法
    @Test
    public void checkTest() {
        Assert.assertEquals(expect, new JunitDemo().checkOdd(input));
    }
}
