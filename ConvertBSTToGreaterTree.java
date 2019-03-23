package tree;

public class ConvertBSTToGreaterTree {
	/*
	 * Q: Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the 
	 * original BST is changed to the original key plus sum of all keys greater than the original key in BST.
	 * */
	
	/*
	 * Algorithm 1: traverse the tree in reverse inOrder and plus the previous node's value to the current
	 * 	node.val. Return root eventually.
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(h) since I use recursion to traverse the tree. so it will use h with call stack.
	 * */
	TreeNode prev;
    public TreeNode convertBST(TreeNode root) {
        TreeNode res = root;
        prev = new TreeNode(0);
        helper(root);
        return res;
    }
    
    private void helper(TreeNode root) {
        // base case
        if (root == null) {
            return;
        }
        helper(root.right);
        root.val += prev.val;
        prev = root;
        helper(root.left);
    }
    
    /*
     * Algorithm 2: no need to record the previous node, just use a global integer to record the previous sum
     * that I need to add.
     * */
    private int sum = 0;
    public TreeNode convertBSTII(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
