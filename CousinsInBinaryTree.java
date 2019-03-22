package tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CousinsInBinaryTree {
	/*
	 * Q: In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
	 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
	 * We are given the root of a binary tree with unique values, and the values x and y of two different 
	 * nodes in the tree.
	 * Return true if and only if the nodes corresponding to the values x and y are cousins.
	 * 
	 * 1. The number of nodes in the tree will be between 2 and 100.
	 * 2. Each node has a unique integer value from 1 to 100.
	 * */
	
	/*
	 * Algorithm 1: Use map to mark parent and depth.
	 *  Use dfs to traverse the tree. And in the process, I use two maps to record the mapping of
	 *  value - depth, value - parent node respectively. As long as x and y have different parents
	 *  and their depth are the same, return true.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(2n)
	 * */
	Map<Integer, Integer> depth = new HashMap<>();
    Map<Integer, TreeNode> parents = new HashMap<>();
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, 0);
        int depthx = depth.get(x), depthy= depth.get(y);
        TreeNode parentX = parents.get(x), parentY = parents.get(y);
        if (depthx == depthy && parentX != parentY) {
            return true;
        }
        return false;
    }
    
    private void dfs(TreeNode root, int dep) {
        //base case
        if (root == null) {
            return;
        }
        depth.put(root.val,dep);
        if (root.left != null) {
            parents.put(root.left.val, root);
        }
        dfs(root.left, dep + 1);
        if (root.right != null) {
            parents.put(root.right.val,root);
        }
        dfs(root.right, dep + 1);
    }
    
    /*
     * Algorithm 2: BFS
     *  Use BFS we can ensure the depths are the same. We only need to store two nodes to record the pareent
     *  of X and Y, after we have visited a layer, we can see if these two parent nodes are the same or not
     *  to return the answer.
     *  
     * Complexity Analysis:
     * T: O(n)
     * S: O(n)
     * */
    public boolean isCousinsIII(TreeNode root, int x, int y) {
        // BFS
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode parentX = null;
            TreeNode parentY = null;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    if (cur.left.val == x) {
                        parentX = cur;
                    } else if (cur.left.val == y) {
                        parentY = cur;
                    }
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    if (cur.right.val == x) {
                        parentX = cur; 
                    } else if (cur.right.val == y) {
                        parentY = cur;
                    }
                    queue.offer(cur.right);
                }
            }
            if (parentX != null && parentY != null && parentX != parentY) {
                return true;
            }
        }
        return false;
    }
}
