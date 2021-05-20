package com.dp.algorithm.binarytree;

/**
 * @author liuxucheng
 * @since 2021/5/6
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {

    }

    /**
     * LCA，最近公共父节点
     * 如果都存在，则返回它们的公共祖先
     * 如果只存在一个，则返回存在的一个
     * 如果都不存在，则返回NULL
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
