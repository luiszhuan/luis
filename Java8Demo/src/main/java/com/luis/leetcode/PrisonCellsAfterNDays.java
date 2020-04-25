package com.luis.leetcode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/prison-cells-after-n-days/solution/n-tian-hou-de-lao-fang-by-leetcode/
public class PrisonCellsAfterNDays {
    public static void main(String[] args) {
        int[] input = new int[]{0, 1, 0, 1, 1, 0, 0, 1};
        System.out.println(new PrisonCellsAfterNDays().prisonAfterNDays(input, 7));
    }

    public int[] prisonAfterNDays(int[] cells, int n) {
        //convert to integer
        int state = 0;
        for (int i = 0; i < cells.length; i++) {
            state = (state ^ cells[i]) << 1;
        }
        state = state >>> 1;
        Map<Integer, Integer> loop = new HashMap<>();
        int nextState = nextDay(state, cells.length);
        int days = 0;
        while (!loop.containsKey(nextState)) {
            loop.put(nextState, ++days);
            nextState = nextDay(nextState, cells.length);
        }
        n = n % days == 0 ? days : n % days;
        for (Map.Entry<Integer, Integer> entry : loop.entrySet()) {
            if (n == entry.getValue()) {
                return convertResult(entry.getKey(), cells.length);
            }
        }
        return null;
    }

    private int[] convertResult(Integer key, int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[length - 1 - i] = (key >> i) & 1;
        }
        return result;
    }

    public int nextDay(int state, int length) {
        int result = 0;
        for (int i = 1; i < length - 1; i++)
            if (((state >> (i - 1)) & 1) == ((state >> (i + 1)) & 1)) {
                result = result ^ (1 << i);
            }
        return result;
    }
}

