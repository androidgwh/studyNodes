package com.gwh.lib.tree;

import java.util.Stack;


/**
 * Created by Guo Wenhui
 * 2020/8/17
 * Binary
 *
 *二叉排序数
 * 当前节点的左节点都比当前节点小，当前节点的右节点都比当前节点大。
 * 类似 二叉树 的中序遍历
 **/
public class BinaryTree<T> {
        Node<String> root;

        public static void main(String[] arg){
            BinaryTree binaryTree = new BinaryTree("A");
            binaryTree.createTree();
            binaryTree.postOrder2(binaryTree.root);
//            System.out.println("");
//            binaryTree.middleOrderTraverse(binaryTree.root);
//            System.out.println("");
//            binaryTree.postOrderTraverse(binaryTree.root);

        }
    public BinaryTree(String data) {
        //
        root = new Node<String>(data, null, null);
    }

    /**
     *
     *        A
     *   B         C
     * D  E           F
     *
     *前序：ABDECF
     * 中序:DBEACF
     * 后序:DEBFCA
     */
    public void createTree(){
        Node<String> NodeB = new Node<>("B", null, null);
        Node<String> NodeC = new Node<>("C", null, null);
        Node<String> NodeD = new Node<>("D", null, null);
        Node<String> NodeE = new Node<>("E", null, null);
        Node<String> NodeF = new Node<>("F", null, null);
        root.leftChild = NodeB;
        root.rightChild = NodeC;
        NodeB.leftChild = NodeD;
        NodeB.rightChild = NodeE;
        NodeC.rightChild = NodeF;
    }
    /**
     *
     * @param root
     */
    public void preOrderTraverse(Node root){
        System.out.println("pre::::: "+(root==null?" node is null":root.data));
        if(null==root){
            return;
        }
        System.out.print("pre order : "+root.data);
        //
        System.out.println("pre left:::: "+root.leftChild);
        preOrderTraverse(root.leftChild);
        //
        System.out.println("pre right:::: "+root.data+" rightChild::::  "+(null==root.rightChild?"rightChild is null":root.rightChild.data));
        preOrderTraverse(root.rightChild);
    }


    /**
     * 栈
     * @param node
     */
    public void preOrderTraverse2(Node node){
    if(null==node){
        return;
    }else {
        Stack<Node<String>> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()) {
            Node<String> pop = stack.pop();
            System.out.println("pre order2: " + pop.data);
            if (pop.rightChild != null) {
                stack.push(pop.rightChild);
            }
            if (pop.leftChild != null) {
                stack.push(pop.leftChild);
            }
        }
    }
    }

    /**
     * DBEACF
     * @param root
     */
    public void middleOrderTraverse2(Node root){
        if(null==root){
            return;
        }
        Stack<Node<String>> stack = new Stack<>();
        Node cur = root;
        while (!stack.isEmpty()||cur!=null){
            while (cur!=null){
                stack.push(cur);
                cur = cur.leftChild;
            }
            Node<String> pop = stack.pop();
            System.out.println(pop.data);
            cur = pop.rightChild;
        }

    }

    /**
     *
     *        A
     *   B         C
     * D  E           F
     *
     *前序：ABDECF
     * 中序:DBEACF
     * 后序:DEBFCA
     *
     * AC
     * DEB
     *
     */
    public void postOrder2(Node<T> root){
        if(null==root){
            return;
        }
        Stack<Node<T>> stack = new Stack<>();
        Node<T> cur = root;
        Node<T> temp = null;
        while (!stack.isEmpty()||cur!=null){
        while (cur!=null){
            stack.push(cur);
            temp = cur;
            cur = cur.leftChild;
            temp.leftChild = null;
        }
            /**
             * temp cur 指向同一片区域
             * stack
             * temp.leftChild =null
             *
             * cur.leftChild=null
             */
            cur = stack.peek();
            //节点的左节点和右节点是否都已经查找过
            if(cur.leftChild==null&&cur.rightChild==null){
                while ((stack.peek().leftChild==null&&stack.peek().rightChild==null)){
                    System.out.println(stack.pop().data);
                    if(!stack.isEmpty()) {
                        cur = stack.peek();
                    }
                    else {
                        return;
                    }
                }
                temp = cur;
                cur = cur.rightChild;
                temp.rightChild = null;
            }else {
                //查找右节点
                temp = cur;
                cur = cur.rightChild;
                temp.rightChild = null;

            }


        }


    }


    public void middleOrderTraverse(Node root){
        if(null==root){
            return;
        }
        middleOrderTraverse(root.leftChild);
        System.out.print("middle order: "+root.data);
        middleOrderTraverse(root.rightChild);
    }

    /**
     * 1、 A
     *
     * @param root
     */
    public void postOrderTraverse(Node root){
        if(null==root){
            return;
        }
        postOrderTraverse(root.leftChild);
        postOrderTraverse(root.rightChild);
        System.out.print("post order: "+root.data);
    }
    public class Node<T> {
        T data;
        Node leftChild;
        Node rightChild;

        public Node(T data, Node leftChild, Node rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
}
