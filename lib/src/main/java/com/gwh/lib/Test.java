package com.gwh.lib;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.WeakHashMap;

/**
 * Created by Guo Wenhui
 * 2020/8/11
 **/
class Test {
    public static void main(String[] args) {
//        Object[] datas = {};
//        ArrayList<String> list = new ArrayList<>();
//        list.add("1111");
//        list.add("22222222222222");
//        list.add("3333333");
//        list.add(null);
//        list.add("44444");
//        list.add("55555555");
//        System.out.println("arrayList  " + list.toString());
////        list.remove(1);
//        System.out.println("arrayList  " + list.toString());
////        list.clear();
////        System.out.println("arrayList  " + list.toString());'
//        System.out.println("**********************************************");
//        int size;
//        for (size = 0; size < list.size(); ) {
//            System.out.println(" " + size);
//            System.out.println(" " + list.get(size));
//            ++size;
//        }
//        System.out.println("**********************************************");
//        int size1;
//        for (size1 = 0; size1 < list.size(); ) {
//            System.out.println(" " + list.get(size1));
//            size1++;
//        }
////      list.ensureCapacity(100);
//
//        Iterator<String> iterator = list.iterator();
//
////        linkedList.add()
//
//        Integer[] array = new Integer[10];
//        int array1[] = new int[10];
//        array1[0] = 10;
//        System.out.println(array[1]);
//        checkArray();
//

//        LinkedList<Integer> linkedList = new LinkedList<>();
//        linkedList.add(11);
//        linkedList.add(22);
//        linkedList.add(33);
//        linkedList.add(55);
//        linkedList.add(2,88);
//        linkedList.add(0,777);
//        linkedList.add(1,55555);
//        linkedList.remove(5);
//        System.out.println("**********************************************");
//        for (int i = 0; i < linkedList.size; i++) {
//            LinkedList<Integer>.Node<Integer> integerNode = linkedList.get(i);
//            System.out.println(" i : "+i+"item: "+integerNode.item+" pre: "+(null==integerNode.pre?null:integerNode.pre.item)+" next: "+(null==integerNode.next?null:integerNode.next.item));
//
//        }
//        LinkedSingleList<Integer> linkedSingleList = new LinkedSingleList<>();
//        linkedSingleList.add(1);
//        linkedSingleList.add(55);
//        linkedSingleList.add(77);
//        linkedSingleList.display();
//
//        char[] chars = reversePolish("(2 + 1) * 3");
//        System.out.println();
//        int result = evalRPN(chars);
//        System.out.print(result);

//        weakHash();


//        Object object = new Object();
//        WeakReference<Object> weakReference = new WeakReference<Object>(object);
////		SoftReference<Object> weakReference = new SoftReference<Object>(object);
//        object = null;
//        System.gc();
//        System.out.println(weakReference.get()); //

        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>(1000000);
        for (int i = 0; i < 100000; i++) {
            System.out.println("i : "+i);
            weakHashMap.put(i, "aaaaaa"+i);
        }
        String msg = weakHashMap.get("99999");
//        String a = weakHashMap.get("a");
//        a = null;
//        weakHashMap.remove("a");
        System.out.println(" weakhash is exist : "+weakHashMap.get(99999));
        System.gc();
        System.out.println("weakHashMap : "+weakHashMap.toString());
        System.out.println(" weakhash is exist : "+weakHashMap.get(99999));
    }

    public static void weakHash(){
        Map<Integer, Byte[]> map = null;

        map = new WeakHashMap<Integer, Byte[]>();
        for (int i = 0; i < 10000; i++) {
            Integer integer = new Integer(i);
            map.put(integer, new Byte[i]);
        }
        //-Xmx5M 这个时候发
        // 现没有OOM

        // -Xmx5M java.lang.OutOfMemoryError: Java heap space
        map = new HashMap<Integer, Byte[]>(10);
        for (int i = 0; i < 100; i++) {
            Integer integer = new Integer(i);
            map.put(integer, new Byte[i]);
        }

        //如果存放在WeakHashMap中的key都存在强引用，那么WeakHashMap就会退化为HashMap。
        // -Xmx5M java.lang.OutOfMemoryError: Java heap space
        // at cn.intsmaze.collection.MapCase.testWeakHash(MapCase.java:119)
        map = new WeakHashMap<Integer, Byte[]>();
        List list = new ArrayList();
        for (int i = 0; i < 1000000000; i++) {
            Integer integer = new Integer(i);
            map.put(integer, new Byte[i]);// 如果你看不起我，你可以把这行注释，你将会发现姜还是老的辣，内存溢出是WeakHashMap而不是List导致.
//            list.add(integer);
        }
        System.out.println(map.get(0));
    }
    public static boolean isValid(String s){
        if(s.contains("()")){
            return true;
        }
        return false;
    }
    private static void checkArray() {
        System.out.println("************************************************");
        OrderArray array = new OrderArray(100);
        array.insert(10);
        array.insert(99);
        array.insert(66);
        array.insert(44);
        array.insert(67);
        array.insert(35);
        array.insert(13);
        array.insert(34);
        array.insert(69);
        array.insert(55);
        array.insert(55);

        int searchKey = 55;
        if (array.find(searchKey) != array.size()) {
            System.out.println("found " + searchKey);
        } else
            System.out.println("car't found " + searchKey);
        System.out.println("************************************************");
        array.display();
        System.out.println("************************************************");
        array.delete(66);
        array.delete(13);
        array.delete(55);
        array.delete(55);
        array.display();
    }


    //用来做操作符入栈的栈
    private static Stack<Character> stack = new Stack<>();

    private static java.util.LinkedList<Character> list = new java.util.LinkedList<>();


    /**
//     *
     * @param tokens
     * @return
     */
    public static int evalRPN(char[] tokens) {
        Stack<Integer> stack = new Stack();
        int opt1 = 0;   //定义两个操作数
        int opt2 = 0;
        for(char token:tokens){
            if(isOperator(token)){      //如果是运算符
                opt1 = stack.pop();
                opt2 = stack.pop();

                //进行运算
                switch (token){
                    case '+':
                        opt2 += opt1;
                        break;
                    case '-':
                        opt2 -= opt1;
                        break;
                    case '*':
                        opt2 *= opt1;
                        break;
                    case '/':
                        opt2 /= opt1;
                        break;
                }
                //将运算结果入栈
                stack.push(opt2);
            }else{          //证明是操作数,直接入栈
                if(token!=' '){
                    stack.push(Integer.parseInt(String.valueOf(token)));
                }
            }
        }
        return stack.pop();
    }

    /**
     * 传入一个四则运算式（中缀表达式）
     * @param expression
     * @return  返回一个队列 后面装有后缀表达式
     */
    public static char[] reversePolish(String expression){
        if(expression == null){
            return null;
        }
        char[] chars = expression.toCharArray();
        for(char s : chars) {
            if (isOperator(s)) {
                if (stack.isEmpty()) {  //如果栈之前是空的，直接把操作符入栈
                    stack.push(s);
                } else {
                    //如果读入的操作符为非")"且优先级比栈顶元素的优先级高或一样，则将操作符压入栈
                    if (priority(stack.peek()) <= priority(s) && s != ')') {
                        stack.push(s);

                    } else if (priority(stack.peek()) > priority(s) && s != ')') {
                        while (stack.size() != 0 && stack.peek()!= '(') {
                            char operator = stack.pop();
                            list.offer(operator);
                        }
                        stack.push(s);

                    } else if (s == ')') {
                        while (stack.peek() != '(') {
                            char operator = stack.pop();
                            list.offer(operator);
                        }
                        //while循环执行结束后，还要弹出"("
                        stack.pop();
                    }
                }

            } else {    //不是操作符的话直接加到队列中
                list.offer(s);
            }
        }

        //把剩下的操作符也添加到队列中
        while (!stack.isEmpty()) {
            char operator = stack.pop();
            list.offer(operator);
        }

        char[] c = new char[list.size()];
        int i = 0;
        while(!list.isEmpty()){
            c[i] = list.poll();
            System.out.print(c[i]);
            i++;
        }
        return c;
    }

    /**
     * 判断是不是操作符
     * @param oper
     * @return
     */
    private static boolean isOperator(char oper){
        if (oper == ('+') || oper==('-') || oper==('*')
                || oper==('/') || oper==('(') || oper== (')')) {
            return true;
        }
        return false;
    }

    /**
     * 计算操作符的优先级
     * @param s
     * @return
     */
    private static int priority(char s){
        switch (s) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case ('/'):
                return 2;
            case '(':
                return 3;
            case ')':
                return 3;
            default :
                return 0;
        }
    }
}
