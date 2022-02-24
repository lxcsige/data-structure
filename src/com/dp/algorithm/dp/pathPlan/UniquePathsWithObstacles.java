package com.dp.algorithm.dp.pathPlan;

/**
 * 问题：一个机器人位于一个 m * n 网格的左上角，每次只能向下或者向右移动一步，现在机器人试图达到网格的右下角，考虑网格中有障碍物，那么从左上角到右下角将会有多少条不同的路径？
 *
 * @author liuxucheng
 * @since 2022/2/23
 */
public class GetPathCountWithBlocks {

    public static void main(String[] args) {

    }

    public static int getPathCountWithBlocks(int[][] v) {
        int m = v.length;
        int n = v[0].length;

        int[][] dp = new int[m][n];
        // 初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] =
        }
    }
}
