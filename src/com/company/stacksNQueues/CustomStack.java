package com.company.stacksNQueues;

public class CustomStack {
    int[] data;
    int tos;

    public CustomStack(int cap) {
        data = new int[cap];
        tos = -1;
    }

    int size() {
        return tos;
    }

    void display() {
        for (int i=0;i<=tos;i++){
            System.out.print(data[i]);
        }
        System.out.println();
    }

    void push(int val) {
        if (size() == data.length-1){
            System.out.println("Stack overflow");
        }
        else{
            tos++;
            data[tos]= val;
        }
    }

    int pop() {
        if(size() == -1){
            System.out.println("Stack underflow");
            return -1;
        }
        else{
            return data[tos--];
        }
    }

    int top() {
        return data[tos];
    }
}
