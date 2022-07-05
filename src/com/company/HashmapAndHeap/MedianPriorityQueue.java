package com.company.HashmapAndHeap;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianPriorityQueue {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianPriorityQueue() {
        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
    }

    public void add(int val) {
        if(right.size() > 0 && val > right.peek()){
            right.add(val);
        }
        else {
            left.add(val);
        }
        if(Math.abs(right.size() - left.size()) > 1){
            if(left.size() > right.size()){
                int remove = left.remove();
                right.add(remove);
            }
            else {
                int remove = right.remove();
                left.add(remove);
            }

        }
    }

    public int remove() {
        if(right.size() == 0 && left.size() == 0){
            System.out.println("Underflow");
            return -1;
        }
        if(right.size() > left.size()){
            return right.remove();
        }
        else {
            return left.remove();
        }
    }

    public int peek() {
        if(right.size() == 0 && left.size() == 0){
            System.out.println("Underflow");
            return -1;
        }
        if(right.size() > left.size()){
            return right.peek();
        }
        else {
            return left.peek();
        }
    }

    public int size() {
        return left.size()+right.size();
    }
}
