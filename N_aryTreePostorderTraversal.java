package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class N_aryTreePostorderTraversal {
	/*
	 * Q: Given an n-ary tree, return the postorder traversal of its nodes' values.
	 * 
	 * */
	
	/*
	 * Algorithm: iteration
	 *  Items in the stack: always put the current node into the stack first and then push into stack using 
	 *  the order from right to left.
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n)
	 * */
	public List<Integer> postorder(Node root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Deque<Node> stack = new LinkedList<>();
		stack.offerFirst(root);
		while (!stack.isEmpty()) {
			Node cur = stack.pollFirst();
			res.add(cur.val);
			for (Node node : cur.children) {
				stack.offerFirst(node);
			}
		}
		Collections.reverse(res);
		return res;
	}
	
	/*
	 * Algorithm 2: recursive
	 * */
	public List<Integer> postorderII(Node root) {
		List<Integer> res = new ArrayList<>();
        if (root != null)
            solve(root, res);
        return res;
    }
    
    public void solve(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }   
        for (Node node : root.children) {
            solve(node, res);
        }
        res.add(root.val);
    }
}
