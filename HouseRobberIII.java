package tree;

import java.util.HashMap;
import java.util.Map;

public class HouseRobberIII {
	/*
	 * Q: The thief has found himself a new place for his thievery again. There is only one entrance 
	 * to this area, called the "root." Besides the root, each house has one and only one parent house. 
	 * After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will 
	 * automatically contact the police if two directly-linked houses were broken into on the same night.
	 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
	 * */
	
	/*
	 * Algorithm: DP+recursion
	 *  There are two cases that we either rob the root node or don't rob it. if we rob the root, we want
	 *  the result starting from root.left.left, root.left.right, root.right.left, root.right.right. If we
	 *  don't rob the root, we want the result from root.left, root.right. In order to record the result of
	 *  the subproblems. We can use a hashMap to map the node with the result.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n)
	 * */
	public int robII(TreeNode root) {
		return helperII(root, new HashMap<>());
	}

	private int helperII(TreeNode root, Map<TreeNode, Integer> map) {
		// base case
		if (root == null) {
			return 0;
		}
		if (map.containsKey(root)) {
			return map.get(root);
		}
		int res = 0;
		if (root.left != null) {
			res += helperII(root.left.left, map) + helperII(root.left.right, map);
		}
		if (root.right != null) {
			res += helperII(root.right.left, map) + helperII(root.right.right, map);
		}
		res = Math.max(root.val + res, helperII(root.left, map) + helperII(root.right, map));
		map.put(root, res);
		return res;
	}
	
	/*
	 * Algorithm 2: DP
	 *  For this problem, matter of fact, each time when we are at a tree node, we only need to consider if
	 *  we rob the current node dp[i] = cur.val + dp[i-1] when we don't rob (i-1) node or not dp[i] = 
	 *  max(dp[i-1]) in two cases that we rob (i-1) or not rob it. So this can be boiled down to two 
	 *  scenarios which are we either rob the current root node or we don't rob the current root node. Then we
	 *  return the max of the two. Therefore, we only need to store two situations int an array with size 2.
	 *  case[0] is we don't rob the current root, case[1] is we rob the current root.
	 *  
	 * Complexity Analysis:
	 * T: O(n)  n is the number of nodes in the tree.
	 * S: O(n)
	 * */
	
	public int rob(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int[] res = helper(root);
		return Math.max(res[0], res[1]);
	}

	private int[] helper(TreeNode root) {
		// base case
		if (root == null) {
			return new int[2];
		}
		int[] left = helper(root.left);
		int[] right = helper(root.right);
		int[] res = new int[2];
		// res[0] means we don't rob the root node
		res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		// res[1] means we rob the root node
		res[1] = left[0] + right[0] + root.val;
		return res;
	}
}
