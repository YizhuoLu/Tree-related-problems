package tree;

public class FlipEquiverlentTree {
	/*
	 * Q: For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left 
	 * and right child subtrees.
	 * A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after 
	 * some number of flip operations.
	 * Write a function that determines whether two binary trees are flip equivalent.  The trees are given 
	 * by root nodes root1 and root2.
	 * 
	 * 1. Each tree will have at most 100 nodes.
	 * 2. Each value in each tree will be a unique integer in the range [0, 99].
	 * */
	
	/*
	 * Algorithm: Recursion
	 *  For the root, there are three cases.
	 * 1. root1 == root2 == null || root1.val == root2.val -> return true;
	 * 2. root1 == null || root2 == null || root1.val!= root2.val -> return false;
	 * 3. tell if their sub-trees are equal -> 2 different ways (root1.left, root2.left)&&(root1.right,root2.right)
	 * || (root1.left, root2.right) &&  (root1.right, root2.left) (with flip).
	 * 
	 * Complexity Analysis:
	 * T: O(min(N1, N2))
	 * S: O(min(H1,H2))
	 * 
	 * T is not O(n^2) because:
	 * We won't traverse all nodes in all four calls. We will return the call immediately once we found a 
	 * mismatch, and all nodes are unique, so it is ok to assume we will visit all nodes only once.
	 * */
	
	public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // base case
        if (root1 == root2) {
            // both = null or equal values
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) || flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
    }
}
