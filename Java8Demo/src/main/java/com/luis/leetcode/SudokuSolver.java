package com.luis.leetcode;

// https://leetcode-cn.com/problems/sudoku-solver/
public class SudokuSolver {
    private static final int SIZE_9 = 9;
    private int[][] row;
    private int[][] col;
    private int[][] square;
    private boolean finished;
    private char[][] board;

    public void solveSudoku(char[][] board) {
        this.board = board;
        row = new int[SIZE_9][SIZE_9 + 1];
        col = new int[SIZE_9][SIZE_9 + 1];
        square = new int[SIZE_9][SIZE_9 + 1];

        for (int i = 0; i < SIZE_9; i++) {
            for (int j = 0; j < SIZE_9; j++) {
                if (board[i][j] != '.') {
                    int number = board[i][j] - '0';
                    putValue(i, j, number);
                }
            }
        }
        putNext(0, 0);
    }

    private boolean putAvailable(int number, int x, int y) {
        return row[x][number] + col[y][number] + square[getSquareNum(x, y)][number] == 0;
    }

    private int getSquareNum(int x, int y) {
        return x / 3 * 3 + y / 3;
    }

    private void putNext(int x, int y) {
        if (board[x][y] != '.') {
            if (x == (SIZE_9 - 1) && y == (SIZE_9 - 1)) {
                finished = true;
            } else if (y + 1 < SIZE_9) {
                putNext(x, y + 1);
            } else {
                putNext(x + 1, 0);
            }
            return;
        }
        for (int d = 1; d <= SIZE_9 && !finished; d++) {
            if (putAvailable(d, x, y)) {
                putValue(x, y, d);
                if (x == (SIZE_9 - 1) && y == (SIZE_9 - 1)) {
                    finished = true;
                } else if (y + 1 < SIZE_9) {
                    putNext(x, y + 1);
                } else {
                    putNext(x + 1, 0);
                }
                if (!finished) {
                    removeValue(x, y, d);
                }
            }
        }
    }

    private void removeValue(int x, int y, int d) {
        row[x][d]--;
        col[y][d]--;
        square[getSquareNum(x, y)][d]--;
        board[x][y] = '.';
    }

    private void putValue(int x, int y, int d) {
        row[x][d]++;
        col[y][d]++;
        square[getSquareNum(x, y)][d]++;
        board[x][y] = (char) (d + '0');
    }
}
