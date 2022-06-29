package com.company.HashmapAndHeap;

import java.util.ArrayList;
import java.util.HashMap;

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






}
