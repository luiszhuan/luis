package com.luis.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class ContainerMostWaterTest {

    @Test
    public void maxArea1() {
        int[] input = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        Assert.assertEquals(49, new ContainerMostWater().maxArea(input));
    }

    @Test
    public void maxArea2() {
        int[] input = new int[]{1, 2, 3, 4};
        Assert.assertEquals(4, new ContainerMostWater().maxArea(input));
    }

    @Test
    public void maxArea3() {
        int[] input = new int[]{4, 3, 2, 1};
        Assert.assertEquals(4, new ContainerMostWater().maxArea(input));
    }

    @Test
    public void maxArea4() {
        int[] input = new int[]{1, 1};
        Assert.assertEquals(1, new ContainerMostWater().maxArea(input));
    }

    @Test
    public void maxArea5() {
        int[] input = new int[]{3, 2, 1, 1, 2, 3};
        Assert.assertEquals(15, new ContainerMostWater().maxArea(input));
    }

    @Test
    public void maxArea6() {
        int[] input = new int[]{1, 2, 3, 3, 2, 1};
        Assert.assertEquals(6, new ContainerMostWater().maxArea(input));
    }

    @Test
    public void maxArea7() {
        int[] input = new int[]{4, 4, 4};
        Assert.assertEquals(8, new ContainerMostWater().maxArea(input));
    }
}