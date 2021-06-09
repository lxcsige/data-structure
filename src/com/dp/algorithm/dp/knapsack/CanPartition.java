package com.dp.algorithm.dp.knapsack;

/**
 * 416_分割等和子集
 *
 * @author xucheng.liu
 * @since 2021/6/9
 */
public class CanPartition {

    public static void main(String[] args) {

    }

    /**
     * DP，转化为01背包问题
     * 难点在于理解边界
     *
     * @param nums
     * @return
     */
    public boolean canPartition1(int[] nums) {
        if (nums.length == 1) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        // dp[i][j]表示nums[0...i]中是否存在和为j的子集
        // 状态转移方程：dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
        // 如果dp[i-1][j]为true，则dp[i][j]一定为true，在二维数组上的表现就是一列元素都是true
        boolean[][] dp = new boolean[nums.length][target + 1];
        // 边界
        for (int i = 0; i < nums.length; i++) {
            // 其实就是j = nums[i]这种特殊情况，可以理解为nums[i]为单独一组，其他元素为另外一组，符合题意
            // 如果仍然不能理解，可以画图
            dp[i][0] = true;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
        }

        return dp[nums.length - 1][target];
    }

    /**
     * 压缩状态
     * 根据状态转移方程dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]，每次计算都只会用到上一层数据，因此可以压缩到一维数组
     * dp[i][j] -> dp[j], dp[i-1][j] -> dp[j], dp[i-1][j-nums[i]] -> dp[j-nums[i]]
     * 需要注意的是遍历顺序，每次计算都会用到前面的元素，如果前面的元素已经被覆盖则会导致本次计算结果有误
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        if (nums.length == 1) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        // 边界
        dp[0] = true;

        for (int i = 1; i < nums.length; i++) {
            // 倒序便利，如果正序遍历需要用到之前的元素，而之前元素已经被更新过了
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j-nums[i]];
            }
        }

        return dp[target];
    }
}
