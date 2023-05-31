package com.dp.algorithm.binarysearch.rotate;

/**
 * leetcode_153_寻找旋转排序数组中的最小值_中等
 *
 * reviewed at 2023.05.29
 *
 * @author liuxucheng
 * @since 2022/8/6
 */
public class FindMin {

    public static void main(String[] args) {
        FindMin solution = new FindMin();
        System.out.println(solution.findMin(new int[]{3,1}));
    }

    /**
     * 无重复，左有序数组大于右有序数组
     * 左有序数组>=nums[0]，右有序数组<nums[0]
     * 注意边界：数组有序
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        // 终止条件left == right
        while (left < right) {
            // 有序，直接返回最小值
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            // 否则二分
            int mid = left + (right - left) / 2;
            // mid在左有序数组中，旋转点在mid的右侧
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                // mid在右有序数组中，旋转点在mid的左侧
                right = mid;
            }
        }
        return nums[left];
    }

    /**
     * 找最大值，其后一位就是最小值
     *
     * @param nums
     * @return
     */
    public int findMin2(int[] nums) {
        int left = 0, right = nums.length - 1;
        // 终止条件left == right
        while (left < right) {
            // 防止只剩下2个数的情况下出现死循环，mid会更靠近right
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] >= nums[left]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return nums[(left + 1) % nums.length];
    }

    /**
     * 和right作比较，不需要考虑数组递增的边界情况
     *
     * @param nums
     * @return
     */
    public int findMin3(int[] nums) {
        int left = 0, right = nums.length - 1;
        // 终止条件left == right
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
