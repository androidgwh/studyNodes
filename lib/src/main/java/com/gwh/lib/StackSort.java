package com.gwh.lib;

/**
 * Created by Guo Wenhui
 * 2020/9/3
 * 堆排序
 * 1、构建初始堆，将待排序列构成一个大顶堆(或者小顶堆)，升序大顶堆，降序小顶堆；
 * 2、将堆顶元素与堆尾元素交换，并断开(从待排序列中移除)堆尾元素。
 * 3、重新构建堆。
 * 4、重复2~3，直到待排序列中只剩下一个元素(堆顶元素)。
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

    /**
     *         6
     *      3    9
     *    2  4  5  1
     *  8  7
     * @param array 6,3,9,2,4,5,1,8,7
     */
    public static void heapSort(int[] array){
        //开始建堆
        //从最后一个非叶子(array.length-1)/2结点开始,从下至上，从左至右调整解耦
        for (int i = (array.length-1)/2; i>=0; i--) { //4 3 2 1
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

    /**
     *        6
     *      3   9
     *    2  4  5  1
     *  8  7
     *
     *   6,3,1,2,4,5,9,8,7
     *
     *        6
     *     3     1
     *  2    4  5   9
     * 8  7
     *
     * 1,2,6,3,4,5,9,8,7
     *
     *           1
     *        2     6
     *     3    4  5   9
     *   8   7
     *
     *   1,2,5,3,4,6,9,8,7
     *          1
     *       2    5
     *     3  4  6  9
     *   8  7
     *
     *              0 1 2 3 4 5 6 7 8
     * @param array 6,3,9,2,4,5,1,8,7
     * @param n 9 9 9 9 9
     * @param k 4 3 2 1 0
     * 从下至上，从左至右调整解耦
     */
    public static void createHeap(int[] array,int n,int k){
        /**
         * kLeft   9×    7  5  3  1
         * kRight  10×   8  6  4  2
         */
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
            /**
             * 7  5  3  1
             * kLeftValue  array[7]=8 array[5]=5
             */
            kLeftValue=array[kLeft];
        }
        if(kRight<n){
            /**
             * 8  6  4  2
             * kRightValue array[8]=7 array[6]=1
             */
            kRightValue=array[kRight];
        }
        //从最上往下递归
        // 三个节点开始比大小(假设这个堆以前就是满足要求的,即根不存在了)
        /**
         * array[3]=2和kLeftValue和kRightValue比较大小，如果比他们都小退出
         */
        if(array[k]<=kLeftValue && array[k]<=kRightValue){
            /**
             * k=4
             */
            return;
        }
        if(kLeftValue<kRightValue){//左子树的处理
            int t=array[k];array[k]=array[kLeft];array[kLeft]=t;
            createHeap(array,n,kLeft);
        }else{//右子树的处理
            /**
             * 5>1
             * array[k] : array[2]=9
             * array[kRight] : array[6]=1
             * 交换
             */
            int t=array[k];array[k]=array[kRight];array[kRight]=t;
            /**
             * 6,3,1,2,4,5,9,8,7
             * n 9
             * kRight 6
             */
            createHeap(array,n,kRight);
        }
    }
}
