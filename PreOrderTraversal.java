package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PreOrderTraversal {
	/*
	 * Q: Given a binary tree, return the preorder traversal of its nodes' values.
	 * */
	
	/*
	 * Algorithm 1: recursion
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(h)
	 * */
	public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root,  ans);
        return ans;
    }
    
    private void dfs(TreeNode root, List<Integer> ans) {
        // base case
        if (root == null){
            return;
        }
        ans.add(root.val);
        dfs(root.left, ans);
        dfs(root.right, ans);
    }
    
    /*
     * Algorithm 2: Iteration
     * 
     * Complexity Analysis:
     * T: O(n)
     * S: O(n)
     * */
    public List<Integer> preorderTraversalII(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            ans.add(cur.val);
            if (cur.right != null) {
                stack.offerFirst(cur.right);
            }
            if (cur.left != null) {
                stack.offerFirst(cur.left);
            }
        }
        return ans;
    }
}
