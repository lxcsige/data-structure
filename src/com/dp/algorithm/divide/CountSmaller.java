package com.dp.algorithm.divide;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode_315_计算右侧小于当前元素的个数
 */
public class CountSmaller {

    private int[] tmp;

    private int[] tmpIndex;

    private int[] index;

    private int[] res;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        // 初始化
        tmp = new int[n];
        tmpIndex = new int[n];
        index = new int[n];
        res = new int[n];
        // 原始的index数组，下标表示当前的index，元素表示原始的index
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }
        mergeSort(nums, 0, n - 1);
        List<Integer> ans = new ArrayList<>(n);
        for (int num : res) {
            ans.add(num);
        }
        return ans;
    }

    private void mergeSort(int[] nums, int left, int right) {
        // base case
        if (left >= right) {
            return;
        }
        // 切分子数组
        int mid = left + (right - left) / 2;
        // 左右子数组递归
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        // 左右子数组归并
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k] = nums[i];
                tmpIndex[k] = index[i];
                // nums[mid+1..j]均小于nums[i]
                res[index[i]] += (j - mid - 1);
                i++;
            } else {
                tmp[k] = nums[j];
                tmpIndex[k] = index[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            tmp[k] = nums[i];
            tmpIndex[k] = index[i];
            res[index[i]] += (right - mid);
            i++;
            k++;
        }
        while (j <= right) {
            tmp[k] = nums[j];
            tmpIndex[k] = index[j];
            j++;
            k++;
        }
        // 修改原数组
        for (int p = left; p <= right; p++) {
            index[p] = tmpIndex[p];
            nums[p] = tmp[p];
        }
    }
}
