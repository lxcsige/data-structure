package com.dp.algorithm.sort;

/**
 * 直接插入排序
 *
 * 将元素插入到前面的有序部分
 *
 * @author xucheng.liu
 * @since 2022/5/11
 */
public class InsertSort {

    public static void main(String[] args) {

    }

    private int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > tmp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = tmp;
        }
        return arr;
    }
}
