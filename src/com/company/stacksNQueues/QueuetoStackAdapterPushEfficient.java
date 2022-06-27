package com.company.stacksNQueues;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueuetoStackAdapterPushEfficient {
    Queue<Integer> mainQ;
    Queue<Integer> helperQ;

    public QueuetoStackAdapterPushEfficient() {
        mainQ = new ArrayDeque<>();
        helperQ = new ArrayDeque<>();
    }

    int size() {
        return mainQ.size();
    }

    void push(int val) {
        mainQ.add(val);
    }

    int pop() {
        if(size() == 0){
            System.out.println("System underflow");
            return -1;
        }
        else {
            while (mainQ.size() > 1){
                helperQ.add(mainQ.remove());
            }
            int val = mainQ.remove();
            while (helperQ.size()> 0){
                mainQ.add(helperQ.remove());
            }
            return val;
        }

    }

    int top() {
        if(size() == 0){
            System.out.println("System underflow");
            return -1;
        }
        else {
            while (mainQ.size() > 1){
                helperQ.add(mainQ.remove());
            }
            int val = mainQ.remove();
            helperQ.add(val);
            while (helperQ.size()> 0){
                mainQ.add(helperQ.remove());
            }
            return val;
        }

    }
}
