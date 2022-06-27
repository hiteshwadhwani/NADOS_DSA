package com.company.stacksNQueues;

import java.io.*;

import java.util.*;

public class Questions {
    //https://nados.io/question/duplicate-brackets
    boolean duplicateBracket(String str){
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch == ')'){
                if(stack.peek() == '('){
                    return true;
                }
                else{
                    while (stack.peek() != '(')
                        stack.pop();
                }
                stack.pop();

            }
            else{
                stack.push(ch);
            }
        }
        return false;
    }
    //https://leetcode.com/problems/valid-parentheses/
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '(')
                stack.push(')');
            else if(ch == '{')
                stack.push('}');
            else if(ch == '[')
                stack.push(']');
            else if(stack.pop() != ch || stack.isEmpty())
                return false;
        }
        return stack.isEmpty();
    }

    //https://nados.io/question/balanced-brackets
    boolean balancedBracket(String s){
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            }
            else if(ch == ')'){
                if(checkValidBracket(stack,'('))
                    return false;
                else stack.pop();
            }
            else if(ch == '}'){
                if(checkValidBracket(stack,'{'))
                    return false;
                else stack.pop();
            }
            else if(ch == ']'){
                if(checkValidBracket(stack,'['))
                    return false;
                else stack.pop();
            }
        }
        return stack.isEmpty();

    }
    static boolean checkValidBracket(Stack<Character> stack,char ch){
        if(stack.isEmpty() || stack.peek() != ch)
            return true;
        return false;
    }


    //https://nados.io/question/next-greater-element-to-the-right
    public static int[] solve(int[] arr){
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        ans[arr.length-1] = -1;
        stack.push(arr[arr.length-1]);
        for (int i=arr.length-2;i>=0;i--){
            while (stack.size() > 0 && arr[i] >= stack.peek()){
                stack.pop();
            }
            if(stack.isEmpty()){
                ans[i] = -1;
            }
            else{
                ans[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return ans;
    }

    //https://nados.io/question/stock-span
    public static int[] StockSpan(int[] a){
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[a.length];
        ans[0] = 1;
        stack.push(0);
        for (int i=1;i<a.length;i++){
            while (stack.size() > 0 && a[i] >= a[stack.peek()]){
                stack.pop();
            }
            if(stack.isEmpty()){
                ans[i] = i +1;
            }
            else{
                ans[i] = i - stack.peek();
            }
            stack.push(i);
        }
        return ans;
    }

    public static void largestHistogram(int[] a){
        int[] rb = new int[a.length]; // nse on the right
        Stack<Integer> st = new Stack<>();

        st.push(a.length - 1);
        rb[a.length - 1] = a.length;
        for (int i = a.length - 2; i >= 0; i--) {
            while (st.size() > 0 && a[i] <= a[st.peek()]) {
                st.pop();
            }

            if (st.size() == 0) {
                rb[i] = a.length;
            } else {
                rb[i] = st.peek();
            }

            st.push(i);
        }

        int[] lb = new int[a.length]; // nse on the left
        st = new Stack<>();

        st.push(0);
        lb[0] = -1;
        for (int i = 1; i < a.length; i++) {
            while (st.size() > 0 && a[i] <= a[st.peek()]) {
                st.pop();
            }

            if (st.size() == 0) {
                lb[i] = -1;
            } else {
                lb[i] = st.peek();
            }

            st.push(i);
        }

        int max = 0;
        for (int i = 0; i < a.length; i++) {
            int width = rb[i] - lb[i] - 1;
            int area = width * a[i];
            if (area > max) {
                max = area;
            }
        }

        System.out.println(max);


    }

    //https://nados.io/question/sliding-window-maximum
    public static void slidingWindow(int[] a,int k){
        Stack<Integer> st = new Stack<>(); //storing indices of next greater element
        int[] nge = new int[a.length]; // NEXT GREATER ELEMENT
        st.push(a.length-1);
        nge[a.length -1] = a.length;
        for (int i= a.length -2;i>=0;i--){
            while (st.size() > 0 && a[i] >= a[st.peek()]){
                st.pop();
            }
            if(st.isEmpty()){
                nge[i] = a.length;
            }
            else{
                nge[i] = st.peek();
            }
            st.push(i);

        }
        int j=0;
        for (int i=0;i<a.length - k;i++){
            if(j < i){
                j =i;
            }
            while (nge[j] < i +k){
                j = nge[j];
            }
            System.out.println(a[j]);
        }

    }

    //https://nados.io/question/infix-evaluation
    static public int checkPrecedence(char ch){
        if(ch == '+'){
            return 1;
        }
        else if(ch == '-'){
            return 1;
        }
        else if(ch == '*'){
            return 2;

        }
        else {
            return 2;
        }
    }
    static public int performOperation(int val1,int val2,char ch){
        if(ch == '+'){
            return val1 + val2;
        }
        else if(ch == '-'){
            return val1 - val2;
        }
        else if(ch == '*'){
            return val1 * val2;

        }
        else{
            return val1 / val2;
        }
    }
    static public int infixEvaluation(String exp){
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (int i=0;i<exp.length();i++){
            char ch = exp.charAt(i);
            if(ch == '('){
                operators.push(ch);
            }
            else if(Character.isDigit(ch)){
                operands.push(ch - '0');
            }
            else if(ch == '+' || ch == '-' || ch =='*' || ch == '/'){
                while (operators.size() > 0 && operators.peek() != '(' && checkPrecedence(ch) <= checkPrecedence(operators.peek())){
                    int val2 = operands.pop();
                    int val1 = operands.pop();
                    char op = operators.pop();
                    int ans = performOperation(val1,val2,op);
                    operands.push(ans);
                }
                operators.push(ch);
            }
            else if(ch == ')'){
                while (operators.size() > 0 && operators.peek() != '('){
                    int val2 = operands.pop();
                    int val1 = operands.pop();
                    char op = operators.pop();
                    int ans = performOperation(val1,val2,op);
                    operands.push(ans);
                }
                operators.pop();
            }
        }
        while (operators.size() != 0){
            int val2 = operands.pop();
            int val1= operands.pop();
            char op = operators.pop();
            int ans = performOperation(val1,val2,op);
            operands.push(ans);
        }
        return operands.pop();
    }
    
    //https://nados.io/question/infix-conversions
    public static void convertToInfixAndPostfix(String exp){
        //infix = val1 + operator + val2
        //prefix = operator + val1 + val2
        //postfix = val1 + val2 + operator
        Stack<String> pre= new Stack<>(); //Stack for prefix conversion
        Stack<Character> op = new Stack<>(); //Stack for operator
        Stack<String> post = new Stack<>(); // Stack for postFix
        for(int i =0;i<exp.length();i++){
            char ch = exp.charAt(i);
            if(ch == '('){
                op.push(ch);
            }
            else if(Character.isAlphabetic(ch)){
                pre.push(Character.toString(ch));
                post.push(Character.toString(ch));
            }
            else if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                while (op.size() > 0 && op.peek() != '(' && checkPrecedence(ch) <= checkPrecedence(op.peek())){
                    String val2 = pre.pop();
                    String val1 = pre.pop();
                    char operator = op.pop();
                    String ans1 = operator+val1+val2;
                    pre.push(ans1);
                    String val4 = post.pop();
                    String val3 = post.pop();
                    String ans2 = val3+val4+operator;
                    post.push(ans2);
                }
                op.push(ch);
            }
            else if(ch == ')'){
                while (op.size() > 0 && op.peek() != '('){
                    String val2 = pre.pop();
                    String val1 = pre.pop();
                    char operator = op.pop();
                    String ans1 = operator+val1+val2;
                    pre.push(ans1);
                    String val4 = post.pop();
                    String val3 = post.pop();
                    String ans2 = val3+val4+operator;
                    post.push(ans2);
                }
                op.pop();
            }

        }
        while (op.size() > 0){
            String val2 = pre.pop();
            String val1 = pre.pop();
            char operator = op.pop();
            String ans1 = operator+val1+val2;
            pre.push(ans1);
            String val4 = post.pop();
            String val3 = post.pop();
            String ans2 = val3+val4+operator;
            post.push(ans2);
        }
        //prefix
        System.out.println(pre.peek());
        //postfix
        System.out.println(post.peek());

    }

    //https://nados.io/question/postfix-evaluation-and-conversions
    public static void postFixConversions(String exp){
        Stack<String> infix = new Stack<>();
        Stack<Integer> value = new Stack<>();
        Stack<String> prefix = new Stack<>();
        for (int i =0;i<exp.length();i++){
            char ch = exp.charAt(i);
            if(Character.isDigit(ch)){
                infix.push(Character.toString(ch));
                prefix.push(Character.toString(ch));
                value.push(ch - '0');
            }
            else if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                //update infix
                String val2 = infix.pop();
                String val1 = infix.pop();
                infix.push("(" + val1 + ch + val2 + ")");

                //update prefix
                String val4 = prefix.pop();
                String val3 = prefix.pop();
                prefix.push(ch + val3 + val4);

                //update ans
                int val6 = value.pop();
                int val5 = value.pop();
                int ans = performOperation(val5,val6,ch);
                value.push(ans);
            }
        }
        System.out.println(value.peek());
        System.out.println(infix.peek());
        System.out.println(prefix.peek());
    }

    //https://nados.io/question/prefix-evaluation-and-conversions
    public static void infixConversion(String exp){
        Stack<String> infix = new Stack<>();
        Stack<Integer> value = new Stack<>();
        Stack<String> postfix = new Stack<>();
        for (int i =exp.length()-1;i>=0;i--){
            char ch = exp.charAt(i);
            if(Character.isDigit(ch)){
                infix.push(Character.toString(ch));
                postfix.push(Character.toString(ch));
                value.push(ch - '0');
            }
            else if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                //update infix
                String val1 = infix.pop();
                String val2 = infix.pop();
                infix.push("(" + val1 + ch + val2 + ")");

                //update prefix
                String val3 = postfix.pop();
                String val4 = postfix.pop();
                postfix.push(val3 + val4 + ch);

                //update ans
                int val5 = value.pop();
                int val6 = value.pop();
                int ans = performOperation(val5,val6,ch);
                value.push(ans);
            }
        }
        System.out.println(value.peek());
        System.out.println(infix.peek());
        System.out.println(postfix.peek());
    }
    public static void celebrityProblem(int[][] arr){
        Stack<Integer> st = new Stack<>(); //storing index
        for (int i=0;i<arr.length;i++){
            st.push(i);
        }
        while (st.size()>1){
            int i= st.pop();
            int j=st.pop();
            if(arr[i][j] == 1){
                st.push(j);
            }
            else {
                st.push(i);
            }
        }
        int ans = st.peek();
        boolean flag = true;
        for (int i=0;i<arr.length;i++){
            if(i != ans){
                if(arr[i][ans] == 0 || arr[ans][i] == 1){
                    flag = false;
                    break;
                }
            }

        }
        if(flag){
            System.out.println(ans);
        }
        else {
            System.out.println("none");
        }
    }

    //https://nados.io/question/merge-overlapping-interval
    public static void overlapInterval(int[][] arr){
      Pair[] pairs = new Pair[arr.length];
      Stack<Pair> st = new Stack<>();
      for (int i=0;i<arr.length;i++){
           pairs[i] = new Pair(arr[i][0],arr[i][1]);
       }
      Arrays.sort(pairs);
       for (int i=0;i<arr.length;i++){
          if(i==0){
                st.push(pairs[0]);
            }
            else {
              Pair top = st.peek();
              if(pairs[i].st > top.et){
                st.push(pairs[i]);
               }
             else{
                    top.et = Math.max(top.et, pairs[i].et);
                }
           }
        }
        Stack<Pair> rs = new Stack<>();
       while (st.size()>0){
            rs.push(st.pop());
       }
       while (rs.size()>0){
           Pair top = rs.pop();
           System.out.println(top.st + " " + top.et);
       }
    }


    public static class Pair implements Comparable< Pair> {
        int st;
        int et;

        Pair(int st, int et) {
            this.st = st;
            this.et = et;
        }

        public int compareTo(Pair other) {
            if (this.st != other.st) {
                return this.st - other.st;
            } else {
                return this.et - other.et;
            }
        }
}

    //https://nados.io/question/smallest-number-following-pattern
    public static void SmallestNumberFollowingPattern(String exp){
        Stack<Integer> st = new Stack<>();
        int num =1;
        for (int i=0;i<exp.length();i++){
            char ch = exp.charAt(i);
            if(ch == 'd'){
                st.push(num++);
            }
            else{ //when we get character i means increasing
                st.push(num++);
                while (st.size() >0){
                    System.out.print(st.pop());
                }
            }
        }
        st.push(num);
        while (st.size()>0){
            System.out.print(st.pop());
        }
    }
    public static void main(String[] args) {
        String exp = "2+6*4/8-3";
        System.out.println(infixEvaluation(exp));
    }


}
