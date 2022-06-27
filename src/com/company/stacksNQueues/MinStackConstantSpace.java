package com.company.stacksNQueues;

import java.util.Stack;

public class MinStackConstantSpace {
    Stack<Integer> data;
    int min;

    public MinStackConstantSpace() {
        data = new Stack<>();
    }

    int size() {
        return data.size();
    }

    void push(int val) {
        if(size() ==0){
            data.push(val);
            min = val;
        }
        else if(val < min){
            data.push(val + (val - min));
            min = val;
        }
        else {
            data.push(val);
        }
    }
    int pop() {
        if(size() == 0){
            System.out.println("Stack underflow");
            return -1;
        }
        else if(data.peek() < min){
            int realVal = min;
            min = 2*min - data.pop();
            return realVal;
        }
        else {
            return data.pop();
        }
    }



    int top() {
        if(size() ==0){
            System.out.println("Stack underflow");
            return -1;
        }
        else {
            return data.peek();
        }
    }

    int min() {
        if(size() == 0){
            System.out.println("Stack underflow");
            return -1;
        }
        else {
            return min;
        }
    }
}
