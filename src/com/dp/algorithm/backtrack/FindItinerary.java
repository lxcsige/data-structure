package com.dp.algorithm.backtrack;

import java.util.*;

/**
 * leetcode_332_重新安排行程_困难
 */
public class FindItinerary {

    private List<String> res;

    public List<String> findItinerary(List<List<String>> tickets) {
        // 按照目的地排序
        tickets.sort(Comparator.comparing(t -> t.get(1)));
        List<String> path = new ArrayList<>();
        boolean[] used = new boolean[tickets.size()];
        path.add("JFK");
        backtrack(tickets, used, path);
        return res;
    }

    private boolean backtrack(List<List<String>> tickets, boolean[] used, List<String> path) {
        // 终止条件
        if (path.size() == tickets.size() + 1) {
            res = path;
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            if (used[i]) {
                continue;
            }
            List<String> ticket = tickets.get(i);
            if (!path.get(path.size() - 1).equals(ticket.get(0))) {
                continue;
            }
            path.add(ticket.get(1));
            used[i] = true;
            if (backtrack(tickets, used, path)) {
                return true;
            }
            path.remove(path.size() - 1);
            used[i] = false;
        }
        return false;
    }
}
