package com.gwh.lib.tree;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by Guo Wenhui
 * 2020/8/18
 **/
public class BinaryFindTree {
    public Node root;

    public Node put(int data) {
        if (null == root) {
            root = new Node(data);
            return root;
        }
        //有根节点，查找叶子节点，比较data大小
        Node parent = null;
        Node node = root;
        while (node != null) {
            parent = node;
            if (data < node.data) {
                node = node.leftChild;
            } else if (data > node.data) {
                node = node.rightChild;
            } else {
                return node;
            }
        }
        Node newNode = new Node(data);
        if (data < parent.data) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
        newNode.parent = parent;
        return newNode;

    }

    public void del(Node node) {

        if (null == node) {
            throw new NoSuchElementException();
        }
        //没有子节点
        Node parent = node.parent;
        if (node.leftChild == null && node.rightChild == null) {
            //根节点
            if (parent == null) {
                root = null;
            } else if (parent.leftChild == node) {
                parent.leftChild = null;
            } else if (parent.rightChild == node) {
                parent.rightChild = null;
            }
            node.parent = null;
        } else if (node.leftChild != null && node.rightChild == null) {
            //只有左子节点
            //找到该节点的最左叶子节点
            if (parent == null) {
                node.parent = null;
                node.leftChild.parent = null;
                root = node.leftChild;
            } else {
                if (node == parent.leftChild) {
                    parent.leftChild = node.leftChild;
                } else {
                    parent.rightChild = node.leftChild;
                }
            }
        } else if (node.rightChild != null && node.leftChild == null) {
            //只有右子节点
            if (parent == null) {
                node.parent = null;
                node.rightChild.parent = null;
                root = node.rightChild;
            } else {
                if (node == parent.leftChild) {
                    parent.leftChild = node.rightChild;
                } else {
                    parent.rightChild = node.rightChild;
                }
            }
        } else {
            //有两个子节点 node.leftChild node.rightChild not null
            //删除节点的右节点的左节点为 null
            if (node.rightChild.leftChild == null) {
                if (parent == null) {
                    root = node.rightChild;
                } else {
                    if (parent.leftChild == node) {
                        parent.leftChild = node.rightChild;
                    } else {
                        parent.rightChild = node.rightChild;
                    }
                }
                node.parent = null;
            }else {
                //删除节点右节点的左节点不为null
                Node minLeft = getMinLeftNode(node.rightChild);
                //左节点的左节点=删除节点的左节点 minLeft 4 4的左节点=删除节点的左节点 1
                minLeft.leftChild = node.leftChild;
                //4的父节点 5
                Node leftP = minLeft.parent;
                //5的左节点=null
                leftP.leftChild = minLeft.rightChild;
                //4的右节点=删除节点的右节点5
                minLeft.rightChild = node.rightChild;
                /**                  12
                 *           4
                 *         1   5
                 *                8
                 */
                if(parent==null){
                    root = minLeft;
                }else {
                    if(parent.leftChild==node){
                        parent.leftChild = minLeft;
                    }else {
                        parent.rightChild = minLeft;

                    }
                }
            }

        }
    }

    private Node getMinLeftNode(Node root){
        Node cur = null;
        if(null==root){
            return null;
        }
        cur = root;
        while (cur.leftChild!=null){
            cur = cur.leftChild;
        }
        return cur;
    }
    public Node searchNode(int data){
        if(root==null){
            return null;
        }
        Node node = root;
        while (node!=null){
            if(node.data<data){
                node = node.rightChild;
            }else if(node.data>data) {
                node = node.leftChild;
            }else if(node.data==data){
                return node;
            }
        }
        return null;
    }
    public void middleOrder(Node node) {
        if (null == node) {
            return;
        }
        Node cur = node;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.leftChild;
            }
            Node pop = stack.pop();
            System.out.println(pop.data);
            cur = pop.rightChild;
        }
    }

    public class Node {
        int data;
        Node leftChild;
        Node rightChild;
        Node parent;

        public Node(int data) {
            this.data = data;
        }


    }

    public static void main(String[] args) {
        BinaryFindTree binaryFindTree = new BinaryFindTree();
        int[] arrays = {12, 3, 23, 5, 8, 1, 19};
        for (int i = 0; i < arrays.length; i++) {
            binaryFindTree.put(arrays[i]);
        }
        binaryFindTree.middleOrder(binaryFindTree.root);
        Node node = binaryFindTree.searchNode(5);
        binaryFindTree.del(node);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        binaryFindTree.middleOrder(binaryFindTree.root);
    }
}
