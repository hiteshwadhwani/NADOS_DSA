package com.company.TimeAndSpace;

public class Question {


    //https://nados.io/question/bubble-sort
    //bubble sort
    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping " + arr[i] + " and " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // return true if ith element is smaller than jth element
    public static boolean isSmaller(int[] arr, int i, int j) {
        System.out.println("Comparing " + arr[i] + " and " + arr[j]);
        if (arr[i] < arr[j]) {
            return true;
        } else {
            return false;
        }
    }
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for(int i=n-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(isSmaller(arr,j+1,j)){
                    swap(arr,j+1,j);
                }
            }
        }

    }

    //https://nados.io/question/selection-sort
    //Selection sort
    //At each itetration smaller element come at starting index
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for(int i=0;i<n-1;i++){
            int midIndex = i;
            for(int j=i+1;j<n;j++){
                if(isSmaller(arr,j,midIndex)){
                    midIndex = j;
                }
            }
            swap(arr,i,midIndex);
        }

    }

    //https://nados.io/question/insertion-sort
    //Insertion Sort
    //best case O(n)
    //worst case O(n^2)
    public static boolean isGreater(int[] arr, int j, int i) {
        System.out.println("Comparing " + arr[i] + " and " + arr[j]);
        if (arr[i] < arr[j]) {
            return true;
        } else {
            return false;
        }
    }
    public static void insertionSort(int[] arr) {
        //at Each itetration array start becoming sorted from start
        int n = arr.length;
        for(int i=0;i<n-1;i++){
            //till i array is sorted
            for(int j=i+1;j>0;j--){
                if(isGreater(arr,j-1,j)){
                    swap(arr,j-1,j);
                }
                else {
                    break;
                }
            }
        }

    }

    //https://nados.io/question/merge-two-sorted-arrays
    //Merge two sorted array in linear time complexity
    public static int[] mergeTwoSortedArrays(int[] a, int[] b){
        int[] ans = new int[a.length+b.length];
        int idx = 0;
        int i=0,j=0;
        while (i < a.length && j < b.length){
            if(a[i] < b[j]){
                ans[idx++] = a[i++];
            }
            else {
                ans[idx++] = b[j++];
            }
        }
        while (i < a.length){
            ans[idx++] = a[i++];
        }
        while (j < b.length){
            ans[idx++] = b[j++];
        }
        return ans;

    }

    //merge sort
    //divide and conquer
    public static int[] mergeSort(int[] arr, int lo, int hi) {
        if(hi - lo == 0){
            return new int[]{arr[lo]};
        }
        int mid = lo + (hi-lo)/2;
        int[] left = mergeSort(arr,lo,mid);
        int[] right = mergeSort(arr,mid+1,hi);
        return mergeTwoSortedArrays(left,right);
    }

}
