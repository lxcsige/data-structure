package com.dp.algorithm.dp.knapsack;

/**
 * @author liuxucheng
 * @since 2021/5/18
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange test = new CoinChange();
        System.out.println(test.coinChange(new int[]{1,2,5}, 100));
    }

    public int coinChange(int[] coins, int amount) {
        int[] nums = new int[amount + 1];
        return helper(coins, amount, nums);
    }

    private int helper(int[] coins, int amount, int[] nums) {
        if (amount == 0) {
            return 0;
        }
        if (nums[amount] != 0) {
            return nums[amount];
        }
        int res = amount + 1;
        for (int coin : coins) {
            if (amount < coin) {
                continue;
            }
            int temp = helper(coins, amount - coin, nums);
            if (temp == -1) {
                continue;
            }
            res = res > temp + 1 ? temp + 1 : res;
        }

        nums[amount] = res == amount + 1 ? -1 : res;
        return nums[amount];
    }
}
