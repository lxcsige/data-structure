package com.dp.algorithm.dp.pathPlan;

/**
 * 63_不同路径2
 *
 * @author xucheng.liu
 * @since 2022/2/24
 */
public class UniquePathsWithObstacles {

    public static void main(String[] args) {

    }

    /**
     * 二维数组
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];
        // 初始状态
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = 1;
            } else {
                break;
            }
        }
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 0) {
                dp[0][j] = 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }

    /**
     * 压缩状态
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[] dp = new int[n];
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 对于第一行，若obstacleGrid[0][j] == 1，则dp[j] = 0
                // 否则dp[j] = dp[j-1] = 0+dp[j-1]，由于dp[j]初始为0，因此dp[j] = dp[j] + dp[j-1]，即dp[j] += dp[j-1]
                // dp[j] += dp[j-1]要求j>=1，因此需要初始化dp[0]
                // 对于1...m行，若obstacleGrid[i][j] == 1，同理dp[j] = 0
                // 否则在j>=1的前提下，dp[j] = dp[i-1][j] + dp[i][j-1]，dp[i-1][j]其实就等于更新前的dp[j]，dp[i][j-1]等于更新后的dp[j-1]
                // 由于正序遍历，在计算dp[j]时dp[j-1]已经更新，因此dp[j] += dp[j-1]
                // 此时还剩下obstacleGrid[i][j] == 0且j==0的情况，dp[j]==dp[i-1][j]，不需要进行更新
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j >= 1) {
                    dp[j] += dp[j-1];
                }
            }
        }

        return dp[n-1];
    }
}
