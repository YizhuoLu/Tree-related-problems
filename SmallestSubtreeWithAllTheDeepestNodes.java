package tree;

import java.util.HashMap;
import java.util.Map;

public class SmallestSubtreeWithAllTheDeepestNodes {
	/*
	 * Q: Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
	 * A node is deepest if it has the largest depth possible among any node in the entire tree.
	 * The subtree of a node is that node, plus the set of all descendants of that node.
	 * Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
	 * 
	 * 1. The number of nodes in the tree will be between 1 and 500.
	 * 2. The values of each node are unique.
	 * */
	
	/*
	 * Algorithm 1: DFS  (Two pass)
	 *  I use a map to mark the node and its corresponding depth. If I find any node with its
	 *  depth equal to the maxDepth, this is the answer. else if its left and right subtree
	 *  contains both deepest nodes, this node is the answer. Else, return left answer or right
	 *  answer that is not null.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(h) h is the height of the tree.
	 * */
	
	Map<TreeNode, Integer> map;
    int maxDepth = -1;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        map = new HashMap<>();
        map.put(null, -1);
        mark(root, null);
        return ans(root);
    }
    
    private void mark(TreeNode root, TreeNode parent) {
        // base case
        if (root == null) {
            return;
        }
        map.put(root, map.get(parent) + 1);
        maxDepth = Math.max(maxDepth, map.get(root));
        mark(root.left, root);
        mark(root.right, root);
    }
    
    private TreeNode ans(TreeNode root) {
        // base case
        if (root == null || map.get(root) == maxDepth) {
            return root;
        }
        TreeNode L = ans(root.left), R = ans(root.right);
        if (L != null && R != null) {
            return root;
        }
        return L != null ? L : R;
    }
}
