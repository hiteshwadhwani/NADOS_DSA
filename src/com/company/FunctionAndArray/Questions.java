package com.company.FunctionAndArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Questions {

    //https://nados.io/question/any-base-addition
    public static int getSum(int b, int n1, int n2){
        int carry = 0;
        int ans = 0;
        int decimalPlace = 1;
        while(n1 > 0 || n2 > 0 || carry > 0){
            int last1 = n1 % 10;
            int last2 = n2 % 10;
            int sum = last1 + last2 + carry;
            carry = sum / b;
            int val = sum % b;
            ans += val * decimalPlace;
            decimalPlace = decimalPlace * 10;
            n1 = n1 / 10;
            n2 = n2 / 10;
        }
        return ans;
    }
    //https://nados.io/question/any-base-multiplication
    public static int getProduct(int b, int n1, int n2){
        int ans = 0;
        int p = 1;
        while (n2 > 0){
            int last = n2 % 10;
            n2 = n2 / 10;
            int prod = getProductofOneDigit(b,n1,last);
            ans = getSum(b,ans,prod * p);
            p *= 10;
        }
        return ans;
    }
    public static int getProductofOneDigit(int b,int n1,int n2) {
        int ans = 0;
        int carry = 0;
        int decimal = 1;
        while (n1 > 0) {
            int last = n1 % 10;
            int prod = (n2 * last) + carry;
            carry = prod / b;
            int val = prod % b;
            ans += val * decimal;
            decimal = decimal * 10;
            n1 = n1 / 10;
        }
        return ans;
    }

    //https://nados.io/question/span-of-array
    public static int SpanOfArray(int n,int[] arr){
        int min = arr[0];
        int max = arr[0];
        for (int i:arr){
            max = Math.max(max,i);
            min = Math.min(max,i);
        }
        return max-min;
    }
    //https://nados.io/question/find-element-in-an-array
    public static int Find_Element_In_An_Array(int[] arr,int k){
        for (int i=0;i<arr.length;i++){
            if(arr[i] == k){
                return i;
            }
        }
        return -1;
    }

    //https://nados.io/question/bar-chart
    public static void Bar_Chart(int[] arr){
        int max = arr[0];
        for (int i:arr){
            max = Math.max(max,i);
        }
        for (int i=max-1;i >= 0;i--){
            for (int j:arr){
                if(j > i){
                    System.out.print('*');
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    //https://nados.io/question/sum-of-two-arrays
    public static void Sum_Of_Two_Arrays(int[] arr1,int[] arr2,int n1,int n2){
        int[] ans = new int[n1 > n2 ? n1 : n2];
        int p = ans.length-1;
        int i= n1 -1;
        int j = n2 -1;
        int carry = 0;
        while (i >=0 || j >= 0){
            int elem1 = i < 0 ? 0 : arr1[i--];
            int elem2 = j < 0 ? 0 : arr2[j--];
            int sum = elem1+elem2+carry;
            carry = sum / 10;
            ans[p] = sum%10;
            p--;
        }
        if(carry > 0){
            System.out.println(carry);
        }
        for (int elem:ans){
            System.out.println(elem);
        }
    }

    //https://nados.io/question/difference-of-two-arrays
    public static int Difference_Of_Two_Arrays(int n1,int n2,int[] arr1, int[] arr2){
        int i = n1-1;
        int j = n2-1;
        int carry = 0;
        int ans = 0;
        int decimal = 1;
        while (j >= 0){
            int last1 =  i < 0 ? 0 : arr1[i] % 10;
            int last2 = arr2[j] % 10;
            int sub = (last2 + carry) - last1;
            if(sub < 0){
                carry = -1;
                sub = sub + 10;
            }
            else {
                carry = 0;
            }
            ans += sub * decimal;
            decimal *= 10;
            i--;
            j--;
        }
        return ans;
    }

    //https://nados.io/question/rotate-an-array
    public static void rotate(int[] a, int k){
        //reverse 3 times
        int n = a.length;
        k = k % n;
        if(k < 0){
            k = n+k;
        }
        reverse_i_to_j(a,0,n-1);
        reverse_i_to_j(a,0, k-1);
        reverse_i_to_j(a,k,n-1);
    }
    public static void reverse_i_to_j(int[] a,int i,int j){
        while (i<=j){
            swap(a, i, j);
            i++;
            j--;
        }
    }
    public static void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int[] inverse(int[] a){
        int[] ans = new int[a.length];
        for(int i=0;i<a.length;i++){
            ans[a[i]] = i;
        }
        return ans;
    }
    //https://nados.io/question/subarray-problem
    public static void subArray(int[] arr){
        for (int i=0;i<arr.length;i++){
            for (int j=i;j<arr.length;j++){
                for (int x=i;x<=j;x++){
                    System.out.print(arr[x]+"   ");
                }
                System.out.println();
            }
        }
    }

    //https://nados.io/question/subsets-of-array
    public static void Subsets(int[] arr,String ans,int i){
        if(i == arr.length){
            System.out.println(ans);
            return;
        }
        Subsets(arr,ans+"-  ",i+1);
        Subsets(arr,ans+arr[i]+" ",i+1);

    }

    //https://nados.io/question/broken-economy
    public static void findCeilAndFloor(int[] arr,int k){
        int l= 0;
        int h = arr.length-1;
        int ans = -1;
        while (l <= h){
            int mid = h + (l-h) /2;
            if(arr[mid] == k){
                ans = mid;
                break;
            }
            else if(arr[mid] < k){
                l = mid +1;
            }
            else {
                h = mid -1;
            }
        }
        if(ans != -1){
            System.out.println(ans);
        }
        else {
            System.out.println(arr[l]);
            System.out.println(arr[h]);
        }
    }

    //https://nados.io/question/first-index-and-last-index
    public static void First_Index_And_Last_Index(int[] arr,int k){
        System.out.println(binarySearch1(arr,true,k));
        System.out.println(binarySearch1(arr,false,k));
    }
    public static int binarySearch1(int[] arr,boolean leftIndex,int k){
        int ans = -1;
        int l=0,h=arr.length-1;
        while (l <= h){
            int mid = h + (l-h)/2;
            if(arr[mid] == k){
                ans = mid;
                if(leftIndex){
                    h = mid-1;
                }
                else {
                    l = mid+1;
                }
            }
            else if(arr[mid] < k){
                l = mid +1;
            }
            else {
                h = mid -1;
            }
        }
        return ans;
    }

    public static void Spiral_Display(int[][] arr,int n,int m){
        int minrow = 0;
        int mincol = 0;
        int maxrow = arr.length - 1;
        int maxcol = arr[0].length - 1;
        int tne = n * m;     //total numbers of elements
        int count = 0;
        while (count < tne) {

            //left wall
            if (count < tne) {
                for (int i = minrow; i <= maxrow; i++) {
                    System.out.println(arr[i][mincol]);
                    count++;
                }
            }
            mincol++;

            //bottom wall
            if (count < tne) {
                for (int i = mincol; i <= maxcol; i++) {
                    System.out.println(arr[maxrow][i]);
                    count++;
                }
            }
            maxrow--;

            //right wall
            if (count < tne) {
                for (int i = maxrow; i >= minrow; i--) {
                    System.out.println(arr[i][maxcol]);
                    count++;
                }
            }
            maxcol--;

            //top wall
            if (count < tne) {
                for (int i = maxcol; i >= mincol; i--) {
                    System.out.println(arr[minrow][i]);
                    count++;
                }
            }
            minrow++;
        }
    }

    //Rotate By 90 Degree
    public static void rotate(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;i<n;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        int[][] ans = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                ans[i][j] = arr[n-1-j][i];
            }
        }
    }

    public static void Ring_Rotate(int[][] arr,int s,int r){
        int[] ring = extractRing(arr,s);
        rotateRing(ring,r);
        updateArray(arr,ring,s);
    }
    public static int[] extractRing(int[][] arr,int s){
        int minRow = s-1;
        int maxRow = arr.length - s;
        int minCol = s-1;
        int maxCol = arr[0].length - s;
        int size = 2*(maxCol-minCol+1) + 2*(maxRow-minRow+1) -4; //can also be written as 2*(maxCol+maxRow-minCol-minRow)
        int[] ans = new int[size];
        int idx = 0;

        //left wall
        for(int i=minCol;i<=maxCol;i++){
            ans[idx++] = arr[minRow][i];
        }
        minRow++;
        //down wall
        for (int i=minRow;i<=maxRow;i++){
            ans[idx++] = arr[i][maxCol];
        }
        maxCol--;
        //right wall
        for (int i=maxCol;i>=minCol;i--){
            ans[idx++] = arr[maxRow][i];
        }
        maxRow--;
        //up wall
        for (int i=maxRow;i>=minRow;i--){
            ans[idx++] = arr[i][minCol];
        }
        return ans;
    }
    public static void rotateRing(int[] ring,int r){
        int n = ring.length;
        r = r % n;
        if(r < 0){
            r = n+r;
        }
        reverse_i_to_j(ring,0,n-1);
        reverse_i_to_j(ring,0,n-r-1);
        reverse_i_to_j(ring,n-r,n-1);
    }
    public static void updateArray(int[][] arr,int[] ring,int s){
        int minRow = s-1;
        int maxRow = arr.length - s;
        int minCol = s-1;
        int maxCol = arr[0].length - s;
        int idx = 0;

        //left wall
        for(int i=minCol;i<=maxCol;i++){
            arr[minRow][i] = ring[idx++];
        }
        minRow++;
        //down wall
        for (int i=minRow;i<=maxRow;i++){
            arr[i][maxCol] = ring[idx++];
        }
        maxCol--;
        //right wall
        for (int i=maxCol;i>=minCol;i--){
            arr[maxRow][i] = ring[idx++];
        }
        maxRow--;
        //up wall
        for (int i=maxRow;i>=minRow;i--){
            arr[i][minCol] = ring[idx++];
        }
    }

    //https://leetcode.com/problems/diagonal-traverse-ii/
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int size = 0;
        int maxColSize = 0;
        for (List<Integer> l:nums){
            size += l.size();
            maxColSize = Math.max(maxColSize,l.size());
        }
        int[] ans = new int[size];
        int idx = 0;
        for (int row=0;row<nums.size();row++){
            int col = 0;
            for (int i=row;i>=0;i--){
                if(col < nums.get(i).size()){
                    ans[idx++] = nums.get(i).get(col);
                }
                col++;
            }
        }
        for (int col=1;col<maxColSize;col++){
            int row = nums.size()-1;
            for (int j=col;j<maxColSize;j++){
                if(j < nums.get(row).size()){
                    ans[idx++] = nums.get(row--).get(j);
                }
                row--;
            }
        }
        return ans;
    }

    //https://leetcode.com/problems/lucky-numbers-in-a-matrix/
    public List<Integer> luckyNumbers (int[][] matrix) {
        //loop on row
        List<Integer> ans = new ArrayList<>();
        for (int i=0;i<matrix.length;i++){
            int lvc = 0; //least value column
            for (int j=1;j<matrix[0].length;j++){
                if(matrix[i][j] < matrix[i][lvc]){
                    lvc = j;
                }
            }
            boolean flag = true;
            for (int row=0;row<matrix.length;row++){
                if(matrix[row][lvc] > matrix[i][lvc]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                ans.add(matrix[i][lvc]);
                break;
            }
        }
        return ans;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int row = -1;
        for (int i=0;i<n;i++){
            if(target < matrix[i][m-1]){
                row = i;
                break;
            }
        }
        //binary search on that row
        int lo = 0;
        int hi = m-1;
        while (lo <= hi){
            int mid = hi + (lo-hi)/2;
            if(matrix[row][mid] == target){
                return true;
            }
            else if(matrix[row][mid] < target){
                lo = mid+1;
            }
            else {
                hi = mid-1;
            }
        }
        return false;
    }


    //https://nados.io/question/print-all-palindromic-substrings
    public static void Print_All_Palindromic_Substrings(String str){
       for (int i=0;i<str.length();i++){
           for (int j=i;j<str.length();j++){
               String sub = str.substring(i,j);
               if(checkPalindrome(sub)){
                    System.out.println(sub);
                }
           }
       }
    }


    public static boolean checkPalindrome(String str){
        int i=0;
        int j=str.length()-1;
        while (i<=j){
            if(str.charAt(i) != str.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    //https://nados.io/question/string-compression
    public static void String_compression1(String str){
        StringBuilder ans = new StringBuilder();
        ans.append(str.charAt(0));
        for (int i=1;i<str.length();i++){
            if(str.charAt(i) != str.charAt(i-1)){
                char c = str.charAt(i);
                ans.append(c);
            }
        }
        System.out.println(ans);
    }
    public static void String_compression2(String str){
        StringBuilder ans = new StringBuilder();
        int i=0;
        int count =1;
        while (i < str.length()){
            if(i+1 < str.length() && str.charAt(i) == str.charAt(i+1)){
                i++;
                count++;
            }
            else {
                ans.append(str.charAt(i));
                if(count != 1){
                    ans.append(count);
                }
                i++;
                count=1;
            }
        }
        System.out.println(ans);
    }


    //https://nados.io/question/string-with-difference-of-every-two-consecutive-characters
    public static void String_With_Difference_Of_Every_Two_Consecutive_Characters(String str){
        StringBuilder ans = new StringBuilder();
        for(int i=0;i<str.length();i++){
            ans.append(str.charAt(i));
            if(i+1 < str.length()){
                ans.append(str.charAt(i+1)-str.charAt(i));
            }
        }
        System.out.println(ans);
    }


    //https://nados.io/question/remove-primes
    public static void remove_primes(ArrayList<Integer> al){
        for (int i=al.size()-1;i>=0;i--){
            if(checkPrime(al.get(i))){
                al.remove(i);
            }
        }

    }
    public static boolean checkPrime(int num){
        for (int div=2;div*div<=num;div++){
            if(num % div == 0){
                return false;
            }
        }
        return true;
    }


    //https://nados.io/question/print-all-permutations-of-a-string-iteratively
    public static void Print_All_Permutations_Of_A_String_Iteratively(String str){
        int fact = factorial(str.length());
        for (int i=0;i<fact;i++){
            StringBuilder tempStr = new StringBuilder(str);
            StringBuilder ans = new StringBuilder();
            int j = str.length();
            int q = i;
            while (j > 0){
                int rem = q % j;
                q = q / j;
                ans.append(tempStr.charAt(rem));
                tempStr.deleteCharAt(rem);
                j--;
            }
            System.out.println(ans);
        }

    }
    public static int factorial(int len){
        if(len == 0 || len == 1){
            return 1;
        }
        return len * factorial(len-1);
    }



    public static void main(String[] args) throws Exception {

        System.out.println(checkPrime(12));
    }

}
