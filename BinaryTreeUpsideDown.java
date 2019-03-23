package tree;

public class BinaryTreeUpsideDown {
	/*
	 * Q: Given a binary tree where all the right nodes are either leaf nodes with a sibling 
	 * (a left node that shares the same parent node) or empty, flip it upside down and turn it into 
	 * a tree where the original right nodes turned into left leaf nodes. Return the new root.
	 * */
	
	/*
	 * Algorithm: Recursion
	 *  The subproblem is func(root.left) which is res. So the rest thing I need to do is to make
	 *  root.left.left = root.right, root.left.right = root, root.left = null, root.right =null.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(h) h is the height of the tree.
	 * */
	public TreeNode upsideDownBinaryTree(TreeNode root) {
        // base case
        if (root == null || root.left == null){
            return root;
        }
        TreeNode res = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return res;
    }
}
