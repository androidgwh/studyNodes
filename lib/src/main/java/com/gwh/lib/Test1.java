package com.gwh.lib;

/**
 * Created by 48608 on 2017/12/8.
 */

public class Test1 {
    public static void main(String[] args) {
        testHeapSort();
    }
    public static void testHeapSort(){
        int[] array=new int[]{6,3,9,2,4,5,1,8,7};
        heapSort(array);
    }
    //堆排序
    public static void heapSort(int[] array){
        //开始建堆
        //从最后一个非叶子(array.length-1)/2结点开始建
        for (int i = (array.length-1)/2; i>=0; i--) {  //i=4;i>=0
            createHeap(array,array.length,i);//array , 9 ,4
        }
        //开始边输出堆顶，边调整堆
        int n=array.length;//表求堆中元素的个数
        while(n>0){
            System.out.print(array[0]+" ");//根取走
            //最后一个放到根上
            array[0]=array[n-1];
            n--;
            //重新调整
            createHeap(array,n,0);
        }

    }

    //需要完成一次建堆的过程
    //n:表示堆中有多少个数据
    //k:准备进行筛选的节点    int[] array=new int[]{6,3,9,2,4,5,1,8,7};
    public static void createHeap(int[] array,int n,int k){//array , 9 ,3
        int kLeft=2*k+1;//左孩子7
        int kRight=2*k+2;//右孩子 8
//        if(key==array[k] || key==array[kLeft] || key==array[kRight]){
//            终止
//        }
        if(kLeft>=n && kRight>=n){//判断有没有子节点
            return;
        }
        int kLeftValue=Integer.MAX_VALUE;
        int kRightValue= Integer.MAX_VALUE;
        if(kLeft<n){
            kLeftValue=array[kLeft]; //8
        }
        if(kRight<n){
            kRightValue=array[kRight]; //7
        }
        //从最上往下递归
        // 三个节点开始比大小(假设这个堆以前就是满足要求的,即根不存在了)
        if(array[k]<=kLeftValue && array[k]<=kRightValue){ //3>8
            return;
        }
        if(kLeftValue<kRightValue){//左子树的处理
            int t=array[k];array[k]=array[kLeft];array[kLeft]=t;
            createHeap(array,n,kLeft);
        }else{//右子树的处理
            int t=array[k];array[k]=array[kRight];array[kRight]=t;//array[k]和array[kRight]交换
            createHeap(array,n,kRight);
        }
    }
    public void test2(){
        dfs(startX,startY);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    //深度优先的算法
    public static int startX=1;
    public static int startY=1;
    public static int endX=6;
    public static int endY=9;

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

    //反回值表示是否可以继续走下去
    //x,y表示去摸索的位置是否可以走
    public static boolean dfs(int x,int y){
        if(map[endX][endY]==2){//如果到了终点，退出
            return true;
        }
        //用2表示自己走到的位置,用1表示走不通的时候，需要回退的位置
        if(map[x][y]==0){
            map[x][y]=2;
            //开始摸索自己周围有没有路可以走
            if(dfs(x,y-1)){//左
                return true;
            }else if(dfs(x+1,y)){//下
                return true;
            }else if(dfs(x-1,y)){//上
                return true;
            }else if(dfs(x,y+1)){//右
                return true;
            }else{//如果四个方向都不能走了
                //开始回退到之前能走的位置
                map[x][y]=1;
                return false;
            }
        }else{
            return false;
        }
    }
}










