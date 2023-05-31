package com.dp.algorithm.dfs;

/**
 * leetcode_695_岛屿的最大面积_中等
 */
public class MaxAreaOfIsland {

    /**
     * dfs
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, area(grid, i, j));
                }
            }
        }
        return res;
    }

    private int area(int[][] grid, int r, int c) {
        // base case
        // 1. 不在矩阵中
        if (!inArea(grid, r, c)) {
            return 0;
        }
        // 2. 海洋格子或已经遍历过的陆地格子
        if (grid[r][c] != 1) {
            return 0;
        }
        // 标记该陆地格子已经遍历过
        grid[r][c] = 2;
        // dfs
        return 1 + area(grid, r + 1, c)
                + area(grid, r - 1, c)
                + area(grid, r, c + 1)
                + area(grid, r, c - 1);
    }

    private boolean inArea(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }
}
