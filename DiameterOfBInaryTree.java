package tree;

public class DiameterOfBInaryTree {
	/*
	 * Q: Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of 
	 * a binary tree is the length of the longest path between any two nodes in a tree. This path may or 
	 * may not pass through the root.
	 * 
	 * The length of path between two nodes is represented by the number of edges between them.
	 * */
	
	/*
	 * Algorithm: 
	 *  Any path can be written as two arrows (in different directions) from some node, where an 
	 *  arrow is a path that starts at some node and only travels down to child nodes.
	 *  If we knew the maximum length arrows L, R for each child, then the best path touches L + R + 1 nodes.
	 *  Attention: we need to do max- 1 when we are going to return the answer, Since we use the height
	 *  in the helper function which is not actually the edge length, so we need minus one.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(h) h is the height of the tree.
	 * */
	private int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return max;
        }
        getHeight(root);
        return max - 1;
    }
    
    private int getHeight(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        max= Math.max(max, left + right + 1);
        return 1 + Math.max(left, right);
    }
}
