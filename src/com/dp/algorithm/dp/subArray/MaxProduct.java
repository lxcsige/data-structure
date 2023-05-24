package com.dp.algorithm.dp.subArray;

/**
 * leetcode_152_乘积最大子数组_中等
 *
 * reviewed at 2023.05.05
 *
 * @author xucheng.liu
 * @since 2022/2/7
 */
public class MaxProduct {

    /**
     * 如果仅用f(x)表示以nums[x]结尾的最大子数组乘积，并不能满足最优子结构，即后面的子问题不能根据前面的子问题推导求解
     * 负负得正，可能会用到前面的最小值以求得当前的最大值
     * 相对于最大子数组和问题，这里需要考虑正负号情况，同时保留以nums[x]结尾的最大乘积和最小乘积
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        // f_max(x-1), f_min(x-1)
        int maxProduct = nums[0], minProduct = nums[0], res = nums[0], tmpProduct;
        for (int i = 1; i < nums.length; i++) {
            // 注意这里的临时变量，如果没有会导致计算minProduct之前maxProduct已更新
            tmpProduct = maxProduct;
            maxProduct = Math.max(Math.max(maxProduct * nums[i], minProduct * nums[i]), nums[i]);
            minProduct = Math.min(Math.min(tmpProduct * nums[i], minProduct * nums[i]), nums[i]);
            res = Math.max(res, maxProduct);
        }
        return res;
    }
}
