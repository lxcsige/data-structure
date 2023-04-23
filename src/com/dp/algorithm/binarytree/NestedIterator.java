package com.dp.algorithm.binarytree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * leetcode_341_扁平化嵌套列表迭代器_中等
 *
 * @author liuxucheng
 * @since 2021/5/6
 */
public class NestedIterator implements Iterator<Integer> {

    private Iterator<Integer> it;

    public NestedIterator(List<NestedInteger> nestedList) {
        List<Integer> result = new ArrayList<>();
        for (NestedInteger root : nestedList) {
            traverse(root, result);
        }
        this.it = result.iterator();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public Integer next() {
        return it.next();
    }

    private void traverse(NestedInteger root, List<Integer> result) {
        if (root.isInteger()) {
            result.add(root.getInteger());
        }

        for (NestedInteger child : root.getList()) {
            traverse(child, result);
        }
    }
}
