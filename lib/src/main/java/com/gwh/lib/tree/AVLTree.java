package com.gwh.lib.tree;

/**
 * Created by Guo Wenhui
 * 2020/8/25
 **/
public class AVLTree {
Node root;
static final int LH=1;
static final int RH=-1;
static final int EH=0;

    /**
     * 左旋
     * 查找旋转目标节点的右子树的左子树
     * 处理 目标节点的右子树的左子树  和 目标节点的关系，断开目标节点右子树和他的左孩子的关系（目标节点的右子树的左子树一定比目标节点大，所以可以作为目标节点的右子树）
     * 处理目标节点的父节点和目标节点右子树的关系
     * 处理目标节点和目标节点右子树的关系
     * @param y
     */
    public void leftRotate(Node y){
        //左旋 找右子树的左子树
        //yr y 和子树的关系
        Node yr = y.right;
        y.right = yr.left;
        if(yr.left!=null){
            yr.left.parent = y;
        }
        //yr y和y.parent的关系
        if(y.parent==null){
            root=yr;
        }else {
            //看之前y是y.parent的左子树还是右子树，决定yr是y.parent的左子树还是右子树
            if(y.parent.left==y){
                y.parent.left = yr;
            }else if(y.parent.right==y){
                y.parent.right = yr;
            }
        }
        yr.parent = y.parent;
        yr.left=y;
        y.parent=yr;

    }

    /**
     * 右旋转
     *1 查找y的左子树yl
     * 2处理yl的的右子树和y的关系，断开yl和yl的右子树的关系
     * 3处理yl和y的parent的关系
     * 4处理yl和y的关系
     * @param y
     */
    public void rightRotate(Node y){
        //1
        Node yl = y.left;
        //2
        y.left = yl.right;
        if(yl.right!=null){
            yl.right.parent=y;
        }
        //3
        if(y.parent==null){
            root=yl;
        }else {
            if(y.parent.left==y){
                y.parent.left=yl;
            }else if(y.parent.right==y){
                y.parent.right=yl;
            }
        }
        yl.parent=y.parent;
        //4
        yl.right=y;
        y.parent=yl;
    }

    /**
     * 左平衡树 t节点的不平衡是因为左子树过深
     * 1 新的节点插入到t的左孩子的左子树 右旋
     * 2 新的节点插入到t的左孩子的右子树
     *   1) 左孩子的右子树的根节点的balance LH
     *   2) 左孩子的右子树的根节点的balance RH
     *   3) 左孩子的右子树的根节点的balance EH
     *   先左旋再右旋
     * @param t
     */
    public void leftBalance(Node t){
        Node tl = t.left;
        switch (tl.balance){
            case LH:
                //右旋
                rightRotate(t);
                t.balance=EH;
                tl.balance=EH;
                break;
            case RH:
                Node tlr = tl.right;
                switch (tlr.balance){
                    case LH:
                        t.balance=RH;
                        tl.balance=EH;
                        tlr.balance=EH;
                        break;
                    case RH:
                        t.balance=EH;
                        tl.balance=LH;
                        tlr.balance = EH;
                        break;
                    case EH:
                        t.balance=EH;
                        tl.balance=EH;
                        tlr.balance = EH;
                        break;
                }
                //先左旋再右旋
                leftRotate(t.left);
                rightRotate(t);
                break;
            case EH:
                break;
        }

}

    /**
     * 右平衡树 t节点的不平衡是由于右子树过深
     * 1 新插入的节点到t的右孩子的右子树 左旋转
     * 2 新插入的节点到t的右孩子的左子树
     * 先右旋再左旋
     *   1) 右孩子的左子树 LH
     *   2) 右孩子的左子树 RH
     *   3) 右孩子的左子树 EH
     * @param t
     */
    public void rightBalance(Node t){
        Node tr = t.right;
        switch (tr.balance){
            case RH:
                leftRotate(t);
                t.balance=EH;
                tr.balance=EH;
                break;
            case LH:
                Node trl = tr.left;
                switch (trl.balance){
                    case LH:
                        t.balance=EH;
                        tr.balance = RH;
                        trl.balance=EH;
                        break;
                    case RH:
                        t.balance=LH;
                        tr.balance=EH;
                        trl.balance=EH;
                        break;
                    case EH:
                        t.balance=EH;
                        tr.balance=EH;
                        trl.balance = EH;
                        break;
                }
                rightRotate(t.right);
                leftRotate(t);
                break;
        }
}
    public class Node<E extends Comparable<E>>{
        E data;
        int balance;
        Node left;
        Node right;
        Node parent;

    }
}
