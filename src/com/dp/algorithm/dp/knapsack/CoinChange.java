package com.dp.algorithm.dp.knapsack;

import java.util.Arrays;

/**
 * 322-零钱兑换
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
     * 时间复杂度：O(k*n^k)，其中k=coins.length，n=amount
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
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 保存中间结果的递归
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
        // base case，注意这里不能返回-1
        if (amount == 0) {
            return 0;
        }
        if (coinNums[amount] != 0) {
            return coinNums[amount];
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
     * 动态规划
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
