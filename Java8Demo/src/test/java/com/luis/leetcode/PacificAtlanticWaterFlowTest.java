package com.luis.leetcode;

import org.junit.Test;

import java.util.List;

public class PacificAtlanticWaterFlowTest {

    @Test
    public void pacificAtlantic() {
        int[][] matrix = new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}};
        List<List<Integer>> result = new PacificAtlanticWaterFlow().pacificAtlantic(matrix);
        System.out.println(result);
    }
}