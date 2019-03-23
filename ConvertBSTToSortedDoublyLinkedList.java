package tree;

import java.util.ArrayList;
import java.util.List;

public class ConvertBSTToSortedDoublyLinkedList {
	/*
	 * Q: Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right 
	 * pointers as synonymous to the previous and next pointers in a doubly-linked list.
	 * We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list 
	 * has a predecessor and successor. For a circular doubly linked list, the predecessor of the first 
	 * element is the last element, and the successor of the last element is the first element.
	 * */
	
	/*
	 * Algorithm 1: InOrder traversal + list 
	 *  In a helper function, use inOrder traversal to load all node into a list recursively. Then connect
	 *  each node iteratively. Eventually, return the first node.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n) since I use a list to load all nodes.
	 * */
	static class Node {
	    public int val;
	    public Node left;
	    public Node right;

	    public Node() {}

	    public Node(int _val,Node _left,Node _right) {
	        val = _val;
	        left = _left;
	        right = _right;
	    }
	};
	
	public static Node treeToDoublyList(Node root) {
        // corner case
        if (root == null) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); ++i) {
            Node first = list.get(i-1);
            Node second =list.get(i);
            second.left = first;
            first.right = second;
        }
        Node first = list.get(list.size()- 1);
        Node second = list.get(0);
        first.right = second;
        second.left = first;
        // Node head = new Node();
        // head.right = second;
        return second;
    }
    
    private static void inOrder(Node root, List<Node> list) {
        // base case
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root);
        inOrder(root.right, list);
    }
    
    /*
     * Algorithm 2: No use of list, just use two pointers one is 'prev', another is 'dummy'.
     * 
     * Complexity Analysis:
     * T: O(n)
     * S: O(n)
     * */
    Node prev;
    public Node treeToDoublyListII(Node root) {
        // corner case
        if (root == null) {
            return null;
        }
        Node dummy = new Node(0, null, null);
        prev = dummy;
        helper(root);
        // connect the head and tail
        dummy.right.left = prev;
        prev.right = dummy.right;
        return dummy.right;
    }
    
    private void helper(Node root){
        // base case
        if (root == null) {
            return;
        }
        helper(root.left);
        prev.right = root;
        root.left = prev;
        prev = root;
        helper(root.right);
    }
}
