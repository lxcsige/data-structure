package com.dp.algorithm.binarytree;

/**
 * @author liuxucheng
 * @since 2021/4/1
 */
public class BuildTree {

    public static void main(String[] args) {
        int[] preOrder = new int[]{1, 2};
        int[] inOrder = new int[]{2, 1};
        TreeNode res = new BuildTree().buildTree(preOrder, inOrder);
    }

    /**
     * 105.从前序和中序遍历构建二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        if (preorder.length != inorder.length) {
            return null;
        }
        return  buildTree(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // 根节点
        TreeNode root = new TreeNode(preorder[preStart]);

        // 在中序遍历数组中找出左子树的size
        int leftSize = 0;
        for(int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                leftSize = i - inStart;
                break;
            }
        }

        root.left = buildTree(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, inStart + leftSize - 1);
        root.right = buildTree(preorder, preStart + leftSize + 1, preEnd,
                inorder, inStart + leftSize + 1, inEnd);

        return root;
    }

    /**
     * 106.从中序和后序遍历构建二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        if (inorder.length != postorder.length) {
            return null;
        }
        return  buildTree(inorder, 0, inorder.length - 1,
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