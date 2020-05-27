package com.luis.leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/pacific-atlantic-water-flow/
 */
public class PacificAtlanticWaterFlow {
    private static final int neighbour[][] = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m;
    private int n;
    boolean[][] atlanticVisited;
    boolean[][] pacificVisited;
    Deque<Point> atlanticDeque;
    Deque<Point> pacificDeque;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return Collections.emptyList();
        }
        // 分别从大西洋和太平洋广度优先便利
        m = matrix.length;
        n = matrix[0].length;
        atlanticDeque = new ArrayDeque<Point>();
        pacificDeque = new ArrayDeque<Point>();
        for (int i = 0; i < m; i++) {
            atlanticDeque.add(new Point(i, 0));
            atlanticVisited[i][0] = true;
            pacificDeque.add(new Point(i, n - 1));
            pacificVisited[i][n - 1] = true;
        }
        for (int i = 1; i < n - 1; i++) {
            atlanticDeque.add(new Point(0, i));
            atlanticVisited[0][i] = true;
            pacificDeque.add(new Point(m - 1, i));
            pacificVisited[m - 1][i] = true;
        }
        atlanticDeque.add(new Point(0, n - 1));
        atlanticVisited[0][n - 1] = true;
        pacificDeque.add(new Point(m - 1, 0));
        pacificVisited[m - 1][0] = true;
        while (!atlanticDeque.isEmpty()) {
            Point cur = atlanticDeque.poll();
            for (int i = 0; i < neighbour.length; i++) {
                int x = cur.x + neighbour[i][0];
                int y = cur.y + neighbour[i][1];
                if (isValidatePoint(x, y) && !atlanticVisited[x][y] && matrix[cur.x][cur.y] <= matrix[x][y]) {
                    atlanticDeque.add(new Point(x, y));
                    atlanticVisited[x][y] = true;
                }
            }
        }
        while (!pacificDeque.isEmpty()) {
            Point cur = pacificDeque.poll();
            for (int i = 0; i < neighbour.length; i++) {
                int x = cur.x + neighbour[i][0];
                int y = cur.y + neighbour[i][1];
                if (isValidatePoint(x, y) && !pacificVisited[x][y] && matrix[cur.x][cur.y] <= matrix[x][y]) {
                    atlanticDeque.add(new Point(x, y));
                    pacificVisited[x][y] = true;
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (atlanticVisited[i][j] && pacificVisited[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private boolean[][] getFalseMatrix() {
        boolean[][] result = new boolean[m][n];
        for (int i = 0; i < result.length; i++) {
            Arrays.fill(result[i], false);
        }
        return result;
    }

    private boolean isValidatePoint(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
