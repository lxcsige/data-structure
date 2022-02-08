package com.dp.algorithm.dp.coin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
//        System.out.println(main.coinChange3(new int[]{2, 5}, 15));
        System.out.println(main.coinChange4(new int[]{5, 2, 1}, 14));
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
            if (amount < coin) {
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

    /**
     * 贪心+回溯
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange4(int[] coins, int amount) {
        return getMinCoinCountOfValue(coins, amount, 0);
    }

    /**
     * 贪心+回溯，尽可能使用最大面值的硬币凑出目标金额，凑不出来就回溯
     *
     * @param coins
     * @param amount
     * @param valueIndex
     * @return
     */
    private int getMinCoinCountOfValue(int[] coins, int amount, int valueIndex) {
        int valueCount = coins.length;
        if (valueCount == 0 || valueIndex == valueCount) {
            return -1;
        }

        // 当前硬币的面值
        int currentCoin = coins[valueIndex];
        // 该面值硬币的最大数量
        int currentCount = amount / currentCoin;
        // 剩余金额
        int restAmount = amount - currentCount * currentCoin;
        if (restAmount == 0) {
            return currentCount;
        }

        // 其他面值硬币的数量
        int restCount;
        while (currentCount >= 0) {
            // 尝试用剩余面值求当前余额的硬币数量
            restCount = getMinCoinCountOfValue(coins, restAmount, ++valueIndex);
            // 没有可用组合
            if (restCount == -1) {
                // 当前面值硬币数-1
                currentCount--;
                // 重新计算剩余金额
                restAmount += currentCoin;
            } else {
                return restCount + currentCount;
            }
        }
        return -1;
    }

    /**
     * 递归，首先求所有组合，然后找出组合中的最值
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange5(int[] coins, int amount) {
        // 初始硬币数量组合
        ArrayList<Integer> initialCounts = new ArrayList<>(Collections.nCopies(coins.length, 0));
        // 存储所有组合
        ArrayList<ArrayList<Integer>> coinCombinations = new ArrayList<>();
        // 求解所有组合（不去重）
        getMinCountsHelper(amount, coins, initialCounts, coinCombinations);
        // 找出所有组合中的最小值
        return getMinimumHelper(coinCombinations);
    }

    private void getMinCountsHelper(int total, int[] values,
                                    ArrayList<Integer> currentCounts,
                                    ArrayList<ArrayList<Integer>> combinations) {
        // 满足条件
        if (0 == total) {
            combinations.add(new ArrayList<>(currentCounts));
            return;
        }

        int valueLength = values.length;
        // 遍历所有面值
        for (int i = 0;  i < valueLength; i ++) {
            int currentValue = values[i];
            // 如果面值大于当前总额，直接跳过
            if (currentValue > total) {
                continue;
            }
            // 否则在当前面值数量组合上的对应位置加1
            ArrayList<Integer> newCounts = new ArrayList<>(currentCounts);
            newCounts.set(i, newCounts.get(i)+1);
            int rest = total - currentValue;
            // 求解剩余额度所需硬币数量
            getMinCountsHelper(rest, values, newCounts, combinations);
        }
    }

    int getMinimumHelper(ArrayList<ArrayList<Integer>> combinations) {
        // 如果没有可用组合，返回-1
        if (0 == combinations.size()) { return -1; }

        int minCount = Integer.MAX_VALUE;
        for (ArrayList<Integer> counts : combinations) {
            int total = 0; // 求当前组合的硬币总数
            for (int count : counts) { total += count; }

            // 保留最小的
            if (total < minCount) { minCount = total; }
        }

        return minCount;
    }
}
