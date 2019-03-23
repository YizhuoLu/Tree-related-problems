package tree;

public class SmallestStringStartingFromLeaf {
	/*
	 * Q: Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 
	 * 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.
	 * Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
	 * 
	 * 1. The number of nodes in the given tree will be between 1 and 8500.
	 * 2. Each node in the tree will have a value between 0 and 25.
	 * */
	
	/*
	 * Algorithm: DFS to find string path starting from leaf until root.
	 *  Each time, at current time, I make suffix = (char) cur.val + suffix; Then I must judge if current node's
	 *  left and right child is null or not. Because if a cur.left =  null, cur.right != null, we must directly
	 *  return dfs(cur.right, suffix) since it requires the string starting from leaf. Otherwise, we judge
	 *  the lexicographical order of left and right string by using compareTo() method.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(h)
	 * */
	public String smallestFromLeaf(TreeNode root) {
        String suffix = "";
        return dfs(root, suffix);
    }
    
    private String dfs(TreeNode root, String suffix)  {
        // base case
        if (root == null) {
            return suffix;
        }
        suffix = "" + (char) ('a' + root.val) + suffix;
        if (root.left == null && root.right == null) {
            return suffix;
        } 
        if (root.left == null || root.right == null) {
            return root.left == null ? dfs(root.right, suffix) : dfs(root.left, suffix);
        }
        String left = dfs(root.left, suffix);
        String right = dfs(root.right, suffix);
        return left.compareTo(right) <= 0 ? left : right;
    }
}
