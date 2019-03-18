package tree;

public class TrimABinarySearchTree {
	/*
	 * Q: Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so 
	 * that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so 
	 * the result should return the new root of the trimmed binary search tree.
	 * */
	
	/*
	 * Algorithm 1: recursion
	 *  This method always return the resultant root node of the current node.
	 *  cases: if root.val is in the range, we connect it with the left return node and right
	 *  return node.
	 *     if root.val < L, we just return the result in the right subtree.
	 *     Else: return the result in the left subtree.
	 *     
	 * Complexity Analysis:
	 * T: O(n) visit all the nodes.
	 * S: O(h) height of call stack, in worst case, it's n.
	 * */
	public TreeNode trimBST(TreeNode root, int L, int R) {
        // corner case
        if (root == null) {
            return root;
        }
        if (root.val >= L && root.val <= R) {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        } else if (root.val < L) {
            return trimBST(root.right, L, R);
        } else {
            // search the left subtree
            return trimBST(root.left, L, R);
        }
    }
	
	/*
	 * Algorithm 2: iteration
	 *  first while loop to find the resultant root node.
	 *  second while loop to remove invalid nodes in the left sub-tree.
	 *  third while loop to remove invalid nodes in the right sub-tree.
	 *  
	 * Complexity:
	 * T: O(n)
	 * S: O(1)
	 * */
	public TreeNode trimBSTII(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        // find the root
        while (root != null && root.val < L || root.val > R) {
            if (root.val < L) {
                root = root.right;
            } else if (root.val > R) {
                root = root.left;
            }
        }
        TreeNode node = root;
        // remove invalid nodes in the left subtree
        while (node != null) {
            while (node.left != null && node.left.val < L) {
                node.left = node.left.right;
            }
            node = node.left;
        }
        node = root;
        while (node != null) {
            while (node.right != null && node.right.val > R) {
                node.right = node.right.left;
            }
            node = node.right;
        }
        return root;
    }
}
