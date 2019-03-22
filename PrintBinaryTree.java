package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintBinaryTree {
	/*
	 * Q: Print a binary tree in an m*n 2D string array following these rules:
	 *  The row number m should be equal to the height of the given binary tree.
	 *  The column number n should always be an odd number.
	 *  The root node's value (in string format) should be put in the exactly middle of the first row 
	 * it can be put. The column and the row where the root node belongs will separate the rest space into 
	 * two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom 
	 * part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part 
	 * should have the same size. Even if one subtree is none while the other is not, you don't need to print 
	 * anything for the none subtree but still need to leave the space as large as that for the other subtree. 
	 * However, if two subtrees are none, then you don't need to leave space for both of them.
	 *  Each unused space should contain an empty string "".
	 *  Print the subtrees following the same rules.
	 *  
	 * The height of binary tree is in the range of [1, 10].
	 * */
	
	/*
	 * Algorithm: recursion.
	 *  First we need to find the height of the tree and initiate a string array with size = h * (2^h - 1). Then
	 *  I fill it with "". Afterwards, I traverse the tree Using dfs call dfs(root, arr, i, l, r) in which
	 *  root is the current node, i is the index of row, l and r are left and right boundaries respectively.
	 *  we need to put current node's value at arr[i][ (l+r)/2 ] Then we do recursive call: 
	 *  dfs(root.left, arr, i+1, l, (l+r)/2). dfs(root.right, arr, i+1, (l+r+1)/2, r). Attention is that in the second
	 *  recursive call, the left boundary should be (l+r+1)/2 instead of (l+r)/2, otherwise we cannot find the 
	 *  correct middle point then.
	 *  
	 * Complexity Analysis:
	 * T: O(h*2^h)
	 * S: O(h*2^h)
	 * */
	public List<List<String>> printTree(TreeNode root) {
        List<List<String>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int h = getHeight(root);
        String[][] arr = new String[h][(1<<h) -  1];
        for (String[] row : arr) {
            Arrays.fill(row, "");
        }
        fill(root, arr, 0, 0, arr[0].length);
        for (String[] row : arr) {
            ans.add(Arrays.asList(row));
        }
        return ans;
    }
    
    private void fill(TreeNode root, String[][] arr, int i, int l, int r) {
        // base case
        if (root == null) {
            return;
        }
        arr[i][(l+r)/2] = "" + root.val;
        fill(root.left, arr, i + 1, l, (l+r)/2);
        fill(root.right, arr, i+1, (l+r+1)/2, r);
    }
    
    private int getHeight(TreeNode root) {
        if (root == null){
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
