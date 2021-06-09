package com.dp.algorithm.binarysearch;

/**
 * @author xucheng.liu
 * @since 2021/6/6
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.binarySearch(new int[]{1,3,5,7,9,11}, 2));
    }

    /**
     * 二分查找第一个不小于target的元素index
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        return l;
    }
}
