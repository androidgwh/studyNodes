package com.gwh.lib.astar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guo Wenhui
 * 2020/9/4
 * A*算法
 **/
public class Astar {
    public static int[][] map=new int[][]{
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
            {3,3,0,0,0,0,0,0,0,1,0,0,0,0,0,0,3,3},
            {3,3,0,0,3,3,3,0,0,0,0,3,3,3,0,0,3,3},
            {3,0,0,0,0,3,3,3,3,3,3,3,3,0,0,3,3,3},
            {3,0,3,3,0,0,0,3,3,3,3,0,0,0,0,0,0,3},
            {3,0,0,3,0,3,0,0,2,0,0,0,3,3,3,3,0,3},
            {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
    };
    /**
     * 用来存能走的路
     */
    public static List<P> openArray = new ArrayList<>();
    /**
     * 用来存不能走的路
     */
    public static List<P> closeArray = new ArrayList<>();
    /**
     * 开始点
     */
    public static P startPoint;
    /**
     * 结束点
     */
    public static P endPoint;

    public static void init(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j]==1){
                    //能走
                    openArray.add(new P(i,j));
                    startPoint = new P(i, j);
                }else if(map[i][j]==3){
                    //阻塞，不能走
                    closeArray.add(new P(i, j));
                }else if(map[i][j]==2){
                    //终点

                    endPoint = new P(i, j);
                }
            }

        }
    }
}
