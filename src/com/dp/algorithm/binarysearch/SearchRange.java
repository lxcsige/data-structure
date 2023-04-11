package com.dp.algorithm.binarysearch;

/**
 * leetcode_34_在排序数组中查找元素的第一个和最后一个位置_中等
 *
 * @author liuxucheng
 * @since 2022/8/1
 */
public class SearchRange {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2};
        SearchRange test = new SearchRange();
        int[] res = test.searchRange3(nums, 3);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }

    /**
     * 非递减，即nums[i] >= nums[i-1]，可能存在重复元素
     * 分别找到左右边界，注意各种边界情况
     * 1. target不在数组范围内
     * 2. target在数组范围内，但不包含
     * 3. target包含在数组中
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        // 找左右边界的区别在于，当nums[mid]==target时，到底是往左还是往右找
        // 往左找的到的是左边界，更新res=mid得到的是包含target的左边界，更新res=right得到是不包含target的左边界
        // 右边界同理
        int left = searchLeftBorder(nums, target);
        int right = searchRightBorder(nums, target);
        // target不在数组范围内，即target<nums[0]或target>[nums.length-1]
        if (left == -2 || right == -2) {
            return new int[]{-1, -1};
        }
        // 数组中未包含target
        if (right - left == 1) {
            return new int[]{-1, -1};
        }
        return new int[]{left + 1, right - 1};
    }

    /**
     * 寻找左边界，小于target的最大index
     * res == -1，说明target<=nums[0]，即target<=数组中的最小元素
     * res == -2，说明target > nums[nums.length - 1]，即target>数组中的最大元素，此时res一直未被赋值
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchLeftBorder(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid, res = -2;
        while (left <= right) {
            mid = (left + right) / 2;
            // nums[mid] > target，则target可能在[left, mid-1]区间，更新right=mid-1
            // nums[mid] == target，考虑target可能不唯一，同样需要继续往左寻找
            if (nums[mid] >= target) {
                right = mid - 1;
                // 因为找的是小于target的最大index，所以更新res=right，而不是res=mid
                // 如果更新res=mid，则得到的左边界其实是大于等于target的最小index
                res = right;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    /**
     * 寻找右边界，大于target的最小index
     * res == -2，说明target<nums[0]，即target<数组中的最小元素，此时res一直未被赋值
     * res == nums.length，说明target >= nums[nums.length - 1]，即target>=数组中的最大元素
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchRightBorder(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid, res = -2;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                // nums[mid] < target，target可能在[mid+1, right]区间，更新left=mid+1
                // nums[mid] == target，考虑target可能不唯一，同样需要继续往右寻找
                left = mid + 1;
                res = left;
            }
        }
        return res;
    }

    /**
     * 分别找>=target和>target的第一个元素的index
     *
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false) - 1;
        // 当right==left-1，表示target不在数组中
        if (right < left) {
            return new int[]{-1, -1};
        }

        return new int[]{left, right};
    }

    private int binarySearch(int[] nums, int target, boolean inclusive) {
        int left = 0, right = nums.length - 1, mid, res = nums.length;
        while (left <= right) {
            mid = (left + right) / 2;
            // 当inclusive为true，nums[mid]==target时，target的左边界可能在[left, mid-1]区间内，更新res=mid，right=mid-1
            // 即往左找，只有nums[mid]>=target时才会继续更新res
            // 当inclusive为false，nums[mid]==target时，因为此时找的是大于target的第一个元素，所以需要继续向右找，更新left=mid+1
            // 即往右找到第一个大于target的元素
            if (nums[mid] > target || (inclusive && nums[mid] >= target)) {
                right = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public int[] searchRange3(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = leftBound(nums, target);
        if (left >= nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        int right = rightBound(nums, target);
        return new int[]{left, right};
    }

    private int leftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int rightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}
