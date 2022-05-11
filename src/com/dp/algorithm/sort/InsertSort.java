package com.dp.algorithm.sort;

/**
 * 直接插入排序
 *
 * @author xucheng.liu
 * @since 2022/5/11
 */
public class InsertSort {

    public static void main(String[] args) {

    }

    /**
     * 从index=1开始遍历，将元素插入到前面的有序部分
     *
     * @param arr
     */
    private int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
        }
        return arr;
    }
}
