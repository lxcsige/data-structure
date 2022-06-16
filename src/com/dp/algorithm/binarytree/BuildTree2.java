package com.dp.algorithm.binarytree;

/**
 * leetcode_106_从中序和后序遍历构建二叉树_中等
 *
 * @author xucheng.liu
 * @since 2022/5/15
 */
public class BuildTree2 {

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        if (inorder.length != postorder.length) {
            return null;
        }
        return  buildTree2(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree2(int[] inorder, int inStart, int inEnd,
                                int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // 根节点
        TreeNode root = new TreeNode(postorder[postEnd]);

        // 在中序遍历数组中找出左子树的size
        int leftSize = 0;
        for(int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                leftSize = i - inStart;
                break;
            }
        }

        root.left = buildTree2(inorder, inStart, inStart + leftSize - 1,
                postorder, postStart, postStart + leftSize - 1);
        root.right = buildTree2(inorder, inStart + leftSize + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1);

        return root;
    }
}
