package tree;

import java.util.ArrayList;

public class EncodeN_aryTreeToBinaryTree {
	/*
	 * Q: Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree 
	 * to get the original N-ary tree. An N-ary tree is a rooted tree in which each node has no more 
	 * than N children. Similarly, a binary tree is a rooted tree in which each node has no more than 
	 * 2 children. There is no restriction on how your encode/decode algorithm should work. You just 
	 * need to ensure that an N-ary tree can be encoded to a binary tree and this binary tree can be 
	 * decoded to the original N-nary tree structure.
	 * 
	 * 1. N is in the range of [1, 1000]
	 * 2. Do not use class member/global/static variables to store states. Your encode and 
	 * decode algorithms should be stateless.
	 * */
	
	/*
	 * Algorithm: recursion
	 *  Conversion principle: next level nodes all go to left, same level nodes all go to right.
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n) in the worst case
	 * */
	
	// Encodes an n-ary tree to a binary tree.
	public TreeNode encode(Node root) {
		// idea is next level -> left, same level -> right
		if (root == null) {
			return null;
		}
		TreeNode res = new TreeNode(root.val);
		if (root.children.size() != 0) {
			res.left = encode(root.children.get(0));
		}
		TreeNode cur = res.left;
		for (int i = 1; i < root.children.size(); ++i) {
			cur.right = encode(root.children.get(i));
			cur = cur.right;
		}
		return res;
	}

	// Decodes your binary tree to an n-ary tree.
	public Node decode(TreeNode root) {
		if (root == null) {
			return null;
		}
		Node res = new Node(root.val, new ArrayList<>());
		TreeNode cur = root.left;
		while (cur != null) {
			res.children.add(decode(cur));
			cur = cur.right;
		}
		return res;
	}
}
