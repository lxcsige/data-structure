package com.dp.algorithm.divide;

/**
 * leetcode_4_寻找两个正序数组的中位数_困难
 *
 * @author liuxucheng
 * @since 2022/7/22
 */
public class FindMedianSortedArrays {

    /**
     * 归并排序，时间复杂度O(m+n)，空间复杂度O(m+n)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }
        while (i < m) {
            nums[k++] = nums1[i++];
        }
        while (j < n) {
            nums[k++] = nums2[j++];
        }
        if (k % 2 == 0) {
            return (nums[k / 2] + nums[k / 2 - 1]) / 2.0;
        } else {
            return nums[k / 2];
        }
    }

    /**
     * 归并排序，找到中位数即停止，同时也不需要保留中位数之前的元素
     * 时间复杂度O(m+n)，空间复杂度O(1)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int i = 0, j = 0;
        // prev表示nums[k-1]，cur表示nums[k]
        int prev = 0, cur = 0;
        // 只需要遍历至len/2的位置
        for (int k = 0; k <= len / 2; k++) {
            prev = cur;
            if (i < m && (j >= n || nums1[i] <= nums2[j])) {
                cur = nums1[i++];
            } else {
                cur = nums2[j++];
            }
        }
        if ((len & 1) == 0) {
            return (prev + cur) / 2.0;
        } else {
            return cur;
        }
    }

    /**
     * 分治
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int l = m + n;
        // 奇数
        if ((l & 1) == 1) {
            return getKth(nums1, 0, nums2, 0, (n + m + 1) / 2);
        } else {
            // 偶数
            int left = getKth(nums1, 0, nums2, 0, (n + m) / 2);
            int right = getKth(nums1, 0, nums2, 0, (n + m) / 2 + 1);
            return (left + right) / 2.0;
        }
    }

    private int getKth(int[] nums1, int start1,
                       int[] nums2, int start2, int k) {
        if (start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }
        if (start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        int mid1 = Math.min(nums1.length, start1 + k / 2) - 1;
        int mid2 = Math.min(nums2.length, start2 + k / 2) - 1;
        if (nums1[mid1] <= nums2[mid2]) {
            return getKth(nums1, mid1 + 1, nums2, start2, k - (mid1 - start1 + 1));
        } else {
            return getKth(nums1, start1, nums2, mid2 + 1, k - (mid2 - start2 + 1));
        }
    }

    /**
     * 获取第k小的元素（k>0），其实就是取排序后的nums[k-1]元素
     * 主要思想：分治，比较pivot1=nums1[k/2-1]和pivot2=nums2[k/2-1]，即分别在nums1和nums2中取第k/2个元素进行比较
     * nums1中小于等于pivot1的元素有nums1[0...k/2-2]共计k/2-1个
     * nums2中小于等于pivot2的元素有nums2[0...k/2-2]共计k/2-1个
     * 取pivot = Math.min(pivot1, pivot2)，那么小于等于pivot的元素最多只有k-2个，意味着pivot最多是第k-1小的元素
     * 同时pivot之前的k/2-1个元素也不可能是第k小的元素，可以去掉这k/2个元素
     * 对于剩下的元素，继续取第(k-k/2)小的元素
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    private int getKth2(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        while (true) {
            // 边界
            // nums1数组到头了，直接返回nums2数组中的第index2+k个元素
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            // nums2数组到头了，直接返回nums1数组中的第index1+k个元素
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            int half = k / 2;
            // 防止越界
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    /**
     * 划分数组+二分查找
     * 中位数可以将数组划分为两个长度相等的子集，第二个子集中的元素总是大于等于第一个子集中的元素
     * 假设nums1的长度是m，nums2的长度是n
     * 在位置i将nums1划分为2个部分，分别有i和m-i个元素
     * 在位置j将nums2划分为2个部分，分别有j和n-j个元素
     * 当m+n为偶数，则i+j=m+n-i-j，即i+j=(m+n)/2，中位数=(max(nums1[i-1],nums2[j-1])+min(nums1[i],nums2[j]))/2
     * 当m+n为奇数，则i+j=m+n-i-j+1，即i+j=(m+n+1)/2，中位数=max(nums[i-1],nums[j-1])
     * 当m+n为偶数，(m+n)/2=(m+n+1)/2，则不管是奇数还是偶数，i+j=(m+n+1)/2
     * 由于0<=i<=m，为了满足0<=j<=n，因此m<=n
     * 同时，由于左子集中的元素始终小于等于右子集中的元素，因此max(nums1[i-1],nums2[j-1])<=min(nums1[i],nums2[j])
     * 由于nums1[i-1]<=nums1[i]，nums2[j-1]<=nums2[j]，因此只需要满足nums1[i-1]<=nums2[j]，nums2[j-1]<=nums1[i]
     * 边界情况：
     * 1. i==0或j==0，可以将nums1[-1]或nums2[-1]赋值为Integer.MIN_VALUE
     * 2. i==m或j==n，可以将nums1[m]或nums2[n]赋值为Integer.MAX_VALUE
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays4(nums2, nums1);
        }
        int left = 0, right = m;
        int medianLen = (m + n + 1) / 2, leftLen1, leftLen2;
        int nums1Left, nums1Right, nums2Left, nums2Right;
        while (left <= right) {
            // mid
            leftLen1 = (left + right) / 2;
            leftLen2 = medianLen - leftLen1;
            // 处理边界
            nums1Left = leftLen1 == 0 ? Integer.MIN_VALUE : nums1[leftLen1 - 1];
            nums1Right = leftLen1 == m ? Integer.MAX_VALUE : nums1[leftLen1];
            nums2Left = leftLen2 == 0 ? Integer.MIN_VALUE : nums2[leftLen2 - 1];
            nums2Right = leftLen2 == n ? Integer.MAX_VALUE : nums2[leftLen2];
            // 找到最大的leftLen1，使得nums1[i-1]<=nums2[j]，此时nums1[i]>nums2[j-1]
            if (nums1Left > nums2Right) {
                right = leftLen1 - 1;
            } else if (nums1Right < nums2Left) {
                left = leftLen1 + 1;
            } else {
                int median1 = Math.max(nums1Left, nums2Left);
                int median2 = Math.min(nums1Right, nums2Right);
                return ((m + n) & 1) == 0 ? (median1 + median2) / 2.0 : median1;
            }
        }
        return 0.0;
    }
}
