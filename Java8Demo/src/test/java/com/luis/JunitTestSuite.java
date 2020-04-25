package com.luis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        ParameterizedJunitDemoTest.class,
        JunitTest.class
})
public class JunitTestSuite {
}
