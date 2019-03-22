package tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoSumIVWithBST {
	/*
	 * Q: Given a Binary Search Tree and a target number, return true if there exist two elements in the BST 
	 * such that their sum is equal to the given target.
	 * */
	
	/*
	 * Algorithm 1: DFS + hashMap
	 *  I use DFS to traverse all the nodes on the tree. And I use a hashMap to record the value that I have
	 *  already visited. In addition, I use a flag(boolean value) to mark if I found such two nodes. Each time
	 *  when I am looking at a node, I check if I can find (k - current.val) in the hashMap. If so, I set flag
	 *  to be true. Else, I keep looking. Eventually, I return flag.
	 *  
	 * Complexity Analysis:
	 * T: O(n) n is the number of nodes.
	 * S: O(h + n) since I use DFS, h is the height of the call stack. n is what the hashMap has costed.
	 * */
	public boolean findTarget(TreeNode root, int k) {
        // Use a HashTable
        // corner case
        if (root == null) {
            return false;
        }
        Map<Integer, TreeNode> map = new HashMap<>();
        boolean[] flag = new boolean[]{false};
        dfs(root, map, k, flag);
        return flag[0];
    }
    
    private void dfs(TreeNode root, Map<Integer, TreeNode> map, int k, 
                       boolean[] flag) {
        // base case
        if (root == null) {
            return;
        }
        if (map.containsKey(k - root.val)) flag[0] = true;
        map.put(root.val, root);
        dfs(root.left, map, k, flag);
        dfs(root.right, map, k, flag);
    }
    
    /*
     * Algorithm 2: No use of flag, just let the helper DFS function to return the final answer.
     *  use set.
     *  
     * Complexity is the same.
     * */
    
    public boolean findTargetII(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }
    
    private boolean dfs(TreeNode root, Set<Integer> set, int k) {
        // base case
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }
}
