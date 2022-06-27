package com.company.stacksNQueues;

public class CustomQueue {
    int[] data;
    int front;
    int size;

    public CustomQueue(int cap) {
        data = new int[cap];
        front = 0;
        size = 0;
    }

    int size() {
        return size;
    }

    void display() {
        for (int i=0;i<front;i++){
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    void add(int val) {
        if(size == data.length){
            System.out.println("Queue overflow");
        }
        else {
            data[front++] = val;
            size++;
        }
    }

    int remove() {
        if(size == 0){
            System.out.println("Queue underflow");
            return -1;
        }
        else {
            int remove = data[0];
            for (int i=1;i<front;i++){
                data[i-1] = data[i];
            }
            front--;
            size--;
            return remove;
        }
    }

    int peek() {
        if(size == 0){
            System.out.println("Queue underflow");
            return -1;
        }
        else {
            return data[0];
        }
    }
}
