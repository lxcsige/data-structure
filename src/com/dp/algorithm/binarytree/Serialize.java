package com.dp.algorithm.binarytree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * leetcode_297_二叉树的序列化和反序列化_困难
 *
 * @author liuxucheng
 * @since 2021/4/19
 */
public class Serialize {

    private static final String SEP = ",";

    private static final String NULL = "#";

    public static void main(String[] args) {
        Serialize main = new Serialize();

        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.left = left;

        String str = main.serialize(root);
        TreeNode root1 = main.deserialize(str);
    }

    /**
     * 先序DFS
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        sb.append(root.val).append(SEP);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        String[] nodeArr = data.split(SEP);
        LinkedList<String> nodes = new LinkedList<>(Arrays.asList(nodeArr));

        return deserialize(nodes);
    }

    private TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }

        String node = nodes.removeFirst();
        if (NULL.equals(node)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(node));
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);

        return root;
    }
}
