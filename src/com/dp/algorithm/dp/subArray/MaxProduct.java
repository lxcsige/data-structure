package com.dp.algorithm.dp.subArray;

/**
 * 152_乘积最大子数组
 *
 * @author xucheng.liu
 * @since 2022/2/7
 */
public class MaxProduct {

    public static void main(String[] args) {
        int[] nums = new int[]{-2,3,-4};
        System.out.println("res = " + maxProduct(nums));
    }

    public static int maxProduct(int[] nums) {
        int product = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tmpProduct = nums[i] * product;
            if (tmpProduct == 0) {
                product = Math.max(nums[i], 0);
            } else if (tmpProduct > 0) {
                product = tmpProduct;
            } else {
                product = nums[i];
            }
            res = Math.max(res, product);
        }
        return res;
    }
}
