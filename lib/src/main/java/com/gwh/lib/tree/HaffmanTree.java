//package com.gwh.lib.tree;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Stack;
//
//
//public class HaffmanTree {
//
//	TreeNode root;
//	public static class TreeNode<T> implements Comparable<TreeNode<T>>{
//		T data;
//		int weight;
//		TreeNode leftChild;
//		TreeNode rightChild;
//		TreeNode parent;
//
//		public TreeNode(T data, int wei) {
//			this.data = data;
//			this.weight = wei;
//			leftChild = null;
//			rightChild = null;
//			parent = null;
//		}
//
//		@Override
//		public int compareTo(TreeNode<T> o) {
//			if (this.weight > o.weight) {
//				return -1;
//			} else if (this.weight < o.weight) {
//				return 1;
//			}
//			return 0;
//		}
//	}
//
//	public TreeNode createHaffManTree(ArrayList<TreeNode> list) {
//		while (list.size() > 1) {
//			Collections.sort(list);
//			TreeNode left = list.get(list.size()-1);
//			TreeNode right = list.get(list.size()-2);
//			TreeNode parent = new TreeNode("p", left.weight + right.weight);
//			parent.leftChild = left;
//			left.parent = parent;
//			parent.rightChild = right;
//			right.parent = parent;
//			list.remove(left);
//			list.remove(right);
//			list.add(parent);
//		}
//		root = list.get(0);
//		return list.get(0);
//	}
//
//	public void showHaffman(TreeNode root) {
//		LinkedList<TreeNode> list = new LinkedList<>();
//		ArrayList<TreeNode> arrayList = new ArrayList<>();
//		list.offer(root);
//		while (!list.isEmpty()) {
//			TreeNode node = list.pop();
//			System.out.println(node.data);
//			if (node.leftChild != null) {
//				list.offer(node.leftChild);
//			}
//			if (node.rightChild != null) {
//				list.offer(node.rightChild);
//			}
//		}
//	}
//
//	public static List<List<String>> levelOrder(TreeNode root) {
//		List<List<String>> ret=new ArrayList<>();
//		LinkedList<TreeNode> queue=new LinkedList<>();
//
//		//队列不为空
//		if(root !=null){
//			queue.offer(root);
//		}
//
//		while(!queue.isEmpty()){
//			int size=queue.size();//1   2
//			List<String> list=new ArrayList<>();
//
//			while (size>0){
//				TreeNode cur=queue.poll();
//				System.out.print(cur.data+" ");
//				list.add((String) cur.data);
//				size--;//0   1
//
//				if(cur.leftChild != null){
//					queue.offer(cur.leftChild);
//				}
//
//				if(cur.rightChild != null){
//					queue.offer(cur.rightChild);
//				}
//			}
//			ret.add(list);
//		}
//		return ret;
//
//
//	}
//	public void getCode(TreeNode node) {
//		Stack<String> stack = new Stack<>();
//		TreeNode tNode = node;
//		while (tNode != null && tNode.parent != null) {
//			// left 0, right 1
//			if (tNode.parent.leftChild == tNode) {
//				stack.push("0");
//			} else if (tNode.parent.rightChild == tNode) {
//				stack.push("1");
//			}
//			tNode = tNode.parent;
//		}
//		while (!stack.isEmpty()) {
//			System.out.print(stack.pop());
//		}
//	}
//
//	public static void main(String[] args) {
//		ArrayList<TreeNode> list = new ArrayList<>();
//		TreeNode<String> node = new TreeNode("good", 54);
//		list.add(node);
//		list.add(new TreeNode("morning", 10));
//		list.add(new TreeNode("afternoon", 20));
//		list.add(new TreeNode("hello", 100));
//		list.add(new TreeNode("hi", 200));
//		HaffmanTree tree = new HaffmanTree();
//		tree.createHaffManTree(list);
//		tree.showHaffman(tree.root);
//		tree.getCode(node);
//		List<List<String>> lists = levelOrder(tree.root);
//		System.out.println("*****************************");
//		System.out.println(lists.toString());
//	}
//	public boolean isSymmetric(TreeNode root) {
//		boolean isSymmetric=true;
//		if(root==null){
//			retrun isSymmetric;
//		}
//		retrun isSymmetric(root.left)&&isSymmetric(root.right);
//	}
//
//}
