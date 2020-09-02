package com.gwh.lib;



/**
 * Created by Guo Wenhui
 * 2020/8/13
 * 手写双向链表
 **/
public class LinkedList<E> {
public Node<E> first;
public Node<E> last;
public int size;

    public LinkedList() {
    }

    public void add(E e){
        //pinjie last
        linkLast(e);
    }

    public Node<E> get(int index){
        if(index<0||index>size-1){
            return null;
        }
        return node(index);
    }

    public void add(int index,E e){
        if(index<0||index>size){
            return;
        }
        if(index==size){
            linkLast(e);
        }else {
            Node<E> node = node(index);
            Node<E> pre = node.pre;
            Node<E> newNode = new Node<>(e, pre, node);
            if(pre==null){
                //add 0
                first = newNode;
            }else {
                pre.next = newNode;
            }
            node. pre = newNode;
            size++;
        }
    }

    public void remove(int index){
        unLink(index);
    }
    private void unLink(int index){
        if(index<0||index>size-1){
            return;
        }
        Node<E> node = node(index);
        Node<E> pre = node.pre;
        Node<E> next = node.next;
        if(pre==null){
            first = node.next;
        }else {
            pre.next = node.next;

        }
        if(next==null){
            last = node.pre;
        }else {
            next.pre = node.pre;
        }

        size--;
    }


    public Node<E> node(int index){
        if(index<size>>1){
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else {
            Node<E> node = last;
            for (int i = size-1; i>index ; i--) {
                node = node.pre;
            }
            return node;
        }
    }
    public void linkLast(E e){
        Node<E> node = new Node<>(e, last, null);
        Node<E> l = last;
        last = node;
        if(l==null){
            //first add first=last
            first = node;
        }else {
            l.next = node;
        }
        size++;
    }

    public class Node<E>{
        public E item;
        public Node<E> pre;
        public Node<E> next;

        public Node(E item, Node<E> pre, Node<E> next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }
}
