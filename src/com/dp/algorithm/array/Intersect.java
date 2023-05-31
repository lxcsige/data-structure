package com.dp.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode_350_两个数组的交集II_简单
 */
public class Intersect {

    public static void main(String[] args) {
        Intersect test = new Intersect();
        test.intersect(new int[]{1,2,2,1}, new int[]{2,2});
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        List<Integer> res = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                res.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] arr = new int[res.size()];
        for (int k = 0; k < arr.length; i++) {
            arr[k] = res.get(k);
        }
        return arr;
    }
}
