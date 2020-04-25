package com.luis.leetcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UniquePathsIIITest {

    @Test
    public void uniquePathsIII0() {
        int[][] grid = new int[][]{{1, 0, 2}};
        Assert.assertEquals(1, new UniquePathsIII().uniquePathsIII(grid));
    }    @Test
    public void uniquePathsIII1() {
        int[][] grid = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        Assert.assertEquals(2, new UniquePathsIII().uniquePathsIII(grid));
    }

    @Test
    public void uniquePathsIII2() {
        int[][] grid = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
        Assert.assertEquals(4, new UniquePathsIII().uniquePathsIII(grid));
    }
}