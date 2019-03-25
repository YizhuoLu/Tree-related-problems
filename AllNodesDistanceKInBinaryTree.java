package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AllNodesDistanceKInBinaryTree {
	/*
	 * Q: We are given a binary tree (with root node root), a target node, and an integer value K.
	 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can 
	 * be returned in any order.
	 * 
	 * 1. The given tree is non-empty.
	 * 2. Each node in the tree has unique values 0 <= node.val <= 500.
	 * 3. The target node is a node in the tree.
	 * 4. 0 <= K <= 1000.
	 * */
	
	/*
	 * Algorithm 1:
	 *   If we know the parent of each node, we can know all the nodes that is 1 away from the node (its left, 
	 *   right, parent). So we can use BFS to continuously visit its neighbor to its up and down side. In order
	 *   to know what distance we have processed from the target node, we use a null pointer to separate nodes
	 *   that are different distance away from the target and use a final list to load all nodes in the queue once
	 *   the distance is the required one.
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n)
	 * */
	Map<TreeNode, TreeNode> map;

	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		map = new HashMap<>();
		dfs(root, null);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(null);
		queue.offer(target);
		Set<TreeNode> seen = new HashSet<>();
		seen.add(null);
		seen.add(target);
		int dep = 0;
		List<Integer> res = new ArrayList<>();
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			if (cur == null) {
				if (dep == K) {
					for (TreeNode node : queue) {
						res.add(node.val);
					}
					break;
				}
				dep++;
				queue.offer(null);
			} else {
				// add neighbors
				if (!seen.contains(cur.left)) {
					seen.add(cur.left);
					queue.offer(cur.left);
				}
				if (!seen.contains(cur.right)) {
					seen.add(cur.right);
					queue.offer(cur.right);
				}
				TreeNode parent = map.get(cur);
				if (!seen.contains(parent)) {
					seen.add(parent);
					queue.offer(parent);
				}
			}
		}
		return res;
	}

	private void dfs(TreeNode cur, TreeNode par) {
		if (cur == null) {
			return;
		}
		map.put(cur, par);
		dfs(cur.left, cur);
		dfs(cur.right, cur);
	}
	
}
