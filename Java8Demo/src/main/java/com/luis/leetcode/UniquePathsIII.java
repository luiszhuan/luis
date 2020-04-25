package com.luis.leetcode;

import java.util.Objects;

//https://leetcode-cn.com/problems/unique-paths-iii/
public class UniquePathsIII {

    int[][] grid;
    Node start;
    Node end;
    int ans;
    int[] neighbourX = new int[]{1, 0, -1, 0};
    int[] neighbourY = new int[]{0, 1, 0, -1};
    int rowCount;
    int colCount;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        int todo = 0;
        rowCount = grid.length;
        colCount = grid[0].length;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (grid[i][j] == 0) {
                    todo++;
                } else if (grid[i][j] == 1) {
                    start = new Node(i, j);
                    todo++;
                } else if (grid[i][j] == 2) {
                    end = new Node(i, j);
                }
            }
        }
        dfs(start, todo);
        return ans;
    }

    private void dfs(Node cur, int todo) {
        if (todo == 0) {
            if (cur.equals(end)) {
                ans++;
            }
            return;
        }
        if (grid[cur.x][cur.y] == -1) {
            return;
        }
        todo--;
        grid[cur.x][cur.y] = 3;
        for (int i = 0; i < neighbourX.length; i++) {
            Node nextNode = new Node(cur.x + neighbourX[i], cur.y + neighbourY[i]);
            if (nextNode.validateNode() && (grid[nextNode.x][nextNode.y] == 0 || grid[nextNode.x][nextNode.y] == 2)) {
                dfs(nextNode, todo);
            }
        }
        grid[cur.x][cur.y] = 0;
    }

    class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean validateNode() {
            return this.x >= 0 && this.x < rowCount && this.y >= 0 && this.y < colCount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
