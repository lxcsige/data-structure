package com.dp.algorithm.array;

/**
 * leetcode_169_多数元素_简单
 *
 * @author liuxucheng
 * @since 2023/5/30
 */
public class MajorityElement {

    /**
     * 摩尔投票法，O(1)空间复杂度
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int major = 0, vote = 0;
        for (int num : nums) {
            if (vote == 0) {
                major = num;
            }
            if (major == num) {
                vote++;
            } else {
                vote--;
            }
        }
        return major;
    }
}
