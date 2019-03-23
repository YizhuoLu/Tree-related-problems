package tree;

public class CountUnivalueSubtrees {
	/*
	 * Q: Given a binary tree, count the number of uni-value subtrees.
	 * A Uni-value subtree means all nodes of the subtree have the same value.
	 * */
	
	/*
	 * Algorithm: DFS
	 *  Each time we check in the current node if the subtree rooted with it is a univalue tree. If so
	 *  we return true, else we return false;
	 *  
	 * Complexity Analysis:
	 * T: O(n) 
	 * S: O(h)
	 * */
	
	private int sum = 0;
    public int countUnivalSubtrees(TreeNode root) {
        // corner case
        if (root == null) {
            return 0;
        }
        dfs(root);
        return sum;
    }
    
    private boolean dfs(TreeNode root) {
        // base case
        if (root == null) {
            return true;
        }
        boolean left = dfs(root.left);
        boolean right = dfs(root.right);
        if (left && right) {
            if (root.left != null && root.left.val != root.val) {
                return false;
            }
            if (root.right != null && root.right.val != root.val) {
                return false;
            }
            sum++;
            return true;
        }
        return false;
        
    }
	
	public static void main(String[] args) {
		boolean flag = true;
		boolean left = false;
		boolean right = false;
		flag = (flag && left && right);
		System.out.println(flag);
	}
}
