package com.gwh.lib.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guo Wenhui
 * 2020/8/27
 *
 **/
public class TreeTest {
    public static void main(String[] args) {
//        List<List<Integer>> res = new ArrayList<>();
        TreeNode root = new TreeNode();
        root.val = 3;
        TreeNode rl = new TreeNode();
        rl.val = 9;
        root.left = rl;
        TreeNode rr = new TreeNode();
        rr.val = 20;
        root.right = rr;
        TreeNode rrl = new TreeNode();
        rrl.val = 15;
        rr.left = rrl;
        TreeNode rrr = new TreeNode();
        rrr.val = 7;
        rr.right = rrr;
//        traversal(root,res,0);
//        System.out.println("******************************");
//        System.out.println(res.toString());

        maxDepth(root);
    }

    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     *
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     *
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0||inorder.length==0){
            return null;
        }
        TreeNode root=new TreeNode ();
        root.val = preorder[0];
        for(int i=0;i<preorder.length;i++){
            if(preorder[0]==inorder[i]){
                root.left=buildTree(Arrays.copyOfRange(preorder,1,i+1), Arrays.copyOfRange(inorder,0,i));
                root.right=buildTree(Arrays.copyOfRange(preorder,i+1,preorder.length),Arrays.copyOfRange(inorder,i+1,inorder.length));
                break;
            }
        }
        return root;
    }
    /**
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {

        if(null!=root) {
            int i = Math.max(maxDepth(root.left), maxDepth(root.right)) ;
            int finalI = i + 1;
            System.out.println(i +" "+root.val);
            return finalI;
        }else {
        }
        System.out.println("0 null");
        return 0;
    }

    /**
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @param res
     * @param level
     */
    private static void traversal(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }

        if (res.size() == level) {
            res.add(new LinkedList<Integer>());
        }

        if ((level % 2)!= 0) {
            //逆行的规律
            System.out.println("level : " + level + " level &1 : " + (level & 1) + " root.val : " + root.val);
            res.get(level).add(0, root.val);
        } else {
            //顺行的规律
            System.out.println("level : " + level + " level &1 : " + (level & 1) + " root.val : " + root.val);
            res.get(level).add(root.val);
        }

        traversal(root.left, res, level + 1);
        traversal(root.right, res, level + 1);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
