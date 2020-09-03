package com.gwh.lib;

/**
 * Created by Guo Wenhui
 * 2020/9/3
 * 堆排序
 **/
class StackSort {
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
        for (int i = (array.length-1)/2; i>=0; i--) {
            createHeap(array,array.length,i);
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
    //k:准备进行筛选的节点
    public static void createHeap(int[] array,int n,int k){
        int kLeft=2*k+1;//左孩子
        int kRight=2*k+2;//右孩子
//        if(key==array[k] || key==array[kLeft] || key==array[kRight]){
//            终止
//        }
        if(kLeft>=n && kRight>=n){//判断有没有子节点
            return;
        }
        int kLeftValue=Integer.MAX_VALUE;
        int kRightValue= Integer.MAX_VALUE;
        if(kLeft<n){
            kLeftValue=array[kLeft];
        }
        if(kRight<n){
            kRightValue=array[kRight];
        }
        //从最上往下递归
        // 三个节点开始比大小(假设这个堆以前就是满足要求的,即根不存在了)
        if(array[k]<=kLeftValue && array[k]<=kRightValue){
            return;
        }
        if(kLeftValue<kRightValue){//左子树的处理
            int t=array[k];array[k]=array[kLeft];array[kLeft]=t;
            createHeap(array,n,kLeft);
        }else{//右子树的处理
            int t=array[k];array[k]=array[kRight];array[kRight]=t;
            createHeap(array,n,kRight);
        }
    }
}
