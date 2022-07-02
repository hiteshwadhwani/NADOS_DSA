package com.company.HashmapAndHeap;

import java.util.ArrayList;
import java.util.LinkedList;

public class Hashmap<K, V> {
    private class HMNode {
        K key;
        V value;

        HMNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size; // n
    private LinkedList<HMNode>[] buckets; // N = buckets.length

    public Hashmap() {
        initbuckets(4);
        size = 0;
    }

    private void initbuckets(int N) {
        buckets = new LinkedList[N];
        for (int bi = 0; bi < buckets.length; bi++) {
            buckets[bi] = new LinkedList<>();
        }
    }

    public void put(K key, V value) throws Exception {
        int index = hashFun(key);
        int bucketIndex = IndexWithinBucket(index , key);

        if(bucketIndex != -1){          //we got the node with key
            //update
            HMNode node = buckets[index].get(bucketIndex);
            node.value = value;
        }
        else {
            //insert
            buckets[index].add(new HMNode(key , value));
            size++;
        }
        double lambda = size * 1.0 / buckets.length;
        if(lambda > 2.0){      //suppose threshHold value for lambda is 2.0
            reHash();
        }

    }

    private void reHash() throws Exception{
        LinkedList<HMNode>[] oldBucket = buckets;
        size = 0;
        initbuckets(oldBucket.length * 2);
        for (int i =0;i<oldBucket.length;i++){
            for (HMNode node : oldBucket[i]){
                put(node.key, node.value);
            }
        }

    }
    private int hashFun(K key){   //will return the index of the bucket in array
        int hc = key.hashCode(); //get hashCode of the key
        return Math.abs(hc) % buckets.length; //get index value within the range of bucket length
    }
    private int IndexWithinBucket(int i , K key){
        int di =0;
        for (HMNode node:buckets[i]){
            if(node.key.equals(key)){
                return di;
            }
            di++;
        }
        return -1;
    }

    public V get(K key) throws Exception {
        int index = hashFun(key);
        int bucketIndex = IndexWithinBucket(index , key);

        if(bucketIndex != -1){          //we got the node with key
            //update
            HMNode node = buckets[index].get(bucketIndex);
            return node.value;
        }
        else {
            return null;
        }
    }

    public boolean containsKey(K key) {
        int index = hashFun(key);
        int bucketIndex = IndexWithinBucket(index , key);

        if(bucketIndex != -1){          //we got the node with key
            return true;
        }
        else {
            return false;
        }
    }

    public V remove(K key) throws Exception {
        int index = hashFun(key);
        int bucketIndex = IndexWithinBucket(index , key);

        if(bucketIndex != -1){          //we got the node with key
            //update
            HMNode node = buckets[index].remove(bucketIndex);
            size--;
            return node.value;
        }
        else {
            return null;
        }
    }

    public ArrayList<K> keyset() throws Exception {
        ArrayList<K> keys = new ArrayList<>();
        for (int i=0;i<buckets.length;i++){
            for (HMNode node : buckets[i]){
                keys.add(node.key);
            }
        }
        return keys;
    }

    public int size() {
        return buckets.length;
    }

    public void display() {
        System.out.println("Display Begins");
        for (int bi = 0; bi < buckets.length; bi++) {
            System.out.print("Bucket" + bi + " ");
            for (HMNode node : buckets[bi]) {
                System.out.print( node.key + "@" + node.value + " ");
            }
            System.out.println(".");
        }
        System.out.println("Display Ends");
    }
}
