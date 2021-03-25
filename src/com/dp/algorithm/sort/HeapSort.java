package com.dp.algorithm.sort;

/**
 * @author xucheng.liu
 * @date 2021/3/25
 */
public class HeapSort {

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] arr = new int[]{5,2,3,4,1,6,7,0,8};
        heapSort.heapSort(arr);
    }

    /**
     * 堆排序，时间复杂度O(nlogn)，不稳定排序，如果在相同key值位于两个子树中，可能会调整顺序
     * 建堆 --> 堆顶与最后一个元素交换 --> 调整堆
     * @param arr
     */
    private void heapSort(int[] arr) {
        /*
         * 建堆，自下而上调整，从最后一个非叶节点开始
         * 正序则建立大根堆，倒序则建立小根堆
         * 时间复杂度O(n)
         */
        for (int i = arr.length/ 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        /*
         * 交换堆顶和末尾元素，调整堆
         * 时间复杂度O(nlogn)
         */
        for (int i = arr.length - 1; i > 0; i--) {
            // 交换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 调整
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 调整堆，i和length限定需要调整的堆在数组中的位置
     *
     * @param arr
     * @param i
     * @param length
     */
    private void adjustHeap(int[] arr, int i, int length) {
        // 待调整堆的root节点，自上而下调整
        int temp = arr[i];
        // 左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 左子节点小于右子节点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            /*
             * 如果最大的子节点都比根节点小，直接跳出，
             * 注意，这里没有作交换，只是将子节点的值赋给父节点
             * 其实就是将最大的子节点赋值给父节点
             */
            if (temp < arr[k]) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        // 给最后一个赋值给父节点的子节点赋值
        arr[i] = temp;
    }

    private void siftUp(int[] arr, int i) {
        int temp = arr[i];
        int k;
        while (i > 0) {
            k = (i - 1) / 2;
            if (arr[k] < temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}
