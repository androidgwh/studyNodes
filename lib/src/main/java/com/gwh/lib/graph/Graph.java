package com.gwh.lib.graph;

import java.util.LinkedList;

/**
 * leetcode算法题 200 690 542 133
 */
public class Graph {
    private  int[] vertices;// 顶点
    private int[][] matrix; // 图的节点的边
    private int verticeSize; // 顶点的数量
    private static final int MAX_WEIGHT = 0xFFF8;
    private boolean[] isVisited;
    public Graph(int verticeSize) {
        this.verticeSize = verticeSize;
        vertices=new int[verticeSize];
        matrix = new int[verticeSize][verticeSize];

        isVisited = new boolean[verticeSize];
        for (int i = 0; i < verticeSize; i++) {
            isVisited[i] = false;

        }
    }

    /**
     * 获取v1到v2的路径长度
     * @param v1
     * @param v2
     * @return
     */
    public int getWidget(int v1,int v2){
        return matrix[v1][v2]==0?0: (matrix[v1][v2] == MAX_WEIGHT ? -1 : matrix[v1][v2]);
    }
    /**
     * 广度优先搜索
     */
    public void bfs(){
        for(int i = 0; i < verticeSize; i++) {
            isVisited[i] = false;
        }

        for(int i = 0; i < verticeSize; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                System.out.println("visited vertice: " + i);
                bfs(i);
            }
        }
        /**
         * 如果上面的i是从n入1开始，下面要在从0遍历到n(1)一次，保证每个节点被访问到
         */
//        for(int i = 0; i < 1; i++) {
//            if (!isVisited[i]) {
//                isVisited[i] = true;
//                System.out.println("visited vertice: " + i);
//                bfs(i);
//            }
//        }
    }

    private void bfs(int i) {
        LinkedList<Integer> queue = new LinkedList<>();
        // 访问第一个邻接节点
        int fn = getFirstNeighbor(i);
        if (fn == -1) {
            return;
        }

        if (!isVisited[fn]) {
            isVisited[fn] = true;
            System.out.println("visited vertice: " + fn);
            queue.offer(fn);
        }
        // 访问其他邻接节点
        int next = getNextNeighbor(i, fn);
        while(next != -1) {
            if (!isVisited[next]) {
                isVisited[next] = true;
                System.out.println("visited vertice: " + next);
                queue.offer(next);
            }
            next = getNextNeighbor(i, next);
        }

        while(!queue.isEmpty()) {
            int point = queue.poll();
            bfs(point);
        }
    }
    public void dfs(){
        for (int i=0;i<verticeSize;i++){
            if(!isVisited[i]){
                //判断节点是否被访问过
                System.out.println("visited vertice dfs "+i);
                dfs(i);
            }
        }
    }

    private void dfs(int i) {
        //以访问的节点为当前节点进行深度优先访问
        isVisited[i] = true;
        int v = getFirstNeighbor(i);
        while (v!=-1){
            if(!isVisited[v]){
                System.out.println("visited vertice dfs111 "+v);
                dfs(v);
            }
            v = getNextNeighbor(i, v);
        }
    }

    /**
     * 获取某个节点的第一个邻节点
     * @param i
     * @return
     */
    private int getFirstNeighbor(int i){
        for (int j = 0; j < verticeSize; j++) {
            if(matrix[i][j]>0&&matrix[i][j]!=MAX_WEIGHT){
                return j;
            }

        }
        return -1;
    }

    /**
     * 获取节点v的邻节点index的下一个邻节点
     * @param v
     * @param index
     * @return
     */
    private int getNextNeighbor(int v,int index){
        for (int i = index+1; i <verticeSize ; i++) {
            if(matrix[v][i]>0&&matrix[v][i]!=MAX_WEIGHT){
                return i;
            }

        }
        return -1;
    }


    public static void main(String[] args) {
        Graph graph = new Graph(5);
        int[] v0 = new int[] {0, 1, 1, MAX_WEIGHT, MAX_WEIGHT};
        int[] v1 = new int[] {MAX_WEIGHT, 0, MAX_WEIGHT, 1, MAX_WEIGHT};
        int[] v2 = new int[] {MAX_WEIGHT, MAX_WEIGHT, 0, MAX_WEIGHT, MAX_WEIGHT};
        int[] v3 = new int[] {1, MAX_WEIGHT, MAX_WEIGHT, 0, MAX_WEIGHT};
        int[] v4 = new int[] {MAX_WEIGHT, MAX_WEIGHT, 1, MAX_WEIGHT, 0};
        graph.matrix[0] = v0;
        graph.matrix[1] = v1;
        graph.matrix[2] = v2;
        graph.matrix[3] = v3;
        graph.matrix[4] = v4;
        graph.bfs();
    }
}
