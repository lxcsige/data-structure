package com.dp.algorithm.binarytree;

import java.util.List;

/**
 * N叉树节点
 *
 * @author xucheng.liu
 * @since 2022/5/20
 */
public class Node {

    public int val;

    public List<Node> children;

    public Node() {

    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
