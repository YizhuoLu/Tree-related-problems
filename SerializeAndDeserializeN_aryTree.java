package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeAndDeserializeN_aryTree {
	/*
	 * Q: Serialization is the process of converting a data structure or object into a sequence of bits so 
	 * that it can be stored in a file or memory buffer, or transmitted across a network connection link to 
	 * be reconstructed later in the same or another computer environment.
	 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in 
	 * which each node has no more than N children. There is no restriction on how your serialization/
	 * deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized 
	 * to a string and this string can be deserialized to the original tree structure.
	 * 
	 * 1. N is in the range of [1, 1000]
	 * 2. Do not use class member/global/static variables to store states. Your serialize and 
	 * deserialize algorithms should be stateless.
	 * */
	
	/*
	 * Algorithm: Use pre-order traversal and I encode the serialized format as node, # of children, child,...
	 *  And queue. Add the number of children after the root node is for the sake of knowing when to terminate.
	 * Complexity Analysis:
	 * T: O(N)
	 * S: O(N)
	 * */
	 // Encodes a tree to a single string.
    public String serialize(Node root) {
        List<String> ans = new ArrayList<>();
        seriHelper(root, ans);
        return String.join(",", ans);
    }
    
    private void seriHelper(Node root, List<String> ans) {
        if (root == null) {
            return;
        }
        ans.add(String.valueOf(root.val));
        ans.add(String.valueOf(root.children.size()));
        for (Node child : root.children) {
            seriHelper(child, ans);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] str = data.split(",");
        Queue<Integer> queue = new LinkedList<>();
        for (String s : str) {
            queue.offer(Integer.valueOf(s));
        }
        return deseriHelper(queue);
    }
    
    private Node deseriHelper(Queue<Integer> queue) {
        Node root = new Node();
        root.val = queue.poll();
        int size = queue.poll();
        root.children = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            root.children.add(deseriHelper(queue));
        }
        return root;
    }
}
