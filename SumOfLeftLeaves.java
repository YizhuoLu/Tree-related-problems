package tree;

import java.util.Deque;
import java.util.LinkedList;

public class SumOfLeftLeaves {
	/*
	 * Q: Find the sum of all left leaves in a given binary tree.
	 * */
	
	/*
	 * Algorithm: I use a global pointer to record the previous node of current node all the time.
	 * 	Once I find prev != null && cur = prev.left && cur.left == null && cur.right == null, I can
	 * 	add cur.val to the final answer and return eventually.
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(h) since I use DFS to traverse the given tree.
	 * */
	public int sumOfLeftLeaves(TreeNode root) {
        TreeNode[] prev = new TreeNode[]{null};
        int[] sum = new int[] {0};
        // corner case
        if (root== null) {
            return sum[0];
        }
        dfs(root, prev, sum);
        return sum[0];
    }
    
    private void dfs(TreeNode root, TreeNode[] prev, int[] sum) {
        // base case
        if (root == null) {
            return;
        }
        if (prev[0] != null && root == prev[0].left && root.left == null &&
            root.right == null) {
            sum[0] += root.val;
        }
        prev[0] = root;
        dfs(root.left, prev, sum);
        dfs(root.right, prev, sum);
    }
	
    /*
     * Algorithm 2: iterative
     * */
    public int sumOfLeftLeavesII(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        
        while(!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            if(node.left != null) {
                if (node.left.left == null && node.left.right == null)
                    ans += node.left.val;
                else
                    stack.offerFirst(node.left);
            }
            if(node.right != null) {
                if (node.right.left != null || node.right.right != null)
                    stack.offerFirst(node.right);
            }
        }
        return ans;
    }
}
