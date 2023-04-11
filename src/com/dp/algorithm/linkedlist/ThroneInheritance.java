package com.dp.algorithm.linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode_1600_王位继承顺序
 *
 * @author liuxucheng
 * @since 2023/2/8
 */
public class ThroneInheritance {

    private Map<String, Node> map = new HashMap<>();

    /**
     * 用单链表保存王位继承顺序（多叉树的前序遍历）
     */
    private Node dummy = new Node("");

    public ThroneInheritance(String kingName) {
        Node king = new Node(kingName);
        map.put(kingName, king);
        dummy.next = king;
    }

    public void birth(String parentName, String childName) {
        Node child = new Node(childName);
        map.put(childName, child);
        Node parent = map.get(parentName);
        if (parent == null) {
            return;
        }
        Node tmp = parent;
        while (tmp.lastChild != null) {
            tmp = tmp.lastChild;
        }
        child.next = tmp.next;
        tmp.next = child;
        parent.lastChild = child;
    }

    public void death(String name) {
        Node node = map.get(name);
        if (node != null) {
            node.isDeleted = 1;
        }
    }

    public List<String> getInheritanceOrder() {
        List<String> res = new ArrayList<>();
        Node cur = dummy.next;
        while (cur != null) {
            if (cur.isDeleted == 0) {
                res.add(cur.name);
            }
            cur = cur.next;
        }
        return res;
    }

    class Node {

        String name;

        Node next;

        // 记录最后一个儿子
        Node lastChild;

        int isDeleted;

        Node (String name) {
            this.name = name;
        }
    }
}
