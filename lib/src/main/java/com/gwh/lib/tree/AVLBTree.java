package com.gwh.lib.tree;

public class AVLBTree<E extends Comparable<E>> {

    Node root;
    static final int LH = 1;
    static final int RH = -1;
    static final int EH = 0;
    int size;
    public void left_rotate(Node x){
        if(x==null){
            return;
        }
        //node right left
        Node y = x.right;
        x.right = y.left;
        if(y.left!=null){
            y.left.parent = x;
        }
        y.parent = x.parent;
        if(x.parent==null){
            root = y;
        }else {
            if(x.parent.left==x){
                x.parent.left = y;
            }else if(x.parent.right==x){
                x.parent.right = y;
            }
        }
        y.left = x;
        x.parent = y;

    }

    public void rightRotate(Node y){
        Node yl = y.left;
        y.left = yl.right;
        if(yl.right!=null){
            yl.right.parent = y;
        }
        if(y.parent==null){
            root=yl;
        }else {
            if(y.parent.left==y){
                y.parent.left = yl;
            }else if(y.parent.right==y){
                y.parent.right = yl;
            }
        }
        yl.parent = y.parent;

        yl.left=y;
        y.parent = yl;
    }
    public boolean insert(E element){
        Node<E> t = root;
        if(t==null){
            root = new Node();
            root.data = element;
            size=1;
            root.balance=0;
            return true;
        }else {
            int cmp=0;
            Node<E> parent;
            Comparable<? super E> e = (Comparable<? super E>)element;
            do {
                parent = t;
                cmp = e.compareTo(t.data);
                if(cmp<0){
                    t = t.left;
                }else if(cmp>0){
                    t = t.right;
                }else {
                    return false;
                }
            } while (t != null);
            Node<E> child = new Node<>();
            child.data = element;
            child.parent = parent;
            if(cmp<=0){
                parent.left = child;
            }else {
                parent.right = child;
            }
            //溯源 找最小不平衡树
            while (parent!=null){
                cmp=e.compareTo(parent.data);
                if(cmp<0){
                    //插入了左边，balance++
                    parent.balance++;
                }else {
                    parent.balance--;
                }
                if(parent.balance==0){
                    break;
                }
                if(Math.abs(parent.balance)==2){
                    fixAfterInsertion(parent);
                    break;
                }else {
                    parent = parent.parent;
                }
            }
            size++;
            return true;
        }

    }

    private void fixAfterInsertion(Node<E> parent) {
        if(parent.balance==2){
            leftBalance(parent);
        }
        if(parent.balance==-2){
            rightBalance(parent);
        }
    }

    public void rightBalance(Node t){
        Node tr = t.right;
        switch (tr.balance){
            case RH:
                left_rotate(t);
                t.balance = EH;
                tr.balance = EH;
                break;
            case LH:
                Node trl = tr.left;
                rightRotate(t.right);
                left_rotate(t);
                switch (trl.balance){
                    case LH:
                        t.balance = EH;
                        tr.balance = RH;
                        trl.balance = EH;

                        break;
                    case RH:
                        t.balance = LH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                }

                break;
            case EH:
                break;
        }
    }
    public void leftBalance(Node t){
        Node tl = t.left;
        switch (tl.balance){
            case LH:
                rightRotate(t);
                t.balance=EH;
                tl.balance = EH;
                break;
            case RH:
                Node tlr = tl.right;
                left_rotate(t.left);
                rightRotate(t);
                switch (tlr.balance){
                    case LH:
                        t.balance=RH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                    case RH:
                        t.balance = EH;
                        tl.balance = LH;
                        tlr.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                }
                break;
            case EH:
                break;
        }
    }

    public class Node<E extends Comparable<E>> {
        E data;
        int balance;
        Node left;
        Node right;
        Node parent;


    }
    public static void main(String[] args) {
//		Integer[] nums = {5, 8, 2, 0, 1, -2, -9, 100};
        Integer[] nums = {5, 8, 2, 0, 1, -2};
        AVLBTree<Integer> vAvlTree = new AVLBTree();
        for (int i = 0; i < nums.length; i++) {
            vAvlTree.insert(nums[i]);
        }
//		vAvlTree.midOrderDisplay(vAvlTree.root);
//        vAvlTree.levelDisplay(vAvlTree.root);
        System.out.println(vAvlTree.root.data);
    }
}
