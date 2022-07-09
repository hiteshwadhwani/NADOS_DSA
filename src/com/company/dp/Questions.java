package com.company.dp;

import java.util.Scanner;

//tabulation (recommended) (iterative approach)-
//1) storage and find meaning
//2) find direction (small problem(base case) to big problem(given problem))
//3) solve in the direction

//memoization (recursive approach)

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

    //https://leetcode.com/problems/min-cost-climbing-stairs/
    public int minCostClimbingStairs(int[] cost) {
        //tabulation
        int[] tb = new int[cost.length + 1];
        tb[cost.length] = 0;
        for (int i=cost.length -1;i>=0;i--){
            int min = Integer.MAX_VALUE;
            for (int j=1;j<=2 && i+j < tb.length; j++){
                min = Math.min(min , tb[i+j]);
            }
            tb[i] = cost[i] + min;
        }
        return Math.min(tb[0],tb[1]);

    }

    //https://nados.io/question/min-cost-in-maze-traversal
    public static int MinCostInMazeTraversal(int[][] maze){
        int[][] tb = new int[maze.length][maze[0].length];
        tb[maze.length-1][maze[0].length-1] = maze[maze.length-1][maze[0].length-1];
        for (int i = maze.length-1;i>=0;i--){
            for (int j=maze[i].length-1;j>=0;j--){
                int min = Integer.MAX_VALUE;
                if(i == maze.length-1 && j == maze[i].length-1){
                    continue;
                }
                if(i + 1 < tb.length){
                    min = Math.min(min , tb[i+1][j]);
                }
                if(j + 1 < tb[i].length){
                    min = Math.min(min , tb[i][j+1]);
                }
                tb[i][j] = min + maze[i][j];
            }
        }
        return tb[0][0];
    }

    public static int Goldmine(int[][] maze){
        int[][] tb = new int[maze.length][maze[0].length];
        for (int j=tb[0].length-1;j>=0;j--){
            for (int i=0;i<tb.length;i++){
                if(j == tb[0].length-1){
                    tb[i][j] = maze[i][j];
                }
                else if(i == 0){
                    tb[i][j] = maze[i][j] + Math.max(tb[i][j+1] , tb[i+1][j+1]);
                }
                else if(i == tb.length -1){
                    tb[i][j] = maze[i][j] + Math.max(tb[i-1][j+1] , tb[i][j+1]);
                }
                else {
                    tb[i][j] = maze[i][j] + Math.max(tb[i][j+1] , Math.max(tb[i-1][j+1] , tb[i+1][j+1]));
                }
            }
        }
        int ans = tb[0][0];
        for (int i=1;i<tb.length;i++){
            ans = Math.max(ans , tb[i][0]);
        }
        return ans;
    }

    //https://nados.io/question/unbounded-knapsack
    public static int UnboundedKnapsack(int[] val,int[] wt,int n,int bagCapacity){
        int[] tb = new int[bagCapacity];
        for (int i=0;i<=bagCapacity;i++){
            if(i==0){
                tb[i] =0;
            }
            else{
                int max = Integer.MIN_VALUE;
                for (int j=0;j<wt.length;j++){
                    if(wt[j] > i){
                        continue;
                    }
                    max = Math.max(max , val[j] + tb[i - wt[j]]);
                }
                tb[i] = max;
            }

        }
        return tb[n];
    }

    //https://nados.io/question/target-sum-subsets-dp                                         //very imp
    public static boolean TargetSumSubsets(int[] arr,int val,int n){
        boolean[][] dp = new boolean[n+1][val+1];
        for (int i=0;i<n+1;i++){
            dp[i][0] = true;
        }
        for (int i=0;i<n+1;i++){
            for (int j =1;j<val+1;j++){
                if(i == 0){
                    dp[i][j] = false;
                }
                else {
                    if(arr[i-1] > j){
                        dp[i][j] = dp[i-1][j];
                    }
                    else {
                        dp[i][j] = dp[i-1][j] || dp[i-1][j - arr[i-1]];
                    }
                }
            }
        }
        return dp[n][val];
    }


    //leetcode (UnboundedKnapsack)
    //https://leetcode.com/problems/coin-change/
    public int coinChange(int[] coins, int amount) {
        if(amount < 1){
            return 0;
        }
        int[] dp = new int[amount+1];
        for (int i=0;i<=amount;i++){
            if(i==0){
                dp[i] =0;
            }
            else{
                int min = -1;
                for (int j=0;j<coins.length;j++){
                    if(coins[j] > i){
                        continue;
                    }
                    int rem = dp[i - coins[j]];
                    if(rem != -1){
                        int temp = rem + 1;
                        min = min < 0 ? temp : (Math.min(temp, min));
                    }
                }
                dp[i] = min;
            }

        }
        return dp[amount];
    }

    //https://nados.io/question/coin-change-combination
    public static int CoinChangeCombination(int[] coins,int amt){
        //tabulation
        //meaning - dp[i] shows that for how many combination of coin we can sum to i
        //direction = i from 0 to n
        //travel and solve
        //imp - we have to solve combination not permutation
        int[] dp = new int[amt+1];
        dp[0] = 1;  //for amt = 0 we have always one combination to not to take and give any money
        for (int j=0;j< coins.length;j++){
            for (int i=1;i<dp.length;i++){
                if(coins[j] > i){
                    continue;
                }
                int rem = dp[i - coins[j]];
                if(rem != 0){
                    dp[i] = rem + dp[i];
                }
            }
        }
        return dp[amt];
    }


    //https://nados.io/question/coin-change-permutations
    public static int CoinChangePermutations(int[] coins,int amt){
        //tabulation
        //same as CoinChangeCombination but here we need to find permutation
        //for permutation we need to find dp[i] for all coins in one travel only
        int[] dp = new int[amt+1];
        dp[0] =1; // for amt =0 we always have one permutation that is not coin taken or given
        for(int i=1;i<dp.length;i++){
            int sum =0;
            for (int j=0;j<coins.length;j++){
                if(coins[j] > i){
                    continue;
                }
                int rem = dp[i - coins[j]];
                sum += rem;
            }
            dp[i] = sum;
        }
        return dp[amt];

    }




}
