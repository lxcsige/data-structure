package com.dp.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_54_螺旋矩阵_中等
 */
public class SpiralOrder {

    public List<Integer> spiralOrder(int[][] matrix) {
        // 行数
        int m = matrix.length;
        // 列数
        int n = matrix[0].length;
        int total = m * n;
        List<Integer> res = new ArrayList<>();
        // 遍历指针
        int row = 0, column = 0;
        // 遍历方向，分别是向右、向下、向左、向上
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // 初始遍历方向，向右
        int directionIdx = 0;
        // visited[i][j]表示matrix[i][j]是否遍历过，防止重复遍历
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < total; i++) {
            res.add(matrix[row][column]);
            visited[row][column] = true;
            int newRow = row + directions[directionIdx][0];
            int newColumn = column + directions[directionIdx][1];
            // 越界或已经访问过，改变遍历方向
            if (newRow >= m || newRow < 0 || newColumn >= n || newColumn < 0
                    || visited[newRow][newColumn]) {
                directionIdx = (directionIdx + 1) % 4;
                newRow = row + directions[directionIdx][0];
                newColumn = column + directions[directionIdx][1];
            }
            row = newRow;
            column = newColumn;
        }
        return res;
    }
}
