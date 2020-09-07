package com.gwh.lib.tree;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 二叉查找树
 */
public class SearchBinaryTree {
    public TreeNode root;

    public TreeNode put(int data) {
        if (null == root) {
            root = new TreeNode(data);
            return root;
        } else {
            TreeNode parent = null;
            TreeNode temp = root;
            while (temp != null) {
                parent = temp;
                if (data < temp.data) {
                    temp = temp.leftChild;
                } else if (data > temp.data) {
                    temp = temp.rightChild;
                } else {
                    return temp;
                }
            }

            TreeNode newNode = new TreeNode(data);
            if (data < parent.data) {
                parent.leftChild = newNode;
            } else {
                parent.rightChild = newNode;
            }
            newNode.parent = parent;
            return newNode;
        }
    }
    public void del(TreeNode node) {

        if (node == null) {
            throw new NoSuchElementException();
        }
        //没有子节点

        TreeNode parent = node.parent;
        if (node.leftChild == null && node.rightChild == null) {
            if (parent == null) {
                root = null;
            } else {
                if (parent.leftChild == node) {
                    parent.leftChild = null;
                } else if (parent.rightChild == node) {
                    parent.rightChild = null;
                }
                node.parent = null;
            }
        }else if(node.leftChild!=null&&node.rightChild==null) {
            //只有左子节点
            if(parent==null){
                node.leftChild.parent = null;
                root = node.leftChild;
            }else {
                if(parent.leftChild==node){
                    parent.leftChild = node.leftChild;
                }else {
                    parent.rightChild = node.leftChild;
                }
            }
            node.parent = null;
        }else if(node.leftChild==null&&node.rightChild!=null){
            if(parent==null){

                node.rightChild.parent = null;
                root = node.rightChild;
            }else {
                if(parent.leftChild==node){
                    parent.leftChild = node.rightChild;
                }else {
                    parent.rightChild = node.rightChild;
                }
            }
            node.parent = null;
        }else {
            if(node.rightChild.leftChild==null){
                //删除节点的右节点的左节点 Null'
                node.rightChild.leftChild = node.leftChild;
                if(parent==null){
                    root = node.rightChild;
                }else {
                    if(parent.leftChild==node){
                        parent.leftChild = node.leftChild;
                    }else {
                        parent.rightChild = node.rightChild;
                    }
                }
                node.parent = null;
            }else {
                TreeNode leftNode = getMinLeftNode(node.rightChild);
                // 1
                leftNode.leftChild = node.leftChild;
                TreeNode leftNodeParent = leftNode.parent;
                //2
                leftNodeParent.leftChild = leftNode.rightChild;
                //3
                leftNode.rightChild = node.rightChild;
                //4
                if(parent==null){
                    root = leftNode;
                }else {
                    if(parent.leftChild==node){
                        parent.leftChild = leftNode;
                    }else {
                        parent.rightChild = leftNode;
                    }
                }

            }

        }

    }
    //只有右子节点
    //有两个子节点

    public TreeNode getMinLeftNode(TreeNode node){
        TreeNode left = node;
        while (node.leftChild!=null){
            left = node.leftChild;
        }
        return left;
    }

    public void midOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.leftChild;
            }
            TreeNode pop = stack.pop();
            System.out.println(pop.data);
            cur = pop.rightChild;
        }
    }
    public void preOrder(TreeNode node){
        if(null==node){
            return;
        }
        System.out.println(node.data);
        preOrder(node.leftChild);
        preOrder(node.rightChild);
    }
    public TreeNode search(int data){
        TreeNode cur = root;
        while (cur!=null){
            if(cur.data==data){
                return cur;
            }else if(data<cur.data){
                cur = cur.leftChild;
            }else if(data>cur.data){
                cur = cur.rightChild;
            }
        }
        return null;
    }

    public class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode parent;

        public TreeNode(int data) {
            this.data = data;
        }

    }





    public static void main(String[] args) {
        SearchBinaryTree searchBinaryTree = new SearchBinaryTree();
        int[] arrays = {12, 3, 23, 5, 8, 1, 19};
        for (int i = 0; i < arrays.length; i++) {
            searchBinaryTree.put(arrays[i]);
        }
        searchBinaryTree.midOrderTraverse(searchBinaryTree.root);
        TreeNode search = searchBinaryTree.search(5);
        searchBinaryTree.del(search);
        searchBinaryTree.midOrderTraverse(searchBinaryTree.root);
    }
}
