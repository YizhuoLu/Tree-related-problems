package tree;

public class InorderSuccessorInBSTII {
	/*
	 * Q: Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
	 * The successor of a node p is the node with the smallest key greater than p.val.
	 * You will have direct access to the node but not to the root of the tree. Each node will have a 
	 * reference to its parent node.
	 * 
	 * 1. If the given node has no in-order successor in the tree, return null.
	 * 2. It's guaranteed that the values of the tree are unique.
	 * 3. Remember that we are using the Node type instead of TreeNode type so their string 
	 * representation are different.
	 * */
	
	/*
	 * Algorithm:  Iteration
	 *  if given node's right is null, the result can only be the first node in its parent nodes which is
	 *  larger than the given node's value when we see the first time. Else, the result can only be in the
	 *  right sub-tree since its right sub-tree nodes are closer to it than anyone of its parents. So we first
	 *  put the tmp node at the the given node's right node. Then we keep going left since all the nodes on
	 *  the right sub-tree all are larger than the given node's val, so as long as we keep going left, we
	 *  can find the smallest larger one.
	 *  
	 * Complexity Analysis:
	 * T: O(x) x is the number of nodes on the target part.
	 * S: O(1)
	 * */
	static class Node {
		public int val;
	    public Node left;
	    public Node right;
	    public Node parent;
	}
	
	public static Node inorderSuccessor(Node x) {
		if (x.right == null) {
			Node res = x.parent;
			while (res != null && res.val < x.val) {
				res = res.parent;
			}
			return res;
		} else {
			// find in the right sub-tree
			Node res = x.right;
			while (res.left != null) {
				res = res.left;
			}
			return res;
		}
	}
}
