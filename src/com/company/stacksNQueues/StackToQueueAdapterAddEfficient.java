package com.company.stacksNQueues;

import java.util.Stack;

public class StackToQueueAdapterAddEfficient {
    Stack<Integer> mainS;
    Stack<Integer> helperS;

    public StackToQueueAdapterAddEfficient() {
        mainS = new Stack<>();
        helperS = new Stack<>();
    }

    int size() { //O(1)
        return mainS.size();
    }

    void add(int val) { // O(1)
        mainS.add(val);
    }

    int remove() {
        if(size() == 0){
            System.out.println("System underflow");
            return -1;
        }
        else {
            while (mainS.size() > 1){
                helperS.push(mainS.pop());
            }
            int val = mainS.pop();
            while (helperS.size() > 0){
                mainS.push(helperS.pop());
            }
            return val;
        }
    }

    int peek() {
        if(size() == 0){
            System.out.println("System underflow");
            return -1;
        }
        else {
            while (mainS.size() > 1){
                helperS.push(mainS.pop());
            }
            int val = mainS.pop();
            helperS.push(val);
            while (helperS.size() > 0){
                mainS.push(helperS.pop());
            }
            return val;
        }
    }
}
