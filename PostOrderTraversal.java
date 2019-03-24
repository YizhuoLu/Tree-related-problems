package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PostOrderTraversal {
	/*
	 * Q: Given a binary tree, return the post-order traversal of its nodes' values.
	 * */
	
	/*
	 * Algorithm 1: recursion
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(h)
	 * */
	public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    
    private void helper(TreeNode root, List<Integer> res) {
        // base case
        if (root == null) {
            return;
        }
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
    }
	
	/*
	 * Algorithm 2: iteration
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n)
	 * */
    public List<Integer> postorderTraversalII(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            res.add(cur.val);
            if (cur.left != null)  {
                stack.offerFirst(cur.left);
            }
            if (cur.right != null) {
                stack.offerFirst(cur.right);
            }
        }
        Collections.reverse(res);
        return res;
    }
}
