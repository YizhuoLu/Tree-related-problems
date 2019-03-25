package tree;

public class SerializeAndDeserializeBST {
	/*
	 * Q: Serialization is the process of converting a data structure or object into a sequence of bits 
	 * so that it can be stored in a file or memory buffer, or transmitted across a network connection link 
	 * to be reconstructed later in the same or another computer environment.
	 *  Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on 
	 *  how your serialization/deserialization algorithm should work. You just need to ensure that a binary 
	 *  search tree can be serialized to a string and this string can be deserialized to the original tree 
	 *  structure.
	 *  
	 *  The encoded string should be as compact as possible.
	 * */
	
	/*
	 * Algorithm: I use preOrdr traversal to visit each node in the original tree. And in the process, I use
	 * " " to separate different node's value. In de-serialization part, I make root node first and then I use
	 * a helper function called insert() to first find the position of the current node (since its' a BST and
	 * the original tree is serialized using preOrder so I will deserialize firstly root node and then left
	 * sub-tree and then right sub-tree. Therefore, I can find the correct insertion position by comparing
	 * the current node's value with the given value so that I can insert the new node at the parent's left
	 * or right. Eventually return the root. (Here the reason for using preOrder is because the feature of
	 * BST).
	 * 
	 * Complexity Analysis:
	 * T: O(n*h) h is the height of the given tree.
	 * S: O(n + h) use of string and call stack.
	 * */
	
	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(TreeNode root, StringBuilder sb) {
        // base case
        if (root == null) {
            return;
        }
        sb.append(root.val + " ");
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] arr = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        for (int i = 1; i < arr.length; ++i){
            insert(root, Integer.parseInt(arr[i]));
        }
        return root;
    }
    
    private void insert(TreeNode root, int val) {
        TreeNode cur = root, parent=null;
        while (cur != null) {
            parent = cur;
            cur = cur.val < val ? cur.right : cur.left;
        }
        if (parent.val < val) {
            parent.right = new TreeNode(val);
        } else {
            parent.left = new TreeNode(val);
        }
    }
	
	public static void main(String[] args) {
		String s = "ee ii o ";
		String[] arr = s.split(" ");
		for (int i = 0; i < arr.length; ++i) {
			System.out.println(arr[i]);
		}
	}
}
