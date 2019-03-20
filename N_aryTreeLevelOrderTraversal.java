package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class N_aryTreeLevelOrderTraversal {
	/*
	 * Q: Given an n-ary tree, return the level order traversal of its nodes' values. 
	 * (ie, from left to right, level by level).
	 * 
	 * 1. The depth of the tree is at most 1000.
	 * 2. The total number of nodes is at most 5000.
	 * */
	
	/*
	 * Algorithm 1: BFS + queue
	 *  record the size of queue each time.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n)
	 * */
	public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                Node cur = queue.poll();
                if (cur.children.size() > 0) {
                    for (Node node : cur.children) {
                        queue.offer(node);
                    }
                }
                list.add(cur.val);
            }
            res.add(list);
        }
        return res;
    }
	
	/*
	 * Algorithm 2: DFS
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n)
	 * */
	public List<List<Integer>> levelOrderII(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }
    
    private void dfs(Node root, List<List<Integer>> res, int index) {
        // base case
        if (root == null) {
            return;
        }
        if (res.size() == index) {
            res.add(new ArrayList<>());
        }
        List<Integer> tmp = res.get(index);
        tmp.add(root.val);
        for (Node node : root.children) {
            dfs(node, res, index + 1);
        }
    }
}
