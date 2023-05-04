package com.dp.algorithm.binarytree;

/**
 * leetcode_100_相同的树_简单
 *
 * reviewed at 2023.04.20
 *
 * @author xucheng.liu
 * @since 2022/5/20
 */
public class IsSameTree {

    /**
     * 后序DFS
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
