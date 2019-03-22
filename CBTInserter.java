package tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class CBTInserter {
	/*
	 * Q: A complete binary tree is a binary tree in which every level, except possibly the last, is completely 
	 * filled, and all nodes are as far left as possible.
	 * Write a data structure CBTInserter that is initialized with a complete binary tree and supports 
	 * the following operations:
	 * 1. CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
	 * 2. CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the 
	 * tree remains complete, and returns the value of the parent of the inserted TreeNode;
	 * 3. CBTInserter.get_root() will return the head node of the tree.
	 * 
	 * 1. The initial given tree is complete and contains between 1 and 1000 nodes.
	 * 2. CBTInserter.insert is called at most 10000 times per test case.
	 * 3. Every value of a given or inserted node is between 0 and 5000.
	 * */
	
	/*
	 * Algorithm 1: BFS
	 *  1. maintain a global variable to record the root node.
	 *  2. In insert method, I use BFS to find the fist node that has 0 or 1 child. Then connect the given node
	 *   to it and return  its value.
	 * 
	 * Complexity Analysis: 
	 * T: O(n * m) n is the number of nodes in the tree, m is the number of times we call insert method.
	 * S: O(n)
	 * */
	TreeNode globalRoot  = null;
    public CBTInserter(TreeNode root) {
        globalRoot = root;
    }
    
    public int insert(int v) {
        // BFS
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(globalRoot);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = queue.poll();
                if (cur.left == null) {
                    cur.left = new TreeNode(v);
                    return cur.val;
                } else if (cur.right == null) {
                    cur.right = new TreeNode(v);
                    return cur.val;
                } else {
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
        }
        return -1;
    }
    
    public TreeNode get_root() {
        return globalRoot;
    }
    
    /*
     * Algorithm 2: BFS + maintain a global deque
     *  Use a global deque to record the node starting from the first node that has 0 or 1 child. Each time
     *  the first node is the parent, and add the new node to the end of the deque.
     *  
     * T: O(n)
     * S: O(n)
     * */
    TreeNode root;
    Deque<TreeNode> deque;
//    public CBTInserteII(TreeNode root) {
//        this.root = root;
//        deque = new LinkedList();
//        Queue<TreeNode> queue = new LinkedList();
//        queue.offer(root);
//
//        // BFS to populate deque
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            if (node.left == null || node.right == null)
//                deque.offerLast(node);
//            if (node.left != null)
//                queue.offer(node.left);
//            if (node.right != null)
//                queue.offer(node.right);
//        }
//    }

    public int insertII(int v) {
        TreeNode node = deque.peekFirst();
        deque.offerLast(new TreeNode(v));
        if (node.left == null)
            node.left = deque.peekLast();
        else {
            node.right = deque.peekLast();
            deque.pollFirst();
        }

        return node.val;
    }

    public TreeNode get_rootII() {
        return root;
    }
}
