package com.dp.algorithm.binarysearch.rotate;

/**
 * leetcode_33_搜索旋转排序数组_中等
 *
 * reviewed at 2023.05.29
 *
 * @author liuxucheng
 * @since 2022/8/3
 */
public class SearchRotate {

    public static void main(String[] args) {
        SearchRotate solution = new SearchRotate();
        System.out.println(solution.search2(new int[]{3, 1}, 3));
    }

    /**
     * 思路：
     * 1. 只有在有序数组里才能进行二分查找
     * 2. [l, mid]和[mid + 1, r]至少有一个区间是有序的
     * 3. 以[l, mid]区间为例
     * 3.1 如果有序，则判断target是否在区间内，在则继续在该区间内进行二分查找，否则需要到[mid + 1, r]区间内进行查找
     * 3.2 如果无序，则[mid + 1, r]区间必然有序，重复3.1的步骤
     * 总结：不断对数组进行二分，至少有一个区间是有序的，如果target在有序区间内，则后续只需要进行二分查找即可，否则继续二分
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // [l, mid]和[mid + 1, r]至少有一个区间是有序的
            // [l, mid]区间有序
            // 这里必须是>=，否则在用例：nums=[3,1], target = 1时会出现问题
            if (nums[mid] >= nums[left]) {
                // 对于有序区间，可以通过比较左右边界和目标值的大小，判断是否落在该区间内
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    // 否则只能到(mid, r]区间内找
                    left = mid + 1;
                }
            } else {
                // (mid, r]区间有序
                // target在(mid, r]区间内，二分查找
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // [l, mid]和[mid + 1, r]至少有一个区间是有序的
            if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 先找到旋转点（最小值），再找目标值
     *
     * @param nums
     * @param target
     * @return
     */
    public int search3(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        // 二分找旋转点
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 二分找target
        if (nums[left] == target) {
            return left;
        }
        // 递增
        if (left == 0) {
            right = n;
        } else if (target >= nums[0] && target <= nums[right - 1]) {
            left = 0;
        } else if (target > nums[left] && target <= nums[n - 1]) {
            left = left + 1;
            right = n;
        } else {
            return -1;
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
