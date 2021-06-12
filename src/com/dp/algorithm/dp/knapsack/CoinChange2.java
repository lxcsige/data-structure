package com.dp.algorithm.dp.knapsack;

/**
 * 518_零钱兑换2
 *
 * @author liuxucheng
 * @since 2021/6/9
 */
public class CoinChange2 {

    public static void main(String[] args) {

    }

    /**
     * 完全背包
     * dp[i][j]表示只用前i种硬币凑成j的组合数
     * 状态转移方程：dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]]
     * 边界：dp[..][0] = 1
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 0;
        }

        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j < coins[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                }
            }
        }

        return dp[coins.length][amount];
    }

    /**
     * 压缩状态
     * dp[i] = sum(dp[i-coin])，coin <= i <= amount
     * 如果存在一种硬币组合的金额之和等于i - coin，则在该硬币组合中增加一个面额为coin的硬币，即可得到一种金额之和等于i的硬币组合
     * 边界：dp[0] = 1
     * 遍历coins，然后再更新dp[i]，因此不会重新计算不同的排列，最后得到的组合是按照coin的顺序
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change2(int amount, int[] coins) {
        if (amount == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            // 与01背包不同的是正序遍历
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }

        return dp[amount];
    }
}
