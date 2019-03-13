package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class N_aryTreePreorderTraversal {
	/*
	 * Q: Given an n-ary tree, return the preorder traversal of its nodes' values.
	 * */
	
	/*
	 * Algorithm: recursion
	 * 
	 * Complexity Analysis:
	 * T: O(n) n is the total number of nodes in the n-ary tree.
	 * S: O(height) 
	 * */
	static class Node {
	    public int val;
	    public List<Node> children;

	    public Node() {}

	    public Node(int _val,List<Node> _children) {
	        val = _val;
	        children = _children;
	    }
	}
	
	public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
    
    private void dfs(Node root, List<Integer> res) {
        // base case
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (Node cur : root.children) {
            dfs(cur, res);
        }
    }
    
    /*
     * Algorithm 2: Iteration 
     *  care about the order that u add nodes into the stack.
     * */
    public List<Integer> preorderII(Node root) {
    	List<Integer> res = new ArrayList<>();
    	if (root == null) {
    		return res;
    	}
    	Deque<Node> stack = new LinkedList<>();
    	stack.offerFirst(root);
    	while (!stack.isEmpty()) {
    		Node cur = stack.pollFirst();
    		res.add(cur.val);
    		for (int i = cur.children.size() - 1; i >= 0; --i) {
    			stack.offerFirst(cur.children.get(i));
    		}
    	}
    	return res;
    }
}
