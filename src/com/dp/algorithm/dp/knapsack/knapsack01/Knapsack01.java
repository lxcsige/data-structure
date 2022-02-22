package com.dp.algorithm.dp.knapsack.knapsack01;

/**
 * 0-1背包
 *
 * @author liuxucheng
 * @since 2021/6/6
 */
public class Knapsack01 {

    public static void main(String[] args) {
        Knapsack01 test = new Knapsack01();
        System.out.print(test.maxValue(5, 20, new int[]{5,6,7,8,9}, new int[]{10, 7, 9, 8, 12}));
    }

    /**
     * dp[i][j] = max(dp[i-1][j], dp[i-1][j-w[i-1]] + c[i-1])，j >= w[i-1]
     * 状态参数：物品和容量，决策：装/不装
     *
     * @param n 物品数量
     * @param v 背包容量
     * @param w 物品重量数组
     * @param c 物品价值数组
     * @return
     */
    public int maxValue(int n, int v, int[] w, int[] c) {
        // dp[i][j]表示前i个物品放到最大容量为j的背包里的最大价值
        int[][] dp = new int[n+1][v+1];
        // 遍历物品
        for (int i = 1; i <= n; i++) {
            // 遍历背包容量
            for (int j = 1; j <= v; j++) {
                // w[i-1]表示第i件物品的重量，如果背包容量小于该物品的重量，则不能放入
                if (j < w[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    // 不放入：dp[i][j] = dp[i-1][j]
                    // 放入：dp[i][j] = dp[i-1][j-w[i-1]]+c[i-1]
                    // 对于j == w[i-1]的情况，可以放入，此时总价值 = 放入之前的价值0（放入之前背包容量为0） + 该物品的价值
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i-1]] + c[i-1]);
                }
            }
        }

        // 哪些物品被放入
        int[] item = new int[n];
        for (int i = n; i > 0;) {
            for (int j = v; j > 0;) {
                // 不放入，标记为0
                if (dp[i][j] == dp[i-1][j]) {
                    item[--i] = 0;
                } else if (j >= w[i-1] && dp[i][j] == dp[i-1][j-w[i-1]] + c[i-1]) {
                    // 放入，标记为1
                    j -= w[i -1];
                    item[--i] = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(item[i]);
        }

        return dp[n][v];
    }

    /**
     * 状态压缩，二维 -> 一维
     *
     * @param n
     * @param v
     * @param w
     * @param c
     * @return
     */
    public int maxValue2(int n, int v, int[] w, int[] c) {
        int[] dp = new int[v+1];
        // 遍历每件物品
        for(int i = 1; i <= n; i++) {
            // 逆序遍历背包容量
            for (int j = v; j >= w[i-1]; j--) {
                dp[j] = Math.max(dp[j], dp[j-w[i-1]] + c[i-1]);
            }
        }

        return dp[v];
    }
}
