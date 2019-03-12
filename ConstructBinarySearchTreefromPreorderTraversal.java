package tree;

import java.util.ArrayList;
import java.util.List;

public class ConstructBinarySearchTreefromPreorderTraversal {
	/*
	 * Q: Return the root node of a binary search tree that matches the given preorder traversal.
	 * 
	 * 1. 1 <= preorder.length <= 100
	 * 2. The values of preorder are distinct.
	 * */
	
	/*
	 * Algorithm: recursion
	 *  since it's pre-order traversal, the first value is always the value for current root. So each time
	 *  I firstly get the root. Then I use two list called left and right to store the node of its left subtree
	 *  and right subtree. Then use the same rule to connect the left and right to the current root node until
	 *  I have used up all the nodes in the given array.
	 *  
	 * Complexity Analysis:
	 * T: O(n) since I need to traverse all the nodes in the given array.
	 * S: O(n) the height of the call stack.
	 * */
	public TreeNode bstFromPreorder(int[] preorder) {
		TreeNode root = new TreeNode(preorder[0]);
        // corner case
        if (preorder.length == 1) {
            return root;
        }
        List<Integer> helper = new ArrayList<>();
        int i = 0;
        while (i < preorder.length) {
           helper.add(preorder[i]);
            ++i;
        }
        return construct(helper);
    }
    
    private TreeNode construct(List<Integer> subtree) {
        // base case
        if (subtree.size() == 0) {
            return null;
        }
        TreeNode root = new TreeNode(subtree.get(0));
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int i = 1;
        while (i < subtree.size()) {
            int cur = subtree.get(i);
            if (cur < root.val) {
                left.add(cur);
            } else {
                right.add(cur);
            }
            ++i;
        }
        root.left = construct(left);
        root.right = construct(right);
        return root;
    }
    
    /*
     * A more concise edition:
     *  No need for list.
     * */
    public TreeNode bstFromPreorderII(int[] preorder) {
        TreeNode root = null;
        for (int i = 0; i < preorder.length; i++){
            root = bstInsert(root, preorder[i]);
        }
        return root;
    }
    
    public TreeNode bstInsert(TreeNode root, int val){
        if (root == null) return new TreeNode(val);
        
        if (val < root.val) {
            root.left = bstInsert(root.left, val);
        } else {
            root.right = bstInsert(root.right, val);
        }
        
        return root;
    }
    
}
