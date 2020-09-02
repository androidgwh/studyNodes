package com.gwh.lib;

/**
 * Created by Guo Wenhui
 * 2020/8/14
 * 手写单链表
 **/
public class LinkedSingleList<T> {
    public Node<T> head;

    public void add(T e){

        Node<T> newNode = new Node<>(e);
        if(head==null){
            head = newNode;
        }else {
            Node<T> node = head;
            while (node.next!=null){
                //找到目前最后一个值
                node = node.next;
            }
            //把最后一个值的next赋值成新的node
            node.next = newNode;
            System.out.println(" node : "+node.next.item+" and next is : "+(null!=node.next.next?node.next.next.item:"null"));
            System.out.println("****************************************");
        }
        /**
         * i:0 item:1
         * head=1
         *
         * ------------
         * i:1 item:55
         * head=1
         * head.next=55
         * ----------------------
         * i:2 item:77
         * head:1
         * head.next=
         *
         */
    }

    public void display(){
        Node<T> node = head;
        int i = 0;
        while (null!=node){
            System.out.println("i : "+(i++)+" item : "+node.item+" next: "+(null!=node.next&&null!=node.next?node.next.item:null));
            node = node.next;
        }
    }
    public class Node<T>{
        public T item;
        public Node<T> next;

        public Node(T item) {
            this.item = item;
        }
    }
}
