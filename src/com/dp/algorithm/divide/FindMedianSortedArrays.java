package com.dp.algorithm.divide;

/**
 * leetcode_4_寻找两个正序数组的中位数_困难
 *
 * @author liuxucheng
 * @since 2022/7/22
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        FindMedianSortedArrays test = new FindMedianSortedArrays();
        test.findMedianSortedArrays4(new int[]{}, new int[]{1});
    }

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
        // 遍历指针
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
     * 思路：分治，求第K小的数
     * 比较2个数组中第k/2个数，如果哪个小，就表明该数组的前k/2个数都不可能是第k小的数，可以直接排除
     * 最终结果：其中一个数组变空了或k变成1
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
            return getKth(nums1, 0, nums2, 0, (l + 1) / 2);
        } else {
            // 偶数
            int left = getKth(nums1, 0, nums2, 0, l / 2);
            int right = getKth(nums1, 0, nums2, 0, l / 2 + 1);
            return (left + right) / 2.0;
        }
    }

    /**
     * 递归
     *
     * @param nums1
     * @param start1
     * @param nums2
     * @param start2
     * @param k
     * @return
     */
    private int getKth(int[] nums1, int start1,
                       int[] nums2, int start2, int k) {
        // base case
        int len1 = nums1.length - start1;
        int len2 = nums2.length - start2;
        // 1. nums1为空
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }
        // 2. nums2为空
        if (len2 == 0) {
            return nums1[start1 + k - 1];
        }
        // 3. k降到1，比较2个数组首元素的大小
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        // 比较2个数组中k/2-1位置的元素的大小，注意数组越界
        int mid1 = start1 + Math.min(len1, k / 2) - 1;
        int mid2 = start2 + Math.min(len2, k / 2) - 1;
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
     * 按中位数的定义划分数组+二分查找
     * 中位数可以将数组划分为两个长度相等的子集，第二个子集中的元素总是大于等于第一个子集中的元素
     * 假设nums1的长度是m，nums2的长度是n
     * 在位置i将nums1划分为2个部分，分别有i和m-i个元素
     * 在位置j将nums2划分为2个部分，分别有j和n-j个元素
     * 当m+n为偶数，则i+j=m+n-i-j，即i+j=(m+n)/2，中位数=(max(nums1[i-1],nums2[j-1])+min(nums1[i],nums2[j]))/2
     * 当m+n为奇数，则i+j=m+n-i-j+1，即i+j=(m+n+1)/2，中位数=max(nums[i-1],nums[j-1])
     * 当m+n为偶数，(m+n)/2=(m+n+1)/2，则不管是奇数还是偶数，i+j=(m+n+1)/2
     * 由于0<=i<=m，为了满足0<=j<=n，因此m<=n
     * 注意：如果m > n，可能出现得到的j小于0的情况
     * 同时，由于左子集中的元素始终小于等于右子集中的元素，因此max(nums1[i-1],nums2[j-1])<=min(nums1[i],nums2[j])
     * 由于nums1[i-1]<=nums1[i]，nums2[j-1]<=nums2[j]，因此只需要满足以下2个条件：
     * 1. nums1[i-1]<=nums2[j]，如果nums1[i - 1] > nums2[j]，那么i减小，j随之增大
     * 2. nums2[j-1]<=nums1[i]，如果nums2[j - 1] > nums1[i]，那么j减小，i随之增大
     * 除此之外，还需要考虑数组越界的问题，因此需要注意边界情况：
     * 1. i==0或j==0，可以将nums1[-1]或nums2[-1]赋值为Integer.MIN_VALUE
     * 2. i==m或j==n，可以将nums1[m]或nums2[n]赋值为Integer.MAX_VALUE
     *
     * 如何确定i的位置：二分查找
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        // 保证m <= n
        if (m > n) {
            return findMedianSortedArrays4(nums2, nums1);
        }
        // 二分查找确定i的位置
        int l = 0, r = m;
        int mid = (m + n + 1) / 2;
        while (l <= r) {
            int i = l + (r - l) / 2;
            int j = mid - i;
            int left1 = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int right1 = i == m ? Integer.MAX_VALUE : nums1[i];
            int left2 = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int right2 = j == n ? Integer.MAX_VALUE : nums2[j];
            // 特性：max(left1, left2) <= min(right1, right2)
            // 由于left1 <= right1，left2 <= right2，因此left1 <= right2 && left2 <= right1
            if (left1 > right2) {
                // 减小i
                r = i - 1;
            } else if (left2 > right1) {
                // 增大i
                l = i + 1;
            } else {
                // 满足条件
                int median1 = Math.max(left1, left2);
                int median2 = Math.min(right1, right2);
                return ((m + n) & 1) == 1 ? median1 : (median1 + median2) / 2.0;
            }
        }
        return 0.0;
    }
}
