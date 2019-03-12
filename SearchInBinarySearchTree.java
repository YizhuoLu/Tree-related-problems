package tree;

public class SearchInBinarySearchTree {
	/*
	 * Q: Given the root node of a binary search tree (BST) and a value. You need to find the node in the 
	 * BST that the node's value equals the given value. Return the subtree rooted with that node. If such 
	 * node doesn't exist, you should return NULL.
	 * 
	 * In the example above, if we want to search the value 5, since there is no node with value 5, we 
	 * should return NULL.
	 * Note that an empty tree is represented by NULL, therefore you would see the expected output 
	 * (serialized tree format) as [], not null.
	 * */
	
	/*
	 * Algorithm: 
	 *  if given root is null, just return null. if it equals to val, just return the current root. if
	 *  its val < val, we need to go right subtree. Otherwise, we need to go left subtree to find.
	 *  
	 * Complexity Analysis:
	 * T: O(n) in the worst case in which all nodes are on the same side.
	 * S: O(height)
	 * */
	public TreeNode searchBST(TreeNode root, int val) {
        // corner case
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val< val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }
}
