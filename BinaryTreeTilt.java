package tree;

public class BinaryTreeTilt {
	/*
	 * Q: Given a binary tree, return the tilt of the whole tree.
	 * The tilt of a tree node is defined as the absolute difference between the sum of all left 
	 * subtree node values and the sum of all right subtree node values. Null node has tilt 0.
	 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
	 * 
	 * 1. The sum of node values in any subtree won't exceed the range of 32-bit integer.
	 * 2. All the tilt values won't exceed the range of 32-bit integer.
	 * */
	
	/*
	 * Algorithm: 
	 *  We can set a global variable called tileSum. In the helper function, it return the total sum
	 *  of the subtree rooted with the current node. In the process, I calculate the tiltSum.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(h)
	 * */
	public int findTilt(TreeNode root) {
        int[] sum = new int[]{0};
        dfs(root, sum);
        return sum[0];
    }
    
    private int dfs(TreeNode root, int[] sum) {
        // base case
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, sum);
        int right = dfs(root.right, sum);
        sum[0] += Math.abs(left - right);
        return left + right + root.val;
    }
}
