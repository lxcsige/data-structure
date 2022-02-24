package com.dp.algorithm.dp.knapsack.knapsackNP;

/**
 * 完全背包，不限制每件物品的数量
 *
 * @author xucheng.liu
 * @since 2022/2/11
 */
public class KnapsackNp {

    public static void main(String[] args) {
        System.out.print(maxValue(5, 20, new int[]{5,6,7,8,9}, new int[]{10, 7, 9, 8, 12}));
    }

    /**
     * dp[i][j] = max(dp[i-1][j], dp[i-1][j-k*w[i-1]]+k*c[i-1])， 0 <= k <= j/w[i-1]
     * 状态参数：物品和容量，决策：对于当前物品，装几个
     *
     * @param n 物品数量
     * @param v 背包容量
     * @param w 物品重量数组
     * @param c 物品价值数组
     * @return
     */
    public static int maxValue(int n, int v, int[] w, int[] c) {
        int[][] dp = new int[n+1][v+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= v; j++) {
                dp[i][j] = dp[i-1][j];
                for (int k = 0; k <= j/w[i-1]; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-k*w[i-1]]+k*c[i-1]);
                }
            }
        }

        return dp[n][v];
    }

    /**
     * dp[i][j] = max(dp[i-1][j], dp[i][j-w[i-1]]+c[i-1])
     * 决策：对于当前物品，放入还是不放
     *
     * @param n 物品数量
     * @param v 背包容量
     * @param w 物品重量数组
     * @param c 物品价值数组
     * @return
     */
    public static int maxValue2(int n, int v, int[] w, int[] c) {
        int[][] dp = new int[n+1][v+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= v; j++) {
                if (j < w[i-1]) {
                    // 不放入
                    dp[i][j] = dp[i-1][j];
                } else {
                    // 不放入：dp[i-1][j]
                    // 放入：dp[i][j-w[i-1]]+c[i-1]
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-w[i-1]]+c[i-1]);
                }
            }
        }

        return dp[n][v];
    }

    /**
     * 状态压缩，依赖上一行和左侧的状态
     * dp[j] = max(dp[j], dp[j-w[i-1]]+c[i-1])
     *
     * @param n 物品数量
     * @param v 背包容量
     * @param w 物品重量数组
     * @param c 物品价值数组
     * @return
     */
    public static int maxValue3(int n, int v, int[] w, int[] c) {
        int[] dp = new int[v+1];

        for (int i = 1; i <= n; i++) {
            // 与0-1背包不同的是遍历顺序不变
            for (int j = 1; j <= v; j++) {
                if (j >= w[i-1]) {
                    dp[j] = Math.max(dp[j], dp[j - w[i - 1]] + c[i - 1]);
                }
            }
        }

        return dp[v];
    }
}
