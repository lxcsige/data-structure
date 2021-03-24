package com.dp.algorithm.btree.bstree;

import com.dp.algorithm.btree.TreeNode;
import com.dp.algorithm.linkedlist.Node;

/**
 * @author xucheng.liu
 * @date 2021/3/24
 */
public class DeleteNode {

    public static void main(String[] args) {

    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // 右子树没有左节点
                if (root.right.left == null) {
                    root.val = root.right.val;
                    root.right = root.right.right;
                } else {
                    root.val = deleteMin(root.right);
                }
                return root;
            }
        }
    }

    private int deleteMin(TreeNode node) {
        TreeNode left = node.left;

        while (left.left != null) {
            node = left;
            left = left.left;
        }
        node.left = left.right;

        return left.val;
    }
}
