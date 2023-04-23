package com.dp.algorithm.array;

/**
 * leetcode_581_最短连续子数组_中等
 */
public class FindUnsortedSubarray {

    /**
     * 将数组划分为A、B、C三段，A和C升序，B无序
     * 那么，min(B) >= max(A)，min(C) >= max(B)，否则无法通过只对B进行排序就让整个数组有序
     * 假设B区间范围是[begin, end]，那么nums[end] < max(B) <= min(C) = nums[end + 1]
     * 如果nums[end] == max(B)，那么nums[end]就可以和C区间构成一个有序区间
     * 同理，nums[begin] > min(B)
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        // 无序区间左边界
        int begin = 0;
        // 无序区间右边界
        int end = -1;
        int max = nums[0];
        int min = nums[n - 1];
        for (int i = 0; i < n; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
            // 此时i肯定在无序区间内
            else if (max > nums[i]) {
                // end向右移动
                // end是小于max的最后一个位置，到了右边的升序区间，max一定小于等于nums[i]
                end = i;
            }
            if (min > nums[n - i - 1]) {
                min = nums[n - i - 1];
            }
            else if (min < nums[n - i - 1]) {
                // begin向左移动
                // begin是大于min的最后一个位置，到了左边的升序区间，由于是从右往左遍历，min一定大于等于nums[n-i-1]，此时begin不会再向左移动
                begin = n - i - 1;
            }
        }
        return end - begin + 1;
    }
}
