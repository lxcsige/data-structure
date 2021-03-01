package com.dp.algorithm.linkedlist;

import java.io.Serializable;

/**
 * @author xucheng.liu
 * @date 2021/2/28
 */
public class Node<E> implements Serializable {

    private static final long serialVersionUID = -6062884936201599651L;

    E val;

    Node<E> next;

    public Node() {

    }

    public Node(E val, Node next) {
        this.val = val;
        this.next = next;
    }

    public Node(E val) {
        this.val = val;
    }
}
