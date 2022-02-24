package com.dp.algorithm.dp.knapsack.knapsackNP;

import java.util.Arrays;

/**
 * 322-零钱兑换，求最少硬币数
 *
 * @author xucheng.liu
 * @since 2021/5/18
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange main = new CoinChange();
//        System.out.println(main.coinChange1(new int[]{1, 2, 5}, 4));
//        System.out.println(main.coinChange2(new int[]{1, 2, 5}, 4));
        System.out.println(main.coinChange3(new int[]{2, 5}, 15));
    }

    /**
     * 暴力递归
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        // base case，注意这里不能返回-1
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        // f(n) = 1 + min(f(n-c[i])), 0 <= i < coins.length
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int temp = coinChange1(coins, amount - coin);
            if (temp == -1) {
                continue;
            }
            res = Math.min(res, temp + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 保存中间结果的递归，备忘录
     * 时间复杂度：O(amount * coins.length)
     * 空间复杂度：O(amount)
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        int[] coinNums = new int[amount + 1];
        return helper(coins, amount, coinNums);
    }

    private int helper(int[] coins, int amount, int[] coinNums) {
        if (coinNums[amount] != 0) {
            return coinNums[amount];
        }

        // base case，注意这里不能返回-1
        if (amount == 0) {
            return 0;
        }

        // f(n) = 1 + min(f(n-c[i])), 0 <= i < coins.length
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount - coin < 0) {
                continue;
            }
            int temp = coinChange1(coins, amount - coin);
            if (temp == -1) {
                continue;
            }
            res = Math.min(res, temp + 1);
        }

        coinNums[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return coinNums[amount];
    }

    /**
     * 动态规划_v1
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 初始化dp数组
        Arrays.fill(dp, -1);
        // 初始化状态
        dp[0] = 0;
        // 遍历状态参数，即目标金额
        for (int i = 1; i < amount + 1; i++) {
            // 模拟最大值
            int minCount = amount + 1;
            // 遍历硬币面值
            for (int coin : coins) {
                // 目标金额小于硬币面值，跳过
                if (i < coin) {
                    continue;
                }
                int restCount = dp[i - coin];
                // 无法凑出剩余金额，跳过
                if (restCount == -1) {
                    continue;
                }
                minCount = Math.min(minCount, restCount + 1);
            }
            // 仅存在可用的硬币组合时，记录到dp数组
            if (minCount !=  amount + 1) {
                dp[i] = minCount;
            }
        }

        return dp[amount];
    }

    /**
     * 动态规划_v2，其实就是完全背包压缩状态之后的解法
     * 注意点在于边界，dp[0] = 0，即目标金额为0时不需要任何硬币就可以凑出来
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange4(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i < amount + 1; i++) {
            // 相当于初始化为正无穷
            dp[i] = amount + 1;
            for (int coin : coins) {
                // 目标金额不小于硬币面ggp值
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
