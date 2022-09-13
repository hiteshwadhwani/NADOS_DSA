package com.intermediate.RecustionAndBacktracking;

import java.util.ArrayList;

public class Question {
    public static void Abbreviation_Using_Backtracking(String str, String asf,int count, int pos){
        if(pos == str.length()){
            if(count > 0){
                System.out.println(asf + count);
            }
            else {
                System.out.println(asf);
            }
            return;
        }
        Abbreviation_Using_Backtracking(str,asf + (count > 0 ? count : "") + str.charAt(pos),count+1,pos+1);
        Abbreviation_Using_Backtracking(str,asf,count+1,pos+1);
    }
    public static void Lexicographical_Numbers(int num,int ans){
        if(ans > num){
            return;
        }
        System.out.println(ans);
        int i;
        if(ans == 0){
            i = 1;
        }
        else{
            i = 0;
        }
        while(i < 10){
            Lexicographical_Numbers(num,ans * 10 + i);
            i++;
        }
    }


    //https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/gold-mine-2-official/ojquestion
    static int max = 0;
    public static void getMaxGold(int[][] arr){
        //pattern - connected components
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr[0].length;j++){
                if(!visited[i][j] && arr[i][j] != 0){
                    int goldWeGet = getMaxGoldRec(arr,i,j,visited);
                    if(goldWeGet > max){
                        max = goldWeGet;
                    }
                }
            }
        }
    }
    public static int getMaxGoldRec(int[][] arr,int i,int j,boolean[][] visited){
        if(i<0 || j < 0 || i == arr.length || j == arr[0].length || visited[i][j] || arr[i][j] == 0){
            return 0;
        }
        visited[i][j] = true;
        int left = getMaxGoldRec(arr,i,j-1,visited);
        int right = getMaxGoldRec(arr,i,j+1,visited);
        int up = getMaxGoldRec(arr,i-1,j,visited);
        int down = getMaxGoldRec(arr,i+1,j,visited);

        return arr[i][j] + left + right + up + down;
    }

    //https://leetcode.com/problems/sudoku-solver/
    public void solveSudoku(char[][] board) {
        //backtracking
        solveSudokuRec(board,0,0);
    }
    public static boolean solveSudokuRec(char[][] board,int row,int col){
        if(row == 9){
            return true;
        }

        int nrow = 0;
        int ncol = 0;
        if(col == 9){
            nrow = row+1;
            ncol = 0;
        }
        else {
            nrow = row;
            ncol = col+1;
        }
        if(board[row][col] != '.'){
            solveSudokuRec(board,nrow,ncol);
        }
        for(int i=1;i<10;i++){
            //we have choice from 1 - 9
            if(checkValid(board,row,col,i)){
                board[row][col] = (char)(i + 48);
                if(solveSudokuRec(board,row,col+1)){
                    return true;
                }
                board[row][col] = '.';
            }
        }
        return false;

    }
    public static boolean checkValid(char[][] board,int row,int col,int num){
        char ch = (char)(num + 48);

        //check in col
        for(int i=0;i<9;i++){
            if(board[i][col] == ch){
                return false;
            }
        }

        //check in row
        for(int j=0;j<9;j++){
            if(board[row][j] == ch){
                return false;
            }
        }

        int minRow = row / 3 * 3;
        int micCOl = col / 3 * 3;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[minRow+i][micCOl + j] == ch){
                    return false;
                }
            }
        }

        return true;


    }
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {

    }

}
