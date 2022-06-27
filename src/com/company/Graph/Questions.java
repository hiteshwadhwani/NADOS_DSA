package com.company.Graph;

import java.util.*;

public class Questions {
    static class Edge{
        int src;    // vertex
        int nbr;    // neighbour vertex
        int wt;     //weight of edge
        Edge(int src, int nbr, int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    //https://nados.io/question/has-path
    //pattern  - DFS
    public static boolean hasPath(ArrayList<Edge>[] graph,int src,int dest,boolean[] visited){
        if(src == dest){
            return true;
        }
        visited[src] = true;
        for (Edge i:graph[src]){
            if(visited[i.nbr] == false){
                boolean nrbHasPath = hasPath(graph , i.nbr , dest , visited);
                if(nrbHasPath) return true;
            }

        }
        return false;
    }

    //https://nados.io/question/print-all-paths
    public static void printAllPaths(ArrayList<Edge>[] graph,int src,int dest,boolean[] visited,String path){
        if(src == dest){
            System.out.print(path);
            return;
        }
        visited[src] = true;
        for (Edge i:graph[src]){
            if(!visited[i.nbr]){
                printAllPaths(graph,i.nbr,dest,visited,path + i.nbr);
            }
        }
        visited[src]=false;
    }

    //https://nados.io/question/multisolver-smallest-longest-ceil-floor-kthlargest-path
    static String spath;
    static Integer spathwt = Integer.MAX_VALUE;
    static String lpath;
    static Integer lpathwt = Integer.MIN_VALUE;
    static String cpath;
    static Integer cpathwt = Integer.MAX_VALUE;
    static String fpath;
    static Integer fpathwt = Integer.MIN_VALUE;
    static class Pair implements Comparable<Pair> {
        int wsf;
        String psf;

        Pair(int wsf, String psf){
            this.wsf = wsf;
            this.psf = psf;
        }

        public int compareTo(Pair o){
            return this.wsf - o.wsf;
        }
    }
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    public static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, int criteria, int k, String psf, int wsf) {
        if(src == dest){
            if(wsf < spathwt){
                spathwt = wsf;
                spath = psf;
            }
            if(wsf > lpathwt){
                lpathwt = wsf;
                lpath = psf;
            }
            if(wsf > criteria && wsf < cpathwt){
                cpathwt = wsf;
                cpath = psf;
            }
            if(wsf < criteria && wsf > fpathwt){
                fpathwt = wsf;
                fpath = psf;
            }
            //case for kth largest element
            //we will make a bucket of values till k
            //bucket will contain the max values from all the values
            //peek of bucket(priority queue) will give the smallest element from that bucket
            if(pq.size() < k){
                pq.add(new Pair(wsf,psf));
            }
            else {
                //swap element whose weight is greater than the peek element weight of the bucket
                if(wsf > pq.peek().wsf){
                    pq.remove();
                    pq.add(new Pair(wsf,psf));
                }
            }
            return;
        }
        visited[src] = true;
        for (Edge e:graph[src]){
            if(!visited[e.nbr]){
                multisolver(graph,e.nbr,dest,visited,criteria,k,psf + e.nbr,wsf + e.wt);
            }
        }
        visited[src] = false;
    }

    //https://nados.io/question/get-connected-components-of-a-graph
    public static void getConnectedComponents(ArrayList<Edge>[] graph,int vtces,boolean[] visited){
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        for (int i=0;i<vtces;i++){
            if(!visited[i]){
                ArrayList<Integer> list = new ArrayList<>();
                dfs(graph , i , list , visited);
                comps.add(list);
            }
        }

    }
    public static void dfs(ArrayList<Edge>[] graph,int src,ArrayList<Integer> list,boolean[] visited){
        list.add(src);
        visited[src]=true;
        for (Edge e:graph[src]){
            if(!visited[e.nbr]){
                dfs(graph , e.nbr , list , visited);
            }
        }
    }

    //https://nados.io/question/is-graph-connected
    public static boolean isGraphConnected(ArrayList<Edge>[] graph,int vtces,boolean[] visited){
        ArrayList<Integer> ans = new ArrayList<>();
        dfs(graph ,0 , ans , visited);
        return ans.size() == vtces;
    }

    //https://nados.io/question/number-of-islands
    public static int numberOfIslands(int[][] arr){
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        int count =0;
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr[0].length;i++){
                if(arr[i][j] ==0 && !visited[i][j]){
                    visitVertex(arr,visited,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    //this function will traverse whole land and mark every vertex true
    public static void visitVertex(int[][] arr,boolean[][] visited,int i,int j){
        if(i<0 || j <0 || i>= arr.length || j>= arr[0].length || arr[i][j] == 1 || visited[i][j]){
            return;
        }
        visited[i][j] = true;
        visitVertex(arr , visited , i+1 ,j);
        visitVertex(arr , visited , i-1 ,j);
        visitVertex(arr , visited , i ,j+1);
        visitVertex(arr , visited , i ,j-1);
    }

    //https://nados.io/question/perfect-friends
    public static int perfectFriends(ArrayList<Edge>[] graph,int v){
        boolean[] visited = new boolean[v];
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        for (int i=0;i<v;i++){
            if(!visited[i]){
                ArrayList<Integer> list = new ArrayList<>();
                dfs(graph , i , list , visited);
                comps.add(list);
            }
        }
        int ans =0;
        for (int i=0;i<comps.size();i++){
            for (int j=i+1;j<comps.size();j++){
                ans+= comps.get(i).size()*comps.get(j).size();
            }
        }
        return ans;
    }
    //https://leetcode.com/problems/number-of-provinces/
    //done in leetcode only

    //https://nados.io/question/hamiltonian-path-and-cycle
    public static void hamiltonianPathAndCycle(ArrayList<Edge>[] graph,int src,boolean[] visited,int vtces,String path,ArrayList<Edge> osrc){
            if(path.length() == vtces-1){
                boolean lastEdge = false;
                for (Edge i:osrc){
                    if(i.nbr == src){
                        lastEdge= true;
                        break;
                    }
                }
                if(lastEdge) System.out.print(path+src+'*');
                else System.out.print(path+src+'.');
            }

            visited[src] = true;
            for (Edge i:graph[src]){
                if(!visited[i.nbr]){
                    hamiltonianPathAndCycle(graph,i.nbr,visited,vtces,path+i.nbr,osrc);
                }
            }
            visited[src] = false;
    }
    //
    public static void printKnightsTour(int[][] chess, int r, int c, int upcomingMove) {
        if(r<0 || c<0 || r==chess.length || c==chess[0].length){
            return;
        }
        if(upcomingMove == chess.length* chess.length +1){
            displayBoard(chess);
        }
        chess[r][c] = upcomingMove;
        printKnightsTour(chess,r-2,c+1,upcomingMove+1);
        printKnightsTour(chess,r-1,c+2,upcomingMove+1);
        printKnightsTour(chess,r+1,c+2,upcomingMove+1);
        printKnightsTour(chess,r+2,c+1,upcomingMove+1);
        printKnightsTour(chess,r+2,c-1,upcomingMove+1);
        printKnightsTour(chess,r+1,c-2,upcomingMove+1);
        printKnightsTour(chess,r-1,c-2,upcomingMove+1);
        printKnightsTour(chess,r-2,c-1,upcomingMove+1);
        chess[r][c]=0;
    }
    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    //bfs
    static class pair{
        int node;
        String path;
        pair(int e, String p){
            this.node = e;
            this.path = p;
        }
    }
    public static void BFS(ArrayList<Edge>[] graph,int src,boolean[] visited){
        ArrayDeque<pair> q = new ArrayDeque<>();
        q.add(new pair(src , src+""));
        while (!q.isEmpty()){
            //remove
            pair top = q.pop();

            //check star
            if(visited[top.node]){
                continue;
            }
            //marks star
            visited[top.node] = true;


            //work
            System.out.println(top.node + "@" + top.path);

            //add
            for (Edge i:graph[top.node]){
                if(!visited[i.nbr]){
                    q.add(new pair(i.nbr , top.path + i.nbr));
                }
            }

        }


    }

//    public static boolean isGraphCyclic(ArrayList<Edge>[] graph,boolean[] visited,int src,int count,int sv,int parent){
//        if(count == graph.length -1){
//            boolean ans = false;
//            for (Edge e:graph[src]){
//                if(e.nbr == sv){
//                    ans = true;
//                }
//            }
//            return ans;
//        }
//        if(visited[src]){
//            return true;
//        }
//        visited[src] = true;
//
//        boolean ans = false;
//
//        for (Edge e:graph[src]){
//            if(e.nbr == parent){
//                continue;
//            }
//            ans = ans || isGraphCyclic(graph,visited,e.nbr,count+1,sv,src);
//        }
//        return ans;
//    }

    public static boolean checkCycle(ArrayList<Edge>[] graph,int src,int vtces){
        boolean ans = false;
        for (int i=0;i<graph.length;i++){
            boolean[] visited = new boolean[vtces];
            ans = ans || isyccylec(graph,i,visited);
        }
        return ans;
    }

    public static boolean isyccylec(ArrayList<Edge>[] graph,int src,boolean[] visited){
        ArrayDeque<pair> q = new ArrayDeque<>();
        q.add(new pair(src , src+""));
        while (!q.isEmpty()){
            //remove
            pair top = q.pop();

            //check star
            if(visited[top.node]){
                return true;
            }
            //marks star
            visited[top.node] = true;


            //add
            for (Edge i:graph[top.node]){
                if(!visited[i.nbr]){
                    q.add(new pair(i.nbr , top.path + i.nbr));
                }
            }

        }
        return false;
    }


    //https://nados.io/question/is-graph-bipartite
    public static boolean checkGraphBipartite(ArrayList<Edge>[] graph , int vtces){
        int[] visited = new int[vtces];   //this array will store the level of vertices visited
        Arrays.fill(visited , -1);    //by default if visited[vertex]==-1 then it is not visited
        for (int i=0;i<vtces;i++){
            if(visited[i] == -1){
                boolean check = checkForBipartiteness(graph,i,visited);
                if(!check){
                    return false;
                }
            }
        }
        return true;
    }

    static class pair1{
        int node;
        String path;
        int level;
        pair1(int e, String p,int level){
            this.node = e;
            this.path = p;
            this.level = level;
        }
    }

    public static boolean checkForBipartiteness(ArrayList<Edge>[] graph,int src,int[] visited){
        ArrayDeque<pair1> q = new ArrayDeque<>();
        q.add(new pair1(src , src + "" , 0));

        while (!q.isEmpty()){     //remove  , check star , work  , add childern
            pair1 top = q.pop();
            if(visited[top.node] != -1){
                if(visited[top.node] != top.level){
                    return false;
                }
            }
            else {
                visited[top.node] = top.level;
            }

            for (Edge i:graph[src]){
                if(visited[i.nbr] == -1){
                    q.add(new pair1(i.nbr , top.path + i.nbr , top.level + 1));
                }
            }
        }
        return true;
    }


    //https://nados.io/question/spread-of-infection
    public static int SpreadOfInfection(ArrayList<Edge>[] graph,int vtces,int src,int t){
        //BFS question
        boolean[] visited = new boolean[vtces];
        int count =0;
        ArrayDeque<pair1> q = new ArrayDeque<>();
        q.add(new pair1(src , src + "" , 1));
        while (!q.isEmpty()){
            pair1 top = q.pop();
            if(visited[top.node]){
                continue;
            }
            visited[top.node] = true;
            if(top.level > t){
                break;
            }
            count++;
            for (Edge e:graph[top.node]){
                if(!visited[e.nbr]){
                    q.add(new pair1(e.nbr , top.path + e.nbr,  top.level + 1));
                }
            }
        }
        return count;
    }


    //https://nados.io/question/shortest-path-in-weights
    //dijkstra algorithm

    static class pair2 implements Comparable<pair2>{
        int node;
        String path;
        int wt;
        pair2(int node,String path,int wt){
            this.node = node;
            this.path = path;
            this.wt = wt;
        }

        @Override
        public int compareTo(pair2 o) {
            return this.wt - o.wt;
        }
    }

    public static void ShortestPathInWeights(ArrayList<Edge>[] graph,int src,int vtces){
        //same as bfs only change is we will use priorityQueue inplace of queue
        PriorityQueue<pair2> q = new PriorityQueue<>();
        q.add(new pair2(src , src + "" , 0));
        boolean[] visited = new boolean[vtces];
        while (!q.isEmpty()){

            //remove
            pair2 top = q.remove();

            //mark star
            if(visited[top.node]){
                continue;
            }
            visited[top.node] = true;

            //work
            System.out.println(top.node + " via " + top.path + " @ " + top.wt);

            //add children
            for (Edge e:graph[top.node]){
                if(!visited[e.nbr]){
                    q.add(new pair2(e.nbr , top.path + e.nbr , top.wt + e.wt));
                }
            }
        }
    }


    //https://nados.io/question/minimum-wire-required-to-connect-all-pcs

    static class Pair3 implements Comparable<Pair3>{
        int node;
        int AE; //acquiring edge
        int wt; //weight from AE
        Pair3(int node,int AE,int wt){
            this.node = node;
            this.AE = AE;
            this.wt = wt;
        }

        @Override
        public int compareTo(Pair3 o) {
            return this.wt - o.wt;
        }
    }


    public  static void MinimumWireRequiredToConnectAllPcs(ArrayList<Edge>[] graph,int vtces){
        //prims algorithm
        //data structure - priorityQueue

        boolean[] visited = new boolean[vtces];
        PriorityQueue<Pair3> pq = new PriorityQueue<>();
        pq.add(new Pair3(0 , -1 , 0));

        while (!pq.isEmpty()){
            Pair3 top = pq.remove();

            if(visited[top.node]){
                continue;
            }
            visited[top.node] = true;
            if(top.AE != -1){
                System.out.println("["+top.node + "-" + top.AE + "@" + top.wt+"]");
            }
            for (Edge e:graph[top.node]){
                if(!visited[e.nbr]){
                    pq.add(new Pair3(e.nbr , top.node , e.wt));
                }
            }
        }
    }

    //https://nados.io/question/order-of-compilation
    public static void OrderOfCompilation(ArrayList<Edge>[] graph,int vtces){
        //topological sort
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[vtces];
        for (int i=0;i<vtces;i++){
            if(!visited[i]){
                topologicalSort(graph , i , st , visited);
            }
        }
        while (!st.isEmpty()){
            System.out.println(st.pop());
        }

    }
    public static void topologicalSort(ArrayList<Edge>[] graph , int src,Stack<Integer> st,boolean[] visited){
        for (Edge e:graph[src]){
            if(!visited[e.nbr]){
                topologicalSort(graph , e.nbr , st , visited);
            }
        }
        if(!visited[src]){
            st.add(src);
        }
        visited[src] = true;
    }

    static class Pair4{
        int node;
        String path;
        Pair4(int node,String path){
            this.node = node;
            this.path = path;
        }
    }

    //https://nados.io/question/iterative-depth-first-traversal
    public static void IterativeDepthFirstTraversal(ArrayList<Edge>[] graph,int vtces,int src){
        Stack<Pair4> st = new Stack<>();
        boolean[] visited = new boolean[vtces];
        st.add(new Pair4(src , src + ""));
        while (!st.isEmpty()){
            Pair4 top = st.pop();
            if(visited[top.node]){
                continue;
            }
            visited[top.node] = true;
            System.out.print(top.node + "@" + top.path);
            for (Edge e:graph[top.node]){
                if(!visited[e.nbr]){
                    st.add(new Pair4(e.nbr , top.path + e.nbr));
                }
            }
        }
    }
























}
