package com.company.LinkedList;

import java.util.LinkedList;

public class LinkedListToQueueAdapter {
    LinkedList<Integer> list;
    int size;

    public LinkedListToQueueAdapter() {
        list = new LinkedList<>();
    }

    int size() {
        return size;
    }

    void add(int val) {
        list.add(val);
        size++;
    }

    int remove() {
        if(size > 0){
            int ans = list.remove(0);
            size--;
            return ans;

        }
        else {
            System.out.println("Queue underflow");
            return -1;

        }
    }

    int peek() {
        if(size > 0){
            return list.peek();
        }
        else {
            System.out.println("Queue underflow");
            return -1;
        }
    }
}
