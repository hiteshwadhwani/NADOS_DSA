package com.intermediate.ArraysAndStrings;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Questions {

    //https://nados.io/question/faulty-keyboard
    public static boolean isPossible(String name, String typed) {
       //pattern - two pointer approach
        int p1 = 0;
        int p2 = 0;
        while (p1 < name.length() && p2 < typed.length()){
            char c1 = name.charAt(p1);
            char c2 = typed.charAt(p2);
            if(c1 == c2){
                p1++;
                p2++;
            }
            else if(p1 > 0 && c2 == name.charAt(p1-1)){
                p2++;
            }
            else {
                return false;
            }
        }
        while (p2 < typed.length()){       //base condition 1
            if(typed.charAt(p2) != name.charAt(p1-1)){
                return false;
            }
            p2++;
        }
        if(typed.length() < name.length()) return false;   //base condition 2
        return p1 >= name.length();   //base condition 3
    }
    //https://nados.io/question/next-greater-element-i-i-i
    //https://leetcode.com/problems/next-greater-element-iii/
    public static String nextGreaterElement(String str) {
        //two pointer approach
        char[] arr = str.toCharArray();
        int i = str.length() -2;
        //find the dip
        while (i >= 0 && arr[i] >= arr[i+1]){
            i--;
        }
        if(i == -1){
            return "-1";
        }
        //find just greater tha i
        int j = str.length()-1;
        while (arr[j] <= arr[i]){
            j--;
        }
        //swap i and j
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        String ans = "";
        for (int x = 0;x<=i;x++){
            ans += arr[x];
        }
        for (int x = str.length()-1;x > i;x--){
            ans += arr[x];
        }
        return ans;
    }
    //https://leetcode.com/problems/container-with-most-water/
    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        int i=0,j = height.length-1;
        while (i < j){
            max = Math.max(max , (Math.min(height[i], height[j])) * (j-i) );
            if(height[i]< height[j]){
                i++;
            }
            else {
                j--;
            }
        }
        return max;

    }

    //https://nados.io/question/majority-element-i
    public static void printMajorityElement(int[] arr) {
        //mapping of array elements to find potential answer
        //we have to map distinct elements of array with each other to find potential answer
        int val = arr[0];
        int count = 1;
        for (int i=1;i<arr.length;i++){
            if(val == arr[i]){
                count++;
            }
            else {
                count--;
            }
            if(count == 0){
                val = arr[i];
                count = 1;
            }
        }
        int c = 0;
        for (int i=0;i<arr.length;i++){
            if(val == arr[i]) c++;
        }
        System.out.println(c > arr.length / 2 ? val : "No Majority Element exist");
    }
    public static ArrayList<Integer> majorityElement2(int[] arr) {
       //mapping elements to find potential answer
        //for finding elements appearing more than n/3 - possible answers are n%3 - 0,1,2
        int val1 = arr[0],val2 = arr[0],count1 = 1,count2 = 0;
        for (int i=1;i<arr.length;i++){
            if(arr[i] == val1){
                count1++;
            }
            else if(arr[i] == val2){
                count2++;
            }
            else{
                if(count1 == 0){
                    val1 = arr[i];
                    count1 = 1;
                }
                else if(count2 == 0){
                    val2 = arr[i];
                    count2 = 1;
                }
                else{
                    count1--;
                    count2--;
                }
            }
        }
        //potential answers are val1 and val2
        count1=0;
        count2=0;
        for (int i:arr){
            if(i == val1){
                count1++;
            }
            else if(i == val2){
                count2++;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        if(count1 > arr.length/3){
            ans.add(val1);
        }
        if(count2 > arr.length/3){
            ans.add(val2);
        }
        return ans;
    }

    public static int maxChunksToSorted(int[] arr) {
        //cyclic sort pattern
        int count =0;
        int max = 0;
        for (int i=0;i<arr.length;i++){
            max = Math.max(arr[i],max);
            if(max == i){ //at this condition all elements on the left of i are smaller than max and on right side of i are greater than max therefore we can make a wall or make a chunk
                count++;
            }
        }
        return count;
    }
    public static int maxChunksToSorted2(int[] arr) {
        int max = arr[0];
        int[] min = new int[arr.length+1];
        min[arr.length] = Integer.MAX_VALUE;
        min[arr.length-1] = arr[arr.length-1];
        for (int i=arr.length-2;i>=0;i--){
            min[i] = Math.min(arr[i],min[i+1]);
        }
        int count =0;
        for (int i=0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
            if(max <= min[i+1]){
                count++;
            }
        }
        return count;
    }
    //https://nados.io/question/range-addition
    public static int[] Range(int length, int[][] updates) {
        //pattern - prefix sum
        //time complexity - O(n+k)
        int[] ans = new int[length];
        for (int k=0;k<updates.length;k++){
            int minIndex = updates[k][0];
            int maxIndex = updates[k][1];
            int val = updates[k][2];
            ans[minIndex] = ans[minIndex] + val;
            if(maxIndex < length-1){
                ans[maxIndex+1] = ans[maxIndex+1] - val;
            }
        }
        //prefix sum of array ans
        for (int i=1;i<length;i++){
            ans[i] = ans[i] + ans[i-1];
        }
        return ans;
    }

    //https://nados.io/question/product-of-array-except-itself-without-using-division-operator
    public static int[] productExceptSelf(int[] arr) {
        //prefix and suffix
        //we will calculate the suffix on the go
        int[] suffix = new int[arr.length];
        suffix[arr.length-1]= arr[arr.length-1];
        for (int i=arr.length-2;i>=0;i++){
            suffix[i] = arr[i] * suffix[i+1];
        }
        int prefix = 1;
        int[] ans = new int[arr.length];
        for (int i=0;i<arr.length;i++){
            if(i == arr.length-1){
                ans[i] = prefix;
            }
            else{
                ans[i] = prefix * suffix[i+1];
            }
            prefix = prefix * arr[i];
        }
        return ans;
    }


    //https://nados.io/question/partition-labels
    //https://leetcode.com/problems/partition-labels/
    public List<Integer> partitionLabels(String s) {
        //we will use hashmap to store the max impact of every character present in string s
        List<Integer> ans = new ArrayList<>();
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.replace(c,i);
            }
            else {
                map.put(c,i);
            }
        }
        int min = -1;
        int max = 0;
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            int impact = map.get(c);
            if(impact > max){
                max = impact;
            }
            if(max == i){
                ans.add(max - min);
                min = max;
                max = 0;
            }
        }
        return ans;
    }

    //https://leetcode.com/problems/partition-array-into-disjoint-intervals/
    public int partitionDisjoint(int[] nums) {
        //max impact pattern
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = nums[0];
        for(int i=1;i<n;i++){
            left[i] = Math.max(left[i-1],nums[i]);
        }
        right[n-1] = nums[n-1];
        for (int i=n-2;i>=0;i--) {
            right[i] = Math.min(right[i + 1], nums[i]);
        }
        for (int i=0;i<n-1;i++){
            if(left[i] < right[i+1]){
                return i+1;
            }
        }
        return n;
    }

    //https://nados.io/question/wiggle-sort-1
    public static void wiggleSort(int[] arr) {
        for (int i=0;i<arr.length;i++){
            if(i%2 == 0){
                //even index
                if(arr[i+1]<arr[i]){
                    swap(arr,i+1,i);
                }
            }
            else {
                //odd index
                if(arr[i+1] > arr[i]){
                    swap(arr,i,i+1);
                }
            }
        }
    }
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    //https://nados.io/question/wiggle-sort-2
    public static void wiggleSort2(int[] nums) {
        //time complexity - O(nlogn)
        //space complexity - O(n)
        int[] copy = Arrays.copyOf(nums,nums.length);
        Arrays.sort(copy);
        int right = (nums.length + 1) / 2 -1;
        int left = nums.length-1;
        for (int i=0;i<nums.length;i++){
            if(i%2 == 0){
                //even index
                nums[i] = copy[left--];
            }
            else {
                nums[i] = copy[right--];
            }
        }
    }

    //https://nados.io/question/majority-element-general
    public static ArrayList<Integer> majorityElement(int[] arr, int k) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (int i:arr){
            if(hm.containsKey(i)){
                hm.replace(i,hm.get(i)+1);
            }
            else {
                hm.put(i,1);
            }
        }
        int x = arr.length/k;
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer i:hm.keySet()){
            if(hm.get(i) > x){
                ans.add(i);
            }
        }
        return ans;
    }

    //https://nados.io/question/max-product-of-three-numbers
    //https://leetcode.com/problems/maximum-product-of-three-numbers/submissions/
    public static int maximumProduct(int[] arr) {
        int max = Integer.MIN_VALUE;
        int max2 = Integer.MAX_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int i=0;i<arr.length;i++){
            if(arr[i] > max){
                max3 = max2;
                max2 = max;
                max = arr[i];
            }
            else if(arr[i] > max2){
                max3 = max2;
                max2 = arr[i];
            }
            else if(arr[i] > max3){
                max3 = arr[i];
            }
            else if(arr[i] < min){
                min2 = min;
                min = arr[i];
            }
            else if(arr[i] < min2){
                min2 = arr[i];
            }
        }
        return Math.max(max * max2 * max3, max * min2 * min);
    }

    //https://leetcode.com/problems/jump-game/
    public boolean canJump(int[] nums) {
        //dp
        //tabulation
        //reverse direction
        int last = nums.length -1;
        for (int i=nums.length-2;i>=0;i--){
            if(i + nums[i] >= last){
                last = i;
            }
        }
        return last == 0;
    }

    //https://leetcode.com/problems/jump-game-ii/
    public static int jump(int[] nums) {
        int[] dp = new int[nums.length];
        //tabulation
        //meaning for dp[i] - minimum jumps needed to reach the last index
        //direction - reverse
        dp[nums.length-1] = 0; //for last index to reach index minimum jumps needed is 0
        for (int i=nums.length-2;i>=0;i--){
            int min = Integer.MAX_VALUE;
            for (int j=1;j<=nums[i] && i+j < nums.length;j++){
                if(dp[i+j] != -1){
                    min = Math.min(min , 1+dp[i+j]);
                }
            }
            dp[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return dp[0];
    }

    //https://leetcode.com/problems/jump-game-ii/
    public static int jumpGreedy(int[] nums) {
        //greedy
        int jump = 0;
        int currIndex = 0;
        int max = 0;
        int i=0;
        while (i != nums.length-1){
            max = Math.max(i + nums[i],max);
            if(i == currIndex){
                //if i reaches curr index it will jump on another big ladder
                jump++;
                currIndex = max;
            }
        }
        return jump;
    }

    //https://leetcode.com/problems/jump-game-iii/
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return canReachRec(arr,start,start,visited);
    }
    public boolean canReachRec(int[] arr,int start,int index,boolean[] visited){
        if(index >= arr.length || index < 0){
            return false;
        }
        else if(arr[index] == 0){
            return true;
        }
        if(visited[index]){
            return false;
        }
        visited[index] = true;
        boolean res1 = canReachRec(arr,start,index + arr[index],visited);
        boolean res2 = canReachRec(arr,start,index - arr[index],visited);
        return res1 || res2;
    }

    //https://nados.io/question/min-jumps-with-i-i-moves
    public static int minJumps(int x) {
        //move toward the destination and find (minimum distance)
        //if (minimum distance) == even then reverse the jump (minimum distance) / 2
        //if (minimum distance) == odd take another jump -> if next jump is odd then minimum distance will be even and reverse the jump (minimum distance) / 2
        //                                               -> if next jump is even then minimum distance will again be odd -> then take another jump which will be surely of
        //                                                                                                                  size odd and minimum distance will be even and
        //                                                                                                                  reverse the jump (minimum distance) / 2
        int sum = 0; //current index
        int i = 1; //next jump
        while (sum < x){
            sum += i;
            i++;
        }
        if(sum == x){
            return i-1;
        }
        else if((sum - x) % 2 == 0){ //when minimum distance is even
            return i-1;
        }
        else if((sum + i - x) % 2 == 0){ //when minimum distance after adding next jump is even
            return i;
        }
        else {
            return i+1; //when minimum distance after adding next jump is odd
        }

    }

    //https://leetcode.com/problems/squares-of-a-sorted-array/
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int n = nums.length;
        int i=0,j = n-1;
        int idx = n-1;
        while (i <= j){
            if(Math.abs(nums[i]) > Math.abs(nums[j])){
                ans[idx--] = nums[i] * nums[i];
                i++;
            }
            else {
                ans[idx--] = nums[j] * nums[j];
                j--;
            }
        }
        return ans;
    }

    //count sort
    public static void countSort(int[] arr, int min, int max) {
        int[] freq = new int[max-min+1];
        //filling the frequency array
        for(int i=0;i<arr.length;i++){
            int idx = arr[i] - min;
            freq[idx]++;
        }
        System.out.println(Arrays.toString(freq));
        //make prefix sum array
        for (int i=1;i<freq.length;i++){
            freq[i] = freq[i] + freq[i-1];
        }
        System.out.println(Arrays.toString(freq));
        //loop the original array in reverese direction
        int[] ans = new int[arr.length];
        for (int i=arr.length-1;i>=0;i--){
            int index = freq[arr[i] - min]-1;
            ans[index] = arr[i];
            freq[arr[i] - min]--;
        }
        for(int i=0;i<arr.length;i++){
            arr[i] = ans[i];
        }
    }

    //https://leetcode.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int max = -1;
        int count = 0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    max = Math.max(max , visitIslandRecursive(grid,visited,i,j));
                    count++;
                }
            }
        }
        return count == 0 ? 0 : max;
    }
    public int visitIslandRecursive(int[][] grid,boolean[][] visited,int i,int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]){
            return 0;
        }
        visited[i][j] = true;
        int left = visitIslandRecursive(grid,visited,i,j-1);
        int right = visitIslandRecursive(grid,visited,i,j+1);
        int up = visitIslandRecursive(grid,visited,i-1,j);
        int down = visitIslandRecursive(grid,visited,i+1,j);
        return left+right+up+down+1;
    }


    //https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int res =0 , dp= 0;
        int prev = -1;
        for (int i=0;i<nums.length;i++){
            if(i > 0 && nums[i] < left){
                res += dp;
            }
            if(nums[i] > right){
                dp = 0;
                prev = i;
            }
            if(nums[i] >= left && nums[i] <= right){
                //left<=nums[i]<=right
                dp = i-prev;
                res += dp;
            }
        }
        return res;
    }



    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,1,1,4}));
    }


}
