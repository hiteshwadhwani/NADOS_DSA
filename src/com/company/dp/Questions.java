package com.company.dp;

import java.util.Scanner;

public class Questions {
    //https://nados.io/question/fibonacci-dp
    public static int Fibonacci_dp(int n,int[] qb){
        //memoization
        if(n==0 || n==1) return n;
        if(qb[n] != 0){
            return qb[n];
        }
        int ans = Fibonacci_dp(n-1,qb) + Fibonacci_dp(n-2,qb);
        qb[n] = ans;
        return ans;
    }

    //https://nados.io/question/climb-stairs
    public static int ClimbStairs(int n,int[] qb){
        //memoization
        if(n<0){
            return 0;
        }
        else if(n ==0){
            return 1;
        }
        if(qb[n] != 0){
            return qb[n];
        }
        int pn1 = ClimbStairs(n-1,qb);
        int pn2 = ClimbStairs(n-2,qb);
        int pn3 = ClimbStairs(n-3,qb);
        int ans = pn1+pn2+pn3;
        qb[n] = ans;
        return ans;
    }

    //https://nados.io/question/climb-stairs
    public static int ClimbStairs2(int n){
        //tabulation
        int[] tb = new int[n+1];
        tb[0] =1;
        for (int i=1;i<=n;i++){
            if(i ==1){
                tb[i] =  tb[i-1];
            }
            else if(i ==2){
                tb[i] = tb[i-1]+tb[i-2];
            }
            else {
                tb[i] = tb[i-1]+tb[i-2]+tb[i-3];
            }
        }
        return tb[n];
    }

    //https://nados.io/question/climb-stairs-with-variable-jumps
    public static int ClimbStairsWithVariableJumps(int[] arr){
        //tabulation
        int[] dp = new int[arr.length+1];
        dp[arr.length] = 1;
        for (int i=arr.length-1;i>=0;i--){
            for (int j=1;j<=arr[i] && i+j < dp.length;j++){
                dp[i] += dp[i+j];
            }
        }
        return dp[0];
    }
    public static int ClimbStairsWithVariableJumps2(int[] arr,int n,int[] qb){
        //memoization
        if(n == arr.length){
            return 1;
        }
        else if(n > arr.length){
            return 0;
        }
        if(qb[n] != 0){
            return qb[n];
        }
        int ans =0;
        for (int i=1;i<=arr[n];i++){
            ans += ClimbStairsWithVariableJumps2(arr , n+i,qb);
        }
        qb[n] = ans;
        return ans;
    }

    //https://nados.io/question/climb-stairs-with-minimum-moves
    public static int ClimbStairsWithMinimumMoves(Integer[] arr) {
        //tabulation
        Integer[] dp = new Integer[arr.length+1];
        dp[arr.length] =0;
        for (int i=arr.length-1;i>=0;i--){
            int min = Integer.MAX_VALUE;
            for (int j=1;j<=arr[i] && i+j < dp.length;j++){
                if(dp[i+j] != null){
                    min = Math.min(min , dp[i+j]);
                }
            }
            if(min != Integer.MAX_VALUE){
                dp[i] = min+1;
            }
        }
        return dp[0];
    }



}
