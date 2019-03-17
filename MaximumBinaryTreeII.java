package tree;

public class MaximumBinaryTreeII {
	/*
	 * Q: We are given the root node of a maximum tree: a tree where every node has a value greater than 
	 * any other value in its subtree.
	 * Just as in the previous problem, the given tree was constructed from an list A (root = Construct(A)) 
	 * recursively with the following Construct(A) routine:
	 * If A is empty, return null. Otherwise, let A[i] be the largest element of A.  Create a root node 
	 * with value A[i]. The left child of root will be Construct([A[0], A[1], ..., A[i-1]]). The right child 
	 * of root will be Construct([A[i+1], A[i+2], ..., A[A.length - 1]]): Return root.
	 * Note that we were not given A directly, only a root node root = Construct(A).
	 * Suppose B is a copy of A with the value val appended to it.  It is guaranteed that B has unique values.
	 * Return Construct(B).
	 * 
	 * 1 <= B.length <= 100
	 * */
	
	/*
	 * Algorithm: iteration
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(1)
	 * */
	public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        // iteration
        TreeNode node = new TreeNode(val);
        if (root == null || root.val < val) {
            node.left = root;
            return node;
        }
        TreeNode cur = root;
        while (cur.right != null && cur.right.val > val) {
            cur = cur.right;
        }
        if (cur.right == null) {
            cur.right = node;
        } else {
            node.left = cur.right;
            cur.right = node;
        }
        return root;
    }
	
	/*
	 * Algorithm 2: recursion.
	 *  If root.val > val, we recursion on the right. Else put the root to be the left of node.
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(h)
	 * */
	public TreeNode insertIntoMaxTreeII(TreeNode root, int val) {
        if (root != null && root.val > val) {
        	root.right = insertIntoMaxTreeII(root.right, val);
        	return root;
        }
        TreeNode node = new TreeNode(val);
        node.left = root;
        return node;
    }
}
