package com.company.stacksNQueues;

public class TwoStack {
    int[] data;
    int tos1;
    int tos2;

    public TwoStack(int cap) {
        data = new int[cap];
        tos1 = -1;
        tos2 = cap;
    }

    int size1() {
        return tos1+1;
    }

    int size2() {
        return data.length - tos2;
    }

    void push1(int val) {
        if(size1() + size2() == data.length){
            System.out.println("Stack overflow");
        }
        else {
            tos1++;
            data[tos1] = val;
        }

    }

    void push2(int val) {
        if(size1() + size2() == data.length){
            System.out.println("Stack overflow");
        }
        else {
            tos2--;
            data[tos2] = val;
        }
    }

    int pop1() {
        if(size1() == 0){
            System.out.println("Stack underflow");
            return -1;
        }
        else {
            return data[tos1--];
        }
    }

    int pop2() {
        if(size2() == 0){
            System.out.println("Stack underflow");
            return -1;
        }
        else {
            return data[tos2++];
        }
    }

    int top1() {
        if(size1() == 0){
            System.out.println("Stack underflow");
            return -1;
        }
        else {
            return data[tos1];
        }
    }

    int top2() {
        if(size2() == 0){
            System.out.println("Stack underflow");
            return -1;
        }
        else {
            return data[tos2];
        }
    }
}
