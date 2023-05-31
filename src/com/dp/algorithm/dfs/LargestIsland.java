package com.dp.algorithm.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * leetcode_827_最大人工岛_困难
 */
public class LargestIsland {

    public int largestIsland(int[][] grid) {
        Map<Integer, Integer> areas = new HashMap<>();
        int index = 2;
        // dfs计算岛屿面积
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    areas.put(index, area(grid, i, j, index));
                    index++;
                }
            }
        }
        int res = 0;
        // 遍历海洋格子
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> islands = getIslands(grid, i, j);
                    int tmpArea = 1;
                    for (int island : islands) {
                        tmpArea += areas.get(island);
                    }
                    res = Math.max(res, tmpArea);
                }
            }
        }
        // 全是岛屿
        if (res == 0) {
            res = areas.get(2);
        }
        return res;
    }

    private int area(int[][] grid, int r, int c, int index) {
        // base case
        if (!inArea(grid, r, c)) {
            return 0;
        }
        // 海洋格子或已经遍历过的岛屿格子
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = index;
        return 1 + area(grid, r + 1, c, index) + area(grid, r - 1, c, index) + area(grid, r, c + 1, index) + area(grid, r, c - 1, index);
    }

    private boolean inArea(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }

    public Set<Integer> getIslands(int[][] grid, int r, int c) {
        Set<Integer> result = new HashSet<>();
        if (inArea(grid, r + 1, c) && grid[r + 1][c] != 0) {
            result.add(grid[r + 1][c]);
        }
        if (inArea(grid, r - 1, c) && grid[r - 1][c] != 0) {
            result.add(grid[r - 1][c]);
        }
        if (inArea(grid, r, c - 1) && grid[r][c - 1] != 0) {
            result.add(grid[r][c - 1]);
        }
        if (inArea(grid, r, c + 1) && grid[r][c + 1] != 0) {
            result.add(grid[r][c + 1]);
        }
        return result;
    }
}
