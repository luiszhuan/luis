package com.luis.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class MaxEqualSubstringsWithinBudgetTest {

    @Test
    public void equalSubstring1() {
        Assert.assertEquals(3, new MaxEqualSubstringsWithinBudget().equalSubstring("abcd", "bcdf", 3));
    }

    @Test
    public void equalSubstring2() {
        Assert.assertEquals(1, new MaxEqualSubstringsWithinBudget().equalSubstring("abcd", "cdef", 3));
    }

    @Test
    public void equalSubstring3() {
        Assert.assertEquals(1, new MaxEqualSubstringsWithinBudget().equalSubstring("abcd", "acde", 0));
    }

    @Test
    public void equalSubstring4() {
        Assert.assertEquals(1, new MaxEqualSubstringsWithinBudget().equalSubstring("a", "b", 1));
    }

    @Test
    public void equalSubstring5() {
        Assert.assertEquals(1, new MaxEqualSubstringsWithinBudget().equalSubstring("ab", "bz", 1));
    }
    @Test
    public void equalSubstring5_1() {
        Assert.assertEquals(2, new MaxEqualSubstringsWithinBudget().equalSubstring("azbc", "aabc", 1));
    }

    @Test
    public void equalSubstring6() {
        Assert.assertEquals(3, new MaxEqualSubstringsWithinBudget().equalSubstring("aabc", "zbcd", 3));
    }
    @Test
    public void equalSubstring7() {
        Assert.assertEquals(3, new MaxEqualSubstringsWithinBudget().equalSubstring("aabc", "abcz", 3));
    }

    @Test
    public void equalSubstring8() {
        Assert.assertEquals(4, new MaxEqualSubstringsWithinBudget().equalSubstring("abacdef", "bczdefg", 4));
    }
    @Test
    public void equalSubstring9() {
        Assert.assertEquals(0, new MaxEqualSubstringsWithinBudget().equalSubstring("abcd", "cdef", 1));
    }
}