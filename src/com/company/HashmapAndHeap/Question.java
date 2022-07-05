package com.company.HashmapAndHeap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Question {
    //https://nados.io/article/highest-frequency-character
    public static char HighestFrequencyCharacter(String s){
        HashMap<Character , Integer> hm = new HashMap<>();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(hm.containsKey(c)){
                int old = hm.get(c);
                hm.put(c , old+1);
            }
            else {
                hm.put(c , 1);
            }
        }
        char max = s.charAt(0);
        for (char c: hm.keySet()){
            if(hm.get(c) > hm.get(max)){
                max = c;
            }
        }
        return max;
    }

    //https://nados.io/question/get-common-elements-1
    public static void GetCommonElements1(int[] a1,int[] a2){
        HashMap<Integer , Integer> hm = new HashMap<>();
        for (int i:a1){
            if(hm.containsKey(i)){
                int old = hm.get(i);
                hm.put(i , old+1);
            }
            else {
                hm.put(i , 1);
            }
        }
        for (int i:a2){
            if(hm.containsKey(i)){
                System.out.println(i);
                hm.remove(i);
            }
        }
    }

    //https://nados.io/question/get-common-elements-2
    public static void GetCommonElements2(int[] a1,int[] a2){
        HashMap<Integer , Integer> hm = new HashMap<>();
        for (int i:a1){
            if(hm.containsKey(i)){
                int old = hm.get(i);
                hm.put(i , old+1);
            }
            else {
                hm.put(i , 1);
            }
        }
        for (int i:a2){
            if(hm.containsKey(i)){
                System.out.println(i);
                if(hm.get(i) == 1){
                    hm.remove(i);
                }
                else {
                    hm.put(i , hm.get(i) -1);
                }
            }
        }
    }

    //https://nados.io/question/longest-consecutive-sequence-of-elements
    //https://leetcode.com/problems/longest-consecutive-sequence/
    public static void LongestConsecutiveSequenceOfElements(int[] arr){
        //make a hashmap for arr
        HashMap<Integer , Boolean> hm = new HashMap<>();
        for (int i:arr){
            hm.put(i , true);
        }
        for (int i:arr){
            if(hm.containsKey(i-1)){
                hm.put(i , false);
            }
        }
        int st = -1;   //starting
        int max = Integer.MIN_VALUE;   //max length
        for (int i:hm.keySet()){
            if(hm.get(i)){
                int len = 1;
                int stp = i;
                while (hm.containsKey(stp + len)){
                    len++;
                }
                if(len > max){
                    max = len;
                    st = stp;
                }
            }

        }
        //print elements
        for (int i=0;i<max;i++){
            System.out.println(st+i);
        }
    }

    //https://nados.io/question/k-largest-elements
    public static void solve(int n,int[] arr,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i:arr){
            pq.add(i);
        }
        for (int i=0;i<k;i++){
            System.out.print(pq.remove() + " ");
        }
    }

    //https://nados.io/question/sort-k-sorted-array
    public static void SortKsortedArray(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        //add k+1 elements in priorityQueue
        for (int i=0;i<k+1;i++){
            pq.add(arr[i]);
        }

        //add 1 element and remove 1 element at a time
        for (int i=k+1;i<arr.length;i++){
            System.out.println(pq.remove());
            pq.add(arr[i]);
        }
        //now the array is empty remove all the elements from the priorityQueue
        while (!pq.isEmpty()){
            System.out.println(pq.remove());
        }

        //time complexity - O(nlogk)
        //why log k ? - because we have maintained the height of priorityQueue k
    }
    static class Pair implements Comparable<Pair>{
        int LI; //list index
        int DI; //data index
        int data; //data
        Pair(int LI,int DI,int data){
            this.LI = LI;
            this.DI = DI;
            this.data = data;
        }

        @Override
        public int compareTo(Pair o) {
            return this.data - o.data;
        }
    }

    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists){
        ArrayList<Integer> rv = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (int i=0;i<lists.size();i++){
            pq.add(new Pair(i , 0, lists.get(i).get(0)));
        }

        while (!pq.isEmpty()){
            //remove
            Pair top = pq.remove();

            //work
            rv.add(top.data);

            //add
            if(top.DI+1 < lists.get(top.LI).size()){
                pq.add(new Pair(top.LI , top.DI +1 , lists.get(top.LI).get(top.DI+1)));
            }


        }

        return rv;
    }







}
