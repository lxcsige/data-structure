package com.dp.algorithm.binarytree.bsttree;

import com.dp.algorithm.binarytree.TreeNode;

import java.util.LinkedList;

/**
 * 二叉查找树
 *
 * @author xucheng.liu
 * @date 2021/3/23
 */
public class BinarySortTree {

    private TreeNode root;

    public BinarySortTree() {
        root = null;
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(TreeNode root) {
        if(root != null) {
            System.out.print(root.val + ", ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 中序遍历结果有序
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(TreeNode root) {
        if(root != null) {
            inOrder(root.left);
            System.out.print(root.val + ", ");
            inOrder(root.right);
        }
    }

    public void postOrder() {
        preOrder(root);
    }

    private void postOrder(TreeNode root) {
        if(root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.val + ", ");
        }
    }

    public TreeNode search(int val) {
        return search(root, val);
    }

    /**
     * 递归查找
     *
     * @param root
     * @param val
     * @return
     */
    private TreeNode search(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val < val) {
            return search(root.right, val);
        } else if (root.val > val) {
            return search(root.left, val);
        } else {
            return root;
        }
    }

    /**
     * 迭代查找
     *
     * @param node
     * @param val
     * @return
     */
    private TreeNode iterativeSearch(TreeNode node, int val) {
        while (node != null) {
            if (node.val < val) {
                node = node.right;
            } else if (node.val > val) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    private TreeNode minimum(TreeNode root) {
        if (root == null) {
            return null;
        }

        while(root.left != null) {
            root = root.left;
        }

        return root;
    }

    public int minimum() {
        TreeNode node = minimum(root);
        if (node != null) {
            return node.val;
        }

        return -1;
    }

    private TreeNode maximum(TreeNode root) {
        if (root == null) {
            return null;
        }

        while(root.right != null) {
            root = root.right;
        }

        return root;
    }

    public int maximum() {
        TreeNode node = maximum(root);
        if (node != null) {
            return node.val;
        }

        return -1;
    }

    /**
     * 查找后继节点
     *
     * @param node
     * @return
     */
    public TreeNode successor(TreeNode node) {
        if (node == null) {
            return null;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        // 是否已经找到该节点
        boolean found = false;
        TreeNode cur = root;
        while (true) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }

            cur = stack.removeLast();
            if (found) {
                return cur;
            }
            if (cur.val == node.val) {
                found = true;
            }
            cur = cur.right;
        }
    }

    /**
     *  寻找前驱节点
     *
     * @param node
     * @return
     */
    public TreeNode predecessor(TreeNode node) {
        if (node == null) {
            return null;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        // 是否已经找到该节点
        boolean found = false;
        TreeNode cur = root;
        while (true) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.right;
            }

            cur = stack.removeLast();
            if (found) {
                return cur;
            }
            if (cur.val == node.val) {
                found = true;
            }
            cur = cur.left;
        }
    }

    /**
     * 插入节点
     *
     * @param node
     */
    public void insert(TreeNode node) {
        if (node == null) {
            return;
        }
        if (root == null) {
            root = node;
            return;
        }

        TreeNode parent = null;
        TreeNode cur = root;

        while (cur != null) {
            if (cur.val > node.val) {
                parent = cur;
                cur = cur.left;
            } else if (cur.val < node.val) {
                parent = cur;
                cur = cur.right;
            } else {
                // 找到与待插入节点值相同的节点
                return;
            }
        }

        if (parent.val > node.val) {
            parent.left = node;
        } else {
            parent.right = node;
        }
    }

    public TreeNode delete(int key) {
        return deleteNode(root, key);
    }

    private TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        // 如果查找的结点比根节点大，继续在右子树查找删除该结点
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            // 如果查找的结点比根节点小，继续在左子树查找删除该结点
            root.left = deleteNode(root.left, key);
        } else {
            // 找到该节点
            // 以叶子节点为根节点的二叉搜索树只有一个元素，可以直接删除
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                // 如果有右子树，只要找到该右子树的最小值来替换，之后将它删除
                root.val = rightMin(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                // 如果有左子树，只要找到该左子树的最大值来替换，之后将它删除
                root.val = leftMax(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    /**
     * 找到以某个结点为根节点的右子树最小值
     *
     * @param node
     * @return
     */
    private int rightMin(TreeNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    /**
     * 找到以某个结点为根节点的左子树最大值
     *
     * @param node
     * @return
     */
    private int leftMax(TreeNode node) {
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }

    /**
     * 删除节点，返回root
     *
     * @param root
     * @param key
     * @return
     */
    private TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            // 待删除节点在左子树中
            root.left = deleteNode2(root.left, key);
            return root;
        } else if (key > root.val) {
            // 待删除节点在右子树中
            root.right = deleteNode2(root.right, key);
            return root;
        } else {
            // key == root.val，root 为待删除节点
            if (root.left == null) {
                // 返回右子树作为新的根
                return root.right;
            } else if (root.right == null) {
                // 返回左子树作为新的根
                return root.left;
            } else {
                // 左右子树都存在，返回后继节点（右子树最左叶子）作为新的根
                TreeNode successor = min(root.right);
                successor.right = deleteMin(root.right);
                successor.left = root.left;
                return successor;
            }
        }
    }

    private TreeNode min(TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private TreeNode deleteMin(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

}
