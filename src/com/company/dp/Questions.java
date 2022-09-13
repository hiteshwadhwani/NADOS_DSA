package com.company.dp;

import java.util.ArrayList;
import java.util.Arrays;
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
    static class PairZeroOneKnapsack{
        int max; //max value
        ArrayList<Integer> list; //track of indices of value used to create max value
        PairZeroOneKnapsack(int max){
            this.max = max;
            this.list = new ArrayList<>();
        }
    }



    //https://nados.io/question/zero-one-knapsack
    //(!!!!!!!!!!!failed few test cases!!!!!!!!!!(sumeet sir approach written below!!!!!!!!!)
    public static int ZeroOneKnapsack(int[] val,int[] wt,int bagCap){
        //tabulation
        PairZeroOneKnapsack[] dp = new PairZeroOneKnapsack[bagCap+1];
        dp[0] = new PairZeroOneKnapsack(0);
        for (int i=1;i<bagCap+1;i++){
            PairZeroOneKnapsack nextans = new PairZeroOneKnapsack(0);
            for (int j=0;j<val.length;j++){
                //weight should not be greater than bagCapacity
                if(wt[j] > i){
                    continue;
                }
                PairZeroOneKnapsack rem = dp[i - wt[j]];
                int nextMax = val[j] + rem.max;

                if(!rem.list.contains(j)){
                    if(nextans.max < nextMax){
                        nextans.max = nextMax;
                        nextans.list.clear();
                        nextans.list.addAll(rem.list);
                        nextans.list.add(j);
                    }
                }
            }
            dp[i] = nextans;
        }
        return dp[bagCap].max;
    }


    //https://nados.io/question/zero-one-knapsack
    //(!!!!!!!!!!!!!!sumeet sir approach!!!!!!!!!!!!!!!!!!)
    public static int ZeroOneKnapsack1(int[] val,int[] wt,int bagCap){
        int[][] dp = new int[val.length+1][bagCap+1];
        for (int i=1;i<val.length+1;i++){
            for (int j=1;j<bagCap+1;j++){
                //weight is greater than bagCapacity
                if(wt[i-1] > j){
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j] , val[i-1] + dp[i-1][j - wt[i-1]]);
                }
            }
        }
        return dp[bagCap][val.length];
    }

    static class Items implements Comparable<Items>{
        int val;
        int wt;
        double ratio;
        Items(int val, int wt, double ratio){
            this.val = val;
            this.wt = wt;
            this.ratio = ratio;
        }

        //descending order
        @Override
        public int compareTo(Items o) {
            if(this.ratio == o.ratio){
                return 0;
            }
            else if(this.ratio < o.ratio){
                return +1;
            }
            else {
                return -1;
            }
        }
    }


    //https://nados.io/question/fractional-knapsack-official
    public static double FractionalKnapsack(int[] val,int[] wt,int bagCapacity){
        //not using dynamic approach
        //greedy approach
        //first find out value/weight ration for all items
        //add value if weight<=bagCapacity
        //else add fraction of value fraction*bagCapacityLeft

        Items[] item = new Items[val.length];
        for (int i=0;i<val.length;i++){
            item[i] = new Items(val[i] , wt[i], (val[i]*1.0)/wt[i]);
        }
        Arrays.sort(item);
        int i=0;
        int value = 0;
        while (bagCapacity > 0 && i< item.length){
            if(bagCapacity >= item[i].wt){
                bagCapacity -= item[i].wt;
                value += item[i].val;
            }
            else {
                return value + item[i].ratio*bagCapacity;
            }
            i++;
        }
        return value;
    }



    //<------------NEW PATTERN ALERT------------->
    //<-------------string DP questions---------->

    //patter1 - include or exclude

    //https://nados.io/question/count-binary-strings
    public static int CountBinaryString(int n){
        int[][] dp = new int[2][n+1];
        //dp[0][i] = string of length i ending with 0
        //dp[1][i] = string of length i ending with 1
        dp[0][1] = 1;
        dp[1][1] = 1;
        for (int j=2;j<n+1;j++){
            dp[0][j] = dp[1][j-1];
            dp[1][j] = dp[0][j-1] + dp[1][j-1];
        }
        return dp[0][n] + dp[1][n];
    }

    public static long ArrangeBuildings(int n){
        long[][] dp = new long[2][n+1];
        //dp[0][i] = string of length i ending with building
        //dp[1][i] = string of length i ending with space
        dp[0][1]= 1;
        dp[1][1] =1;
        for (int j=2;j<n+1;j++){
            dp[0][j] = dp[1][j-1];
            dp[1][j] = dp[0][j-1]+dp[1][j-1];
        }
        long sum = dp[0][n] + dp[1][n];
        return sum*sum;
    }

    //https://nados.io/question/count-encodings
    public static int CountEncodings(String str){
        int[] dp = new int[str.length()];
        //dp[i] = number of encodings till index i in string str
        //four cases - 1) 0 and 0
        //             2) 0 and non-zero
        //             3) non-zero and 0
        //             4) non-zero and non-zero
        // str.substring(i-1,i+1) <= 26 - imp case
        dp[0] = 1; //string of length 1 will always contain one encode
        for (int i=1;i<dp.length;i++){
            if(str.charAt(i-1) == '0' && str.charAt(i) == '0'){
                dp[i] = 0; //if both are 0 the no encoding will be made
            }
            else if(str.charAt(i-1) != '0' && str.charAt(i) == '0'){
                if(str.charAt(i-1) == '1' || str.charAt(i-1) == '2'){
                    dp[i] = i-2 < 0 ? 1 : dp[i-2];
                }
                else {
                    dp[i] = 0;
                }
            }
            else if(str.charAt(i-1) == '0' && str.charAt(i) != '0'){
                dp[i] = dp[i-1];
            }
            else {
                if(Integer.parseInt(str.substring(i-1 , i+1)) < 27) {
                    dp[i] = dp[i - 1] + (i-2 < 0 ? 1 : dp[i - 2]);
                }
                else {
                    dp[i] = dp[i-1];
                }
            }

        }
        return dp[str.length()-1];

    }

    //https://nados.io/question/count-a-b-c-subsequences
    public static int CountABCSubsequences(String str){
        //tabulation
        int[][] dp = new int[3][str.length()+1];
        //dp[0][i] = it stores the number of subsequences till str.substring(i) of pattern a+
        //dp[1][i] = it stores the number of subsequences till str.substring(i) of pattern a+b+
        //dp[2][i] = it stores the number of subsequences till str.substring(i) of pattern a+b+c+
        //formula for finding a+ = 2a + 1 //why 1 = a will make subsequence of itself for patter a+
        //formula for finding a+b+ = 2b + a // why + a = because b can make subsequence by adding itself in a+ for pattern a+b+
        //formula for finding a+b+c+ = 2c + b //// why + b = because c can make subsequence by adding itself in a+b+ pattern for pattern a+b+c+
        // why 2a , 2b , 2c ? = because character always have two choice to combine with previous subsequence or not


        //<--------(one approach for above given explanation)----->
//        for (int i=1;i< dp.length;i++){
//            if(str.charAt(i-1) == 'a'){
//                dp[0][i] = 2 * dp[0][i-1] + 1;
//                dp[1][i] = dp[1][i-1];
//                dp[2][i] = dp[2][i-1];
//            }
//            else if(str.charAt(i-1) == 'b'){
//                dp[0][i] = dp[0][i-1];
//                dp[1][i] = 2 * dp[1][i-1] + dp[0][i-1];
//                dp[2][i] = dp[2][i-1];
//            }
//            else if(str.charAt(i-1) == 'c'){
//                dp[0][i] = dp[0][i-1];
//                dp[1][i] = dp[1][i-1];
//                dp[2][i] = 2 * dp[2][i-1] + dp[1][i-1];
//            }
//        }
//        for (int[] a:dp){
//            System.out.println(Arrays.toString(a));
//        }
//        return dp[2][str.length()];

        int a= 0,ab= 0,abc= 0;
        for (int i=0;i<str.length();i++){
            if(str.charAt(i) == 'a'){
                a = 2*a + 1;
            }
            else if(str.charAt(i) == 'b'){
                ab = 2 * ab + a;
            }
            else { //char is 'c
                abc = 2 * abc + ab;
            }
        }
        return abc;

    }

    //https://nados.io/question/maximum-sum-non-adjacent-elements
    public static int MaximumSumNonAdjacentElements(int[] elem){
        int inclusive = 0; //max sum till i inclusive of the current element (not adjacent elements)
        int exclusive = 0; //max sum till i exclusive of the current element (not adjacent elements)
        //how to find inclusive ? = inclusive[i] = exclusive[i-1] + elem(i)
        //how to find exclusive ? = exclusive[i] = max(inclusive[i-1] , exclusive[i-1])

        for (int i=0;i< elem.length;i++){
            int prevInclusive = inclusive;
            inclusive = exclusive + elem[i];
            exclusive = Math.max(prevInclusive , exclusive);
        }
        return Math.max(inclusive,exclusive);
    }

    //https://nados.io/question/paint-house
    public static int PaintHouse(int[][] houses){
        int red = 0;
        int green = 0;
        int blue = 0;
        for (int i=0;i< houses.length;i++){
            System.out.println(red + " " + green + " " + blue);
            int prevRed = red , prevGreen = green , prevBlue = blue;
            red = Math.min(prevGreen + houses[i][0] , prevBlue + houses[i][0]);
            green = Math.min(prevRed + houses[i][1] , prevBlue + houses[i][1]);
            blue = Math.min(prevRed + houses[i][2] , prevGreen + houses[i][2]);
        }
        return Math.min(red , Math.min(green , blue));
    }

    public static int PaintHouseManyColors(int[][] houses){
        int[][] dp = new int[houses[0].length][houses.length];
        for (int j=0;j<houses[0].length;j++){
            dp[j][0] = houses[0][j];
        }
        for (int j=1;j<dp[0].length;j++){
            for (int i=0;i<dp.length;i++){
                int min = Integer.MAX_VALUE;
                for (int k=0;k<houses[0].length;k++){
                    if(k == i){
                        continue;
                    }
                    min = Math.min(min , dp[k][j-1]);
                }
                dp[i][j] = houses[j][i] + min;
            }
        }
        for(int[] r: dp){
            System.out.println(Arrays.toString(r));
        }
        int ans = dp[0][houses.length-1];
        for (int i=0;i<houses[0].length;i++){
            ans = Math.min(ans , dp[i][houses.length-1]);
        }
        return ans;
    }


    //https://nados.io/question/paint-fence
    //https://leetcode.com/problems/paint-fence/
    public static int PaintFence(int n,int k){
        int same = k;
        int diff = k * (k-1);
        int total = same + diff;

        for (int i=3;i<=n;i++){
            same = diff;
            diff = total * (k-1);
            total = same + diff;
        }
        return total;
    }

    //https://nados.io/question/tiling-with-2-1-tiles
    public static int Tilingfloor(int floor,int[] qb){
        //memoization
        if(floor == 1){
            return 1;
        }
        else if(floor == 2){
            return 2;
        }
        else {
            if(qb[floor] != 0){
                return qb[floor];
            }
            int horizontalTiling = Tilingfloor(floor -1,qb);
            int vericalTiling = Tilingfloor(floor -2,qb);
            int totalWays = horizontalTiling + vericalTiling;
            qb[floor] = totalWays;
            return totalWays;
        }
    }

    public static int Tilingfloor2(int n,int m){
        //if length < breadth then there is only 1 way
        if(n < m){
            return 1;
        }
        //dp[i] = number of ways to tile a floor of length i
        int[] dp = new int[n+1];
        //floor of length has 0 ways
        dp[0] = 0;
        //till length < breath there is only 1 way to tile horizontal
        for (int i=1;i<m;i++){
            dp[i] = 1;
        }
        //when breadth is equal to length then there are two ways to tile vertically and horizontally
        dp[m] = 2;
        for (int i=m+1;i<=n;i++){
            dp[i] = dp[i-1] + (i-m < 0 ? 0 : dp[i - m]);
        }
        return dp[n];
    }

    //https://nados.io/question/friends-pairing
    public static int FriendsPairing(int n,int[] qb){
        //memoization
        //when we have 1 friend we can only make 1 combination
        if(n == 1){
            return 1;
        }
        //when we have two friends we can have 2 combinations ((12) friends -> 1-2(both solo) , 12(both pair))
        else if(n == 2){
            return 2;
        }
        else{
            if(qb[n] != 0){
                return qb[n];
            }
            int solo = FriendsPairing(n-1,qb);
            int pair = (n-1) * FriendsPairing(n-2,qb);
            int total = solo + pair;
            qb[n] = total;
            return total;
        }
    }

    //https://nados.io/question/partition-into-subsets\
    public static long partitionKSubset(int n, int k) {
        long[][] dp = new long[k+1][n+1];
        for (int i=1;i<k+1;i++){
            for (int j=1;j<n+1;j++){
                if(i > j){
                    dp[i][j] = 0;
                }
                else if(i == j){
                    dp[i][j] = 1;
                }
                else{
                    dp[i][j] = j * dp[i][j-1] + dp[i-1][j-1];
                }
            }
        }
        return dp[k][n];
    }


    //https://nados.io/question/buy-and-sell-stocks-one-transaction-allowed
    public static int Buy_And_Sell_Stocks_One_Transaction_Allowed(int[] price){
        int min = price[0];
        int max = 0;
        for (int i=0;i<price.length;i++){
            if(price[i] < min){
                min = price[i];
            }
            if(max < price[i] - min){
                max = price[i] - min;
            }
        }
        return max;
    }

    //https://nados.io/question/buy-and-sell-stocks-infinite-transactions-allowed
    public static int Buy_And_Sell_Stocks_Infinite_Transactions_Allowed(int[] price){
        int[] dp = new int[price.length +1];
        int buy = price[0];
        for (int i=1;i<dp.length;i++){
            if(price[i-1] < buy){
                buy = price[i-1];
            }
            int todaySellProfit = price[i-1] - buy;
            if(todaySellProfit > 0){
                dp[i] = todaySellProfit;
                buy = price[i-1];
            }
        }
        int maxProfit = 0;
        for(int i:dp){
            maxProfit += i;
        }
        return maxProfit;
    }

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    //same pattern Buy_And_Sell_Stocks_Infinite_Transactions_Allowed
    public int maxProfit(int[] prices) {
        int profit =0;
        for(int i=1;i<prices.length;i++){
            if(prices[i] > prices[i-1]){
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }


    //https://nados.io/question/buy-and-sell-stocks-with-transaction-fee-infinite-transactions-allowed
    public static int Buy_And_Sell_Stocks_With_Transaction_Fee_Infinite_Transactions_Allowed(int[] prices,int transactionFee){            //very imp (SEEN)
        int broughtStateProfit = -prices[0];
        int soldStateProfit = 0;
        for (int i=1;i<prices.length;i++){
            int oldBroughtState = broughtStateProfit;
            broughtStateProfit = Math.max(broughtStateProfit , soldStateProfit - prices[i]);
            soldStateProfit = Math.max(soldStateProfit , prices[i] - transactionFee + oldBroughtState);
        }
        return soldStateProfit;
    }

    //https://nados.io/question/buy-and-sell-stocks-with-cooldown-infinite-transaction-allowed
    public static int Buy_And_Sell_Stocks_With_Cooldown_Infinite_Transaction_Allowed(int[] prices){
        int broughtStateProfit = -prices[0];
        int soldStateProfit = 0;
        int prevSoldStateProfit = 0;
        for (int i=1;i<prices.length;i++){
            int newBroughtStateProfit = 0;
            int newSoldStateProfit = 0;
            if(prevSoldStateProfit - prices[i] > broughtStateProfit){
                newBroughtStateProfit = prevSoldStateProfit - prices[i];
            }
            else {
                newBroughtStateProfit = broughtStateProfit;
            }
            if(prices[i] + broughtStateProfit > soldStateProfit){
                newSoldStateProfit = prices[i] + broughtStateProfit;
            }
            else {
                newSoldStateProfit = soldStateProfit;
            }
            prevSoldStateProfit = soldStateProfit;
            broughtStateProfit = newBroughtStateProfit;
            soldStateProfit = newSoldStateProfit;


        }
        return soldStateProfit;
    }

    //https://nados.io/question/buy-and-sell-stocks-k-transactions-allowed
    public static int Buy_And_Sell_Stocks_K_Transactions_Allowed(int[] price,int k){
        int[][] dp = new int[k+1][price.length];   //row = number of transaction , column = price of stock on day i
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                int max = dp[i][j-1];
                for (int x=0;x<j;x++){
                    max = Math.max(max , dp[i-1][x] + (price[j] - price[x]));
                }
                dp[i][j] = max;
            }
        }
        return dp[k][price.length-1];
        //O(n^3)
    }

    //optimized solution of above question
    //https://nados.io/question/buy-and-sell-stocks-k-transactions-allowed
    public static int Buy_And_Sell_Stocks_K_Transactions_Allowed_optimized_sol(int[] price,int k){
        int[][] dp = new int[k+1][price.length];   //row = number of transaction , column = price of stock on day i
        for (int i=1;i<dp.length;i++){
            int max = Integer.MIN_VALUE;
            for (int j=1;j<dp[0].length;j++){
                max = Math.max(max , dp[i-1][j-1] - price[j-1]);
                dp[i][j] = Math.max(dp[i][j-1] , max + price[j]);
            }
        }
        return dp[k][price.length-1];
        //O(n^2)
    }

    //https://nados.io/question/buy-and-sell-stocks-two-transactions-allowed
    //this question can also be solved with above solution but with O(n^2) complexity, and we need optimized way
    public static int Buy_And_Sell_Stocks_Two_Transactions_Allowed(int[] price){
        int[] dpl = new int[price.length]; //dpl[i] stores the maximum profit from day 0 till day i where it is mandetory to sell on day i
        int minLeft = price[0]; //it stores the minimum (buy) when filling dpl
        for (int i=1;i<price.length;i++){
            //dpl[i-1] = max profit till day i-1
            //price[i] - minLeft = profit if we sell on day i
            if(price[i] < minLeft){
                minLeft = price[i];
            }
            dpl[i] = Math.max(dpl[i-1] , price[i] - minLeft);
        }
        int[] dpr = new int[price.length]; //dpr[i] stores the max profit from day i to price.length (last day) if it is mandetory to but on day i
        int maxRight = price[price.length-1]; //it stores the maximum from right (selling value) when filling dpr
        for (int i = price.length -2;i>=0;i--){
            if(price[i] > maxRight){
                maxRight = price[i];
            }
            dpr[i] = Math.max(dpr[i+1] , maxRight - price[i]);
        }
        //now to find maximum profit with 2 transactions we have 2 arrays dpl and dpr
        //for day i we have max one transaction from left of i stored in dpl[i] and one max transaction from right of i stored in dpr[i]
        int max = 0;
        for (int i=0;i<price.length;i++){
            max = Math.max(max , dpl[i] + dpr[i]);
        }
        return max;
        //time complexity O(n)
    }































}
