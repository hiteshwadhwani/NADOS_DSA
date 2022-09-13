package com.company.LinkedList;

import java.util.LinkedList;

public class LinkedListToStackAdapter {
    LinkedList<Integer> list;
    int size;

    public LinkedListToStackAdapter() {
        list = new LinkedList<>();
        size = 0;
    }
    int size() {
        return size;
    }

    void push(int val) {
        list.add(val);
        size++;
    }

    int pop() {
        int ans = list.remove(size-1);
        size--;
        return ans;
    }

    int top() {
        return list.get(size-1);
    }
}
