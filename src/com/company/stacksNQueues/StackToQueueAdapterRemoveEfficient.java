package com.company.stacksNQueues;

import java.util.Stack;

public class StackToQueueAdapterRemoveEfficient {
    Stack<Integer> mainS;
    Stack<Integer> helperS;

    public StackToQueueAdapterRemoveEfficient() {
        mainS = new Stack<>();
        helperS = new Stack<>();
    }

    int size() {
        return mainS.size();
    }

    void add(int val) {
        while (mainS.size() > 0){
            helperS.add(mainS.pop());

        }
        mainS.add(val);
        while (helperS.size() > 0){
            mainS.add(helperS.pop());
        }
    }

    int remove() { //O(1)
        if(size() == 0){
            System.out.println("Queue underflow");
            return -1;
        }
        else {
            return mainS.pop();
        }
    }

    int peek() { //O(1)
        if(size() == 0){
            System.out.println("Queue underflow");
            return -1;
        }
        else {
            return mainS.peek();
        }
    }
}
