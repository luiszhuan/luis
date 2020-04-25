package com.luis.leetcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JumpGameIITest {

    @Test
    public void jump0() {
        int[] a = new int[]{2, 3, 1, 1, 4};
        Assert.assertEquals(2, new JumpGameII().jump(a));
    }

    @Test
    public void jump1() {
        int[] a = new int[]{2, 3, 1};
        Assert.assertEquals(1, new JumpGameII().jump(a));
    }

    @Test
    public void jump2() {
        int[] a = new int[]{1, 2, 1, 1, 1};
        Assert.assertEquals(3, new JumpGameII().jump(a));
    }

    @Test
    public void jump3() {
        int[] a = new int[]{1, 1, 1, 1, 1};
        Assert.assertEquals(4, new JumpGameII().jump(a));
    }

    @Test
    public void jump4() {
        int[] a = new int[]{4, 1, 1, 3, 1, 1, 1};
        Assert.assertEquals(2, new JumpGameII().jump(a));
    }

    @Test
    public void jump5() {
        int[] a = new int[]{0};
        Assert.assertEquals(0, new JumpGameII().jump(a));
    }
    @Test
    public void jump6() {
        int[] a = new int[]{2, 1, 1, 1, 1};
        Assert.assertEquals(3, new JumpGameII().jump(a));
    }    @Test
    public void jump2_1() {
        int[] a = new int[]{7,0,9,6,9,6,1,7,9,0,1,2,9,0,3};
        Assert.assertEquals(3, new JumpGameII().jump2(a));
    }
}