package tree;

import java.util.Arrays;

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
	/*
	 * Q: Return any binary tree that matches the given preorder and postorder traversals.
	 * Values in the traversals pre and post are distinct positive integers.
	 * 
	 * 1. 1 <= pre.length == post.length <= 30
	 * 2. pre[] and post[] are both permutations of 1, 2, ..., pre.length.
	 * 3. It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
	 * */
	
	/*
	 * Algorithm: recursion
	 *  Find the left part in both array and right part as well. Then we can connect them to left, right
	 * recursively.
	 *  we have pre[] having items order in pre-order, post[] ordered in post-order.
	 * pre[0] is root. pre[1] is the root of left sub-tree. So the length of left part is indexOf(post[pre[1]) + 1.
	 *  
	 * Complexity Analysis:
	 * T: O(n^2)
	 * S: O(n^2)
	 * */
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int N = pre.length;
        if (N == 0) return null;
        TreeNode res = new TreeNode(pre[0]);
        if (N == 1) return res;
        
        int leftSize = 0;
        for (int i = 0; i < N; ++i) {
            if (post[i] == pre[1]) {
                leftSize = i + 1;
                break;
            }
        }
        res.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, leftSize + 1), Arrays.copyOfRange(post, 0, leftSize));
        res.right = constructFromPrePost(Arrays.copyOfRange(pre, leftSize + 1, N), Arrays.copyOfRange(post, leftSize, N - 1));
        return res;
    }
}
