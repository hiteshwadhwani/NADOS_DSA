package com.company.HashmapAndHeap;

import java.util.ArrayList;

public class Priorityqueue {
    ArrayList<Integer> data;

    public Priorityqueue() {
        data = new ArrayList<>();
    }

    public void add(int val) {
        data.add(val);
        upHepify(data.size()-1);
    }
    public void upHepify(int i){
        if(i == 0){
            return;
        }
        int child = i;
        int parent = (i-1)/2;
        if(data.get(child) < data.get(parent)){
            swap(child , parent);
            upHepify(parent);
        }
    }
    public void swap(int child,int parent){
        int x = data.get(child);
        int y = data.get(parent);
        data.set(child , y);
        data.set(parent , x);
    }
    public void downHepify(int i){
        int mini = i;
        int li = 2*i+1;
        if(li < data.size() && data.get(li) < data.get(mini)){
            mini = li;
        }
        int ri = 2*i+2;
        if(ri < data.size() && data.get(ri) < data.get(mini)){
            mini = ri;
        }
        if(mini != i){
            swap(mini , i);
            downHepify(mini);
        }
    }

    public int remove() {
        //base case
        if(data.size() == 0){
            System.out.println("underflow");
            return -1;
        }
        swap(0 , data.size() -1);
        int val = data.remove(data.size() -1);
        downHepify(0);
        return val;
    }

    public int peek() {
        if(data.size() == 0){
            System.out.println("underflow");
            return -1;
        }
        return data.get(0);
    }

    public int size() {
        return data.size();
    }
}
