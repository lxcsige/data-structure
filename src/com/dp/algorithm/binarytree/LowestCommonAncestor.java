package com.dp.algorithm.binarytree;

/**
 *
 *
 * @author liuxucheng
 * @since 2021/5/6
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {

    }

    /**
     * 先序遍历
     * 前提条件：节点p和q一定存在于以root为根节点的二叉树中
     *
     * 函数定义：如果同时包含p和q则返回最近公共祖先节点，否则返回包含的p或q节点（不是很清晰）
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
        // 找到p或者q节点时，自下而上回溯
        // 如果p是q的祖先节点，那么发现p节点后就会回溯，依赖p和q都在root树中的前提
        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // p和q节点分布在root节点的左右子树，此时返回root节点，即最近公共祖先节点
        if (left != null && right != null) {
            return root;
        }
        // 1. left == null && right == null，说明p和q不在root的子树中，返回null
        // 2. left != null && right == null，此时又可以分为2种情况：
        // 2.1 左子树同时包含p和q节点，此时返回的left节点就是最近公共祖先节点
        // 2.2 左子树只包含p或q节点中的一个，此时返回的left节点就是p或q节点
        // 3.left == null && right != null，同2
        return left == null ? right : left;
    }

    private TreeNode res;

    /**
     * 后序遍历
     * f(root)表示包含p或q节点，那么f(root) = f(root.left) || f(root) || root == p || root == q
     * 如果root是最近公共祖先节点，那么(f(root.left) && f(root.right)) || ((root == p || root == q) && (f(root.left) || f(root.right)))
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        contains(root, p, q);
        return res;
    }

    private boolean contains(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null) {
            return false;
        }
        boolean left = contains(root.left, p, q);
        boolean right = contains(root.right, p, q);
        if ((left && right) || ((root == p || root == q) && (left || right))) {
            res = root;
        }
        return left || right || root == p || root == q;
    }
}
