package com.gwh.lib.depthFirst;

/**
 * Created by Guo Wenhui
 * 2020/9/3
 * 深度优先算法，算路规划，
 * 规则：0表示路可走，3表示不通的墙，走过的路标记为2，遇到死胡同（查找上下左右是否有是0的）即周围都是3
 * 将该位置填为1，并且回退，继续查找四周可走的点，知道找到点或者根本找不到点
 *
 * printSingleColor可打印带颜色的输出
 **/
public class DepthFirst {
    public static void main(String[] args) {
        dfs(startX,startY);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j]==2) {
                    System.out.print("\033[30;4m" + map[i][j] + "\033[0m" + " ");
                }else if(map[i][j]==3){
                    System.out.print("\033[46;37;4m" +map[i][j] + "\033[0m"+" ");
                }else if(map[i][j]==1){
                    System.out.print("\033[47;4m" +map[i][j]  + "\033[0m"+" ");
                }else {
                    System.out.print("\033[42;33;4m" + map[i][j] + "\033[0m"+" ");
                }
            }
            System.out.println();
        }
    }
    public static void printSingleColor(String pattern,int code,int n,String content) {
        System.out.format("%s\33[%d;%dm%s%n", pattern, code, n, content);
    }
        public static int[][] map=new int[][]{
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
            {3,0,0,0,0,0,0,0,3,3,0,0,0,3,3,0,3,3},
            {3,3,3,0,3,3,3,0,3,3,0,3,0,0,0,0,3,3},
            {3,3,3,0,3,3,3,0,0,0,0,3,0,3,3,3,3,3},
            {3,0,0,0,0,0,3,3,3,3,3,0,0,0,0,3,3,3},
            {3,0,3,3,3,0,0,0,0,0,3,0,3,3,0,0,0,3},
            {3,0,0,3,3,3,3,3,3,0,0,0,3,3,3,3,0,3},
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
    };
    public static int startX=1;
    public static int startY=1;
    public static int endX=6;
    public static int endY=9;
    /**
     * 0 标识能走 3表示不能走 走过的地方标记为2 走到死胡同后把退回的地方标记为1
     * @param x
     * @param y
     * @return 有没有路可走
     */
    public static boolean dfs(int x,int y){
        //走到了末尾
        if(map[endX][endY]==2){
            return true;
        }
       if(map[x][y]==0){
           //该条路可走，标记为2
           map[x][y]=2;
           //查询上下左右是否有路可走
           if(dfs(x,y-1)){
               return true;
           }else if(dfs(x,y+1)){
               return true;
           }else if(dfs(x-1,y)){
               return true;
           }else if(dfs(x+1,y)){
               return true;
           }else {
               //没有路可走，死胡同
               map[x][y]=1;
               return false;
           }
       }else {
           return false;
       }
    }
}
